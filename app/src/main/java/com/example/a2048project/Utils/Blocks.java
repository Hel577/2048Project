package com.example.a2048project.Utils;

public class Blocks {
    public int num;
    public int[] cor;
    public boolean is_new;//Show is the Block updated(For dynamic function in UI)

    public Blocks(int init_num,int i, int j){
        this.cor = new int[2];
        this.num = init_num;
        this.cor[0] = i;
        this.cor[1] = j;
        this.is_new = true;
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

    public void setIs_new(Boolean is_new){
        this.is_new = is_new;
    }

    public boolean merge(Object blocks){
        if(this.equals(blocks)){
            this.num = 2*this.getNum();
            this.is_new = true;
            return true;
        }
        else{
            this.is_new = false;
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
