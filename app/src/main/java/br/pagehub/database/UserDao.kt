package br.pagehub.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pagehub.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun inserir(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}
