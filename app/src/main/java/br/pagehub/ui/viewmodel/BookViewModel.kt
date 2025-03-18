package br.pagehub.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pagehub.model.BookItem
import br.pagehub.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _livrosPopulares = MutableLiveData<List<BookItem>?>()
    val livrosPopulares: LiveData<List<BookItem>?> get() = _livrosPopulares

    private val _livrosRecomendados = MutableLiveData<List<BookItem>?>()
    val livrosRecomendados: LiveData<List<BookItem>?> get() = _livrosRecomendados

    //LiveData para armazenar os livros pesquisados pelo usuário
    private val _livrosPesquisados = MutableLiveData<List<BookItem>?>()
    val livrosPesquisados: LiveData<List<BookItem>?> get() = _livrosPesquisados

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        carregarLivrosPopulares()
        carregarLivrosRecomendados()
    }

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

     fun pesquisarLivros(query: String)
    {
        viewModelScope.launch {
            try {
                val resultado = repository.searchBooks(query)
                if(resultado.isNotEmpty())
                {
                    _livrosPesquisados.value = resultado //atualiza  a livedata com os resultados
                    Log.d("BookViewModel", "Livros Pesquisados: $resultado")
                }
                else
                {
                    _errorMessage.value = "Nenhum livro foi encontrado para '$query'"
                    Log.e("BookViewModel", "Nenhum livro encontrado para '$query'")
                }
            }
            catch (e: Exception)
            {
                _errorMessage.value = "Erro ao buscar livros: ${e.message}"
                Log.e("BookViewModel", "Erro ao buscar livros", e)
            }
        }
    }

}
