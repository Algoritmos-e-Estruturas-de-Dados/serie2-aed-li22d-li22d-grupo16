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
 *   - 1 = file1 only (binary 01)
 *   - 2 = file2 only (binary 10)
 *   - 3 = present in both files (binary 11 → 1 | 2)
 */
class Implementation2 {
    private var pointMap = HashMapCustom<Point, Int>()

    /**
     * Loads points from two .co files and marks their origin using bit flags.
     * - If a point appears only in file1 → value = 1 (bit 0)
     * - If a point appears only in file2 → value = 2 (bit 1)
     * - If a point appears in both files → value = 3 (1 | 2 = 3)
     *
     * This structure allows efficient set operations later on.
     *
     * @param file1 Path to the first .co file
     * @param file2 Path to the second .co file
     */
    fun loadDocuments2(file1: String, file2: String) {
        pointMap = HashMapCustom()

        val points1 = PointUtils2.readPointsFromFile(file1)
        val points2 = PointUtils2.readPointsFromFile(file2)

        // Process points from file1
        for (p in points1) {
            val previous = pointMap[p]
            // If the point is new, set value to 1 (binary 01)
            // If already exists, keep existing bits and set bit 0 using OR with 1
            pointMap.put(p, if (previous == null) 1 else previous or 1)
        }

        // Process points from file2
        for (p in points2) {
            val previous = pointMap[p]
            // If the point is new, set value to 2 (binary 10)
            // If already exists, keep existing bits and set bit 1 using OR with 2
            pointMap.put(p, if (previous == null) 2 else previous or 2)
        }
    }

    /**
     * Returns the union of both point sets.
     * Includes all unique points, regardless of which file they came from.
     *
     * @return A PointList containing all unique points from both files
     */
    fun union2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            // Add every point, regardless of its origin
            result.append(entry.key)
        }
        return result
    }

    /**
     * Returns the intersection of both point sets.
     * Includes only points that appeared in both files.
     *
     * @return A PointList containing points common to both files (value == 3)
     */
    fun intersection2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            // A point is in both files if its value is 3 (binary 11)
            if (entry.value == 3) result.append(entry.key)
        }
        return result
    }

    /**
     * Returns the difference of the sets: points that exist only in file1.
     *
     * @return A PointList containing points from file1 not present in file2 (value == 1)
     */
    fun difference2(): PointList {
        val result = PointList(pointMap.size)
        for (entry in pointMap) {
            // A point is exclusive to file1 if its value is 1 (binary 01)
            if (entry.value == 1) result.append(entry.key)
        }
        return result
    }
}
