package com.example.a2048project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import com.example.a2048project.Game.EasyGame
import com.example.a2048project.Game.Step
import com.example.a2048project.UIkt.GameCallback
import com.example.my2048.GameBoardView   // 你的 Compose 组件
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val game = EasyGame(this)
        val gridVersion = mutableStateOf(0)
        val showGameOver = mutableStateOf(false)

        setContent {
            val version by gridVersion
            val gameOver by showGameOver

            if (gameOver) {
                AlertDialog(
                    onDismissRequest = { /* 点击外部，可选：什么都不做，强制用户点按钮 */ },
                    title = { Text("游戏结束") },
                    text = { Text("最终得分: ${game.score}") },
                    confirmButton = {
                        TextButton(onClick = {
                            game.initGame()              // 重置 Java 后端
                            gridVersion.value++       // 刷新界面
                            showGameOver.value = false // 关闭弹窗
                        }) {
                            Text("重新开始")
                        }
                    },
                )
            }

            GameBoardView(game = game,
                version = version,
                callback = object : GameCallback {
                    override fun onSwip(direction: Step.Action){
                        game.step(direction)
                        gridVersion.value++

                        if(game.game_over){
                            showGameOver.value = true
                        }
                    }

                    override fun clickTile() {
                        game.rollBack();
                        gridVersion.value++
                    }
                }
            )
        }
    }
}