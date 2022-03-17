package com.example.mvvmfullapp.data

import com.example.mvvmfullapp.data.model.QuoteModel
import com.example.mvvmfullapp.data.model.QuoteProvider
import com.example.mvvmfullapp.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}