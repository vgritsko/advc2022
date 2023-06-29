import common.readDataFromFile

class Solution10 {
    private val cycles = arrayOf(20, 60, 100, 140, 180, 220)
    private val signalStrengths = mutableListOf<Int>()
    private var x: Int = 1
        set(value) {
            field += value
        }

    private var cycle = 0

    fun execute() {
        val instructions = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_10_1")
        instructions.forEach {
            val signal = it.split(" ")
            if (signal.size > 1) {
                executeInstruction(signal[0], signal[1].toInt())
            } else {
                executeInstruction(signal[0], null)
            }
        }
        println(signalStrengths.sum())
    }

    private fun executeInstruction(instruction: String, value: Int?) {
        when (instruction) {
            "addx" -> {
                cycle += 1
                calculateSignalStrength(cycle)
                cycle += 1
                calculateSignalStrength(cycle)
                x = value!!
            }

            "noop" -> {
                cycle += 1
                calculateSignalStrength(cycle)
            }

            else -> throw IllegalArgumentException()
        }
    }

    private fun calculateSignalStrength(cycle: Int) {
        if (cycle in cycles) {
            val signalStrength = (cycle * x)
            signalStrengths.add(signalStrength)
            println("signalStrength = $signalStrength  cycle = $cycle  X=$x")
        }
    }
}