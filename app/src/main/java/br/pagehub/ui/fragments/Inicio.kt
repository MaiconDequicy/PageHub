package br.pagehub.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pagehub.R
import br.pagehub.ui.adapter.BookAdapter
import br.pagehub.viewmodel.BookViewModel

class Inicio : Fragment() {
    private val viewModel: BookViewModel by viewModels()
    private lateinit var recyclerViewPopulares: RecyclerView
    private lateinit var recyclerViewRecomendados: RecyclerView
    private lateinit var adapterPopulares: BookAdapter
    private lateinit var adapterRecomendados: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa os RecyclerViews
        recyclerViewPopulares = view.findViewById(R.id.recyclerLivrosPopulares)
        recyclerViewRecomendados = view.findViewById(R.id.recyclerLivrosRecomendados)

        // Configura o LayoutManager para os RecyclerViews
        recyclerViewPopulares.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecomendados.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Inicializa os adaptadores com listas vazias
        adapterPopulares = BookAdapter()
        adapterRecomendados = BookAdapter()

        // Define os adaptadores nos RecyclerViews
        recyclerViewPopulares.adapter = adapterPopulares
        recyclerViewRecomendados.adapter = adapterRecomendados

        // Observando os dados dos livros populares
        viewModel.livrosPopulares.observe(viewLifecycleOwner, Observer { livros ->
            if (livros.isNullOrEmpty()) {
                Log.e("InicioFragment", "Livros populares estão vazios")
            } else {
                adapterPopulares.submitList(livros)  // Atualiza a lista de livros populares
            }
        })

        // Observando os dados dos livros recomendados
        viewModel.livrosRecomendados.observe(viewLifecycleOwner, Observer { livros ->
            if (livros.isNullOrEmpty()) {
                Log.e("InicioFragment", "Livros recomendados estão vazios")
            } else {
                adapterRecomendados.submitList(livros)  // Atualiza a lista de livros recomendados
            }
        })
    }
}
