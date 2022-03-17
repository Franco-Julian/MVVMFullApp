package com.example.mvvmfullapp.data.network

import com.example.mvvmfullapp.core.RetrofitHelper
import com.example.mvvmfullapp.data.model.QuoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
             response.body() ?: emptyList()
        }
    }
}