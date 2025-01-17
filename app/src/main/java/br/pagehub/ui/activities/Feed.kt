package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.pagehub.R

class Feed : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.statusBarColor = getColor(R.color.cor01) // Substitua pelo ID da cor desejada
        }

        val toolbarFeed = findViewById<Toolbar>(R.id.toolbarFeed)

        setSupportActionBar(toolbarFeed)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setTitle("")
        }
    }
}