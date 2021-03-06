package dev.shellbell.morse

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_pref_buttons.*
import dev.shellbell.morse.helper.Helper
import dev.shellbell.morse.morsetable.MorseTableFragment
import dev.shellbell.morse.settings.SettingsFragment
import dev.shellbell.morse.translate.TranslateFragment
import dev.shellbell.morse.ui.CircleButton
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent


/**
 * Created by Shailesh351 on 22/6/19.
 */

class MainActivity : AppCompatActivity(), Player {

    private lateinit var flashPref: Preference<Boolean>
    private lateinit var soundPref: Preference<Boolean>
    private lateinit var vibrationPref: Preference<Boolean>

    private val morsePlayer: MorsePlayer by lazy {
        MorsePlayer(
            this,
            flashPref,
            soundPref,
            vibrationPref
        )
    }

    private val morseTableFragment: MorseTableFragment by lazy { MorseTableFragment() }
    private val translateFragment: TranslateFragment by lazy { TranslateFragment() }
    private val settingsFragment: SettingsFragment by lazy { SettingsFragment() }

    private val fm: FragmentManager by lazy { supportFragmentManager }
    private var active: Fragment = morseTableFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Run your application only in portrait mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setUpPreferences()
        setUpActionBar()
        setUpFragments()
        setUpFABs()
        setUpBottomNavigation()
        setUpKeyBoard()
    }

    override fun play(string: String) {
        morsePlayer.play(string)
    }

    override fun play(char: Char) {
        morsePlayer.play(char)
    }

    override fun isPlaying(): Boolean {
        return morsePlayer.isPlaying()
    }

    override fun stop() {
        morsePlayer.stop()
    }

    private fun setUpPreferences() {
        val preferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val rxPreferences = RxSharedPreferences.create(preferences)

        flashPref = rxPreferences.getBoolean(Helper.PREF_FLASH, true)
        soundPref = rxPreferences.getBoolean(Helper.PREF_SOUND, true)
        vibrationPref = rxPreferences.getBoolean(Helper.PREF_VIBRATION, true)
    }

    private fun setUpFragments() {
        fm.beginTransaction().add(R.id.fragment_container, settingsFragment, SettingsFragment.TAG)
            .hide(settingsFragment).commit()
//        fm.beginTransaction().add(R.id.fragment_container, morseTableFragment, MorseTableFragment.TAG)
//            .hide(morseTableFragment).commit()
        fm.beginTransaction().add(R.id.fragment_container, translateFragment, TranslateFragment.TAG)
            .hide(translateFragment).commit()
        //Default
        fm.beginTransaction()
            .add(R.id.fragment_container, morseTableFragment, MorseTableFragment.TAG).commit()
    }

    private fun setUpBottomNavigation() {
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_table -> {
                    fm.beginTransaction().hide(active).show(morseTableFragment).commit()
                    active = morseTableFragment
                }
                R.id.navigation_translate -> {
                    fm.beginTransaction().hide(active).show(translateFragment).commit()
                    active = translateFragment
                }
//                R.id.navigation_settings -> {
//                    fm.beginTransaction().hide(active).show(settingsFragment).commit()
//                    active = settingsFragment
//                }
                else -> {
                    //Other item
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        navigation.selectedItemId = R.id.navigation_table
    }

    private fun setUpFABs() {
        val buttonFlash =
            CircleButton(fab_flash, R.drawable.ic_flash_on, R.drawable.ic_flash_off, flashPref)
        val buttonSound =
            CircleButton(fab_sound, R.drawable.ic_sound_on, R.drawable.ic_sound_off, soundPref)
        val buttonVibrate =
            CircleButton(
                fab_vibrate,
                R.drawable.ic_vibration_on,
                R.drawable.ic_vibration_off,
                vibrationPref
            )
    }

    private fun setUpActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.appbar)
    }

    private fun setUpKeyBoard() {
        KeyboardVisibilityEvent.setEventListener(this) { isOpen ->
            if (isOpen) {
                navigation.visibility = View.GONE
            } else {
                navigation.visibility = View.VISIBLE
            }
        }
    }
}
