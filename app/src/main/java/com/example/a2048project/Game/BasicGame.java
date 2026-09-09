package com.example.a2048project.Game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a2048project.Role.BaseRole;
import com.example.a2048project.Role.Role;
import com.example.a2048project.Utils.GameFormat;
import com.example.a2048project.Utils.ScoreCalculator;

import java.util.Stack;

import kotlin.NotImplementedError;

public abstract class BasicGame  implements
         Runnable {
    private ScoreCalculator scoreCalculator;
    private GameFormat gameFormat;
    private Role role;
    private Stack<Step> stepStack;

    public BasicGame(Context context){
        this.scoreCalculator = new ScoreCalculator();
        this.gameFormat = GameFormat.getInstance(this.get_width(),this.get_height());
        this.role = new BaseRole();
        this.stepStack = new Stack<>();
    }

    public int getScore(){
        //Maintain scoreCalculator
        return scoreCalculator.getScores();
    }

    private void stepsStore(){
        //Store steps and maintain stacks
        throw new NotImplementedError();
    }

    public  GameFormat get_Format(){
        return this.gameFormat;
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

    public void draw(){
        //complete page show

    }

    public abstract int get_width();
    public abstract int get_height();


}
