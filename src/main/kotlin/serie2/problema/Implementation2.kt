package serie2.problema

import serie2.part4.HashMapCustom
import serie2.problema.point.Point
import serie2.problema.point.PointList
import serie2.problema.point.PointUtils2

/**
 * Implementation2 performs set operations on 2D points using a custom HashMap implementation.
 * It combines points from both input files into a single map to efficiently determine
 * union, intersection, and difference results.
 *
 * - Uses HashMapCustom<Point, Int> where the key is the point and value is the file origin:
 *   - 1 = file1 only
 *   - 2 = file2 only
 *   - 3 = present in both files
 */
class Implementation2 {
    private var pointMap = HashMapCustom<Point, Int>()

    /**
     * Loads points from two .co files and marks their origin using bits.
     * - If a point appears in both files, its value will be 3 (1 | 2).
     *
     * @param file1 Path to the first .co file
     * @param file2 Path to the second .co file
     */
    fun loadDocuments2(file1: String, file2: String) {
        pointMap = HashMapCustom()

        val points1 = PointUtils2.readPointsFromFile(file1)
        val points2 = PointUtils2.readPointsFromFile(file2)

        // Mark points from file 1
        for (p in points1) {
            val previous = pointMap[p]
            pointMap.put(p, if (previous == null) 1 else previous or 1)
        }

        // Mark points from file 2
        for (p in points2) {
            val previous = pointMap[p]
            pointMap.put(p, if (previous == null) 2 else previous or 2)
        }
    }

    /**
     * Returns the union of both point sets (all unique points).
     *
     * @return A PointList containing all unique points from both files
     */
    fun union2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            result.append(entry.key)
        }
        return result
    }

    /**
     * Returns the intersection (points present in both files).
     *
     * @return A PointList containing points common to both files
     */
    fun intersection2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            if (entry.value == 3) result.append(entry.key)
        }
        return result
    }

    /**
     * Returns the difference (points in file 1 not in file 2).
     *
     * @return A PointList containing points only from file1
     */
    fun difference2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            if (entry.value == 1) result.append(entry.key)
        }
        return result
    }
}
