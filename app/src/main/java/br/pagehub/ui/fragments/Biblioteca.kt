package br.pagehub.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pagehub.R
import br.pagehub.repository.LivroSalvoRepository
import br.pagehub.ui.adapters.LivrosSalvosAdapter
import br.pagehub.ui.viewmodel.LivroSalvoViewModel
import com.example.pagehub.data.database.AppDatabase

class Biblioteca : Fragment(R.layout.fragment_biblioteca) {

    private lateinit var viewModel: LivroSalvoViewModel
    private lateinit var livrosAdapter: LivrosSalvosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView(view)
        viewModel.todosLivrosSalvos.observe(viewLifecycleOwner) { listaDeLivros ->
            livrosAdapter.submitList(listaDeLivros)
        }
    }

    private fun setupViewModel() {
        val database = AppDatabase.getDatabase(requireContext())
        val repository = LivroSalvoRepository(database.livroSalvoDao())
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LivroSalvoViewModel(repository) as T
            }
        }
        viewModel = ViewModelProvider(this, factory)[LivroSalvoViewModel::class.java]
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBiblioteca)
        livrosAdapter = LivrosSalvosAdapter()
        recyclerView.adapter = livrosAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
