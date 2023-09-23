package day_12

import common.readDataFromFile
import java.util.*


class Solution_01 {
    private lateinit var map: HeightMap
    fun execute() {
        val input = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_12_1")
        map = parseInput(input)
        val res = map.shortestPath(
            start = map.start!!,
            isGoal = { it == map.finish },
            canMove = { from: Int, to: Int -> to - from <= 1 }
        )
        println(res)

        val res2 = map.shortestPath(
            start = map.finish!!,
            isGoal = { map.elevations[it] == 0 },
            canMove = { from: Int, to: Int -> from - to <= 1 }
        )
        println(res2)
    }
}

data class Zone(val x: Int, val y: Int) {
    fun adjacentZones(): Set<Zone> = setOf(
        copy(x = x - 1),
        copy(x = x + 1),
        copy(y = y - 1),
        copy(y = y + 1)
    )
}

class HeightMap(val elevations: Map<Zone, Int>, val start: Zone?, val finish: Zone) {
    fun shortestPath(
        start: Zone,
        isGoal: (Zone) -> Boolean,
        canMove: (Int, Int) -> Boolean
    ): Int {
        val visited = mutableSetOf<Zone>()
        val queue = PriorityQueue<PathCost>().apply { add(PathCost(start, 0)) }

        while (queue.isNotEmpty()) {
            val nextZone = queue.poll()

            if (nextZone.zone !in visited) {
                visited += nextZone.zone
                val neighbors = nextZone.zone.adjacentZones()
                    .filter { it in elevations }
                    .filter { canMove(elevations.getValue(nextZone.zone), elevations.getValue(it)) }

                if (neighbors.any { isGoal(it) }) {
                    return nextZone.cost + 1
                }
                queue.addAll(neighbors.map { PathCost(it, nextZone.cost + 1) })
            }
        }
        throw IllegalStateException("No path from $start to $finish")
    }
}

private fun parseInput(input: List<String>): HeightMap {
    var start: Zone? = null
    var finish: Zone? = null
    val elevations = input.flatMapIndexed { y, row ->
        row.mapIndexed { x, char ->
            val here = Zone(x, y)
            here to when (char) {
                'S' -> 0.also { start = here }
                'E' -> 25.also { finish = here }
                else -> char - 'a'
            }
        }
    }.toMap()
    return HeightMap(elevations, start!!, finish!!)
}

private data class PathCost(val zone: Zone, val cost: Int) : Comparable<PathCost> {
    override fun compareTo(other: PathCost): Int =
        this.cost.compareTo(other.cost)
}

