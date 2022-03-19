package com.example.mvvmfullapp.domain

import com.example.mvvmfullapp.data.QuoteRepository
import com.example.mvvmfullapp.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetQuoteUseCaseTest{

    @RelaxedMockK
    private lateinit var  repository: QuoteRepository

    lateinit var getQuoteUseCase: GetQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuoteUseCase = GetQuoteUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anithing the get the get values from api`() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuoteUseCase()

        //Then
        coVerify(exactly = 1) { repository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api returns somethin, put it into database`() = runBlocking {
        //Given
        val myList = listOf(QuoteItem("Hola Mundo","Julian"))
        coEvery { repository.getAllQuotesFromApi() } returns myList

        //When
        val response = getQuoteUseCase()

        //Then
        coVerify(exactly = 1) { repository.clearQuotes() }
        coVerify(exactly = 1) { repository.insertQuotes(any()) }
        assert(response == myList)

    }

}