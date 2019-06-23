package me.shellbell.morse

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by Shailesh351 on 23/6/19.
 */

class CircleButton(
    val fab: FloatingActionButton,
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int
) {
    var curState = CircleButtonState.ON

    var onElevation: Float = 6F
    var offElevation: Float = 2F

    var colorBackgroundOn: Int = R.color.colorButtonOn
    var colorBackgroundOff: Int = R.color.colorButtonOff

    init {
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
            }
            CircleButtonState.OFF -> {
                //changeElevation(offElevation)
                changeBackground(colorBackgroundOff)
                changeIcon(iconOff)
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

