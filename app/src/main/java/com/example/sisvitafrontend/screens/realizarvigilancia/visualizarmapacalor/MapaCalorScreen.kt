package com.example.sisvitafrontend.screens.realizarvigilancia.visualizarmapacalor

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.screens.realizarvigilancia.FiltrosViewModel
import com.example.sisvitafrontend.ui.components.DateFilter
import com.example.sisvitafrontend.ui.components.SelectList
import com.example.sisvitafrontend.ui.components.TextButton
import com.example.sisvitafrontend.ui.components.Background
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng

@Composable
fun HeatmapScreen(
    realizarVigilanciaHeatMapViewModel: RealizarVigilanciaHeatMapViewModel = viewModel(
        modelClass =
        RealizarVigilanciaHeatMapViewModel::class
            .java
    ),
    filtrosViewModel: FiltrosViewModel = viewModel(modelClass = FiltrosViewModel::class.java)
) {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle(context)
    var expanded by remember { mutableStateOf(false) }

    val listTipoTest by filtrosViewModel.listTipoTest.observeAsState(initial = emptyList())
    val listAnsiedad by filtrosViewModel.listAnsiedad.observeAsState(initial = emptyList())

    Background()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { mapView ->
            mapView.getMapAsync(OnMapReadyCallback { googleMap ->
                setupMap(googleMap)
                realizarVigilanciaHeatMapViewModel.listTest.observe(
                    context as LifecycleOwner
                ) { listTest ->
                    googleMap.clear()
                    if (listTest != null && listTest.isNotEmpty()) {
                        addHeatMap(googleMap, listTest)
                    }
                }
            })
        }
        Column(
            modifier = Modifier.offset(y = 32.dp)
        ){
            TextButton(
                text = R.string.filters,
                color = R.color.light_green_900,
                textColor = R.color.white_900,
                shape = 12,
                size = DpSize(100.dp, 40.dp),
                textSize = 12,
                onClick = { expanded = !expanded }
            )
            if (expanded) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    SelectList(
                        label = R.string.test_type,
                        selectedItem = filtrosViewModel.tipoTest.value,
                        items = listTipoTest,
                        onItemSelected = {
                            filtrosViewModel.setTipoTest(it)
                            filtrosViewModel.setAnsiedad("")
                            filtrosViewModel.getAnsiedad(it)
                        }
                    )
                    DateFilter(
                        label = R.string.date,
                        startDateLabel = R.string.initial_date,
                        endDateLabel = R.string.final_date,
                        startDate = filtrosViewModel.fechaInicial.value,
                        endDate = filtrosViewModel.fechaFinal.value,
                        onDateRangeSelected = { startDate, endDate ->
                            filtrosViewModel.setFechaInicial(startDate)
                            filtrosViewModel.setFechaFinal(endDate)
                        },
                        onDateSelected = {
                            filtrosViewModel.setTextDate("${filtrosViewModel.fechaInicial.value} - ${filtrosViewModel.fechaFinal.value}")
                        }
                    )
                    SelectList(
                        label = R.string.anxiety_level,
                        selectedItem = filtrosViewModel.ansiedad.value,
                        items = listAnsiedad,
                        onItemSelected = { filtrosViewModel.setAnsiedad(it) }
                    )
                    TextButton(
                        text = R.string.apply_filters,
                        color = R.color.light_green_300,
                        textColor = R.color.black_900,
                        shape = 12,
                        size = DpSize(200.dp, 40.dp),
                        textSize = 12,
                        onClick = {
                            expanded = false
                            realizarVigilanciaHeatMapViewModel.getResolvedTestByFilter(
                                filtrosViewModel.tipoTest.value,
                                filtrosViewModel.fechaInicial.value,
                                filtrosViewModel.fechaFinal.value,
                                filtrosViewModel.ansiedad.value,
                                filtrosViewModel.textDate.value
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextButton(
                        text = R.string.clear_filters,
                        color = R.color.light_green_300,
                        textColor = R.color.black_900,
                        shape = 12,
                        size = DpSize(200.dp, 40.dp),
                        textSize = 12,
                        onClick = {
                            expanded = false
                            filtrosViewModel.clearFilter()
                            realizarVigilanciaHeatMapViewModel.getResolvedTestResponse()
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(context: android.content.Context): MapView {
    val mapView = remember {
        MapView(context).apply {
            onCreate(Bundle())
        }
    }

    DisposableEffect(mapView) {
        mapView.onResume()
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    return mapView
}

private fun setupMap(
    googleMap: GoogleMap,
) {
    googleMap.uiSettings.isZoomControlsEnabled = true
    val peru = LatLng(-9.19, -75.0152)
    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peru, 5f))
}

private fun addHeatMap(googleMap: GoogleMap, listTest: List<ResolveTestResponseHeatMap>) {

    val latLngList = listTest.map {
        WeightedLatLng(
            LatLng(it.latitude, it.longitude),
            it.totalIntensity.toDouble()
        )
    }

    if (latLngList.isNotEmpty()) {
        val provider = HeatmapTileProvider.Builder()
            .weightedData(latLngList)
            .radius(30)
            .maxIntensity(20.0)
            .build()

        googleMap.addTileOverlay(
            TileOverlayOptions().tileProvider
                (provider).zIndex(1.0f)
        )
    }
}