import serie2.problema.FileUtils
import serie2.problema.Point
import kotlin.test.*

class FileReaderTest {

    @Test
    fun testReadPoints() {
        val pointMap = FileUtils.readPoints("src/main/resources/data/Test1.co")

        val expectedPoints = setOf(
            Point("1", 1, 2),
            Point("2", 2, 3),
            Point("3", 4, 5)
        )

        assertEquals(expectedPoints, pointMap.values.toSet())
    }
}
