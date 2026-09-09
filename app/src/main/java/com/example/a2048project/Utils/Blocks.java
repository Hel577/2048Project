package com.example.a2048project.Utils;

public class Blocks {
    public int num;
    public int[] cor;

    public Blocks(int init_num,int i, int j){
        this.num = init_num;
        this.cor[0] = i;
        this.cor[1] = j;
    }

    public int getNum(){
        return this.num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void setCor(int i, int j){
        this.cor[0] = i;
        this.cor[1] = j;
    }

    public boolean merge(Object blocks){
        if(this.equals(blocks)){
            this.num = 2*this.getNum();
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean equals(Object blocks){
        //define compare with diffrent blocks
        if(blocks==null){
            return false;
        }
        else if(getClass()!=blocks.getClass()){
            return false;
        }
        else{
            Blocks my_blocks = (Blocks)blocks;
            return this.num == my_blocks.num;
        }
    }
}
