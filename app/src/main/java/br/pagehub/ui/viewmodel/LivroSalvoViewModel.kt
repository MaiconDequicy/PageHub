package br.pagehub.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pagehub.model.LivroSalvo
import br.pagehub.repository.LivroSalvoRepository
import kotlinx.coroutines.launch

class LivroSalvoViewModel(private val repository: LivroSalvoRepository) : ViewModel() {

    val todosLivrosSalvos = repository.getTodosLivros()

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
}