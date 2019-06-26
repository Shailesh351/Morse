package me.shellbell.morse

/**
 * Created by Shailesh351 on 24/6/19.
 */

interface Player {

    fun play(string: String)

    fun play(char: Char)

    fun isPlaying(): Boolean

    fun stop()
}