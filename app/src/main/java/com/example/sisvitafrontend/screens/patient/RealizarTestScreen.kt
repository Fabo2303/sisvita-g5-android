package com.example.sisvitafrontend.screens.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.api.requests.AnswerRequest
import com.example.sisvitafrontend.api.responses.AlternativeResponse
import com.example.sisvitafrontend.api.responses.QuestionResponse
import com.example.sisvitafrontend.components.global.Background
import com.example.sisvitafrontend.components.global.CustomDialog
import com.example.sisvitafrontend.navigation.Screen
import com.example.sisvitafrontend.viewmodels.ResolvedTestViewModel
import com.example.sisvitafrontend.viewmodels.TemplateTestViewModel

@Composable
fun RealizarTestScreen(
    navController: NavController, id: String,
    templateTestViewModel: TemplateTestViewModel = viewModel(
        modelClass = TemplateTestViewModel::class
    ),
    resolvedTestViewModel: ResolvedTestViewModel = viewModel(
        modelClass = ResolvedTestViewModel::class
    )
) {
    val templateTest by templateTestViewModel.templateTest.observeAsState()
    templateTestViewModel.getTemplateTestById(id.toLong())

    val questions = templateTest?.questions
    val options = templateTest?.alternatives

    val selectedOptions = remember { mutableStateMapOf<Int, AlternativeResponse>() }

    val title by resolvedTestViewModel.title.observeAsState("")
    val message by resolvedTestViewModel.message.observeAsState("")
    val color by resolvedTestViewModel.color.observeAsState(0)

    val context = LocalContext.current
    val dataStoreManager = remember {
        DataStoreManager(context)
    }

    Background()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        templateTest?.let { Header(it.name) }
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                templateTest?.let {
                    if (questions != null && options != null) {
                        itemsIndexed(questions) { index, question ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .background(
                                        colorResource(id = R.color.white_900),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                MultipleSingleSelectionOptions(
                                    title = "${index + 1}. ${question.statement}",
                                    options = options,
                                    selectedOption = selectedOptions[question.id.toInt()],
                                    onOptionSelected = { selectedOption ->
                                        selectedOptions[question.id.toInt()] = selectedOption
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${selectedOptions.size} / ${questions?.size}",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.white_900)
            )

            CustomDialog(
                buttonText = R.string.send_test,
                title = title,
                content = message,
                color = color,
                onClick = {
                    templateTest?.let {
                        createRequest(
                            selectedOptions, it.questions, it.id,
                            resolvedTestViewModel, dataStoreManager
                        )
                    }
                },
                dialogOnClick = {
                    if (title != context.getString(R.string.error)) {
                        navController.navigate(Screen.MenuTestScreen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun Header(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white_900), shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 0,
                    bottomStartPercent = 0,
                    bottomEndPercent = 50
                )
            ), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
            Image(
                painter = painterResource(id = R.drawable.rounded_logo),
                contentDescription = stringResource(id = R.string.logo_sisvita),
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
fun SingleSelectionOptions(
    options: List<AlternativeResponse>,
    selectedOption: AlternativeResponse?,
    onOptionSelected: (AlternativeResponse) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        options.forEach { option ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) }
                )
                Text(
                    text = "${option.score}. ${option.description}",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleSingleSelectionOptions(
    title: String,
    options: List<AlternativeResponse>,
    selectedOption: AlternativeResponse?,
    onOptionSelected: (AlternativeResponse) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SingleSelectionOptions(
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )
    }
}

fun createRequest(
    alternatives: Map<Int, AlternativeResponse>,
    questions: List<QuestionResponse>,
    testId: Long,
    resolvedTestViewModel: ResolvedTestViewModel,
    dataStoreManager: DataStoreManager
) {
    val answerRequest: MutableList<AnswerRequest> = mutableListOf()

    questions.forEach { question ->
        val alternative = alternatives[question.id.toInt()] ?: return@forEach
        answerRequest.add(
            AnswerRequest(
                idQuestion = question.id,
                idAlternative = alternative.id,
                inverted = question.inverted,
                score = alternative.score,
                invertedScore = alternative.invertedScore
            )
        )
    }

    resolvedTestViewModel.setAnswersRequest(answerRequest)
    resolvedTestViewModel.sendResolvedTest(testId, dataStoreManager, questions.size)
}