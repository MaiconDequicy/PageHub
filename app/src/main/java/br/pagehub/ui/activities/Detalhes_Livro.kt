package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast // Importe o Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.pagehub.R
import br.pagehub.model.LivroSalvo // Importe o LivroSalvo
import br.pagehub.repository.LivroSalvoRepository
import br.pagehub.ui.viewmodel.LivroSalvoViewModel
import br.pagehub.viewmodel.LivroSalvoViewModelFactory
import com.example.pagehub.data.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class Detalhes_Livro : AppCompatActivity() {

    private val livroSalvoViewModel: LivroSalvoViewModel by viewModels {
        val dao = AppDatabase.getDatabase(this).livroSalvoDao()
        val repository = LivroSalvoRepository(dao)
        LivroSalvoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_livro)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01)
        }

        val toolbarCadastro = findViewById<Toolbar>(R.id.toolbarDetalhesLivro)
        setSupportActionBar(toolbarCadastro)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }

        val titulo = intent.getStringExtra("titulo")
        val autor = intent.getStringExtra("autor")
        val descricao = intent.getStringExtra("descricao")
        val generos = intent.getStringExtra("generos")
        val imagemUrl = intent.getStringExtra("imagemUrl")

        findViewById<TextView>(R.id.textViewTitulo).text = titulo
        findViewById<TextView>(R.id.textViewAutor).text = autor
        findViewById<TextView>(R.id.textViewDescricao).text = descricao
        findViewById<TextView>(R.id.textViewGeneros).text = generos

        val imageView = findViewById<ImageView>(R.id.imageViewCapaLivro)
        Picasso.get()
            .load(imagemUrl)
            .placeholder(R.drawable.livro2)
            .into(imageView)

        val btnSalvar = findViewById<FloatingActionButton>(R.id.fabFavoritarLivro)
        btnSalvar.setOnClickListener {
            if (titulo != null && autor != null) {
                val livroParaSalvar = LivroSalvo(
                    titulo = titulo,
                    autor = autor,
                    imagemUrl = imagemUrl,
                    descricao = descricao,
                    generos = generos,
                    statusLeitura = "Quero Ler"
                )

                livroSalvoViewModel.inserirLivro(livroParaSalvar)
                Toast.makeText(this, "$titulo adicionado à biblioteca!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro: dados do livro incompletos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}