package day_02

import common.readDataFromFile

private const val LOST = 0
private const val DRAW = 3
private const val WON = 6

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSOR(3)
}

fun String.toShape(): Shape {
    return when (this) {
        "A", "X" -> Shape.ROCK
        "B", "Y" -> Shape.PAPER
        "C", "Z" -> Shape.SCISSOR
        else -> {
            throw Exception("Undefined shape!")
        }
    }
}

fun gameScore(elfStrategy: String, myStrategy: String): Int {
    return when (elfStrategy.toShape() to myStrategy.toShape()) {
        Pair(Shape.ROCK, Shape.ROCK), Pair(Shape.PAPER, Shape.PAPER), Pair(Shape.SCISSOR, Shape.SCISSOR) -> DRAW
        Pair(Shape.ROCK, Shape.PAPER), Pair(Shape.PAPER, Shape.SCISSOR), Pair(Shape.SCISSOR, Shape.ROCK) -> WON
        Pair(Shape.ROCK, Shape.SCISSOR), Pair(Shape.PAPER, Shape.ROCK), Pair(Shape.SCISSOR, Shape.PAPER) -> LOST
        else -> {
            throw Exception("Undefined strategy")
        }
    }
}


class Solution_1 {
    fun execute() {
        val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_2_1"
        //       val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/day_02_data"
        val listOfRounds =
            readDataFromFile(fileName)
        val score = listOfRounds.sumOf {
            val (elf, me) = it.split(" ")
            val score = gameScore(elf, me)
            score + me.toShape().score
        }
        println("score = $score")
    }
}