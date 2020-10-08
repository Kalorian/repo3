package kalorian.baiwen.execution;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.player.Hand;

/**
 * description:
 * author:
 * time:2020/9/7
 */
public class Execution {

    public static void main(String[] args) {
        Hand hand=new Hand();
        Card card=new Card();
        System.out.println(hand);
        for(int i=0;i<12;i++){
            hand.addCard(card);
        }
        System.out.println(hand);
    }
}
