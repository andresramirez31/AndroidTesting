package com.booktrace.androidTesting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.booktrace.test2.Quote
import com.booktrace.test2.QuoteRepository
import com.booktrace.test2.QuoteViewModel
import com.booktrace.test2.MainContent
import org.mockito.Mockito.`when`

import org.mockito.kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun randomQuoteButton_isDisplayed_andWorks() = runTest {
        // Arrange: Mock del repositorio para pruebas predecibles
        val mockQuoteRepository = mock(QuoteRepository::class.java)
        val expectedQuote = Quote("This is a fixed test quote", "Test Author")

        // Configurar el comportamiento de getRandomQuote() para que devuelva siempre la misma cita
        `when`(mockQuoteRepository.getRandomQuote()).thenReturn(expectedQuote)

        // Act: Configurar la actividad con el mock del repositorio
        composeTestRule.setContent {
            val quoteViewModel = QuoteViewModel(mockQuoteRepository)
            MainContent(quoteViewModel)
        }

        // Assert: Verificar que el botón "Get Random Quote" esté visible
        composeTestRule.onNodeWithText("Get Random Quote")
            .assertIsDisplayed()

        // Act: Simular clic en el botón
        composeTestRule.onNodeWithText("Get Random Quote")
            .performClick()

        // Esperar a que la cita se actualice
        composeTestRule.waitForIdle()

        // Assert: Verificar que la cita mockeada se muestra en la pantalla
        composeTestRule.onNodeWithText("This is a fixed test quote")
            .assertIsDisplayed()

        // Assert: Verificar que el autor también está visible
        composeTestRule.onNodeWithText("Test Author")
            .assertIsDisplayed()
    }
}
