package com.example.sisvitafrontend.screens.specialist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Observer
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.responses.AlternativeResponse
import com.example.sisvitafrontend.api.responses.Cities
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse
import com.example.sisvitafrontend.viewmodels.RealizarVigilanciaViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.CoroutineScope

class HeatMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val viewModel: RealizarVigilanciaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heat_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.listTest.observe(this, Observer { listTest ->
            if (listTest != null && listTest.isNotEmpty()) {
                addHeatMap(
                    listTest
                )
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val peru = LatLng(-9.19, -75.0152)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peru, 5f))
    }


    private fun addHeatMap(listTest: List<ResolvedTestResponse>) {
        val cities = mutableMapOf<String, Cities>()
        listTest.map {
            val city = it.paciente.user.persona.ubigeo
            if (cities.containsKey(city.ubigeo)) {
                cities[city.ubigeo] =
                    cities[city.ubigeo]!!.copy(quantity = cities[city.ubigeo]!!.quantity + 20)
            } else {
                cities[city.ubigeo] = Cities(city.departamento, city.longitud, city.latitud, 50)
            }
        }

        val latLngList = cities.map {
            WeightedLatLng(
                LatLng(it.value.latitude, it.value.longitude),
                it.value.quantity.toDouble()
            )
        }

        if (latLngList.isNotEmpty()) {
            val provider = HeatmapTileProvider.Builder()
                .weightedData(latLngList)
                .radius(20) // Ajustar el radio según sea necesario
                .maxIntensity(100.0)
                .build()

            // Añadir la superposición del mapa de calor al mapa
            mMap.addTileOverlay(
                com.google.android.gms.maps.model.TileOverlayOptions().tileProvider
                    (provider).zIndex(1.0f)
            )
        }
    }
}