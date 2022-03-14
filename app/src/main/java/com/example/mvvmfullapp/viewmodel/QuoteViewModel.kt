package com.example.mvvmfullapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmfullapp.model.QuoteModel
import com.example.mvvmfullapp.model.QuoteProvider

class QuoteViewModel: ViewModel() {

    private val _currentQuote: MutableLiveData<QuoteModel> by lazy {
        MutableLiveData<QuoteModel>()
    }
    val currentQuote : LiveData<QuoteModel>
    get() = _currentQuote

    fun randomQuote(){
        val quote = QuoteProvider.radomQuote()
        _currentQuote.postValue(quote)
    }

}