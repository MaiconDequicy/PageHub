package br.pagehub.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update // Importe a anotação @Update
import br.pagehub.model.LivroSalvo

@Dao
interface LivroSalvoDao {

    @Query("SELECT * FROM livros_salvos ORDER BY id DESC")
    fun getTodosLivros(): LiveData<List<LivroSalvo>>

    @Query("SELECT * FROM livros_salvos WHERE statusLeitura = :status")
    fun getLivrosPorStatus(status: String): LiveData<List<LivroSalvo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirLivro(livro: LivroSalvo)

    @Delete
    suspend fun deletarLivro(livro: LivroSalvo)

    @Update
    suspend fun atualizarLivro(livro: LivroSalvo)

    @Query("SELECT * FROM livros_salvos WHERE id = :idDoLivro")
    fun getLivroPorId(idDoLivro: Int): LiveData<LivroSalvo>
}