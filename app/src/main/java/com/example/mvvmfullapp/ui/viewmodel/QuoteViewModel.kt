package com.example.mvvmfullapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfullapp.data.model.QuoteModel
import com.example.mvvmfullapp.data.model.QuoteProvider
import com.example.mvvmfullapp.domain.GetQuoteUseCase
import com.example.mvvmfullapp.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    private val _currentQuote: MutableLiveData<QuoteModel> by lazy {
        MutableLiveData<QuoteModel>()
    }
    val currentQuote : LiveData<QuoteModel>
    get() = _currentQuote

    private val _loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val loading : LiveData<Boolean>
        get() = _loading

    val getQuoteUseCase = GetQuoteUseCase()

    fun onCreate(){
        viewModelScope.launch {
            _loading.postValue(true)
            val quote = getQuoteUseCase()
            if (!quote.isNullOrEmpty()){
                _currentQuote.postValue(quote[0])
            }
            _loading.postValue(false)

        }
    }

    val getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun randomQuote(){
        viewModelScope.launch {
            _loading.postValue(true)
            val quotes = QuoteProvider.quotes
                val position = (quotes.indices).random()
                _currentQuote.postValue(quotes[position])
            _loading.postValue(false)
        }
    }

}