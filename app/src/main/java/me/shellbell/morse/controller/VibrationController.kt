package me.shellbell.morse.controller

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.f2prateek.rx.preferences2.Preference
import me.shellbell.morselib.Constants


/**
 * Created by Shailesh351 on 23/6/19.
 */

class VibrationController(context: Context, pref: Preference<Boolean>) : BaseController(pref) {

    var vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    override fun generateDot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect =
                VibrationEffect.createOneShot(Constants.DOT_TIME_INTERVAL.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        } else {
            vibrator.vibrate(Constants.DOT_TIME_INTERVAL.toLong())
        }
        Thread.sleep(Constants.DOT_TIME_INTERVAL.toLong())
    }

    override fun generateDash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect =
                VibrationEffect.createOneShot(Constants.DASH_TIME_INTERVAL.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        } else {
            vibrator.vibrate(Constants.DASH_TIME_INTERVAL.toLong())
        }
        Thread.sleep(Constants.DASH_TIME_INTERVAL.toLong())
    }
}