package com.example.a2048project.Game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a2048project.GameActivity;
import com.example.a2048project.Role.BaseRole;
import com.example.a2048project.Role.Role;
import com.example.a2048project.Utils.GameFormat;
import com.example.a2048project.Utils.ScoreCalculator;

import java.util.Stack;

import kotlin.NotImplementedError;

public abstract class BasicGame extends SurfaceView implements
        SurfaceHolder.Callback, Runnable {
    private ScoreCalculator scoreCalculator;
    private GameFormat gameFormat;
    private Role role;
    private Stack<Step> stepStack;

    public BasicGame(Context context){
        super(context);
        this.scoreCalculator = new ScoreCalculator();
        this.gameFormat = GameFormat.getInstance(this.get_width(),this.get_height());
        this.role = new BaseRole();
        this.stepStack = new Stack<>();
    }

    private int getScore(){
        //Maintain scoreCalculator
        return scoreCalculator.getScores();
    }

    private void stepsStore(){
        //Store steps and maintain stacks
        throw new NotImplementedError();
    }

    public void setRole(BaseRole role){
        this.role = role;
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

    public abstract int get_width();
    public abstract int get_height();


}
