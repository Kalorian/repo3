package kalorian.baiwen.element.player;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * description:
 * author:
 * time:2020/9/7
 */
public class Hand extends ArrayList<Card> {

    public final int maxHand=12;
    private Player belongs;

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

    /*@Override
    public boolean add(Card card) {
        if(this.size()<12){

            return super.add(card);
        }
        belongs.getBelongs().getMessage().append(belongs.getName()+"爆牌了\n");
        return false;
    }*/

    public void addCard(Card card){
        set(size(),card);
    }

    public void addCard(ArrayList<Card> list){
        for(Card card:list){
            set(size(),card);
        }
    }

    public void removeCard(Card card){
        remove(card);
        add(null);
    }

    public Player getBelongs() {
        return belongs;
    }

    public void setBelongs(Player belongs) {
        this.belongs = belongs;
    }
}
