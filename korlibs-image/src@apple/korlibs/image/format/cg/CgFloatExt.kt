@file:OptIn(ExperimentalForeignApi::class, UnsafeNumber::class)

package korlibs.image.format.cg

import korlibs.math.geom.*
import kotlinx.cinterop.*
import platform.CoreGraphics.*

@OptIn(UnsafeNumber::class)
inline val Int.cg: CGFloat get() = this.toDouble().toCgFloat()
@OptIn(UnsafeNumber::class)
inline val Float.cg: CGFloat get() = this.toCgFloat()
@OptIn(UnsafeNumber::class)
inline val Double.cg: CGFloat get() = this.toCgFloat()

fun CGRect.toRectangle(): Rectangle = Rectangle(this.origin.x, this.origin.y, this.size.width, this.size.height)
fun CGPoint.toPoint(): Point = Point(x, y)
fun CGSize.toSize(): Size = Size(width, height)

fun CGRectMakeExt(x: Int, y: Int, width: Int, height: Int): CValue<CGRect> = CGRectMake(x.cg, y.cg, width.cg, height.cg)
fun CGRectMakeExt(x: Float, y: Float, width: Float, height: Float): CValue<CGRect> = CGRectMake(x.cg, y.cg, width.cg, height.cg)
fun CGRectMakeExt(x: Double, y: Double, width: Double, height: Double): CValue<CGRect> = CGRectMake(x.cg, y.cg, width.cg, height.cg)
