package com.example.a2048project.Game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a2048project.GameActivity;

import kotlin.NotImplementedError;

public abstract class BasicGame extends SurfaceView implements
        SurfaceHolder.Callback, Runnable {
    public BasicGame(Context context){
        super(context);
        return;
    }

    private void scoreCalculate(){
        //Maintain scoreCalculator
        throw new NotImplementedError();
    }

    private void stepsStore(){
        //Store steps and maintain stacks
        throw new NotImplementedError();
    }

    private void summonBlocks(){
        //Randomly summon Blocks of 1, 2, 4(40%,40%,20%)
        throw new NotImplementedError();
    }

    private void step(){
        //Implements each step
        throw new NotImplementedError();
    }

    @Override
    public void run(){
        //Implement main game function
        throw new NotImplementedError();
    }


}
