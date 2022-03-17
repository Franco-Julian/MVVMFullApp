package com.example.mvvmfullapp.domain

import com.example.mvvmfullapp.data.QuoteRepository
import com.example.mvvmfullapp.data.model.QuoteModel

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes() ?: null
}