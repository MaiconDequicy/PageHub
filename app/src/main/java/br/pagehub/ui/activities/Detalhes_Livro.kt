package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.pagehub.R
import com.squareup.picasso.Picasso

class Detalhes_Livro : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_livro)


        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }

        val toolbarCadastro = findViewById<Toolbar>(R.id.toolbarDetalhesLivro)
        setSupportActionBar(toolbarCadastro)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }

        findViewById<TextView>(R.id.textViewTitulo).text = intent.getStringExtra("titulo")
        findViewById<TextView>(R.id.textViewAutor).text = intent.getStringExtra("autor")
        findViewById<TextView>(R.id.textViewDescricao).text = intent.getStringExtra("descricao")
        findViewById<TextView>(R.id.textViewGeneros).text = intent.getStringExtra("generos")


        val imagemUrl = intent.getStringExtra("imagemUrl")
        val imageView = findViewById<ImageView>(R.id.imageViewCapaLivro)
        Picasso.get()
            .load(imagemUrl)
            .placeholder(R.drawable.livro2)
            .into(imageView)

    }
}