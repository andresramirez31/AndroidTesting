package com.booktrace.test2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuoteViewModel (private val quoteRepository: QuoteRepository) : ViewModel(){
    val quote = MutableLiveData<Quote>()

    fun getRandomQuote() {
        viewModelScope.launch {
            quote.value = quoteRepository.getRandomQuote()
        }
    }
}
