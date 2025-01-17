package br.pagehub.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import br.pagehub.R
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }

        val handler = android.os.Handler(Looper.getMainLooper())

        handler.postDelayed(
            {
                irTelaLogin()
            }, 2000)

    }

    private fun irTelaLogin()
    {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}