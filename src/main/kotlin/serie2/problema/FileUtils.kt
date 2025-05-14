package serie2.problema

import java.io.File
import java.io.FileWriter

/**
 * Utility object that provides file operations for reading and writing collections of 2D points.
 */
object FileUtils {

    /**
     * Reads a set of 2D points from the given `.co` file and returns them as a map.
     *
     * Each line in the file must follow the format: "v <id> <x> <y>".
     *
     * @param filePath the path to the input file.
     * @return a map of points indexed by their identifier.
     */
    fun readPoints(filePath: String): Map<String, Point> {
        val map = mutableMapOf<String, Point>()
        val inputPath = "src/main/resources/data/$filePath"

        // Read each line from the file and parse points
        File(inputPath).forEachLine { line ->
            val parts = line.trim().split(" ")

            // Validate format and extract point values
            if (parts.isNotEmpty() && parts[0] == "v" && parts.size == 4) {
                val id = parts[1]
                val x = parts[2].toInt()
                val y = parts[3].toInt()
                map[id] = Point(id, x, y)
            }
        }

        return map
    }

    /**
     * Writes a set of 2D points to a file in the "resources/outputs" directory.
     *
     * Each point is written in the format: "v <id> <x> <y>".
     *
     * @param outputFile the name of the output file (e.g., "result.co").
     * @param points the set of points to write.
     */
    fun writePointsImp1(outputFile: String, points: Set<Point>) {
        // Build the full output path under resources/outputs
        val outputPath = "src/main/resources/outputs/$outputFile"

        // Create the file if it does not exist
        File(outputPath).apply {
            createNewFile()
        }

        // Write all points to the file using the defined format
        FileWriter(outputPath).use { writer ->
            points.forEach { point ->
                writer.write("v ${point.id} ${point.x} ${point.y}\n")
            }
        }
    }

    /**
     * Writes a list of 2D points to a file in the "resources/outputs" directory.
     *
     * Each point is written in the format: "v <id> <x> <y>".
     *
     * @param outputFile the name of the output file (e.g., "result.co").
     * @param points the list of points to write.
     */
    fun writePointsImp2(outputFile: String, points: List<Point>) {
        // Build the full output path under resources/outputs
        val outputPath = "src/main/resources/outputs/$outputFile"

        // Create the file if it does not exist
        File(outputPath).apply {
            createNewFile()
        }

        // Write all points to the file using the defined format
        FileWriter(outputPath).use { writer ->
            for (point in points) {
                writer.write("v ${point.id} ${point.x} ${point.y}\n")
            }
        }
    }

}
