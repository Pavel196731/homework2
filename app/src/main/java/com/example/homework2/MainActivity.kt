package com.example.homework2

import BooksScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.homework2.data.BaseAppContainer
import com.example.homework2.data.NetworkBooksRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NetworkBooksRepository(BaseAppContainer.retrofitService)
        val viewModel = ViewModelProvider(this)[BooksViewModel::class.java].apply {
            this.initialize(repository)
        }

        setContent {
            BooksScreen(viewModel)
        }
    }
}
