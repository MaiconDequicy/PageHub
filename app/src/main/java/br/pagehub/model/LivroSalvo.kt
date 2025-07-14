package br.pagehub.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livros_salvos")
data class LivroSalvo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val autor: String,
    val imagemUrl: String?,
    val descricao: String?,
    val generos: String?,
    val statusLeitura: String
)
