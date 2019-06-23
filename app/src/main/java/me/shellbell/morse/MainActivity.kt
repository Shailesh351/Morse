package me.shellbell.morse

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_pref_buttons.*
import me.shellbell.morse.controller.FlashController
import me.shellbell.morse.controller.SoundController
import me.shellbell.morse.controller.VibrationController
import me.shellbell.morse.helper.Helper
import me.shellbell.morse.ui.CircleButton
import me.shellbell.morselib.Morse

class MainActivity : AppCompatActivity() {

    private lateinit var flashPref: Preference<Boolean>
    private lateinit var soundPref: Preference<Boolean>
    private lateinit var vibrationPref: Preference<Boolean>

    private val flashController: FlashController by lazy { FlashController(this, flashPref) }
    private val soundController: SoundController by lazy { SoundController(this, soundPref) }
    private val vibrationController: VibrationController by lazy { VibrationController(this, vibrationPref) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPreferences()
        setUpActionBar()
        setUpFABs()
        setUpBottomNavigation()

        play.setOnClickListener {
            val string = "sos"
            textview.text = Morse.encode(string)
            flashController.play(string)
            soundController.play(string)
            vibrationController.play(string)
        }
    }

    private fun setUpPreferences() {
        var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        var rxPreferences = RxSharedPreferences.create(preferences)

        flashPref = rxPreferences.getBoolean(Helper.PREF_FLASH, true)
        soundPref = rxPreferences.getBoolean(Helper.PREF_SOUND, true)
        vibrationPref = rxPreferences.getBoolean(Helper.PREF_VIBRATION, true)
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
        val buttonFlash = CircleButton(fab_flash, R.drawable.ic_flash_on, R.drawable.ic_flash_off, flashPref)
        val buttonSound = CircleButton(fab_sound, R.drawable.ic_sound_on, R.drawable.ic_sound_off, soundPref)
        val buttonVibrate =
            CircleButton(fab_vibrate, R.drawable.ic_vibration_on, R.drawable.ic_vibration_off, vibrationPref)
    }

    private fun setUpActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.appbar)
    }
}
