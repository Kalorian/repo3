package kalorian.baiwen.element.player;

import kalorian.baiwen.element.Shikigami;

import java.util.ArrayList;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class PrepareArea extends ArrayList<Shikigami> {

    public PrepareArea(Shikigamies shikigamies){
        addAll(shikigamies);
    }
}
