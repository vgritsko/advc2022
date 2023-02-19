package day_03

import common.readDataFromFile
import day_02.getMyStrategy
import day_02.toGameResult

class Solution_1 {

    private fun Char.toPriority(): Int {
        return if (this.code.toByte() >= 97) {
            this.code.toByte() - 96
        } else {
            this.code.toByte() - 38
        }
    }

    fun execute() {
        val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_3_1"
        val listOfItems = readDataFromFile(fileName)

        val score = listOfItems.sumOf { it ->
            val (firstCompartment, secondCompartment) = it.chunked(it.length/2)
            var commonItem = ' '
            firstCompartment.forEach {
                if (secondCompartment.contains(it)) {
                    commonItem = it
                }
            }
            commonItem?.toPriority()
        }
        println("score = $score")

    }
}