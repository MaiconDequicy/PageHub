package br.pagehub.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.pagehub.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }

        val botaoCadastro = findViewById<Button>(R.id.botaoTelaCadastro)
        botaoCadastro.setOnClickListener {
            irTelaCadastro()
        }

    }

    private fun irTelaCadastro()
    {
        val intent = Intent(this, Cadastro::class.java)
        startActivity(intent)

    }
}