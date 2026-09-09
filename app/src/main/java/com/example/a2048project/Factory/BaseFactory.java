package com.example.a2048project.Factory;

import com.example.a2048project.Utils.Blocks;

public abstract class BaseFactory {
    public BaseFactory(){
        return;
    }

    public Blocks createBlocks(int num,int i,int j){
        return new Blocks(num,i,j);
    }

}
