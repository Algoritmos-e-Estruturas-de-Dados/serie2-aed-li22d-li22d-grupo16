package serie2.problema

/**
 * Object responsible for managing and performing set operations
 * (union, intersection, and difference) between two collections of 2D points.
 */
object Implementation1 {

    // Maps storing the points from each input file, indexed by point ID
    private var pointsMap1: Map<String, Point> = emptyMap()
    private var pointsMap2: Map<String, Point> = emptyMap()

    /**
     * Loads two point collection files from the resources/data directory.
     *
     * @param file1 the name of the first file (e.g., "Test1.co").
     * @param file2 the name of the second file (e.g., "Test2.co").
     */
    fun loadDocuments1(file1: String, file2: String) {

        // Read both files and store the results in the maps
        pointsMap1 = FileUtils.readPoints(file1)
        pointsMap2 = FileUtils.readPoints(file2)
    }

    /**
     * Returns the union of the points from both documents.
     * Points are included without duplication.
     *
     * @return a set of points that appear in at least one document.
     */
    fun union1(): Set<Point> {
        // Merge values from both maps and convert to set to remove duplicates
        return (pointsMap1.values + pointsMap2.values).toSet()
    }

    /**
     * Returns the intersection of the points from both documents.
     * Only points that exist in both documents will be included.
     *
     * @return a set of common points.
     */
    fun intersection1(): Set<Point> {
        // Keep only points that exist in both collections
        return pointsMap1.values.intersect(pointsMap2.values.toSet()).toSet()
    }

    /**
     * Returns the difference between the points of the first and second document.
     * Only points that are in the first document but not in the second are included.
     *
     * @return a set of unique points from the first document.
     */
    fun difference1(): Set<Point> {
        // Subtract points from map2 from the ones in map1
        return pointsMap1.values.minus(pointsMap2.values.toSet()).toSet()
    }
}
