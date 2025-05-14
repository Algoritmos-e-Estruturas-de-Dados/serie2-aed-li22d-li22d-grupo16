package serie2.problema

import serie2.part4.*

/**
 * Provides operations over two point maps such as union, intersection and difference.
 * Uses a custom map implementation (HashMapCustom) and avoids Kotlin standard collections.
 */
object Implementation2 {

    // Map to store points from the first file
    private var pointsMap1: CustomMutableMap<String, Point> = HashMapCustom()

    // Map to store points from the second file
    private var pointsMap2: CustomMutableMap<String, Point> = HashMapCustom()

    /**
     * Loads two documents containing point data and stores them in separate custom maps.
     *
     * @param file1 Path to the first input file
     * @param file2 Path to the second input file
     */
    fun loadDocuments2(file1: String, file2: String) {
        val tmp1 = FileUtils.readPoints(file1)
        val tmp2 = FileUtils.readPoints(file2)

        // Clear maps before loading new data
        pointsMap1.clear()
        pointsMap2.clear()

        // Copy entries from temporary maps to internal maps
        for ((k, v) in tmp1.entries) {
            pointsMap1.put(k, v)
        }
        for ((k, v) in tmp2.entries) {
            pointsMap2.put(k, v)
        }
    }

    /**
     * Computes the union of the two point maps.
     * All unique entries from both maps are included.
     *
     * @return A list of unique points contained in either of the maps
     */
    fun union2(): List<Point> {
        val result = HashMapCustom<String, Point>()

        // Add all entries from map1
        for (entry in pointsMap1) {
            result.put(entry.key, entry.value)
        }

        // Add entries from map2 only if not already in the result
        for (entry in pointsMap2) {
            if (!result.containsKey(entry.key)) {
                result.put(entry.key, entry.value)
            }
        }

        return toPointList(result)
    }

    /**
     * Computes the intersection of the two point maps.
     * Only points with keys present in both maps are included.
     *
     * @return A list of points common to both maps
     */
    fun intersection2(): List<Point> {
        val result = HashMapCustom<String, Point>()

        // Add entries that exist in both maps
        for (entry in pointsMap1) {
            if (pointsMap2.containsKey(entry.key)) {
                result.put(entry.key, entry.value)
            }
        }

        return toPointList(result)
    }

    /**
     * Computes the difference between the two point maps.
     * Only points from map1 whose keys are not in map2 are included.
     *
     * @return A list of points present in map1 but not in map2
     */
    fun difference2(): List<Point> {
        val result = HashMapCustom<String, Point>()

        // Add entries from map1 that do not exist in map2
        for (entry in pointsMap1) {
            if (!pointsMap2.containsKey(entry.key)) {
                result.put(entry.key, entry.value)
            }
        }

        return toPointList(result)
    }

    /**
     * Converts a custom map of points into a Kotlin list.
     * This function uses Kotlin collections, but can be replaced with a custom list if needed.
     *
     * @param map The custom map containing points
     * @return A list of point values from the map
     */
    private fun toPointList(map: HashMapCustom<String, Point>): List<Point> {
        // Convert the custom map into a standard list of points
        val result = mutableListOf<Point>()
        for (entry in map) {
            result.add(entry.value)
        }
        return result
    }
}
