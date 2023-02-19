package day_03

import common.readDataFromFile

class Solution_2 {

    private fun Char.toPriority(): Int {
        return if (this.code.toByte() >= 97) {
            this.code.toByte() - 96
        } else {
            this.code.toByte() - 38
        }
    }

    fun execute() {
        val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_3_2"
        val listOfItems = readDataFromFile(fileName)
        val res = listOfItems
            .chunked(3)
            .map { Triple(it[0], it[1], it[2]) }
            .sumOf { it ->
                var commonItem = ' '
                val (first, second, third) = it
                first.forEach {
                    if (second.contains(it) && third.contains(it)) {
                        commonItem = it
                    }
                }
                commonItem.toPriority()
            }
        println("score = $res")

    }
}