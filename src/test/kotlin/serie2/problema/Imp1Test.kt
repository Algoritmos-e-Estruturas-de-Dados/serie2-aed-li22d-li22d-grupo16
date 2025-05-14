package serie2.problema

import kotlin.test.*

class Implementation1Test {

    @Test
    fun testUnion1Operation() {
        val file1 = "Test1.co"
        val file2 = "Test2.co"

        Implementation1.loadDocuments1(file1, file2)

        val expectedUnion = setOf(
            Point("1", 1, 2),
            Point("2", 2, 3),
            Point("3", 4, 5),
            Point("4", 5, 6),
            Point("5", 7, 8)
        )

        val unionResult = Implementation1.union1()
        assertEquals(expectedUnion, unionResult)
    }

    @Test
    fun testIntersection1Operation() {
        val file1 = "Test1.co"
        val file2 = "Test2.co"

        Implementation1.loadDocuments1(file1, file2)

        val expectedIntersection = setOf(
            Point("2", 2, 3)
        )

        val intersectionResult = Implementation1.intersection1()
        assertEquals(expectedIntersection, intersectionResult)
    }

    @Test
    fun testDifference1Operation() {
        val file1 = "Test1.co"
        val file2 = "Test2.co"

        Implementation1.loadDocuments1(file1, file2)

        val expectedDifference = setOf(
            Point("1", 1, 2),
            Point("3", 4, 5)
        )

        val differenceResult = Implementation1.difference1()
        assertEquals(expectedDifference, differenceResult)
    }
}
