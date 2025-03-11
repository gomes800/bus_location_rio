package com.example.bus_location_rio.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.bus_location_rio.retrofit.BusLocation
import com.example.bus_location_rio.retrofit.fetchBusData
import com.example.bus_location_rio.retrofit.getUserLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(line: String) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Inicializa a posição do usuário em um ponto padrão (exemplo: centro do Rio de Janeiro)
    val userLocation = remember { mutableStateOf(LatLng(-22.9068, -43.1729)) }
    val buses = remember { mutableStateOf(listOf<BusLocation>()) }

    // Inicializa a câmera com uma posição padrão
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation.value, 14f)
    }

    // Obtém a localização do usuário e atualiza a posição da câmera
    LaunchedEffect(Unit) {
        getUserLocation(fusedLocationClient) { location ->
            val newLocation = LatLng(location.latitude, location.longitude)
            userLocation.value = newLocation

            // Atualiza a posição da câmera quando a localização do usuário for obtida
            cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(newLocation, 14f))
        }
        fetchBusData(line, buses)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Marcador do usuário
            Marker(
                state = rememberMarkerState(position = userLocation.value),
                title = "Você",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )

            // Marcadores dos ônibus
            buses.value.forEach { bus ->
                Marker(
                    state = rememberMarkerState(position = LatLng(bus.latitude, bus.longitude)),
                    title = "Ônibus ${bus.linha}"
                )
            }
        }
    }
}
