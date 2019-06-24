package me.shellbell.morse.morsetable

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Shailesh351 on 24/6/19.
 */

class MorseViewHolder(view: View, private val listener: MorseViewClickListener) : RecyclerView.ViewHolder(view),
    View.OnClickListener {

    var charTextView: AppCompatTextView = view.char_text_view
    var morseTextView: AppCompatTextView = view.morse_text_view

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION)
            listener.onClick(v, adapterPosition)
    }
}

interface MorseViewClickListener {
    fun onClick(view: View?, position: Int)
}