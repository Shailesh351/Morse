package dev.shellbell.morselib

import org.junit.Assert
import org.junit.Test

/**
 * Created by Shailesh351 on 22/6/19.
 */

class MorseTest {

    @Test
    fun char_encoding_correct() {
        val morse = Morse.encode('s')
        Assert.assertEquals("...", morse)
    }

    @Test
    fun single_word_encoding_correct() {
        val morse = Morse.encode("shell")
        Assert.assertEquals("... .... . .-.. .-..", morse)
    }

    @Test
    fun multiple_word_encoding_correct() {
        val morse = Morse.encode("shell bell")
        Assert.assertEquals("... .... . .-.. .-..   -... . .-.. .-..", morse)
    }

    @Test
    fun single_word_decoding_correct() {
        val word = Morse.decode("... .... . .-.. .-..")
        Assert.assertEquals("shell", word)
    }

    @Test
    fun multiple_word_decoding_correct() {
        val word = Morse.decode("... .... . .-.. .-..   -... . .-.. .-..")
        Assert.assertEquals("shell bell", word)
    }
}