package kalorian.baiwen.element.player;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Shikigami;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * description:
 * author:
 * time:2020/9/7
 */
public class Library extends HashMap<Integer,Card> {

    private ArrayList<Integer> count;

    public Library(Shikigamies shikigamies){
        int i=0;
        for(Shikigami shikigami:shikigamies){
            put(i,shikigami.getCard1());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard2());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard3());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard4());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard5());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard6());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard7());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
            put(i,shikigami.getCard8());
            shikigami.getCard1().setLibraryIndex(i);
            i++;
        }
        count=new ArrayList<>();
        for(int j=0;j<this.size();j++){
            count.add(j);
        }
    }

    public Card extract(){
        int index=(int)(Math.random()*count.size());
        Card card=get(index);
        count.remove(index);

        return card;
    }

    public ArrayList<Integer> getCount() {
        return count;
    }

    public void setCount(ArrayList<Integer> count) {
        this.count = count;
    }
}
