package dev.shellbell.morse

import android.content.Context
import com.f2prateek.rx.preferences2.Preference
import dev.shellbell.morse.controller.FlashController
import dev.shellbell.morse.controller.SoundController
import dev.shellbell.morse.controller.VibrationController

/**
 * Created by Shailesh351 on 26/6/19.
 */

class MorsePlayer(
    context: Context,
    flashPref: Preference<Boolean>,
    soundPref: Preference<Boolean>,
    vibrationPref: Preference<Boolean>
) {
    private val flashController: FlashController by lazy { FlashController(context, flashPref) }
    private val soundController: SoundController by lazy { SoundController(context, soundPref) }
    private val vibrationController: VibrationController by lazy { VibrationController(context, vibrationPref) }

    fun play(char: Char) {
        flashController.play(char.toString())
        soundController.play(char.toString())
        vibrationController.play(char.toString())
    }

    fun play(string: String) {
        flashController.play(string)
        soundController.play(string)
        vibrationController.play(string)
    }

    fun isPlaying(): Boolean {
        return flashController.isPlaying() || soundController.isPlaying() || vibrationController.isPlaying()
    }

    fun stop() {
        flashController.stop()
        soundController.stop()
        vibrationController.stop()
    }
}