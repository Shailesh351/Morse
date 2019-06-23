package me.shellbell.morse.controller

import me.shellbell.morselib.Constants
import me.shellbell.morselib.Morse

/**
 * Created by Shailesh351 on 23/6/19.
 */

abstract class MorseController : Runnable {

    private var thread: Thread? = null
    private var stringToEncode = ""

    open fun play(string: String) {
        stringToEncode = string
        stop()
        start()
    }

    private fun start() {
        thread = Thread(this)
        thread!!.start()
    }

    private fun stop() {
        thread?.let {
            thread!!.interrupt()
        }
    }

    override fun run() {
        val chars = getEncodedCharArray()
        var previous = ' '

        for (char in chars) {
            if (!thread!!.isInterrupted) {
                try {
                    when (char) {
                        Constants.DOT -> {
                            generateDot()
                            Thread.sleep(Constants.CHAR_PAUSE_TIME_INTERVAL.toLong())
                        }
                        Constants.DASH -> {
                            generateDash()
                            Thread.sleep(Constants.CHAR_PAUSE_TIME_INTERVAL.toLong())
                        }
                        Constants.MORSE_CHARACTER_SEPARATOR -> {
                            if (previous == Constants.DOT || previous == Constants.DASH) {
                                Thread.sleep(
                                    Constants.CHARACTER_SEPERATOR_TIME_INTERVAL.toLong() -
                                            Constants.CHAR_PAUSE_TIME_INTERVAL.toLong()
                                )
                            } else {
                                Thread.sleep(Constants.CHARACTER_SEPERATOR_TIME_INTERVAL.toLong())
                            }
                        }
                        Constants.WORD_SEPERATOR_PLACEHOLDER -> {
                            if (previous == Constants.DOT || previous == Constants.DASH) {
                                Thread.sleep(
                                    Constants.WORD_SEPERATOR_TIME_INTERVAL.toLong() -
                                            Constants.CHAR_PAUSE_TIME_INTERVAL.toLong()
                                )
                            } else {
                                Thread.sleep(Constants.CHARACTER_SEPERATOR_TIME_INTERVAL.toLong())
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    break
                }
            } else {
                break
            }
            previous = char
        }
    }

    private fun getEncodedCharArray(): CharArray {
        var morse = Morse.encode(stringToEncode)
        morse = morse.replace(Constants.MORSE_WORD_SEPARATOR, Constants.WORD_SEPERATOR_PLACEHOLDER.toString())
        return morse.toCharArray()
    }

    abstract fun generateDot()

    abstract fun generateDash()
}