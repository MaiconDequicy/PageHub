package br.pagehub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pagehub.repository.LivroSalvoRepository
import br.pagehub.ui.viewmodel.LivroSalvoViewModel

class LivroSalvoViewModelFactory(
    private val repository: LivroSalvoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LivroSalvoViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return LivroSalvoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}