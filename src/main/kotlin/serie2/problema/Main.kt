package serie2.problema

/**
 * Entry point for the ProcessPointsCollections application.
 *
 * This application allows performing set operations (union, intersection, and difference)
 * between two collections of 2D points loaded from text files with `.co` extension.
 *
 * Commands supported during runtime:
 * - implementation <1|2> : selects which implementation to use.
 * - load <file1> <file2> : loads points from two files.
 * - union <outputFile> : writes all unique points from both files into the output file.
 * - intersection <outputFile> : writes points common to both files into the output file.
 * - difference <outputFile> : writes points that exist in file1 but not in file2.
 * - exit : terminates the program.
 */
fun main() {
    println("Welcome. First, choose the implementation to use: 'implementation 1' or 'implementation 2'")

    var selectedImplementation: Int? = null

    while (true) {
        val input = readlnOrNull()?.trim() ?: continue
        val command = input.split(" ")

        when (command[0]) {
            "imp" -> {
                if (command.size < 2 || (command[1] != "1" && command[1] != "2")) {
                    println("Invalid implementation. Choose '1' or '2'.")
                    continue
                }
                selectedImplementation = command[1].toInt()
                println("Using Implementation $selectedImplementation.")
            }

            "load" -> {
                if (selectedImplementation == null) {
                    println("Please select an implementation first using: implementation <1|2>")
                    continue
                }
                val file1 = command.getOrNull(1)
                val file2 = command.getOrNull(2)
                if (file1 == null || file2 == null) {
                    println("Usage: load <file1> <file2>")
                    continue
                }

                if (selectedImplementation == 1) {
                    Implementation1.loadDocuments1(file1, file2)
                } else {
                    Implementation2.loadDocuments2(file1, file2)
                }
                println("Documents loaded.")
            }

            "union" -> {
                if (selectedImplementation == null) {
                    println("Please select an implementation first using: implementation <1|2>")
                    continue
                }
                val outputFile = command.getOrNull(1)
                if (outputFile == null) {
                    println("Usage: union <outputFile>")
                    continue
                }

                if (selectedImplementation == 1) {
                    val result = Implementation1.union1()
                    FileUtils.writePointsImp1(outputFile, result)
                } else {
                    val result = Implementation2.union2()
                    FileUtils.writePointsImp2(outputFile, result)
                }
                println("Union complete. File $outputFile generated.")
            }

            "intersection" -> {
                if (selectedImplementation == null) {
                    println("Please select an implementation first using: implementation <1|2>")
                    continue
                }
                val outputFile = command.getOrNull(1)
                if (outputFile == null) {
                    println("Usage: intersection <outputFile>")
                    continue
                }

                if (selectedImplementation == 1) {
                    val result = Implementation1.intersection1()
                    FileUtils.writePointsImp1(outputFile, result)
                } else {
                    val result = Implementation2.intersection2()
                    FileUtils.writePointsImp2(outputFile, result)
                }
                println("Intersection complete. File $outputFile generated.")
            }

            "difference" -> {
                if (selectedImplementation == null) {
                    println("Please select an implementation first using: implementation <1|2>")
                    continue
                }
                val outputFile = command.getOrNull(1)
                if (outputFile == null) {
                    println("Usage: difference <outputFile>")
                    continue
                }

                if (selectedImplementation == 1) {
                    val result = Implementation1.difference1()
                    FileUtils.writePointsImp1(outputFile, result)
                } else {
                    val result = Implementation2.difference2()
                    FileUtils.writePointsImp2(outputFile, result)
                }
                println("Difference complete. File $outputFile generated.")
            }

            "exit" -> {
                println("Exiting application.")
                break
            }

            else -> {
                println("Unrecognized command.")
            }
        }
    }
}
