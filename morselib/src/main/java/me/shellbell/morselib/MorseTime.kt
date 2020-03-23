package me.shellbell.morselib

/**
 * Created by Shailesh351 on 27/6/19.
 */

class MorseTime(speed: Int) {

    var DOT_TIME_INTERVAL = speed

    val DASH_TIME_INTERVAL
            get() = DOT_TIME_INTERVAL * 3

    val CHAR_PAUSE_TIME_INTERVAL
            get() = DOT_TIME_INTERVAL

    val CHARACTER_SEPERATOR_TIME_INTERVAL
            get() = DOT_TIME_INTERVAL * 3

    val WORD_SEPERATOR_TIME_INTERVAL
            get() = DOT_TIME_INTERVAL * 7

    fun setSpeed(speed: Int) {
        DOT_TIME_INTERVAL = speed
    }
}