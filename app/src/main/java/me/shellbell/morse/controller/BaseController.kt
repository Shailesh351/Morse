package me.shellbell.morse.controller

import com.f2prateek.rx.preferences2.Preference
import me.shellbell.morse.helper.Helper
import me.shellbell.morselib.Constants
import me.shellbell.morselib.Morse

/**
 * Created by Shailesh351 on 23/6/19.
 */

abstract class BaseController(pref: Preference<Boolean>) : Runnable {

    private var thread: Thread? = null
    private var stringToEncode = ""

    private var isOn = true

    init {
        val disposable = pref.asObservable().subscribe {
            isOn = it
        }
    }

    open fun play(string: String) {
        stringToEncode = string
        stop()
        start()
    }

    private fun start() {
        thread = Thread(this)
        thread!!.start()
    }

    fun isPlaying(): Boolean {
        if (thread == null || thread!!.isInterrupted)
            return false
        return true
    }

    fun stop() {
        thread?.let {
            if (!thread!!.isInterrupted)
                thread!!.interrupt()
        }
    }

    override fun run() {
        val chars = getEncodedCharArray()
        var previous = ' '

        Thread.sleep(Helper.NEW_PLAY_DELAY)

        for (char in chars) {
            if (!thread!!.isInterrupted) {
                try {
                    when (char) {
                        Constants.DOT -> {
                            if (isOn) {
                                generateDot()
                            } else {
                                Thread.sleep(Constants.DOT_TIME_INTERVAL.toLong())
                            }
                            Thread.sleep(Constants.CHAR_PAUSE_TIME_INTERVAL.toLong())
                        }
                        Constants.DASH -> {
                            if (isOn) {
                                generateDash()
                            } else {
                                Thread.sleep(Constants.DASH_TIME_INTERVAL.toLong())
                            }
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