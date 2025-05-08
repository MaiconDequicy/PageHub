package br.pagehub.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pagehub.R
import br.pagehub.repository.BookRepository
import br.pagehub.ui.adapter.BookAdapter
import br.pagehub.viewmodel.BookViewModel
import br.pagehub.viewmodel.BookViewModelFactory

class AddLivro : AppCompatActivity() {
    // Usando o viewModels() e passando a fábrica
    private val viewModel: BookViewModel by viewModels {
        BookViewModelFactory(BookRepository())
    }

    private lateinit var adapter: BookAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_livro)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01)
        }

        val toolbarAddBook = findViewById<Toolbar>(R.id.toolbarAddBook)
        setSupportActionBar(toolbarAddBook)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }

        adapter = BookAdapter{livroSelecionado ->

            val intent = Intent(this, Detalhes_Livro::class.java).apply {
                putExtra("titulo", livroSelecionado.volumeInfo.title)
                putExtra("autor", livroSelecionado.volumeInfo.authors?.joinToString(","))
                putExtra("imagemUrl", livroSelecionado.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
                putExtra("descricao", livroSelecionado.volumeInfo.description)
                putExtra("generos", livroSelecionado.volumeInfo.categories?.joinToString(","))
            }
            startActivity(intent)
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLivros)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //configurando o widget de busca
        val searchView = findViewById<SearchView>(R.id.searchViewLivros)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.pesquisarLivros(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.livrosPesquisados.observe(this){books -> adapter.submitList(books)}

    }
}
