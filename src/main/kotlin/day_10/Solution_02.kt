package day_10

import common.readDataFromFile
import kotlin.math.abs

//https://adventofcode.com/2022/day/10/ part II

class Solution_02 {
    private val instructions = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_10_1")

    private val cpuSeq = sequence {
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

        println(cpuSeq
            .runningFoldIndexed(1) { index, acc, i -> acc + i }
            .forEachIndexed { index, i ->
                val newIndex = (index - 1).mod(40)
                if (newIndex == 0) {
                    print('\n')
                }
                if (abs(newIndex - i) <= 1) {
                    print('#')
                } else {
                    print('.')
                }
            }
        )
    }
}

