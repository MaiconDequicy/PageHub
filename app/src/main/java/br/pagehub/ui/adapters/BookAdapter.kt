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

class BookAdapter(
    private val onItemClick: (BookItem) -> Unit = {}  // Clique opcional
) : ListAdapter<BookItem, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(
        itemView: View,
        private val onItemClick: (BookItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val imgCover: ImageView = itemView.findViewById(R.id.bookCover)
        private val txtTitle: TextView = itemView.findViewById(R.id.bookTitle)
        private val txtAuthor: TextView = itemView.findViewById(R.id.bookAuthor)

        fun bind(book: BookItem) {
            txtTitle.text = book.volumeInfo.title ?: "Título desconhecido"
            txtAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "Autor desconhecido"

            val thumbnailUrl = book.volumeInfo.imageLinks?.thumbnail
            if (!thumbnailUrl.isNullOrEmpty()) {
                val fixedUrl = thumbnailUrl.replace("http://", "https://")
                Picasso.get()
                    .load(fixedUrl)
                    .placeholder(R.drawable.livro2)
                    .into(imgCover, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            Log.d("Picasso", "Imagem carregada com sucesso")
                        }

                        override fun onError(e: Exception?) {
                            Log.e("Picasso", "Erro ao carregar imagem: ${e?.message}")
                            imgCover.setImageResource(R.drawable.livro2)
                        }
                    })
            } else {
                imgCover.setImageResource(R.drawable.livro2)
            }

            itemView.setOnClickListener {
                onItemClick(book)  // 🔥 Chama o clique passando o livro
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<BookItem>() {
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem == newItem
        }
    }
}
