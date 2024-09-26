package com.booktrace.test2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuoteViewModelFactory(private val quoteRepository: QuoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(quoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
