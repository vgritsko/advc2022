package day_05

import common.readDataFromFile
import java.util.Stack

class Solution_1 {

    fun execute() {
        init()
        val commands = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_5_1")
        commands.forEach { s ->
            var (move, from, to) = s.split(" ")
                .filter { it.all { c -> c.isDigit() } }
                .map { it.toInt() }

            while (move > 0) {
                val poppedVal = arrayOfStacks?.get(from)?.pop()
                arrayOfStacks?.get(to)?.push(poppedVal)
                move -= 1
            }
        }

        for (i in 1 until arrayOfStacks?.size!!) {
            print(arrayOfStacks?.get(i)?.last())
        }
    }

    private fun init() {
        arrayOfStacks = arrayOf(stack0, stack1, stack2, stack3,stack4,stack5,stack6,stack7,stack8,stack9)
        stack0.add("")
        stack1.addAll(listOf("S","M","R","N","W","J","V","T"))
        stack2.addAll(listOf("B","W","D","J","Q","P","C","V"))
        stack3.addAll(listOf("B","J","F","H","D","R","P"))
        stack4.addAll(listOf("F","R","P","B","M","N","D"))
        stack5.addAll(listOf("H","V","R","P","T","B"))
        stack6.addAll(listOf("C","B","P","T"))
        stack7.addAll(listOf("B","J","R","P","L"))
        stack8.addAll(listOf("N","C","S","L","T","Z","B","W"))
        stack9.addAll(listOf("L","S","G"))
    }

    private var arrayOfStacks: Array<Stack<String>>? = null
    private val stack0 = Stack<String>()
    private val stack1 = Stack<String>()
    private val stack2 = Stack<String>()
    private val stack3 = Stack<String>()
    private val stack4 = Stack<String>()
    private val stack5 = Stack<String>()
    private val stack6 = Stack<String>()
    private val stack7 = Stack<String>()
    private val stack8 = Stack<String>()
    private val stack9 = Stack<String>()
}