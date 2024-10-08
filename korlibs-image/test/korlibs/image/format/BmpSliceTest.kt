package korlibs.image.format

import korlibs.image.*
import korlibs.image.atlas.*
import korlibs.image.bitmap.*
import korlibs.io.async.*
import korlibs.io.file.std.*
import korlibs.math.geom.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class BmpSliceTest {
    val props = ImageDecodingProps(format = ImageFormats(PNG))

    @Test
    fun testName() = doTest {
        val slice = resourcesVfs["rgba.png"].readBitmapSlice(name = "hello", props = props)
        assertEquals("hello", slice.name)
        assertEquals(SizeInt(4, 1), slice.bounds.size)
    }

    @Test
    fun testPacking() = doTest {
        val atlas = AtlasPacker.pack(listOf(
            resourcesVfs["rgba.png"].readBitmapSlice(name = "hello", props = props)
        ))
        val slice = atlas["hello"]
        assertEquals("hello", slice.name)
        assertEquals(SizeInt(4, 1), slice.bounds.size)
    }

    @Test
    fun testPackingMutable() = doTest {
        val atlas = MutableAtlasUnit()
        resourcesVfs["rgba.png"].readBitmapSlice(atlas = atlas, name = "hello", props = props)
        val slice = atlas["hello"]
        assertEquals("hello", slice.name)
        assertEquals(SizeInt(4, 1), slice.bounds.size)
    }
}
