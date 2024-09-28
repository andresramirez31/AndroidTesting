package com.booktrace.test2

class QuoteRepository {
    private val quotes = listOf(
        Quote("El conocimiento es poder.", "Francis Bacon"),
        Quote("La vida es lo que pasa mientras estás ocupado haciendo otros planes.", "John Lennon"),
        Quote("No cuentes los días, haz que los días cuenten.", "Muhammad Ali"),
        Quote("La imaginación es más importante que el conocimiento.", "Albert Einstein"),
        Quote("La única manera de hacer un gran trabajo es amar lo que haces.", "Steve Jobs"),
        Quote("En medio de la dificultad yace la oportunidad.", "Albert Einstein"),
        Quote("La felicidad no es algo hecho. Viene de tus propias acciones.", "Dalai Lama"),
        Quote("El éxito es la suma de pequeños esfuerzos repetidos día tras día.", "Robert Collier"),
    )

    fun getRandomQuote(): Quote {
        return quotes.random()
    }

    fun getQuotes(): List<Quote>{
        return quotes
    }
}
