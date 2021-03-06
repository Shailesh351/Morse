package dev.shellbell.morse.ui

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.f2prateek.rx.preferences2.Preference
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.shellbell.morse.R

/**
 * Created by Shailesh351 on 23/6/19.
 */

class CircleButton(
    private val fab: FloatingActionButton,
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    private val pref: Preference<Boolean>
) {
    var curState = CircleButtonState.ON

    var onElevation: Float = 6F
    var offElevation: Float = 2F

    var colorBackgroundOn: Int = R.color.colorButtonOn
    var colorBackgroundOff: Int = R.color.colorButtonOff

    init {
        when (pref.get()) {
            true -> setState(CircleButtonState.ON)
            false -> setState(CircleButtonState.OFF)
        }

        fab.setOnClickListener {
            toggleState()
        }
    }

    private fun toggleState() {
        when (curState) {
            CircleButtonState.ON -> setState(CircleButtonState.OFF)
            CircleButtonState.OFF -> setState(CircleButtonState.ON)
        }
    }

    private fun setState(newState: CircleButtonState) {
        if (newState == curState)
            return

        curState = newState

        when (newState) {
            CircleButtonState.ON -> {
                //changeElevation(onElevation)
                changeBackground(colorBackgroundOn)
                changeIcon(iconOn)
                pref.set(true)
            }
            CircleButtonState.OFF -> {
                //changeElevation(offElevation)
                changeBackground(colorBackgroundOff)
                changeIcon(iconOff)
                pref.set(false)
            }
        }
    }

    private fun changeElevation(elevation: Float) {
        fab.compatElevation = elevation * fab.context.resources.displayMetrics.density
    }

    private fun changeBackground(color: Int) {
        fab.backgroundTintList = ContextCompat.getColorStateList(fab.context, color)
    }

    private fun changeIcon(icon: Int) {
        fab.setImageDrawable(ContextCompat.getDrawable(fab.context, icon))
    }
}

enum class CircleButtonState {
    ON, OFF
}

