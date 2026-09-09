package com.example.a2048project.Utils;

import com.example.a2048project.Game.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Format {
    protected int width;
    protected int height;
    protected ArrayList<Blocks> my_format;
    public Format(int width, int height){
        this.width = width;
        this.height = height;
        this.my_format = new ArrayList<>(width*height);
    }

    protected int index(int roll,int column){
        return roll*this.width+column;
    }

    protected int[] coordinate(int index){
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

    protected Blocks getUp(int roll, int column){
        //Get direct up blocks
        int i,j;
        j = column;
        for(i=roll;i>-1;i--){
            if(this.getFormat(i,j)!=null){
                return this.getFormat(i,j);
            }
        }
        return null;
    }

    protected Blocks getDown(int roll, int column){
        //Get direct down blocks
        int i,j;
        j = column;
        for(i=roll;i<this.height;i++){
            if(this.getFormat(i,j)!=null){
                return this.getFormat(i,j);
            }
        }
        return null;
    }

    protected Blocks getLeft(int roll, int column){
        //Get direct left blocks
        int i,j;
        i = roll;
        for(j=column;j>-1;j--){
            if(this.getFormat(i,j)!=null){
                return this.getFormat(i,j);
            }
        }
        return null;
    }

    protected Blocks getRight(int roll, int column){
        //Get direct right blocks
        int i,j;
        i = roll;
        for(j=column;j<this.width;j++){
            if(this.getFormat(i,j)!=null){
                return this.getFormat(i,j);
            }
        }
        return null;
    }

    public void initFormat(int width, int height){
        this.my_format.clear();
        this.my_format.ensureCapacity(width*height);
    }

    public int numOfBlockes(){
        int i;
        int result = 0;
        for(i=0;i<this.width*this.height;i++){
            if(this.my_format.get(i)!=null){
                result+=1;
            }
        }
        return result;
    }

    public void delBlock(Blocks blocks){
        this.my_format.remove(this.index(blocks.cor[0],blocks.cor[1]));
    }

    public void moveBlock(Blocks blocks,int i,int j){
        Collections.swap(this.my_format,this.index(blocks.cor[0],blocks.cor[1]),this.index(i,j));
        blocks.setCor(i,j);

    }

    public ArrayList<Blocks> getArray(){
        return this.my_format;
    }

    public void loadArray(ArrayList<Blocks> array){
        this.my_format = array;
    }

}
