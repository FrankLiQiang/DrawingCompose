package com.frank.drawingcompose.ui.theme

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import kotlin.math.sqrt

private val currentColor = Color.White
private var BackgroundColor = Color.Black
var index by mutableStateOf(0L)
const val PI = 3.14159
private var StartX = 0f
private var StartY = 0f
var isNewMultiShape = true
var isCloseShape = false
var isLastEdgeMoved = false

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawCanvas() {
    Canvas(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        StartX = it.x
                        StartY = it.y

                        when (currentType) {
                            ANY -> {
                                val path = MyPath(currentColor, lineWidth)
                                history.add(path)
                                path.path.moveTo(it.x, it.y)
                            }

                            LINE -> {
                                val line = Line(it.x, it.y, it.x, it.y, currentColor, lineWidth)
                                history.add(line)
                            }

                            STAR -> {
                                val star = Star(it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(star)
                            }

                            SHAPE3 -> {
                                val shape3 = Shape3567(3, it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(shape3)
                            }

                            SHAPE5 -> {
                                val shape5 = Shape3567(5, it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(shape5)
                            }

                            SHAPE6 -> {
                                val shape6 = Shape3567(6, it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(shape6)
                            }

                            SHAPE7 -> {
                                val shape7 = Shape3567(7, it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(shape7)
                            }

                            CIRCULAR -> {
                                val c = Circular(it.x, it.y, 0f, currentColor, lineWidth)
                                history.add(c)
                            }

                            RECT -> {
                                val rect = Rect(it.x, it.y, it.x, it.y, currentColor, lineWidth)
                                history.add(rect)
                            }

                            OVAL -> {
                                val o = Oval(it.x, it.y, it.x, it.y, currentColor, lineWidth)
                                history.add(o)
                            }

                            SQUARE -> {
                                val rect = Rect(it.x, it.y, it.x, it.y, currentColor, lineWidth)
                                history.add(rect)
                            }

                            MULTISHAPE -> {
                                if (isNewMultiShape) {
                                    isNewMultiShape = false
                                    isLastEdgeMoved = false
                                    val mShape = MultiShape(it.x, it.y, currentColor, lineWidth)
                                    history.add(mShape)
                                } else if (!history.isEmpty()) {
                                    val s = history.lastElement()
                                    if (s is MultiShape) {
                                        s.addPoint(it.x, it.y)
                                        index++
                                    }
                                }
                            }
                        }
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if (!history.isEmpty()) {

                            index++
                            val s = history.lastElement()
                            when (currentType) {
                                ANY -> {
                                    (s as MyPath).path.lineTo(it.x, it.y)
                                }

                                STAR -> {
                                    val r = getDistance(StartX, StartY, it.x, it.y)
                                    (s as Star).myInit(StartX, StartY, r)
                                }

                                SHAPE3, SHAPE5, SHAPE6, SHAPE7 -> {
                                    val r = getDistance(StartX, StartY, it.x, it.y)
                                    (s as Shape3567).myInit(StartX, StartY, r)
                                }

                                CIRCULAR -> {
                                    val r = getDistance(StartX, StartY, it.x, it.y)
                                    (s as Circular).r = r
                                }

                                LINE -> {
                                    (s as Line).myInit(StartX, StartY, it.x, it.y)
                                }

                                RECT -> {
                                    (s as Rect).myInit(StartX, StartY, it.x, it.y)
                                }

                                OVAL -> {
                                    (s as Oval).x2 = it.x
                                    s.y2 = it.y
                                }

                                SQUARE -> {
                                    (s as Rect).myInit(StartX, StartY, it.x, StartY + it.x - StartX)
                                }

                                MULTISHAPE -> {
                                    (s as MultiShape).moveLastPoint(it.x, it.y)
                                }
                            }
                        }
                    }

                    MotionEvent.ACTION_POINTER_UP -> {
                        if (currentType == MULTISHAPE) {
                            if (!history.isEmpty()) {
                                val s = history.lastElement()
                                if (s is MultiShape) {
                                    s.addPoint(it.x, it.y)
                                    if (!isLastEdgeMoved) {
                                        index++
                                    }
                                }
                            }
                        }
                    }
                }
                true
            }
    ) {
        if (index < 0) return@Canvas

        for (i in history.indices) {
            if (history[i] is MyPath) {
                val myPath = history[i] as MyPath

                drawPath(
                    path = myPath.path,
                    color = myPath.color,
                    style = Stroke(width = myPath.penSize)
                )
            } else if (history[i] is Circular) {
                val c = history[i] as Circular
                drawCircle(
                    center = Offset(c.x, c.y),
                    color = c.color,
                    style = Stroke(width = c.penSize),
                    radius = c.r
                )
            } else if (history[i] is Oval) {
                val o = history[i] as Oval
                val path = Path()
                path.addOval(androidx.compose.ui.geometry.Rect(o.x1, o.y1, o.x2, o.y2))
                drawPath(
                    path, color = o.color,
                    style = Stroke(width = o.penSize)
                )
            }
        }
    }
}

fun getDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))
}
