package br.pagehub.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.pagehub.R
import br.pagehub.databinding.ActivityLoginBinding
import br.pagehub.viewmodel.LoginViewModel

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01)
        }

        binding.botaoTelaCadastro.setOnClickListener {
            irTelaCadastro()
        }

        binding.botaoLogin.setOnClickListener {
            autenticarUsuario()
        }

    }

    //funções de navegação
    private fun irTelaCadastro()
    {
        val intent = Intent(this, Cadastro::class.java)
        startActivity(intent)

    }
    private fun irTelaFeed()
    {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
        finish()
    }

    private fun autenticarUsuario()
    {
        val email = binding.campoEmailLogin.text.toString().trim()
        val senha = binding.campoSenhaLogin.text.toString().trim()

        if(email.isEmpty() || senha.isEmpty())
        {
            Toast.makeText(this, "Existem Campos Vazios!", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.autenticarUsuario(email, senha){sucesso ->
            if(sucesso)
            {
                Toast.makeText(this, "Login feito! Bem Vindo!", Toast.LENGTH_SHORT).show()
                irTelaFeed()
            }
            else
            {
                Toast.makeText(this, "E-mail ou Senha Estão Incorretos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


