package com.ynov.newsapp.everything.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynov.newsapp.everything.domain.model.Article
import com.ynov.newsapp.everything.domain.useCase.EverythingUseCase
import com.ynov.newsapp.everything.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    private val useCase: EverythingUseCase
) : ViewModel() {

    private val _state : MutableStateFlow<EverythingState> = MutableStateFlow(EverythingState())
    val state : StateFlow<EverythingState> = _state

    fun getEverything() = viewModelScope.launch(Dispatchers.IO) {

        useCase().collectLatest { everythingState ->
            when(everythingState) {
                is Resource.Success -> {

                    withContext(Dispatchers.Main) {
                        _state.value = EverythingState(articles = everythingState.data ?: emptyList())
                    }

                    //_state.value = EverythingState(articles = everythingState.data ?: emptyList())
                }
                is Resource.Error -> {
                    //_state.value = EverythingState(error = "An unexpected error occured")
                    //Log.e("EverythingViewModel", "getEverything: ${everythingState.message}")

                    withContext(Dispatchers.Main) {
                        _state.value = EverythingState(error = "${everythingState.message}")
                    }
                    Log.e("EverythingViewModel", "getEverything: ${everythingState.message}")

                }
                is Resource.Loading -> {
                    //_state.value = EverythingState(isLoading = true)

                    withContext(Dispatchers.Main) {
                        _state.value = EverythingState(isLoading = true)
                    }
                }
            }
        }
    }
}

class EverythingState(
    val articles : List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
