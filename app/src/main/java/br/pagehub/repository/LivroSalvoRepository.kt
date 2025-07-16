package br.pagehub.repository

import androidx.lifecycle.LiveData
import br.pagehub.model.LivroSalvo
import br.pagehub.room.LivroSalvoDao

class LivroSalvoRepository(private val dao: LivroSalvoDao)
{

    fun getTodosLivros(): LiveData<List<LivroSalvo>> = dao.getTodosLivros()

    fun getLivrosPorStatus(status: String): LiveData<List<LivroSalvo>> =
        dao.getLivrosPorStatus(status)

    suspend fun inserirLivro(livro: LivroSalvo) = dao.inserirLivro(livro)

    suspend fun deletarLivro(livro: LivroSalvo) = dao.deletarLivro(livro)
}
