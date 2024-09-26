package com.booktrace.test2

class QuoteRepository {
    private val quotes = listOf(
        Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
        Quote("two.", "a"),
        Quote("Three", "b"),
        Quote("Four", "c"),
        Quote("FIVE.", "d"),
        Quote("six", "Se"),
        Quote("seven", "f"),
        Quote("eight.", "g"),


        // Add more quotes here
    )

    fun getRandomQuote(): Quote {
        return quotes.random()
    }

    fun getQuotes(): List<Quote>{
        return quotes
    }
}
