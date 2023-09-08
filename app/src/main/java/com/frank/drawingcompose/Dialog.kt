package com.frank.drawingcompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.frank.drawingcompose.ui.theme.ANY
import com.frank.drawingcompose.ui.theme.CIRCULAR
import com.frank.drawingcompose.ui.theme.CRUDE_SIZE
import com.frank.drawingcompose.ui.theme.LINE
import com.frank.drawingcompose.ui.theme.MIDDLE_SIZE
import com.frank.drawingcompose.ui.theme.MULTISHAPE
import com.frank.drawingcompose.ui.theme.MyPath
import com.frank.drawingcompose.ui.theme.OVAL
import com.frank.drawingcompose.ui.theme.RECT
import com.frank.drawingcompose.ui.theme.SHAPE3
import com.frank.drawingcompose.ui.theme.SHAPE5
import com.frank.drawingcompose.ui.theme.SHAPE6
import com.frank.drawingcompose.ui.theme.SHAPE7
import com.frank.drawingcompose.ui.theme.SINGLE_SIZE
import com.frank.drawingcompose.ui.theme.SQUARE
import com.frank.drawingcompose.ui.theme.STAR
import com.frank.drawingcompose.ui.theme.Shape3567
import com.frank.drawingcompose.ui.theme.Star
import com.frank.drawingcompose.ui.theme.currentType
import com.frank.drawingcompose.ui.theme.history
import com.frank.drawingcompose.ui.theme.index
import com.frank.drawingcompose.ui.theme.isCloseShape
import com.frank.drawingcompose.ui.theme.isNewMultiShape
import com.frank.drawingcompose.ui.theme.lineWidth

var isShowDialog by mutableStateOf(false)

@Composable
private fun MenuDialog() {
    Dialog({ isShowDialog = false }) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .padding(6.dp),
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxSize()
            ) {

                Row(Modifier.weight(1.0f)) {
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = ANY
                                isShowDialog = false
                            }
                    ) {
                        val thePath =
                            MyPath(if (currentType == ANY) Color.Blue else Color.White, 4f)
                        thePath.path.moveTo(90f, 25f)
                        thePath.path.lineTo(52f, 130f)
                        thePath.path.lineTo(150f, 100f)
                        thePath.path.lineTo(90f, 150f)
                        thePath.path.lineTo(150f, 170f)
                        drawPath(
                            path = thePath.path,
                            color = thePath.color,
                            style = Stroke(width = thePath.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = LINE
                                isShowDialog = false
                            }
                    ) {
                        drawLine(
                            start = Offset(x = 50f, y = 30f),
                            end = Offset(x = 170f, y = 170f),
                            strokeWidth = 4f,
                            color = if (currentType == LINE) Color.Blue else Color.White
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = RECT
                                isShowDialog = false
                            }
                    ) {
                        drawRect(
                            color = if (currentType == RECT) Color.Blue else Color.White,
                            topLeft = Offset(x = size.width / 6F, y = size.height / 3F),
                            style = Stroke(width = 4f),
                            size = Size(size.width * 2 / 3, size.height / 3)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    DrawText(
                        "Open",
                        Modifier
                            .weight(1.0f)
                    ) { isShowDialog = false }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(1.0.dp)
                        .background(Color.White)
                ) {}
                Row(Modifier.weight(1.0f)) {
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = SQUARE
                                isShowDialog = false
                            }
                    ) {
                        drawRect(
                            color = if (currentType == SQUARE) Color.Blue else Color.White,
                            topLeft = Offset(x = size.width / 5F, y = size.height / 5F),
                            style = Stroke(width = 4f),
                            size = size * 2f / 3f
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = CIRCULAR
                                isShowDialog = false
                            }
                    ) {
                        drawCircle(
                            if (currentType == CIRCULAR) Color.Blue else Color.White,
                            style = Stroke(width = 4f),
                            radius = size.width / 3
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = OVAL
                                isShowDialog = false
                            }
                    ) {
                        scale(scaleX = 1.5f, scaleY = 1f) {
                            drawCircle(
                                if (currentType == OVAL) Color.Blue else Color.White,
                                style = Stroke(width = 4f),
                                radius = size.width / 4
                            )
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    DrawText(
                        "Save",
                        Modifier
                            .weight(1.0f)
                    ) { isShowDialog = false }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(1.0.dp)
                        .background(Color.White)
                ) {}
                Row(Modifier.weight(1.0f)) {
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = STAR
                                isShowDialog = false
                            }
                    ) {
                        val star = Star(
                            100f, 120f, 90f,
                            if (currentType == STAR) Color.Blue else Color.White, 4f
                        )
                        drawPath(
                            path = star.path,
                            color = star.color,
                            style = Stroke(width = star.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = SHAPE5
                                isShowDialog = false
                            }
                    ) {
                        val shape5 = Shape3567(5, 100f, 120f, 80f, Color.White, 4f)
                        drawPath(
                            path = shape5.path,
                            color = if (currentType == SHAPE5) Color.Blue else Color.White,
                            style = Stroke(width = shape5.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = SHAPE6
                                isShowDialog = false
                            }
                    ) {
                        val shape6 = Shape3567(6, 100f, 120f, 80f, Color.White, 4f)
                        drawPath(
                            path = shape6.path,
                            color = if (currentType == SHAPE6) Color.Blue else Color.White,
                            style = Stroke(width = shape6.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    DrawText(
                        "ColorF",
                        Modifier.weight(1.0f)
                    ) {
                        isShowDialog = false
                        isFrontColor = true
                        isColorDialog = true
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(1.0.dp)
                        .background(Color.White)
                ) {}
                Row(Modifier.weight(1.0f)) {
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = SHAPE3
                                isShowDialog = false
                            }
                    ) {
                        val shape3 = Shape3567(3, 100f, 120f, 80f, Color.White, 4f)
                        drawPath(
                            path = shape3.path,
                            color = if (currentType == SHAPE3) Color.Blue else Color.White,
                            style = Stroke(width = shape3.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                currentType = SHAPE7
                                isShowDialog = false
                            }
                    ) {
                        val shape7 = Shape3567(7, 100f, 120f, 80f, Color.White, 4f)
                        drawPath(
                            path = shape7.path,
                            color = if (currentType == SHAPE7) Color.Blue else Color.White,
                            style = Stroke(width = shape7.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                isNewMultiShape = true
                                isCloseShape = false
                                currentType = MULTISHAPE
                                isShowDialog = false
                            }
                    ) {
                        val thePath =
                            MyPath(if (currentType == MULTISHAPE) Color.Blue else Color.White, 4f)
                        thePath.path.moveTo(70f, 15f)
                        thePath.path.lineTo(12f, 180f)
                        thePath.path.lineTo(150f, 100f)
                        thePath.path.lineTo(180f, 150f)
                        thePath.path.lineTo(120f, 170f)
                        drawPath(
                            path = thePath.path,
                            color = thePath.color,
                            style = Stroke(width = thePath.penSize)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    DrawText(
                        "ColorB",
                        Modifier.weight(1.0f)
                    ) {
                        isShowDialog = false
                        isFrontColor = false
                        isColorDialog = true
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(1.0.dp)
                        .background(Color.White)
                ) {}
                Row(Modifier.weight(1.0f)) {
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                lineWidth = SINGLE_SIZE
                                isShowDialog = false
                            }
                    ) {
                        drawLine(
                            start = Offset(x = 20f, y = 120f),
                            end = Offset(x = 180f, y = 120f),
                            strokeWidth = SINGLE_SIZE,
                            color = if (lineWidth == SINGLE_SIZE) Color.Blue else Color.White
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                lineWidth = MIDDLE_SIZE
                                isShowDialog = false
                            }
                    ) {
                        drawLine(
                            start = Offset(x = 20f, y = 120f),
                            end = Offset(x = 180f, y = 120f),
                            strokeWidth = MIDDLE_SIZE,
                            color = if (lineWidth == MIDDLE_SIZE) Color.Blue else Color.White
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    Canvas(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                            .clickable {
                                lineWidth = CRUDE_SIZE
                                isShowDialog = false
                            }
                    ) {
                        drawLine(
                            start = Offset(x = 20f, y = 120f),
                            end = Offset(x = 180f, y = 120f),
                            strokeWidth = CRUDE_SIZE,
                            color = if (lineWidth == CRUDE_SIZE) Color.Blue else Color.White
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(1.0.dp)
                            .background(Color.White)
                    ) {}
                    DrawText(
                        "Clear",
                        Modifier
                            .weight(1.0f)
                    ) {
                        history.clear()
                        index = 0
                        isNewMultiShape = true
                        isShowDialog = false
                    }
                }
            }
        }
    }
}


@Composable
fun ShowMenuDialog() {
    if (isShowDialog) {
        MenuDialog()
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun DrawText(str: String, modifier: Modifier, event: () -> Unit) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier
            .fillMaxSize()
            .clickable { event() }
    ) {
        val measuredText =
            textMeasurer.measure(
                AnnotatedString(str),
                constraints = Constraints.fixed(size.width.toInt(), size.height.toInt()),
                style = TextStyle(
                    fontSize = 22.sp, color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        drawText(
            measuredText,
            topLeft = Offset(0.0f, size.height / 4.5f)
        )
    }
}