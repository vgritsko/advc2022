package day_07

import common.readDataFromFile

class Solution_1 {

    fun excute() {
        val commandLines = readDataFromFile("/home/vgritsko/IdeaProjects/advc2022/src/main/resources/data_day_7_1")
        val root = TreeNode(name = "/")
        var currentNode = root
        commandLines.forEach { commandLine ->
            val commands = commandLine.split(" ")
            if (commands[0] == "dir") {
                val childNode = TreeNode()
                childNode.name = commands[1]
                currentNode.addChild(childNode.name, childNode)
            }
            if (commands[1] == "cd") {
                try {
                    currentNode = if (commands[2] == "..") {
                        currentNode.parent!!
                    } else {
                        currentNode.childrenDirs[commands[2]]!!
                    }
                } catch (e: NullPointerException) {
                    println(commands)
                }
            }

            if (commands[0].isNumber()) {
                val file = File()
                file.name = commands[1]
                file.size = commands[0].toLong()
                currentNode.files.add(file)
            }
        }

        println(root.allChildren
            .filter { it.size <= 100_000 }
            .sumOf { it.size })

        // part2
        val usedDiskSpace = root.size
        val freeDiskSpace = 70_000_000 - usedDiskSpace
        val needDiskSpace = 30_000_000 - freeDiskSpace

        println(root.allChildren
            .filter { it.size >= needDiskSpace }
            .minBy { it.size }
        )

    }
}

fun String.isNumber(): Boolean {
    return this.toLongOrNull() != null
}

class TreeNode(var name: String = "", var parent: TreeNode? = null) {
    var childrenDirs: MutableMap<String, TreeNode> = mutableMapOf()
    var files: MutableList<File> = mutableListOf()
    var size: Long = 0
        get() {
            val size = files.fold(0L) { acc, file -> acc + file.size }
            if (childrenDirs.isEmpty()) {
                return size
            }
            return childrenDirs.values.fold(size) { acc, treeNode -> acc + treeNode.size }
        }

    val allChildren: List<TreeNode>
        get() = childrenDirs.values + childrenDirs.flatMap { it.value.allChildren }

    fun addChild(name: String, node: TreeNode) {
        childrenDirs[name] = node
        node.parent = this
    }

    override fun toString(): String {
        var s = "${name}"
        if (!childrenDirs.isEmpty()) {
            s += " {" + childrenDirs.map { it.toString() } + " }"
        }
        s += " size = $size"
        return s
    }
}

data class File(var name: String = "", var size: Long = 0)