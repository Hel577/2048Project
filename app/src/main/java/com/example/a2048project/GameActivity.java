package com.example.a2048project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.my2048.GameKt;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[][] currentBoard = {
                {0, 1, 2, 4},
                {8, 16, 32, 64},
                {128, 256, 512, 1024},
                {2048, 0, 0, 64}
        };
        // 2. 直接调用你在 Kotlin 里写好的组件！
        // Kotlin 里写的高阶函数和 @Composable 组件在 Java 中会被识别为一个静态方法
        GameBridgeKt.attachGameUiToActivity(this, currentBoard);
    };
}