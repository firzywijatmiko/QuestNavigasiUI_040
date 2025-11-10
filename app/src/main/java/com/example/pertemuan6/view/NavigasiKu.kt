package com.example.pertemuan6.view

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// --- 1. Enumerasi Rute Navigasi ---
enum class Navigasi {
    Formulirku,
    Detail
}

// --- 2. Fungsi Pembantu Navigasi (Didefinisikan SATU KALI) ---
/**
 * Kembali ke rute Formulirku dan menghapus semua yang ada di atasnya dari back stack.
 */
private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    // popBackStack: Kembali ke rute Formulirku.
    // inclusive = false: Formulirku tetap ada di back stack.
    navController.popBackStack(route = Navigasi.Formulirku.name, inclusive = false)
}

// --- 3. Composable Utama (NavHost) ---
@Composable
fun DataApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
    ) { isiRuang ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(paddingValues = isiRuang)
        ) {

            // Rute: Formulirku
            composable(route = Navigasi.Formulirku.name) {
                FormIsian(
                    OnSubmitBtnClick = {
                        // Navigasi ke rute Detail
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }

            // Rute: Detail
            composable(route = Navigasi.Detail.name) {
                TampilData(
                    onBackBtnClick = {
                        // Kembali ke Formulirku menggunakan fungsi pembantu
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
            fun cancelAndBackToFormulir(
                navController: NavHostController
            ) {
                navController.popBackStack(route = Navigasi.Formulirku.name, inclusive = false)
            }

        }
    }
}


@Composable
fun FormIsian(OnSubmitBtnClick: () -> Unit) {
    Text(text = "Ini adalah layar Formulir")
    Button(onClick = OnSubmitBtnClick) {
        Text("Submit dan ke Detail")
    }
}
