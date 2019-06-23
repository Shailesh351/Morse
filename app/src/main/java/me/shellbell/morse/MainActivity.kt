package me.shellbell.morse

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_pref_buttons.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar()
        setUpFABs()
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

    private fun setUpFABs() {
        val buttonFlash = CircleButton(fab_flash, R.drawable.ic_flash_on, R.drawable.ic_flash_off)
        val buttonSound = CircleButton(fab_sound, R.drawable.ic_sound_on, R.drawable.ic_sound_off)
        val buttonVibrate = CircleButton(fab_vibrate, R.drawable.ic_vibration_on, R.drawable.ic_vibration_off)
    }

    private fun setUpActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.appbar)
    }
}
