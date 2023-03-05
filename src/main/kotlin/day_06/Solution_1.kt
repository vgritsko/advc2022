package day_06

import common.readDataFromFile
import common.readDataFromFileString

class Solution_1 {

    fun CharSequence.allCharsUnique(): Boolean = all(hashSetOf<Char>()::add)

    fun excute() {
        val dataStream = readDataFromFileString("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_6_1")
        var i = 0
        dataStream.windowed(size = 14) {
            if (it.allCharsUnique()) {
                println("place=${i + it.length}")
            }
            i += 1
        }
    }
}