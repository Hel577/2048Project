package com.example.a2048project.Utils;

import java.util.ArrayList;

public class Format {
    private static Format instance;
    private int width;
    private int height;
    private ArrayList<Blocks> my_format;
    private Format(int width, int height){
        this.width = width;
        this.height = height;
        this.my_format = new ArrayList<>(width*height);
    }

    private int index(int roll,int column){
        return roll*this.width+column;
    }

    private int[] coordinate(int index){
        int roll = index/this.width;
        int column = index % this.width;
        return new int[]{roll,column};
    }

    public void setFormat(int roll,int column, Blocks block){
        this.my_format.set(this.index(roll, column),block);
    }

    public Blocks getFormat(int roll, int column){
        return this.my_format.get(this.index(roll, column));
    }

    public void InitFormat(int width, int height){
        this.my_format.clear();
        this.my_format.ensureCapacity(width*height);
    }

    public static Format getInstance(int width, int height) {
        if(instance==null){
            return new Format(width,height);
        }
        else{
            return instance;
        }
    }
}
