package com.booktrace.test2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel (private val quoteRepository: QuoteRepository) : ViewModel(){
    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote

    fun getRandomQuote() {
        viewModelScope.launch {
            val newQuote = quoteRepository.getRandomQuote()
            println("Fetching quote...: $newQuote")
            _quote.value = newQuote
            println("Fetching quote...: ${quote.value}")
        }
    }
}
