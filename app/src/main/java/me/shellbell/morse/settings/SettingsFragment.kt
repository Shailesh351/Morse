package me.shellbell.morse.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import kotlinx.android.synthetic.main.fragment_settings.*
import me.shellbell.morse.R
import me.shellbell.morse.helper.Helper

/**
 * Created by Shailesh351 on 24/6/19.
 */

class SettingsFragment : Fragment() {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val rxPreferences = RxSharedPreferences.create(preferences)

    private val speedPref: Preference<Int> by lazy { rxPreferences.getInteger(Helper.PREF_SPEED, 250) }

    companion object {
        const val TAG = "SETTINGS_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSpeedSeekBar()
    }

    private fun setUpSpeedSeekBar() {
        play_seek_bar.progress = 25
        play_seek_bar.incrementProgressBy(10)
        play_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = (play_seek_bar.max - progress) * 10
                speedPref.set(speed)
                play_seek_bar_text.text = "$speed"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekbar: SeekBar?) {

            }
        })
    }
}
