package com.example.a2048project.Game;

import android.content.Context;

public class EasyGame extends BasicGame{
    public EasyGame(Context context){
        super(context);
    }

    @Override
    public int get_width(){
        return 4;
    }

    @Override
    public int get_height(){
        return 4;
    }
}
