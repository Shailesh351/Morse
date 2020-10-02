package dev.shellbell.morse.morsetable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.shellbell.morse.R
import dev.shellbell.morselib.MorseCode

/**
 * Created by Shailesh351 on 24/6/19.
 */

class MorseAdapter(
    private val data: ArrayList<MorseCode>,
    private val listener: MorseViewClickListener
) : RecyclerView.Adapter<MorseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MorseViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MorseViewHolder, position: Int) {
        val item = data[position]
        holder.charTextView.text = item.char.toString()
        holder.morseTextView.text = item.morse
    }

    override fun getItemCount(): Int {
        return data.size
    }
}