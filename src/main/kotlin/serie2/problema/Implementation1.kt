package serie2.problema

import serie2.problema.point.Point
import serie2.problema.point.PointUtils

/**
 * Implementation1 performs set operations (union, intersection, difference)
 * on 2D points using a single HashMap<Point, Int> to track their origin.
 *
 * Each Point is stored as a key, and the value (Int) represents its presence:
 * - 1: Point exists only in file1
 * - 2: Point exists only in file2
 * - 3: Point exists in both files (1 | 2)
 *
 * This strategy ensures efficient detection of duplicates and supports
 * all three set operations based on bitwise flags.
 */
class Implementation1 {

    // Stores all unique points across both files, tracking their origin using flags (1, 2, or 3)
    private val allPoints = HashMap<Point, Int>()

    /**
     * Loads two point sets from the specified input files and fills the HashMap.
     * Each point is associated with a value that represents the file(s) it came from.
     *
     * @param file1 Path to the first .co file
     * @param file2 Path to the second .co file
     */
    fun loadDocuments1(file1: String, file2: String) {
        val pointsFromFile1 = PointUtils.readPointsFromFile(file1).points
        val pointsFromFile2 = PointUtils.readPointsFromFile(file2).points

        // Mark all points from file1 with flag 1
        for (p in pointsFromFile1) {
            allPoints[p] = allPoints.getOrDefault(p, 0) or 1
        }

        // Mark all points from file2 with flag 2 (or combine with existing if also in file1)
        for (p in pointsFromFile2) {
            allPoints[p] = allPoints.getOrDefault(p, 0) or 2
        }
    }

    /**
     * Returns the union of both point sets.
     * All unique points are included, regardless of source.
     *
     * @return A PointUtils containing all distinct points from both files
     */
    fun union1(): PointUtils {
        // All keys represent unique points from either file
        return PointUtils(allPoints.keys.toList())
    }

    /**
     * Returns the intersection of both point sets.
     * Only points that appear in both files are included.
     *
     * @return A PointUtils containing points found in both files
     */
    fun intersection1(): PointUtils {
        // Filter map to include only entries marked as present in both files (1 | 2 == 3)
        val common = allPoints.filterValues { it == 3 }.keys.toList()
        return PointUtils(common)
    }

    /**
     * Returns the difference between the first and second point sets.
     * Only points exclusive to the first file are included.
     *
     * @return A PointUtils containing points that exist only in file1
     */
    fun difference1(): PointUtils {
        // Filter map to include only entries with flag 1 (exclusive to file1)
        val onlyInFile1 = allPoints.filterValues { it == 1 }.keys.toList()
        return PointUtils(onlyInFile1)
    }
}
