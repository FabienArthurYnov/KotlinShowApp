package com.ynov.newsapp.most_popular.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ynov.newsapp.most_popular.data.ShowRepository
import com.ynov.newsapp.most_popular.domain.GetMostPopularShowsUseCase
import com.ynov.newsapp.show_details.domain.AppNavHost
import com.ynov.newsapp.show_details.domain.GetShowDetailsUseCase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(
                    GetMostPopularShowsUseCase(ShowRepository()),
                    GetShowDetailsUseCase(ShowRepository()) // Pass GetShowDetailsUseCase here
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Call the AppNavHost to handle navigation
            AppNavHost(viewModel)
        }
    }
}



