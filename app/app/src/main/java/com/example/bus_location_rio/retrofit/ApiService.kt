package com.example.bus_location_rio.retrofit

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.runtime.MutableState
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("bus/line/{line}")
    suspend fun getBuses(@Path("line") line: String): List<BusLocation>
}

fun fetchBusData(line: String, buses: MutableState<List<BusLocation>>) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitClient.apiService.getBuses(line)
            withContext(Dispatchers.Main) {
                buses.value = response
            }
        } catch (e: Exception) {
            Log.e("MapScreen", "Erro ao buscar ônibus: ${e.message}")
        }
    }
}

@SuppressLint("MissingPermission")
fun getUserLocation(fusedLocationClient: FusedLocationProviderClient, onLocationReceived: (Location) -> Unit) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let { onLocationReceived(it) }
    }
}


object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.158:8080"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

data class BusLocation(val latitude: Double, val longitude: Double, val linha: String)


