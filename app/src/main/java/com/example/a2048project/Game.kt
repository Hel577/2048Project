package com.example.my2048 // 换成你自己的包名

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.log2

// 2048 单个数字方块的 UI



@Composable
fun TileView(value: Int) {
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
            .background(getColor(value),
                shape = RoundedCornerShape(8.dp)),
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

// 整个 4x4 棋盘网格的 UI
@Composable
fun GameBoardView(board: Array<IntArray>) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .pointerInput(Unit){
//                detectVerticalDragGestures { change, dragAmount -> {
//                    if(dragAmount>20){
//
//                    }
//                } }
//            }
//    )
    val game =

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color(0xFFBBADA0),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            for (row in board) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (value in row) {
                        TileView(value = value)
                    }
                }
            }
        }
    }
}