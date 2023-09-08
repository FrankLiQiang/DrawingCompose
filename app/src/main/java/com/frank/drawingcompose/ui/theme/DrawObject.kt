package com.frank.drawingcompose.ui.theme

import android.graphics.PointF
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import java.util.Stack
import kotlin.math.cos
import kotlin.math.sin

const val ANY = 0
const val RECT = 1
const val CIRCULAR = 2
const val OVAL = 3
const val LINE = 4
const val SQUARE = 5
const val STAR = 6
const val SHAPE3 = 7
const val SHAPE5 = 8
const val SHAPE6 = 9
const val SHAPE7 = 10
const val MULTISHAPE = 11

const val SINGLE = 12
const val MIDDLE = 13
const val CRUDE = 14

const val Open = 15
const val Save = 16
const val Clear = 17
const val FColor = 18
const val BColor = 19

const val SINGLE_SIZE = 2F
const val MIDDLE_SIZE = 13F
const val CRUDE_SIZE = 26F
var lineWidth = MIDDLE_SIZE
val history: Stack<Any> = Stack<Any>()
var currentType = ANY

open class MyPath constructor(var color: Color, var penSize: Float) {
    val path: Path = Path()
}

class Star(x: Float, y: Float, r: Float, color: Color, penSize: Float) : MyPath(color, penSize) {

    val p = arrayOf(PointF(), PointF(), PointF(), PointF(), PointF())

    init {
        myInit(x, y, r)
    }

    fun myInit(x: Float, y: Float, r: Float) {
        for (i in 0..4) {
            p[i].x = x + r * cos(-PI / 2 + PI / 2.5f * i).toFloat()
            p[i].y = y + r * sin(-PI / 2 + PI / 2.5f * i).toFloat()
        }

        path.reset()
        path.moveTo(p[0].x, p[0].y)
        path.lineTo(p[2].x, p[2].y)
        path.lineTo(p[4].x, p[4].y)
        path.lineTo(p[1].x, p[1].y)
        path.lineTo(p[3].x, p[3].y)
        path.close()
    }
}

class Circular(var x: Float, var y: Float, var r: Float, var color: Color, var penSize: Float)

class Rect(x1: Float, y1: Float, x2: Float, y2: Float, color: Color, penSize: Float) :
    MyPath(color, penSize) {

    init {
        myInit(x1, y1, x2, y2)
    }

    fun myInit(x1: Float, y1: Float, x2: Float, y2: Float) {
        path.reset()
        path.moveTo(x1, y1)
        path.lineTo(x2, y1)
        path.lineTo(x2, y2)
        path.lineTo(x1, y2)
        path.close()
    }
}

class Oval(
    var x1: Float,
    var y1: Float,
    var x2: Float,
    var y2: Float,
    var color: Color,
    var penSize: Float
)

class Line(x1: Float, y1: Float, x2: Float, y2: Float, color: Color, penSize: Float) :
    MyPath(color, penSize) {

    init {
        myInit(x1, y1, x2, y2)
    }

    fun myInit(x1: Float, y1: Float, x2: Float, y2: Float) {
        path.reset()
        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.close()
    }
}

class Shape3567(var num: Int, x: Float, y: Float, r: Float, color: Color, penSize: Float) :
    MyPath(color, penSize) {

    val p = arrayOf(PointF(), PointF(), PointF(), PointF(), PointF(), PointF(), PointF())

    init {
        myInit(x, y, r)
    }

    fun myInit(x: Float, y: Float, r: Float) {
        for (i in 0 until num) {
            p[i].x = x + r * cos(-PI / 2 + 2 * PI / num * i).toFloat()
            p[i].y = y + r * sin(-PI / 2 + 2 * PI / num * i).toFloat()
        }
        path.reset()
        path.moveTo(p[0].x, p[0].y)
        for (i in 0 until num - 1) {
            path.lineTo(p[i + 1].x, p[i + 1].y)
        }
        path.close()
    }
}

class MultiShape(x1: Float, y1: Float, color: Color, penSize: Float) :
    MyPath(color, penSize) {

    private var points: ArrayList<PointF> = arrayListOf()

    init {
        path.reset()
        path.moveTo(x1, y1)

        points.clear()
        points.add(PointF(x1, y1))
    }

    fun addPoint(x: Float, y: Float) {
        points.add(PointF(x, y))
        path.lineTo(x, y)
    }

    fun moveLastPoint(x: Float, y: Float) {
        isLastEdgeMoved = true

        path.reset()
        path.moveTo(points[0].x, points[0].y)
        for (i in 0 until points.size - 1) {
            path.lineTo(points[i].x, points[i].y)
        }
        path.lineTo(x, y)
        if (points.size == 1) {
            points.add(PointF(x, y))
        } else {
            points.last().x = x
            points.last().y = y
        }
    }
}
