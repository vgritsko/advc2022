package day_02

import common.readDataFromFile

private const val LOST = 0
private const val DRAW = 3
private const val WON = 6

fun String.toGameResult(): Int {
    return when (this) {
        "X" -> LOST
        "Y" -> DRAW
        "Z" -> WON
        else -> {
            throw Exception("Undefined game result")
        }
    }
}

fun getMyStrategy(elfStrategy: String, gameResult: Int): Shape {
    return when (elfStrategy.toShape() to gameResult) {
        Pair(Shape.ROCK, DRAW) -> Shape.ROCK
        Pair(Shape.PAPER, DRAW) -> Shape.PAPER
        Pair(Shape.SCISSOR, DRAW) -> Shape.SCISSOR

        Pair(Shape.ROCK, LOST) -> Shape.SCISSOR
        Pair(Shape.PAPER, LOST) -> Shape.ROCK
        Pair(Shape.SCISSOR, LOST) -> Shape.PAPER

        Pair(Shape.ROCK, WON) -> Shape.PAPER
        Pair(Shape.PAPER, WON) -> Shape.SCISSOR
        Pair(Shape.SCISSOR, WON) -> Shape.ROCK

        else -> {
            throw Exception("Undefined shape")
        }
    }
}


class Solution_2 {
    fun execute() {
       val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_2_1"
//        val fileName = "/home/vgritsko/IdeaProjects/advc2022/src/main/resources/day_02_data"
        val listOfRounds =
            readDataFromFile(fileName)
        val score = listOfRounds.sumOf {
            val (elf, result) = it.split(" ")
            val myStrategy = getMyStrategy(elf, result.toGameResult())
            myStrategy.score + result.toGameResult()
        }
        println("score = $score")
    }
}