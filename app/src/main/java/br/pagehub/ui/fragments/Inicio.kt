package br.pagehub.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pagehub.R
import br.pagehub.model.BookItem
import br.pagehub.repository.BookRepository
import br.pagehub.ui.activities.AddLivro
import br.pagehub.ui.adapter.BookAdapter
import br.pagehub.viewmodel.BookViewModel
import br.pagehub.viewmodel.BookViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Inicio : Fragment()
{
    private lateinit var viewModel: BookViewModel
    private lateinit var recyclerViewPopulares: RecyclerView
    private lateinit var recyclerViewRecomendados: RecyclerView
    private lateinit var adapterPopulares: BookAdapter
    private lateinit var adapterRecomendados: BookAdapter
    private lateinit var botaoAddLivro: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Inicializa os RecyclerViews
        recyclerViewPopulares = view.findViewById(R.id.recyclerLivrosPopulares)
        recyclerViewRecomendados = view.findViewById(R.id.recyclerLivrosRecomendados)
        botaoAddLivro = view.findViewById(R.id.fabAdicionarLivro)

        botaoAddLivro.setOnClickListener {
            irTelaAddBook()
        }

        recyclerViewPopulares.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecomendados.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapterPopulares = BookAdapter {book -> irParaDetalhesLivro(book)}
        adapterRecomendados = BookAdapter{book -> irParaDetalhesLivro(book)}

        recyclerViewPopulares.adapter = adapterPopulares
        recyclerViewRecomendados.adapter = adapterRecomendados

        val repository = BookRepository()

        val factory = BookViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(BookViewModel::class.java)


        viewModel.livrosPopulares.observe(viewLifecycleOwner, Observer { livros ->
            if (livros.isNullOrEmpty()) {
                Log.e("InicioFragment", "Livros populares estão vazios")
            } else {
                adapterPopulares.submitList(livros)
            }
        })

        viewModel.livrosRecomendados.observe(viewLifecycleOwner, Observer { livros ->
            if (livros.isNullOrEmpty()) {
                Log.e("InicioFragment", "Livros recomendados estão vazios")
            } else {
                adapterRecomendados.submitList(livros)
            }
        })
    }

    private fun irTelaAddBook() {
        val intent = Intent(requireContext(), AddLivro::class.java)
        startActivity(intent)
    }

    private fun irParaDetalhesLivro(book: BookItem)
    {
        val context = requireContext()
        val intent = Intent(context, br.pagehub.ui.activities.Detalhes_Livro::class.java).apply {
            putExtra("titulo", book.volumeInfo.title)
            putExtra("autor", book.volumeInfo.authors?.joinToString(","))
            putExtra("imagemUrl", book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
            putExtra("descricao", book.volumeInfo.description)
            putExtra("generos", book.volumeInfo.categories?.joinToString(","))
        }
        startActivity(intent)
    }
}


