package br.pagehub.repository

import br.pagehub.model.BookItem
import br.pagehub.network.RetrofitClient

class BookRepository {
    private val apiService = RetrofitClient.apiService

    //Método para buscar livros populares
    suspend fun getPopularBooks(): List<BookItem>? {
        val response = apiService.getBooksByCategory("best selling books", "relevance")
        return if (response.isSuccessful) {
            response.body()?.items
        } else {
            null
        }
    }

    //Método para buscar livros recomendados
    suspend fun getRecommendedBooks(): List<BookItem>? {
        val response = apiService.getBooksByCategory("classic literature", "relevance")
        return if (response.isSuccessful) {
            response.body()?.items
        } else {
            null
        }
    }

    //função para buscar livros com palavras rsrs
    suspend fun searchBooks(query: String): List<BookItem> {
        return try {
            val response = apiService.getBooksByCategory(query, "relevance")
            if (response.isSuccessful) {
                response.body()?.items ?: emptyList()
            } else {

                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
