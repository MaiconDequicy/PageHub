package br.pagehub.ui.adapters

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
import br.pagehub.model.LivroSalvo
import com.squareup.picasso.Picasso

class LivrosSalvosAdapter(
    private val onItemClicked: (LivroSalvo) -> Unit
) : ListAdapter<LivroSalvo, LivrosSalvosAdapter.LivroSalvoViewHolder>(LivroSalvoDiffCallback()) {


    class LivroSalvoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val capa: ImageView = itemView.findViewById(R.id.imageViewCapa)
        private val titulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        private val autor: TextView = itemView.findViewById(R.id.textViewAutor)
        private val status: TextView = itemView.findViewById(R.id.textViewStatusLeitura)


        fun bind(livro: LivroSalvo, onItemClicked: (LivroSalvo) -> Unit) {

            titulo.text = livro.titulo
            autor.text = livro.autor
            status.text = livro.statusLeitura

            //Configura o clique no item inteiro
            itemView.setOnClickListener {
                onItemClicked(livro)
            }

            //Carrega a imagem da capa usando Picasso
            val urlImagem = livro.imagemUrl
            if (!urlImagem.isNullOrEmpty()) {
                val urlSegura = urlImagem.replace("http://", "https://")
                Picasso.get()
                    .load(urlSegura)
                    .placeholder(R.drawable.livro2)
                    .error(R.drawable.livro2)
                    .into(capa, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            Log.d("Picasso", "Imagem do livro salvo carregada: $urlSegura")
                        }
                        override fun onError(e: Exception?) {
                            Log.e("Picasso", "Erro ao carregar imagem: ${e?.message}")
                        }
                    })
            } else {
                capa.setImageResource(R.drawable.livro2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroSalvoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_livro_biblo, parent, false)
        return LivroSalvoViewHolder(view)
    }


    override fun onBindViewHolder(holder: LivroSalvoViewHolder, position: Int) {
        val livro = getItem(position)

        holder.bind(livro, onItemClicked)
    }
}

class LivroSalvoDiffCallback : DiffUtil.ItemCallback<LivroSalvo>() {
    override fun areItemsTheSame(oldItem: LivroSalvo, newItem: LivroSalvo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LivroSalvo, newItem: LivroSalvo): Boolean {
        return oldItem == newItem
    }
}