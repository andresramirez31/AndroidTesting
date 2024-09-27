package com.booktrace.androidTesting

import com.booktrace.test2.Quote
import com.booktrace.test2.QuoteRepository
import com.booktrace.test2.QuoteViewModel
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.Mockito.mock
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuoteUnitTests {

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        // Set the Main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Reset the Main dispatcher to the original
        Dispatchers.resetMain()
    }

    @Test
    fun testGetRandomQuote() {
        val quoteRepository = QuoteRepository()
        val quote = quoteRepository.getRandomQuote()
        val quotes = quoteRepository.getQuotes()

        // Ensure the quote is from the list
        assertTrue(quotes.contains(quote))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetRandomQuoteMock() = runTest {

        //Arrange
        val mockRepository = mock(QuoteRepository::class.java)
        val expectedQuote = Quote("seven", "f")
        `when`(mockRepository.getRandomQuote()).thenReturn(expectedQuote)

        //Act
        val viewModel = QuoteViewModel(mockRepository)
        viewModel.getRandomQuote()

        advanceUntilIdle()

        //Assert
        verify(mockRepository).getRandomQuote()
        val actualQuote =  viewModel.quote.value
        println("Actual quote: ${viewModel.quote.value}")
        assertEquals(expectedQuote, actualQuote)


    }
}