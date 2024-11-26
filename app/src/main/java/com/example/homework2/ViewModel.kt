package com.example.homework2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework2.data.Book
import com.example.homework2.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BooksViewModel(application: Application) : AndroidViewModel(application){

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private lateinit var repository: BookRepository

    fun initialize(repository: BookRepository) {
        this.repository = repository
    }

    fun loadBooks(query: String, maxResults: Int) {
        viewModelScope.launch {
            if (_books.value.isNotEmpty()) return@launch

            _isLoading.value = true
            _errorMessage.value = null

            try {
                val newBooks = repository.getBooks(query, maxResults)
                _books.value = newBooks
            } catch (e: Exception) {
                _errorMessage.value = getApplication<Application>().getString(R.string.loading_failed)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retryLoadBooks(query: String, maxResults: Int) {
        loadBooks(query, maxResults)
    }
}