package serie2.problema

import kotlin.test.*

class FileReaderTest {

    @Test
    fun testReadPoints() {
        val pointMap = PointList.readFromFile("Test1.co")

        val expectedPoints = setOf(
            Point("1", 1, 2),
            Point("2", 2, 3),
            Point("3", 4, 5)
        )

        assertEquals(expectedPoints, pointMap.points.toSet())
    }
}
