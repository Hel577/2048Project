package com.example.a2048project.Game;

import android.content.Context;

import com.example.a2048project.Utils.GameFormat;

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

    @Override
    public int get_count(){
        //1/2 if the slots is available, else 1
        GameFormat gameFormat = this.get_Format();
        int slots = gameFormat.getWidth()*gameFormat.getHeight()- gameFormat.numOfBlockes();
        if(slots>0){
            boolean para = Math.random()>0.5;
            if(para){
                return 1;
            }
            else{
                return 1;
            }
        }
        return 0;
    }
}
