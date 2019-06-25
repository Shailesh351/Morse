package me.shellbell.morselib

/**
 * Created by Shailesh351 on 22/6/19.
 */

class Constants {
    companion object {

        val DOT = '.'

        val DASH = '-'

        val INVALID = "null"

        //One Space
        val MORSE_CHARACTER_SEPARATOR = ' '

        //Three Spaces
        val MORSE_WORD_SEPARATOR = "   "

        val WORD_SEPERATOR_PLACEHOLDER = '$'



        val DOT_TIME_INTERVAL = 250

        val DASH_TIME_INTERVAL = DOT_TIME_INTERVAL * 3

        val CHAR_PAUSE_TIME_INTERVAL = DOT_TIME_INTERVAL

        val CHARACTER_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL * 3

        val WORD_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL * 7
    }
}