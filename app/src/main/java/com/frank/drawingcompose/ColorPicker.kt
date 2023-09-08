package com.frank.drawingcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.frank.drawingcompose.ui.theme.BackgroundColor
import com.frank.drawingcompose.ui.theme.currentColor
import com.frank.drawingcompose.ui.theme.index

var isColorDialog by mutableStateOf(false)
var isFrontColor by mutableStateOf(true)

@Composable
private fun CustomDialogWithResultExample(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Color) -> Unit,
    myColor: Color
) {
    var red by remember { mutableStateOf(myColor.red * 255) }
    var green by remember { mutableStateOf(myColor.green * 255) }
    var blue by remember { mutableStateOf(myColor.blue * 255) }

    val color = Color(
        red = red.toInt(),
        green = green.toInt(),
        blue = blue.toInt(),
        alpha = 255
    )

    Dialog(onDismissRequest = onDismiss) {

        Card(
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = if (isFrontColor) "Select Prospect Color" else "Select Background Color",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Color Selection
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column {

                        Text(text = "Red ${red.toInt()}")

                        Slider(
                            value = red,
                            onValueChange = { red = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Green ${green.toInt()}")
                        Slider(
                            value = green,
                            onValueChange = { green = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Blue ${blue.toInt()}")
                        Slider(
                            value = blue,
                            onValueChange = { blue = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            border = BorderStroke(1.dp, Color.DarkGray),
                            color = color,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {}
                    }
                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = "CANCEL")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        onPositiveClick(color)
                    }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
fun ShowColorPicker() {
    if (isColorDialog) {
        CustomDialogWithResultExample(
            onDismiss = {
                isColorDialog = false
            },
            onNegativeClick = {
                isColorDialog = false
            },
            onPositiveClick = { color ->
                isColorDialog = false
                if (isFrontColor) {
                    currentColor = color
                } else {
                    BackgroundColor = color
                    index++
                }
            },
            myColor = if (isFrontColor) {
                currentColor
            } else {
                BackgroundColor
            }
        )
    }
}
