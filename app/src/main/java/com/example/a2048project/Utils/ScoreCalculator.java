package com.example.a2048project.Utils;

public class ScoreCalculator {
    private int scores;
    public ScoreCalculator(){
        this.scores = 0;
    }

    public void add(int score_add){
        this.scores += score_add;
    }

    public void initScore(){
        this.scores = 0;
    }

    public void setScores(int score){
        this.scores = score;
    }

    public int getScores(){
        return this.scores;
    }
}
