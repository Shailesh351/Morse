package me.shellbell.morselib

/**
 * Created by Shailesh351 on 22/6/19.
 */

class MorseDecoder {

    private var morseToCharMap: HashMap<String, Char> = MorseMapper().getMorseToCharMap()

    fun decode(string: String): String {
        val str = string.trim()

        val words = str.split(Constants.MORSE_WORD_SEPARATOR)
        val builder = StringBuilder()

        for ((i, w) in words.withIndex()) {
            val word = w.trim()
            val dotDashStrings = word.split(Constants.MORSE_CHARACTER_SEPARATOR)

            for ((j, dotDashString) in dotDashStrings.withIndex()) {
                val c = morseToCharMap[dotDashString]

                if (c != null) {
                    builder.append(c)
                } else {
                    builder.append(Constants.INVALID)
                }
            }

            if (i != words.size - 1) {
                builder.append(" ")
            }
        }

        return builder.toString()
    }
}