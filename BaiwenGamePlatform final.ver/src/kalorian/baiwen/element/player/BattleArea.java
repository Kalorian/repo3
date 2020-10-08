package kalorian.baiwen.element.player;

import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;

import java.util.ArrayList;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class BattleArea extends ArrayList<Shikigami> {

    private Player belongs;

    public BattleArea() {

    }

    public Player getBelongs() {
        return belongs;
    }

    public void setBelongs(Player belongs) {
        this.belongs = belongs;
    }
}
