package me.shellbell.morselib

/**
 * Created by Shailesh351 on 22/6/19.
 */

class Morse {

    companion object {
        private val morseEncoder by lazy { MorseEncoder() }
        private val morseDecoder by lazy { MorseDecoder() }

        fun encode(char: Char): String {
            return morseEncoder.encode(char)
        }

        fun encode(string: String): String {
            return morseEncoder.encode(string)
        }

        fun decode(string: String): String {
            return morseDecoder.decode(string)
        }
    }
}