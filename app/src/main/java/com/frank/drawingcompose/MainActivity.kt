package com.frank.drawingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.frank.drawingcompose.ui.theme.DrawCanvas
import com.frank.drawingcompose.ui.theme.DrawingComposeTheme
import com.frank.drawingcompose.ui.theme.history
import com.frank.drawingcompose.ui.theme.index
import com.frank.drawingcompose.ui.theme.isNewMultiShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingComposeTheme {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black)) {
                    Row(
                        Modifier
                            .height(35.dp)
                            .background(Color.Black)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_close_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .background(Color.Black)
                                .clickable {
                                    isNewMultiShape = true
                                    if (!history.isEmpty()) {
                                        history.pop()
                                        index --
                                    }
                                }
                        )
                        Row(modifier = Modifier
                            .weight(1.0f)) {}
                        Image(
                            painter = painterResource(id = R.drawable.baseline_more_vert_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(end = 10.dp)
                                .background(Color.Black)
                                .clickable {
                                    isShowDialog = true
                                }
                        )
                    }
                    ShowMenuDialog()
                    DrawCanvas()
                }
            }
        }
    }
}
