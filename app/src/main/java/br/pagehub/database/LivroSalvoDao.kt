package br.pagehub.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}
