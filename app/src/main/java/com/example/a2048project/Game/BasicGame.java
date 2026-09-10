package com.example.a2048project.Game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a2048project.Role.BaseRole;
import com.example.a2048project.Role.Role;
import com.example.a2048project.Utils.Blocks;
import com.example.a2048project.Utils.GameFormat;
import com.example.a2048project.Utils.ScoreCalculator;

import java.util.ArrayList;
import java.util.Stack;

import kotlin.NotImplementedError;

public abstract class BasicGame  implements
         Runnable {
    private ScoreCalculator scoreCalculator;
    private GameFormat gameFormat;
    private Role role;
    private Stack<Step> stepStack;
    public boolean game_over;
    private int step;

    public BasicGame(Context context){
        this.scoreCalculator = new ScoreCalculator();
        this.gameFormat = GameFormat.getInstance(this.get_width(),this.get_height());
        this.role = new BaseRole();
        this.stepStack = new Stack<>();
        this.game_over = false;
        this.step = 0;
        this.initGame();
    }

    public int getScore(){
        //Maintain scoreCalculator
        return scoreCalculator.getScores();
    }

    private void stepsStore(int step, Step.Action action, ArrayList<Blocks> array){
        //Store steps and maintain stacks
        Step current_step = new Step(step,action,array,this.getScore());
        this.stepStack.push(current_step);
    }

    public  GameFormat get_Format(){
        return this.gameFormat;
    }

    public void setRole(BaseRole role){
        this.role = role;
    }

    public void rollBack(){
        //撤回操作
        if(this.stepStack.isEmpty()){
            throw new RuntimeException("No steps taken");
        }
        else{
            Step step = this.stepStack.pop();
            this.gameFormat.loadArray(step.FormatStatus);
            this.step = step.step;
            this.scoreCalculator.setScores(step.score);
        }
    }


    public void step(Step.Action action){
        //Implements each step
        if(this.game_over){
            return;
        }
        this.stepsStore(this.step,action,this.gameFormat.cloneArray());
        this.step++;
        int score = this.gameFormat.takeAction(action);
        if(score == -1){
            rollBack();
            return;
        }
        this.scoreCalculator.add(score);
        this.gameFormat.randomlySummonBlocks(this.get_count());
        if(!this.gameFormat.isAvailable()){
            this.game_over = true;
        }
    }

    @Override
    public void run(){
        //Implement main game function
        throw new NotImplementedError();
    }

    public void initGame(){
        this.scoreCalculator.initScore();
        this.gameFormat.initFormat(this.get_width(),this.get_height());
        this.stepStack.empty();
        this.gameFormat.randomlySummonBlocks(3);
        this.game_over = false;
        this.step = 0;
    }


    public abstract int get_width();
    public abstract int get_height();
    public abstract int get_count();


}
