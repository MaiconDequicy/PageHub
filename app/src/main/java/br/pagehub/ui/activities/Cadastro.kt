package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.pagehub.R
import br.pagehub.databinding.ActivityCadastroBinding
import br.pagehub.ui.viewmodel.CadastroViewModel
import com.example.pagehub.data.model.User

class Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val cadastroViewModel: CadastroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.botaoFazerCadastro.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {
        val nome = binding.campoNome.text.toString().trim()
        val nomeUsuario = binding.campoNomeUsuario.text.toString().trim()
        val email = binding.campoEmailCadastro.text.toString().trim()
        val senha = binding.campoSenhaCadastro.text.toString().trim()
        val confirmarSenha = binding.campoConfirmarSenhaCadastro.text.toString().trim()

        if (nome.isEmpty() || nomeUsuario.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha != confirmarSenha) {
            Toast.makeText(this, "As senhas não são iguais!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criando o objeto usuário
        val user = User(name = nome, username = nomeUsuario, email = email, password = senha)

        // Inserir usuário no ViewModel (supondo que o método inserirUsuario tenha uma maneira de indicar sucesso ou falha)
        cadastroViewModel.inserirUsuario(user)

        // Verificar se o cadastro foi bem-sucedido
        Toast.makeText(this, "Cadastro Feito!", Toast.LENGTH_SHORT).show()

        // Redireciona para o Login
        finish()  // Fechando a tela de cadastro e indo para o login automaticamente
    }
}
