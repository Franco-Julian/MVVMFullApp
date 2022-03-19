package com.example.mvvmfullapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfullapp.domain.GetQuoteUseCase
import com.example.mvvmfullapp.domain.GetRandomQuoteUseCase
import com.example.mvvmfullapp.domain.model.QuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    private val _currentQuote: MutableLiveData<QuoteItem> by lazy {
        MutableLiveData<QuoteItem>()
    }
    val currentQuote: LiveData<QuoteItem>
        get() = _currentQuote

    private val _loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val loading: LiveData<Boolean>
        get() = _loading

    fun onCreate() {
        viewModelScope.launch {
            _loading.postValue(true)
            val quote = getQuoteUseCase()
            if (!quote.isNullOrEmpty()) {
                _currentQuote.postValue(quote[0])
            }
            _loading.postValue(false)

        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            _loading.postValue(true)
            val quote = getRandomQuoteUseCase()
            _currentQuote.postValue(quote)
            _loading.postValue(false)
        }
    }

}