package com.example.a2048project

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.my2048.GameBoardView
import com.example.a2048project.Utils.GameFormat

// 这是一个专供 Java 调用的桥梁函数
fun attachGameUiToActivity(activity: ComponentActivity, boardData: Array<IntArray>) {
    activity.setContent {
        // 这里可以直接调用你用 Compose 写的 2048 棋盘 UI
        GameBoardView(board = boardData)
    }
}