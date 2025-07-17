package br.pagehub.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pagehub.model.LivroSalvo
import br.pagehub.repository.LivroSalvoRepository
import kotlinx.coroutines.launch

class LivroSalvoViewModel(private val repository: LivroSalvoRepository) : ViewModel() {

    val todosLivrosSalvos: LiveData<List<LivroSalvo>> = repository.getTodosLivros()

    fun inserirLivro(livro: LivroSalvo) {
        viewModelScope.launch {
            repository.inserirLivro(livro)
        }
    }

    fun deletarLivro(livro: LivroSalvo) {
        viewModelScope.launch {
            repository.deletarLivro(livro)
        }
    }

    fun getLivroPorId(id: Int): LiveData<LivroSalvo> {
        return repository.getLivroPorId(id)
    }

    fun atualizarLivro(livro: LivroSalvo) {
        viewModelScope.launch {
            repository.atualizarLivro(livro)
        }
    }
}