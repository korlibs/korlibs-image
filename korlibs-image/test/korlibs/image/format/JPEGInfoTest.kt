package korlibs.image.format

import korlibs.image.*
import korlibs.io.async.suspendTest
import korlibs.io.file.std.resourcesVfs
import korlibs.math.geom.*
import kotlinx.coroutines.test.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JPEGInfoTest {
    @Test
    fun test() = doTest {
        val header = resourcesVfs["Portrait_3.jpg"].readImageInfo(JPEGInfo)
        assertNotNull(header)
        assertEquals(Size(1800, 1200), header.size)
        assertEquals(ImageOrientation.ROTATE_180, header.orientation)
    }

    @Test
    fun test2() = doTest {
        val header = resourcesVfs["exif1.jpeg"].readImageInfo(JPEGInfo)
        assertNotNull(header)
        assertEquals(Size(3024, 4032), header.size)
        assertEquals(ImageOrientation.ROTATE_180, header.orientation)
    }
}
