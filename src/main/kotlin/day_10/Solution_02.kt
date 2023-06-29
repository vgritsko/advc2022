package day_10

import common.readDataFromFile

//https://adventofcode.com/2022/day/10/

class Solution_02 {
    val instructions = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_10_1")

    val seq = sequence {
        yield(0)
        instructions.forEach {
            if (it.startsWith("addx")) {
                yieldAll(listOf(0, it.split(" ").last().toInt()))
            } else {
                yield(0)
            }
        }
    }

    fun execute() {
        println(
            seq
                .runningFoldIndexed(1) { _, acc, i ->
                    acc + i
                }
                .mapIndexed { index, i -> index * i }
                .filterIndexed { index, _ -> index.mod(40) == 20 }
                .sum()
        )
    }
}