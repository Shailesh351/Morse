package me.shellbell.morse

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar()
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_table -> {
                    textview.text = "Morse Table"
                }
                R.id.navigation_translate -> {
                    textview.text = "Translate"
                }
                R.id.navigation_settings -> {
                    textview.text = "Settings"
                }
                else -> {
                    textview.text = "Morse Code"
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        navigation.selectedItemId = R.id.navigation_translate
    }

    private fun setUpActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.appbar)
    }
}
