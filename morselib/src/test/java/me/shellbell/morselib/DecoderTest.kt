package dev.shellbell.morselib

import org.junit.Assert
import org.junit.Test

/**
 * Created by Shailesh351 on 22/6/19.
 */

class DecoderTest {

    @Test
    fun single_word_decoding_correct() {
        val word = MorseDecoder().decode("... .... . .-.. .-..")
        Assert.assertEquals("shell", word)
    }

    @Test
    fun multiple_word_decoding_correct() {
        val word = MorseDecoder().decode("... .... . .-.. .-..   -... . .-.. .-..")
        Assert.assertEquals("shell bell", word)
    }
}