package com.example.a2048project.Utils;

import android.text.Spannable;

import com.example.a2048project.Factory.BaseFactory;
import com.example.a2048project.Factory.BlocksFactory;
import com.example.a2048project.Game.Step;
import com.example.a2048project.Role.BaseRole;
import com.example.a2048project.Role.Role;

import java.util.ArrayList;
import java.util.Stack;


public class GameFormat extends Format{
    //instance mode, implement all action in this class
    private static GameFormat instance;
    private Role role;
    private BaseFactory blocksFactory;
    private GameFormat(int width, int height){
        super(width, height);
        this.role = new BaseRole();
    }

    public void setRole(Role usr_role){
        this.role = usr_role;
    }

    public void setBlocksFactory(BlocksFactory factory){
        this.blocksFactory = factory;
    }

    public void randomlySummonBlocks(int count){
        //count defines num of blocks to summon
        //always in 1,2,4
        int i;
        for(i=0;i<count;){
            int roll = (int)(Math.random()*100%this.height);
            int column = (int)(Math.random()*100%this.width);
            if(this.getFormat(roll,column)==null){
                continue;//this slot is striked. Generate a new
            }
            double para = Math.random();
            int num = 2;
            if(para<0.4){
                num = 1;
            } else if (para>0.8) {
                num = 4;
            }
            Blocks blocks = this.blocksFactory.createBlocks(num,roll,column);
            this.setFormat(roll,column,blocks);
            i++;
        }
    }

    private int takeUp(){
        int i=0;
        int j=0;
        int score = 0;
        for(;j<this.width;j++){
            Blocks block = this.getDown(i,j);
            while(block!=null){
                this.moveBlock(block,i,j);
                i++;
                Blocks next_block = getDown(block.cor[0]+1,block.cor[1]);
                if(block.merge(next_block)){
                    score += block.getNum();
                    block = getDown(next_block.cor[0]+1,next_block.cor[1]);
                    this.delBlock(next_block);
                }
                else{
                    block = next_block;
                }
            }
        }
        return score;
    }

    private int takeDown(){
        int i=this.height-1;
        int j=0;
        int score = 0;
        for(;j<this.width;j++){
            Blocks block = this.getUp(i,j);
            while(block!=null){
                this.moveBlock(block,i,j);
                i--;
                Blocks next_block = getUp(block.cor[0]-1,block.cor[1]);
                if(block.merge(next_block)){
                    score += block.getNum();
                    block = getUp(next_block.cor[0]-1,next_block.cor[1]);
                    this.delBlock(next_block);
                }
                else{
                    block = next_block;
                }
            }
        }
        return score;
    }

    private int takeLeft(){
        int i=0;
        int j=0;
        int score = 0;
        for(;i<this.height;i++){
            Blocks block = this.getRight(i,j);
            while(block!=null){
                this.moveBlock(block,i,j);
                j++;
                Blocks next_block = getRight(block.cor[0],block.cor[1]+1);
                if(block.merge(next_block)){
                    score += block.getNum();
                    block = getRight(next_block.cor[0],next_block.cor[1]+1);
                    this.delBlock(next_block);
                }
                else{
                    block = next_block;
                }
            }
        }
        return score;
    }

    private int takeRight(){
        int i=0;
        int j=this.width-1;
        int score = 0;
        for(;i<this.height;i++){
            Blocks block = this.getLeft(i,j);
            while(block!=null){
                this.moveBlock(block,i,j);
                j--;
                Blocks next_block = getLeft(block.cor[0],block.cor[1]-1);
                if(block.merge(next_block)){
                    score += block.getNum();
                    block = getLeft(next_block.cor[0],next_block.cor[1]-1);
                    this.delBlock(next_block);
                }
                else{
                    block = next_block;
                }
            }
        }
        return score;
    }


    public int takeAction(Step.Action action){
        int score = 0;
        switch (action){
            case UP: {
                score = this.takeUp();
                break;
            }
            case DOWN:{
                score = this.takeDown();
                break;
            }
            case RIGHT:{
                score = this.takeRight();
                break;
            }
            case LEFT:{
                score = this.takeLeft();
                break;
            }
        }
        return score;
    }

    public boolean isAvailable(){
        //judge is game over
        ArrayList<Blocks> current_array = (ArrayList<Blocks>) (this.getArray().clone());
        for(Step.Action action :Step.Action.values()){
            int score = this.takeAction(action);
            this.loadArray(current_array);
            if(score==0&&this.numOfBlockes()==this.height*this.width){
                continue;
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public void initFormat(int width,int height){
        super.initFormat(width, height);
        this.role = new BaseRole();
    }

    public static GameFormat getInstance(int width, int height){
        if(instance==null){
            instance = new GameFormat(width,height);
            return instance;
        }
        else{
            return instance;
        }
    }
}
