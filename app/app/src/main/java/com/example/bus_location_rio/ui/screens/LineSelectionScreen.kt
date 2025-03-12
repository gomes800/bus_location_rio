package com.example.bus_location_rio.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LineSelectionScreen(navController: NavController) {
    val lines = listOf("371", "472", "103", "104") // Exemplo de linhas de ônibus

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Escolha uma linha de ônibus:", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn {
            items(lines) { line ->
                Button(
                    onClick = { navController.navigate("map/$line") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text("Linha $line")
                }
            }
        }
    }
}
