package day_11

import java.util.*

//https://adventofcode.com/2022/day/11

class Solution_01 {
    fun execute() {
        var round = 1
        while (round <= 1000) {
            println("Round  $round")
            testArray_1.forEach {
                inspectItems(it, testArray_1)
            }

            round++
        }
        testArray_1.forEach {
            it.printArray()
            it.printInspectionCounter()
        }
    }
}

class Monkey(
    private val TAG: Int,
    private val itemsWorryLevel: Queue<Long>,
    private val operationParam: Long,
    private val divParam: Long,
    private val monkeyNumberTrue: Int,
    private val monkeyNumberFalse: Int,
    private val operation: (Long, Long) -> Long
) {
    private var inspectionCounter = 0L

    init {
        println("Init block $itemsWorryLevel")
    }

    fun printArray() {
        println("$TAG $itemsWorryLevel")
    }

    fun printInspectionCounter() {
        println("inspection counter = $inspectionCounter")
    }

    fun catch(item: Long) {
        itemsWorryLevel.add(item)
    }

    private fun inspect(): Pair<Long?, Int> {
        val item = itemsWorryLevel.poll()
        if (item != null) {
            inspectionCounter++
            val newItem:Long = operation.invoke(item, operationParam)
            val newItemAfterDiv = newItem
            println("Monkey$TAG itemWorryLevel =$item afterOperation $newItem afterDivision $newItemAfterDiv  items = $itemsWorryLevel")
            if (newItemAfterDiv % divParam == 0L) {
                return Pair(newItemAfterDiv, monkeyNumberTrue)
            } else {
                return Pair(newItemAfterDiv, monkeyNumberFalse)
            }
        }
        return Pair(null, -1)
    }

    fun takeTurn(): Collection<Pair<Long?, Int>> {
        val values = mutableListOf<Pair<Long?, Int>>()
        val iterator = itemsWorryLevel.iterator()
        while (iterator.hasNext()) {
            val result = inspect()
            println("Monkey$TAG Item with worry level ${result.first} is thrown to monkey ${result.second}")
            println()
            values.add(result)
        }
        return values
    }
}

fun inspectItems(monkey: Monkey, monkeys: Array<Monkey>) {
    monkey.takeTurn().forEach {
        if (it.first != null) {
            monkeys[it.second].catch(it.first!!)
        }
    }

}

val testArray_1 = arrayOf(
    Monkey(0, LinkedList(mutableListOf(79L, 98L)),
        operationParam = 19L,
        divParam = 23L,
        monkeyNumberTrue = 2,
        monkeyNumberFalse = 3,
        operation = { oldVal: Long, param: Long -> oldVal * param }),

    Monkey(1, LinkedList(mutableListOf(54L, 65L, 75L, 74L)),
        operationParam = 6L,
        divParam = 19L,
        monkeyNumberTrue = 2,
        monkeyNumberFalse = 0,
        operation = { oldVal: Long, param: Long -> oldVal + param }),

    Monkey(2, LinkedList(mutableListOf(79L, 60L, 97L)),
        operationParam = 1L,
        divParam = 13L,
        monkeyNumberTrue = 1,
        monkeyNumberFalse = 3,
        operation = { oldVal: Long, _: Long -> oldVal * oldVal }),

    Monkey(3, LinkedList(mutableListOf(74L)),
        operationParam = 3L,
        divParam = 17L,
        monkeyNumberTrue = 0,
        monkeyNumberFalse = 1,
        operation = { oldVal: Long, param: Long -> oldVal + param })
)


val testArray = arrayOf(
    Monkey(0, LinkedList(mutableListOf(59, 65, 86, 56, 74, 57, 56)),
        operationParam = 17,
        divParam = 3,
        monkeyNumberTrue = 3,
        monkeyNumberFalse = 6,
        operation = { oldVal: Long, param: Long -> oldVal * param }),

    Monkey(1, LinkedList(mutableListOf(63, 83, 50, 63, 56)),
        operationParam = 2,
        divParam = 13,
        monkeyNumberTrue = 3,
        monkeyNumberFalse = 0,
        operation = { oldVal: Long, param: Long -> oldVal + param }),

    Monkey(2, LinkedList(mutableListOf(93, 79, 74, 55)),
        operationParam = 1,
        divParam = 2,
        monkeyNumberTrue = 0,
        monkeyNumberFalse = 1,
        operation = { oldVal: Long, param: Long -> oldVal + param }),

    Monkey(3, LinkedList(mutableListOf(86, 61, 67, 88, 94, 69, 56, 91)),
        operationParam = 7,
        divParam = 11,
        monkeyNumberTrue = 6,
        monkeyNumberFalse = 7,
        operation = { oldVal: Long, param: Long -> oldVal + param }),

    Monkey(4, LinkedList(mutableListOf(76, 50, 51)),
        operationParam = -1,
        divParam = 19,
        monkeyNumberTrue = 2,
        monkeyNumberFalse = 5,
        operation = { oldVal: Long, _: Long -> oldVal * oldVal }),

    Monkey(5, LinkedList(mutableListOf(77, 76)),
        operationParam = 8,
        divParam = 17,
        monkeyNumberTrue = 2,
        monkeyNumberFalse = 1,
        operation = { oldVal: Long, param: Long -> oldVal + param }),

    Monkey(6, LinkedList(mutableListOf(74)),
        operationParam = 2,
        divParam = 5,
        monkeyNumberTrue = 4,
        monkeyNumberFalse = 7,
        operation = { oldVal: Long, param: Long -> oldVal * param }),

    Monkey(7, LinkedList(mutableListOf(86, 85, 52, 86, 91, 95)),
        operationParam = 6,
        divParam = 7,
        monkeyNumberTrue = 4,
        monkeyNumberFalse = 5,
        operation = { oldVal: Long, param: Long -> oldVal + param })
)

