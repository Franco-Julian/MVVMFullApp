package com.example.mvvmfullapp.domain.model

import com.example.mvvmfullapp.data.database.entities.QuoteEntity
import com.example.mvvmfullapp.data.model.QuoteModel

data class QuoteItem (val quote: String, val author: String)

fun QuoteModel.toDomain() = QuoteItem(quote,author)

fun QuoteEntity.toDomain() = QuoteItem(quote, author)