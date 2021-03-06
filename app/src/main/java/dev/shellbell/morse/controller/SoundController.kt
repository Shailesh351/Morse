package dev.shellbell.morse.controller

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import com.f2prateek.rx.preferences2.Preference


/**
 * Created by Shailesh351 on 23/6/19.
 */

class SoundController(val context: Context, pref: Preference<Boolean>) : BaseController(pref) {

    companion object {
        private const val TAG: String = "SOUND_CONTROLLER"
    }

    private val STREAM_TYPE = AudioManager.STREAM_MUSIC
    private val VOLUME = 80

    private val TONE_TYPE_DOT = ToneGenerator.TONE_DTMF_0
    private val TONE_TYOE_DASH = ToneGenerator.TONE_DTMF_0

    private val toneGenerator = ToneGenerator(STREAM_TYPE, VOLUME)

    override fun generateDot() {
        toneGenerator.startTone(TONE_TYPE_DOT, morseTime.DOT_TIME_INTERVAL)
        Thread.sleep(morseTime.DOT_TIME_INTERVAL.toLong())
    }

    override fun generateDash() {
        toneGenerator.startTone(TONE_TYOE_DASH, morseTime.DASH_TIME_INTERVAL)
        Thread.sleep(morseTime.DASH_TIME_INTERVAL.toLong())
    }
}