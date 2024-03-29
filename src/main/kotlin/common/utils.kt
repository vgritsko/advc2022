package common




fun readDataFromFile(fileName: String): List<String> {
    val listOfStrings = mutableListOf<String>()
    java.io.File(fileName).readLines().forEach {
        listOfStrings.add(it)
    }
    return listOfStrings.toList()
}

fun readDataFromFileString(fileName: String): String {
    return java.io.File(fileName).readText()
}


fun readDataFromFileDay1(fileName: String): Pair<Int, MutableList<Int>> {
    val listOfValues = mutableListOf<Int>()
    var sum = 0
    var maxSum = 0
    java.io.File(fileName).readLines().forEach {
        if (it.isNotBlank()) {
            sum += it.toInt()
        } else {
            if (maxSum < sum) maxSum = sum
            listOfValues.add(sum)
            sum = 0
        }
    }

    return Pair(maxSum, listOfValues)
}




