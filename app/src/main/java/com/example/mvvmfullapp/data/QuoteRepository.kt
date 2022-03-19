package com.example.mvvmfullapp.data

import com.example.mvvmfullapp.data.database.dao.QuoteDao
import com.example.mvvmfullapp.data.database.entities.QuoteEntity
import com.example.mvvmfullapp.data.network.QuoteService
import com.example.mvvmfullapp.domain.model.QuoteItem
import com.example.mvvmfullapp.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
    ) {

    suspend fun getAllQuotesFromApi(): List<QuoteItem> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<QuoteItem>{
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes : List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() = quoteDao.clear()
}