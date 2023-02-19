package day_04

import common.readDataFromFile

class Solution_1 {

    private fun isOverlapped(range1: IntRange, range2: IntRange): Boolean {
        val res1 =  range1.first <= range2.first && range1.last >= range2.last
        val res2 =  range2.first <= range1.first && range2.last >= range1.last
        return res1 || res2
    }

    private fun String.toIntRange(): IntRange {
        val (start, end) = this.split("-")
        return start.toInt()..end.toInt()
    }

    fun execute() {
        val inputData = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_4_1")
        var counter = 0
        inputData.forEach {
            val (firstRange, secondRange) = it.split(",")
            val isOverlapped = isOverlapped(firstRange.toIntRange(), secondRange.toIntRange())
            if (isOverlapped) counter++

        }
        println(counter)
    }
}