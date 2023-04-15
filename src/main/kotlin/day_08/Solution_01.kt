package day_08

import common.readDataFromFile
import kotlin.properties.Delegates

class Solution_01 {
    private var matrixHeight by Delegates.notNull<Int>()
    private var matrixWidth by Delegates.notNull<Int>()

    fun execute() {
        val input = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_8_1")
        val matrix: Array<IntArray> = parseInput(input)
        matrixHeight = matrix.size
        matrixWidth = matrix.first().size
        val solution = countTrees(matrix)
        println(solution)
    }

    private fun countTrees(matrix: Array<IntArray>): Int {
        return (1 until matrixHeight - 1).sumOf { y ->
            (1 until matrixWidth - 1).count { x ->
                matrix.isVisible(x, y)
            }
        } + (2 * matrixHeight) + (2 * matrixWidth) - 4
    }


    private fun parseInput(input: List<String>): Array<IntArray> = input.map { row ->
        row.map(Char::digitToInt).toIntArray()
    }.toTypedArray()

    private fun Array<IntArray>.viewFrom(x: Int, y: Int): List<List<Int>> =
        listOf(
            (y - 1 downTo 0).map { this[it][x] },
            (y + 1 until matrixHeight).map { this[it][x] },
            this[y].take(x).asReversed(),
            this[y].drop(x + 1)
        )

    private fun Array<IntArray>.isVisible(x: Int, y: Int): Boolean =
        viewFrom(x, y).any { direction ->
            direction.all { it < this[y][x] }
        }
}