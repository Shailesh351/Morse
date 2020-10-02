package dev.shellbell.morselib

/**
 * Created by Shailesh351 on 22/6/19.
 */

class MorseMapper {

    private val charToMorseMap: HashMap<Char, String> = HashMap()
    private val morseToCharMap: HashMap<String, Char> = HashMap()
    private val morseList: ArrayList<MorseCode> = arrayListOf()

    init {
        createMap()
    }

    fun getCharToMorseMap(): HashMap<Char, String> {
        return charToMorseMap
    }

    fun getMorseToCharMap(): HashMap<String, Char> {
        return morseToCharMap
    }

    fun getMorseCodeList(): ArrayList<MorseCode> {
        return morseList
    }

    private fun createMap() {
        charToMorseMap['a'] = ".-"
        charToMorseMap['b'] = "-..."
        charToMorseMap['c'] = "-.-."
        charToMorseMap['d'] = "-.."
        charToMorseMap['e'] = "."
        charToMorseMap['f'] = "..-."
        charToMorseMap['g'] = "--."
        charToMorseMap['h'] = "...."
        charToMorseMap['i'] = ".."
        charToMorseMap['j'] = ".---"
        charToMorseMap['k'] = "-.-"
        charToMorseMap['l'] = ".-.."
        charToMorseMap['m'] = "--"
        charToMorseMap['n'] = "-."
        charToMorseMap['o'] = "---"
        charToMorseMap['p'] = ".--."
        charToMorseMap['q'] = "--.-"
        charToMorseMap['r'] = ".-."
        charToMorseMap['s'] = "..."
        charToMorseMap['t'] = "-"
        charToMorseMap['u'] = "..-"
        charToMorseMap['v'] = "...-"
        charToMorseMap['w'] = ".--"
        charToMorseMap['x'] = "-..-"
        charToMorseMap['y'] = "-.--"
        charToMorseMap['z'] = "--.."
        charToMorseMap['1'] = ".----"
        charToMorseMap['2'] = "..---"
        charToMorseMap['3'] = "...--"
        charToMorseMap['4'] = "....-"
        charToMorseMap['5'] = "....."
        charToMorseMap['6'] = "-...."
        charToMorseMap['7'] = "--..."
        charToMorseMap['8'] = "---.."
        charToMorseMap['9'] = "----."
        charToMorseMap['0'] = "-----"

        for ((key, value) in charToMorseMap) {
            morseToCharMap[value] = key
            morseList.add(MorseCode(key, value))
        }

        val list: ArrayList<MorseCode> = arrayListOf()
        morseList.filter { it.char.isLetter() }.sortedBy { it.char }.toCollection(list)
        morseList.filter { it.char.isDigit() }.sortedBy { it.char }.toCollection(list)
        morseList.clear()
        morseList.addAll(list)
    }
}