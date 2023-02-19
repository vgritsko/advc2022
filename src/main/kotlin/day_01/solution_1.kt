package day_01

import common.readDataFromFileDay1

class Solution_1{
    fun execute () {
        val result = readDataFromFileDay1("/home/vgritsko/IdeaProjects/AdventOfCode2022/src/main/resources/data_day_1_1")
        val listOfValues = result.second
        listOfValues.sortDescending()
        val sum = listOfValues.subList(0,3).sum()


    }
}