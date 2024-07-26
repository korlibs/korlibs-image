package korlibs.image.format

import korlibs.concurrent.lock.*

class ImageFormatsMutable() : ImageFormats() {
    @PublishedApi internal val lock = NonRecursiveLock()

    constructor(vararg formats: ImageFormat) : this() {
        register(*formats)
    }

    fun register(vararg formats: ImageFormat): ImageFormatsMutable {
        lock { this._formats = this._formats + formats - this }
        return this
    }

    fun registerFirst(vararg formats: ImageFormat): ImageFormatsMutable {
        lock { this._formats = (formats.toSet() + this._formats) - this }
        return this
    }

    fun unregister(vararg formats: ImageFormat): ImageFormatsMutable {
        lock { this._formats = this._formats - formats.toSet() }
        return this
    }

    // @TODO: This is not thread-safe, if we call this from two different threads at once strange things might happen
    inline fun <T> temporalRegister(vararg formats: ImageFormat, callback: () -> T): T {
        val oldFormats = lock { this._formats.toSet() }
        try {
            register(*formats)
            return callback()
        } finally {
            lock { this._formats = oldFormats }
        }
    }
}
