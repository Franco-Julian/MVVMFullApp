package com.example.mvvmfullapp.domain

import com.example.mvvmfullapp.data.QuoteRepository
import com.example.mvvmfullapp.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRandomQuoteUseCaseTest{
    @RelaxedMockK
    private lateinit var  repository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun `When there is no quote in database return null`() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromDatabase() } returns emptyList()

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty return quote`() = runBlocking {
        //Given
        val myList = listOf(QuoteItem("Hola Mundo","Juli"))
        coEvery { repository.getAllQuotesFromDatabase() } returns myList

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == myList.first())
    }
}