package com.booktrace.test2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.booktrace.test2.ui.theme.Test2Theme

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    val quote by quoteViewModel.quote.observeAsState()

    Column {
        quote?.let {
            QuoteDisplay(quote = it)
        }

        Button(onClick = { quoteViewModel.getRandomQuote() }) {
            Text(text = "Get Random Quote")
        }
    }
}

@Composable
fun QuoteDisplay(quote: Quote) {
    Column {
        Text(text = quote.text, style = MaterialTheme.typography.headlineSmall)
        Text(text = quote.author, style = MaterialTheme.typography.bodyLarge)
    }
}