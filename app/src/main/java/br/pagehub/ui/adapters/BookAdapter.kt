package br.pagehub.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.pagehub.R
import br.pagehub.model.BookItem
import com.squareup.picasso.Picasso

class BookAdapter : ListAdapter<BookItem, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgCover: ImageView = itemView.findViewById(R.id.bookCover)
        private val txtTitle: TextView = itemView.findViewById(R.id.bookTitle)
        private val txtAuthor: TextView = itemView.findViewById(R.id.bookAuthor)

        fun bind(book: BookItem) {
            //Verificar se o título é nulo ou vazio
            txtTitle.text = book.volumeInfo.title ?: "Título desconhecido"

            //Verificar se os autores são nulos ou vazios
            txtAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "Autor desconhecido"

            //verificar se há imagem, caso contrário usar imagem padrão
            val thumbnailUrl = book.volumeInfo.imageLinks?.thumbnail
            if (!thumbnailUrl.isNullOrEmpty()) {
                //Ajusta a URL da imagem (caso necessário)
                val fixedUrl = thumbnailUrl.replace("http://", "https://")

                Picasso.get()
                    .load(fixedUrl)
                    .placeholder(R.drawable.livro2)  //Imagem que será exibida enquanto carrega
                    .into(imgCover, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            Log.d("Picasso", "Imagem carregada com sucesso")
                        }

                        override fun onError(e: Exception?) {
                            Log.e("Picasso", "Erro ao carregar imagem: ${e?.message}")
                            imgCover.setImageResource(R.drawable.livro2)  // Imagem de fallback
                        }
                    })

            } else {
                imgCover.setImageResource(R.drawable.livro2)  //Coloque uma imagem padrão se não houver thumbnail
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<BookItem>() {
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem.id == newItem.id  //Verifica se os livros são os mesmos
        }

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem == newItem  //Verifica se o conteúdo dos livros é igual
        }
    }
}
