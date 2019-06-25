package me.shellbell.morse.translate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_translate.*
import me.shellbell.morse.Player
import me.shellbell.morse.R
import me.shellbell.morselib.Morse


/**
 * Created by Shailesh351 on 24/6/19.
 */

class TranslateFragment : Fragment() {

    companion object {
        const val TAG = "TRANSLATE_FRAGMENT"
    }

    private lateinit var player: Player

    private val TEXT_TO_MORSE = 0
    private val MORSE_TO_TEXT = 1
    private var mode = TEXT_TO_MORSE

    private val textEditTextListener = object : TextWatcher {
        var ignore = false

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            if (ignore)
                return

            ignore = true

            if (editable.toString().isNotEmpty()) {
                val morse = Morse.encode(editable.toString())
                if (morse.isNotEmpty())
                    morse_edit_text.setText(morse.toCharArray(), 0, morse.length)
                text_edit_text.setSelection(text_edit_text.text!!.length)
            } else {
                morse_edit_text.setText(charArrayOf(), 0, 0)
            }
            ignore = false
        }
    }

    private val morseEditTextListener = object : TextWatcher {
        var ignore = false

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            if (ignore)
                return

            ignore = true
            if (editable.toString().isNotEmpty()) {
                val text = Morse.decode(editable.toString())
                if (text.isNotEmpty())
                    text_edit_text.setText(text.toCharArray(), 0, text.length)
                morse_edit_text.setSelection(morse_edit_text.text!!.length)
            } else {
                text_edit_text.setText(charArrayOf(), 0, 0)
            }
            ignore = false
        }
    }

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
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        setTranslateMode(TEXT_TO_MORSE)
    }

    private fun setUpListeners() {
        fab_play.setOnClickListener {
            val text = text_edit_text.text.toString().trim()
            if (text.isNotBlank())
                player.play(text)
        }

        fab_change_mode.setOnClickListener {
            when (mode) {
                TEXT_TO_MORSE -> {
                    setTranslateMode(MORSE_TO_TEXT)
                }
                MORSE_TO_TEXT -> {
                    setTranslateMode(TEXT_TO_MORSE)
                }
            }
        }

        fab_copy.setOnClickListener {
            when (mode) {
                TEXT_TO_MORSE -> {
                    copyToClipboard(morse_edit_text.text.toString())
                }
                MORSE_TO_TEXT -> {
                    copyToClipboard(text_edit_text.text.toString())
                }
            }
        }
    }

    private fun setTranslateMode(newMode: Int) {
        mode = newMode
        when (newMode) {
            TEXT_TO_MORSE -> {
                mode_text_view.text = "TEXT TO MORSE"

                edit_text_container.removeView(morse_input_layout)
                edit_text_container.removeView(text_input_layout)
                edit_text_container.addView(text_input_layout)
                edit_text_container.addView(morse_input_layout)

                text_edit_text.isEnabled = true
                morse_edit_text.isEnabled = false
            }
            MORSE_TO_TEXT -> {
                mode_text_view.text = "MORSE TO TEXT"

                edit_text_container.removeView(text_input_layout)
                edit_text_container.removeView(morse_input_layout)
                edit_text_container.addView(morse_input_layout)
                edit_text_container.addView(text_input_layout)

                morse_edit_text.isEnabled = true
                text_edit_text.isEnabled = false
            }
        }
        invalidateEditTextListeners()
    }

    private fun invalidateEditTextListeners() {
        morse_edit_text.removeTextChangedListener(morseEditTextListener)
        text_edit_text.removeTextChangedListener(textEditTextListener)
        when (mode) {
            TEXT_TO_MORSE -> {
                text_edit_text.addTextChangedListener(textEditTextListener)
            }
            MORSE_TO_TEXT -> {
                morse_edit_text.addTextChangedListener(morseEditTextListener)
            }
        }
    }

    private fun copyToClipboard(text: CharSequence) {
        val clipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
