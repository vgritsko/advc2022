package day_09

import common.readDataFromFile
import kotlin.math.absoluteValue
import kotlin.math.sign

class Solution_01 {
    fun execute () {
        val input =  readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_9_1")
        val headPath = parseInput(input)
        var head = Point()
        var tail = Point()
        val tailVisits = mutableSetOf(Point())

        headPath.forEach { direction ->
            head = head.makeStep(direction)
            if (!head.touches(tail)) {
                tail = tail.makeStepTowards(head)
            }
            tailVisits += tail
        }

        println(tailVisits.size)
    }
}

data class Point(val x: Int = 0, val y: Int = 0) {
    fun makeStep(direction: Char): Point =
        when (direction) {
            'U' -> copy(y = y + 1)
            'D' -> copy(y = y - 1)
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            else -> throw IllegalArgumentException("Wrong direction value ${direction}")
        }

    fun makeStepTowards(otherPoint: Point): Point =
        Point(
            (otherPoint.x - x).sign + x,
            (otherPoint.y - y).sign + y
        )

    fun touches(other: Point): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
}

private fun parseInput(input: List<String>): String =
    input.joinToString("") { row ->
        val direction = row.substringBefore(" ")
        val numberOfMoves = row.substringAfter(' ').toInt()
        direction.repeat(numberOfMoves)
    }