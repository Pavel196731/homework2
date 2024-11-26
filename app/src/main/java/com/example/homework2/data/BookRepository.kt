package com.example.homework2.data

import BookService


interface BookRepository{
    suspend fun getBooks(query: String, maxResults: Int) : List<Book>
}
class NetworkBooksRepository(
    private val bookService: BookService
) : BookRepository {
    override suspend fun getBooks(
        query: String,
        maxResults: Int
    ): List<Book> = bookService.bookSearch(query, maxResults).items.map { items ->
        Book(
            title = items.volumeInfo?.title,
            previewLink = items.volumeInfo?.previewLink,
            imageLink = items.volumeInfo?.imageLinks?.thumbnail
        )
    }
}