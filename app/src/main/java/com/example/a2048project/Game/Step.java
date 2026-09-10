package com.example.a2048project.Game;

import com.example.a2048project.Utils.Blocks;

import java.util.ArrayList;

public class Step {
    public int step;//Step count
    public enum Action{
        LEFT, RIGHT, UP, DOWN
    }//Action taken in this step
    public Action action;
    public ArrayList<Blocks> FormatStatus;//Format Array
    public int score;

    public Step(int step,Action action,ArrayList<Blocks> array,int score){
        this.step = step;
        this.action = action;
        this.FormatStatus = array;
        this.score = score;
    }
}
