package me.shellbell.morse.morsetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.shellbell.morse.R

/**
 * Created by Shailesh351 on 24/6/19.
 */

class MorseTableFragment : Fragment() {

    companion object{
        const val TAG = "MORSE_TABLE_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_morse_table, container, false)
    }
}
