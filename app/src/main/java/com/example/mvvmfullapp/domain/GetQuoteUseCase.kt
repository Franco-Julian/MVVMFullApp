package com.example.mvvmfullapp.domain

import com.example.mvvmfullapp.data.QuoteRepository
import com.example.mvvmfullapp.data.database.entities.toDatabase
import com.example.mvvmfullapp.data.model.QuoteModel
import com.example.mvvmfullapp.domain.model.QuoteItem
import javax.inject.Inject


class GetQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<QuoteItem>? {
        val quotes = repository.getAllQuotesFromApi()
        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
            //carar database
        } else {
            repository.getAllQuotesFromDatabase()
        }

    }
}