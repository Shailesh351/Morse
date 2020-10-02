package dev.shellbell.morselib

/**
 * Created by Shailesh351 on 22/6/19.
 */

class MorseEncoder {

    private var charToMorseMap: HashMap<Char, String> = MorseMapper().getCharToMorseMap()

    fun encode(char: Char): String {
        return charToMorseMap[char] ?: Constants.INVALID
    }

    fun encode(string: String): String {
        val str = string.toLowerCase()
        val words = str.split(" ")
        val builder = StringBuilder()

        for ((i, word) in words.withIndex()) {
            val chars = word.toCharArray()

            for ((j, char) in chars.withIndex()) {
                val morse = charToMorseMap[char]
                builder.append(morse)

                if (j != chars.size - 1) {
                    builder.append(Constants.MORSE_CHARACTER_SEPARATOR)
                }
            }

            if (i != words.size - 1) {
                builder.append(Constants.MORSE_WORD_SEPARATOR)
            }
        }

        return builder.toString().replace("null", "")
    }
}