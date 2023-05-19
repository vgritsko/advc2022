package day_09

import common.readDataFromFile
import kotlin.math.absoluteValue
import kotlin.math.sign

class Solution_02 {
    fun execute () {
        val input =  readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_9_1")
        val headPath = parseInput(input)
        val tailVisits = mutableSetOf(Point())
        val rope = Array(10){Point()}

        headPath.forEach { direction ->
           rope[0] = rope[0].makeStep(direction)
            rope.indices.windowed(2,1) {(head,tail)->
                if (!rope[head].touches(rope[tail])) {
                    rope[tail] = rope[tail].makeStepTowards(rope[head])
                }

            }
            tailVisits += rope.last()
        }

        println(tailVisits.size)
    }
}