package br.pagehub.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pagehub.model.BookItem
import br.pagehub.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val _livrosPopulares = MutableLiveData<List<BookItem>?>()
    val livrosPopulares: LiveData<List<BookItem>?> get() = _livrosPopulares

    private val _livrosRecomendados = MutableLiveData<List<BookItem>?>()
    val livrosRecomendados: LiveData<List<BookItem>?> get() = _livrosRecomendados

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        carregarLivrosPopulares()
        carregarLivrosRecomendados()
    }

    // Carregar livros populares com uma categoria específica
    private fun carregarLivrosPopulares() {
        viewModelScope.launch {
            try {
                val result = repository.getPopularBooks()
                if (result != null) {
                    _livrosPopulares.value = result
                    Log.d("BookViewModel", "Livros Populares: $result")
                } else {
                    _errorMessage.value = "Nenhum livro popular encontrado"
                    Log.e("BookViewModel", "Nenhum livro popular encontrado")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao buscar livros populares: ${e.message}"
                Log.e("BookViewModel", "Erro ao buscar livros populares", e)
            }
        }
    }

    // Carregar livros recomendados com uma categoria específica
    private fun carregarLivrosRecomendados() {
        viewModelScope.launch {
            try {
                val result = repository.getRecommendedBooks()
                if (result != null) {
                    _livrosRecomendados.value = result
                    Log.d("BookViewModel", "Livros Recomendados: $result")
                } else {
                    _errorMessage.value = "Nenhum livro recomendado encontrado"
                    Log.e("BookViewModel", "Nenhum livro recomendado encontrado")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao buscar livros recomendados: ${e.message}"
                Log.e("BookViewModel", "Erro ao buscar livros recomendados", e)
            }
        }
    }
}
