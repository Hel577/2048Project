package com.example.my2048 // 换成你自己的包名

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a2048project.Game.BasicGame
import com.example.a2048project.Game.Step
import com.example.a2048project.UIkt.GameCallback
import kotlin.math.log2

// 2048 单个数字方块的 UI



@Composable
fun TileView(value: Int, is_new: Boolean ,is_merged: Boolean ,callback: GameCallback) {
    val scale by animateFloatAsState(
        targetValue = if(is_new) 1f else 1f,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 400f
        ),
        label = "tileScale"
    )

    // 根据数字改变方块颜色（简易版）
    fun getColor(value: Int): Color {
        if(value==0){
            return Color(0xFFFFFFFF)
        }
        val fraction = (log2(value.toDouble().coerceAtLeast(1.0)) / 11.0).coerceIn(0.0, 1.0)

        // 色相在 25° (橙) 到 50° (金黄) 之间微调
        val hue = 50f - fraction * 25f
        // 明度从 0.6 上升到 0.85，颜色会越来越亮，看着更轻松
        val lightness = 0.85f - fraction * 0.25f

        return Color.hsl(hue.toFloat(), 0.85f, lightness.toFloat())
    }

    Box(
        modifier = Modifier
            .size(70.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .background(getColor(value),
                shape = RoundedCornerShape(8.dp))

        ,
        contentAlignment = Alignment.Center
    ) {
        if (value > 0) {
            Text(
                text = value.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if(value<64) Color(0xFF776E65) else Color.White
            )
        }
    }
}



@Composable
fun GameBoardView(
    game: BasicGame,
    version: Int,
    callback: GameCallback
) {
    val game_format = game._Format
    val score = game.score

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            // 👇 加滑动手势
            .pointerInput(Unit) {
                var dragX = 0f
                var dragY = 0f
                detectDragGestures(
                    onDragStart = {
                        dragX = 0f
                        dragY = 0f
                    },
                    onDrag = { change, amount ->
                        change.consume()
                        dragX += amount.x
                        dragY += amount.y
                    },
                    onDragEnd = {
                        val threshold = 100f
                        when {
                            kotlin.math.abs(dragX) > kotlin.math.abs(dragY) -> {
                                if (dragX > threshold) callback.onSwip(Step.Action.RIGHT)
                                else if (dragX < -threshold) callback.onSwip(Step.Action.LEFT)
                            }
                            else -> {
                                if (dragY > threshold) callback.onSwip(Step.Action.DOWN)
                                else if (dragY < -threshold) callback.onSwip(Step.Action.UP)
                            }
                        }
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "得分: $score",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF776E65),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            modifier = Modifier
                .background(Color(0xFFBBADA0), RoundedCornerShape(12.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (row in 0 until game_format.height) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (col in 0 until game_format.width) {
                        val block = game_format.getFormat(row, col)
                        TileView(
                            value = block?.num ?: 0,
                            block?.is_new ?:false,
                            block?.is_merged?:false,
                            callback)
                    }
                }
            }
        }

        Button(onClick = {
            callback.clickTile()
        }) {
            Text("Roll Back")
        }
    }
}