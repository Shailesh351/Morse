package dev.shellbell.morse.morsetable

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_morse_table.*
import dev.shellbell.morse.Player
import dev.shellbell.morse.R
import dev.shellbell.morselib.MorseCode
import dev.shellbell.morselib.MorseMapper

/**
 * Created by Shailesh351 on 24/6/19.
 */

class MorseTableFragment : Fragment() {

    companion object {
        const val TAG = "MORSE_TABLE_FRAGMENT"
    }

    private val data = MorseMapper().getMorseCodeList()
    private lateinit var player: Player

    init {
        data.addAll(26, generateSequence { MorseCode(' ', "") }.take(4).toList())
    }

    private val listener: MorseViewClickListener = object : MorseViewClickListener {
        override fun onClick(view: View?, position: Int) {
            val data = data[position]
            if (data.char.isLetter() || data.char.isDigit()) {
                player.play(data.char)
            }
        }
    }

    private val adapter = MorseAdapter(data, listener)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Player) {
            player = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_morse_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val gridLayoutManager = GridLayoutManager(context, 5, RecyclerView.VERTICAL, false)

        recycler_view.layoutManager = gridLayoutManager
        recycler_view.adapter = adapter
    }
}
