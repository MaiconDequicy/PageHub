package br.pagehub.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import br.pagehub.R
import br.pagehub.ui.fragments.Biblioteca
import br.pagehub.ui.fragments.BuscarLivros
import br.pagehub.ui.fragments.Inicio
import com.google.android.material.bottomnavigation.BottomNavigationView

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
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
            setTitle("")
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationViewFeed)

        replaceFragment(Inicio())

        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId)
            {
                R.id.nav_home -> replaceFragment(Inicio())
                R.id.nav_busca -> replaceFragment(BuscarLivros())
                R.id.nav_estante -> replaceFragment(Biblioteca())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}