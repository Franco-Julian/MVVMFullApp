package com.example.mvvmfullapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmfullapp.domain.GetQuoteUseCase
import com.example.mvvmfullapp.domain.GetRandomQuoteUseCase
import com.example.mvvmfullapp.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuoteUseCase: GetQuoteUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var  quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuoteUseCase,getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `on create quoteViewModel put quote on _currentQuote`() = runTest {
        //Given
        val quotes = listOf(QuoteItem("Hola Mundo","Juli"), QuoteItem("Adios Mundo","Juli"))
        coEvery { getQuoteUseCase() } returns quotes

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.currentQuote.value == quotes.first())
    }

    @Test
    fun `when randomQuote check new quote in currentquote`() = runTest {
        //Given
        val quote = QuoteItem("Hola Mundo","Juli")
        coEvery { getRandomQuoteUseCase() } returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.currentQuote.value == quote)
    }

    @Test
    fun `when randomQuoteUseCase return null keep last quote`() = runTest {
        //Given
        val quote = QuoteItem("Hola Mundo","Juli")
        quoteViewModel._currentQuote.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel._currentQuote.value == quote)


    }
}
