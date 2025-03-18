package br.pagehub.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import br.pagehub.repository.UserRepository
import com.example.pagehub.data.database.UserDatabase
import com.example.pagehub.data.model.User
import kotlinx.coroutines.launch

class CadastroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun inserirUsuario(user: User) {
        viewModelScope.launch {
            repository.inserir(user)
        }
    }
}
