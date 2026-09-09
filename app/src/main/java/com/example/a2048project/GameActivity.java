package com.example.a2048project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a2048project.Game.BasicGame;
import com.example.a2048project.Game.EasyGame;
import com.example.my2048.GameKt;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasyGame game = new EasyGame(this);
        game.get_Format().randomlySummonBlocks(5);
        // 2. 直接调用你在 Kotlin 里写好的组件！
        // Kotlin 里写的高阶函数和 @Composable 组件在 Java 中会被识别为一个静态方法
        GameBridgeKt.attachGameUiToActivity(this, game);
    };
}