package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.pagehub.R

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }

        val toolbarCadastro = findViewById<Toolbar>(R.id.toolbarCadastro)
        setSupportActionBar(toolbarCadastro)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }
    }
}
