package me.shellbell.morse.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.shellbell.morse.R

/**
 * Created by Shailesh351 on 24/6/19.
 */

class SettingsFragment : Fragment() {

    companion object{
        const val TAG = "SETTINGS_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}
