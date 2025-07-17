package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.pagehub.R
import br.pagehub.model.LivroSalvo
import br.pagehub.repository.LivroSalvoRepository
import br.pagehub.ui.viewmodel.LivroSalvoViewModel
import br.pagehub.viewmodel.LivroSalvoViewModelFactory
import com.example.pagehub.data.database.AppDatabase
import com.squareup.picasso.Picasso

class LivroSalvoDetalhesActivity : AppCompatActivity() {

    private val viewModel: LivroSalvoViewModel by viewModels {
        val repository = LivroSalvoRepository(AppDatabase.getDatabase(this).livroSalvoDao())
        LivroSalvoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livro_salvo_detalhes)

        val toolbar = findViewById<Toolbar>(R.id.toolbarDetalhesLivroSalvo)
        setSupportActionBar(toolbar)
        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }

        val livroId = intent.getIntExtra("LIVRO_ID", -1)

        if (livroId == -1) {
            finish()
            return
        }

        viewModel.getLivroPorId(livroId).observe(this) { livroDoBanco ->
            // Assim que o livro for encontrado, preenche a tela com os dados
            if (livroDoBanco != null) {
                popularUi(livroDoBanco)
            }
        }
    }

    private fun popularUi(livro: LivroSalvo) {
        findViewById<TextView>(R.id.textViewTitulo).text = livro.titulo
        findViewById<TextView>(R.id.textViewAutor).text = livro.autor
        findViewById<TextView>(R.id.textViewStatus).text = livro.statusLeitura
        findViewById<TextView>(R.id.textViewGeneros).text = livro.generos
        findViewById<TextView>(R.id.textViewDescricao).text = livro.descricao

        val imageView = findViewById<ImageView>(R.id.imageViewCapaLivro)
        Picasso.get()
            .load(livro.imagemUrl)
            .placeholder(R.drawable.livro2)
            .error(R.drawable.livro2)
            .into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}