package kalorian.baiwen.element.player;

import kalorian.baiwen.element.Card;

import java.util.ArrayList;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Hand extends ArrayList<Card> {

    public final int maxHand=12;

    public Hand(){
        for(int i=0;i<maxHand;i++){
            add(null);
        }
    }

    @Override
    public int size() {
        int count=super.size();
        int nullCount=0;
        for(Object object:this){
            if(object==null){
                nullCount+=1;
            }
        }
        return count-nullCount;
    }

    @Override
    public boolean add(Card card) {
        if(this.size()<12){
            return super.add(card);
        }
        return false;
    }

}
