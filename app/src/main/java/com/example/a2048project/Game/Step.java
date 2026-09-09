package com.example.a2048project.Game;

import com.example.a2048project.Utils.Blocks;

import java.util.ArrayList;

public class Step {
    public int step;//Step count
    public enum Action{
        LEFT, RIGHT, UP, DOWN
    }//Action taken in this step
    public ArrayList<Blocks> FormatStatus;//Format Array
}
