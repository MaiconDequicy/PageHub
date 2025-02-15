package br.pagehub.repository

import br.pagehub.database.UserDao
import com.example.pagehub.data.model.User

class UserRepository(private val userDao: UserDao)
{
    suspend fun inserir(user: User)
    {
        userDao.inserir(user)
    }
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}