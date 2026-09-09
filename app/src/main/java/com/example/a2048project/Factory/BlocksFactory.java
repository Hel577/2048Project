package com.example.a2048project.Factory;

import com.example.a2048project.Utils.Blocks;

public class BlocksFactory extends BaseFactory{
    public BlocksFactory(){
        super();
    }

    @Override
    public Blocks createBlocks(int num,int i,int j){
        return new Blocks(num,i,j);
    }
}
