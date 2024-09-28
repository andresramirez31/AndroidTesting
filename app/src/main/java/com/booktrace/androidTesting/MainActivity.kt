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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            quote?.let {
                QuoteDisplay(quote = it)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { quoteViewModel.getRandomQuote() }) {
                Text(text = "Get Random Quote")
            }
        }
    }
}

@Composable
fun QuoteDisplay(quote: Quote) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = quote.text, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = quote.author, style = MaterialTheme.typography.bodyLarge)
    }
}