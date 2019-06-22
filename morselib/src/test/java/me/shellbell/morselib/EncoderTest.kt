package me.shellbell.morselib

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Shailesh351 on 22/6/19.
 */

class EncoderTest {

    @Test
    fun char_encoding_correct() {
        val morse = MorseEncoder().encode('s')
        assertEquals("...", morse)
    }

    @Test
    fun single_word_encoding_correct() {
        val morse = MorseEncoder().encode("shell")
        assertEquals("... .... . .-.. .-..", morse)
    }

    @Test
    fun multiple_word_encoding_correct() {
        val morse = MorseEncoder().encode("shell bell")
        assertEquals("... .... . .-.. .-..   -... . .-.. .-..", morse)
    }
}