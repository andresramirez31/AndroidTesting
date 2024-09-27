package com.booktrace.test2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.booktrace.test2.ui.theme.Test2Theme

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val quoteRepository = QuoteRepository()
            val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory(quoteRepository))

            Test2Theme {

                    MainContent(quoteViewModel)

            }
        }
    }
}

@Composable
fun MainContent(quoteViewModel: QuoteViewModel) {
    val quote by quoteViewModel.quote.collectAsState()

    // Usamos Box para aplicar un color de fondo y centrar contenido
    Box(
        modifier = Modifier
            .fillMaxSize()  // Ocupar todo el tamaño de la pantalla
            .background(Color(0xFFEDE7F6)) // Fondo color lila claro
            .padding(16.dp), // Espaciado para no pegar todo a los bordes
        contentAlignment = Alignment.Center // Centrar contenido dentro del Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente
            verticalArrangement = Arrangement.Center // Centra verticalmente dentro del Box
        ) {
            quote?.let {
                QuoteDisplay(quote = it)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la frase y el botón

            Button(onClick = { quoteViewModel.getRandomQuote() }) {
                Text(text = "Get Random Quote")
            }
        }
    }
}

@Composable
fun QuoteDisplay(quote: Quote) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Centra la frase y el autor
        modifier = Modifier.padding(8.dp) // Espaciado alrededor de la frase
    ) {
        Text(text = quote.text, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el texto y el autor
        Text(text = quote.author, style = MaterialTheme.typography.bodyLarge)
    }
}