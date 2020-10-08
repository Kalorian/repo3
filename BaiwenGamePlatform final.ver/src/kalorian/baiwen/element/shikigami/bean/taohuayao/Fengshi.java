package kalorian.baiwen.element.shikigami.bean.taohuayao;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.player.Shikigamies;
import kalorian.baiwen.element.shikigami.Form;

import java.util.ArrayList;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Fengshi extends Form {

    public Fengshi() {
        super();
        setInForm(true);
        setName("丰实");
        setDescribe("入场和己方回合开始时，随机为一个己方受伤式神恢复3点生命。");
        setBasicATK(3);
        setBasicmaxHP(7);
    }

    @Override
    public void approach() {
        ArrayList<Shikigami> hurtedShikigami=new ArrayList<Shikigami>();
        Shikigamies shikigamies = getBelongs().getBelongs().getShikigamies();
        for(Shikigami shikigami:shikigamies){
            if(shikigami.getCurrentHP()<shikigami.getCurrentmaxHP()){
                hurtedShikigami.add(shikigami);
            }
        }
        int size=hurtedShikigami.size();
        if(size!=0){
            int index=(int)(Math.random()*size);
            Shikigami shikigami=shikigamies.get(index);
            getBelongs().heal(3,shikigami);
        }
    }

    @Override
    public void turnStart() {
        ArrayList<Shikigami> hurtedShikigami=new ArrayList<Shikigami>();
        Shikigamies shikigamies = getBelongs().getBelongs().getShikigamies();
        for(Shikigami shikigami:shikigamies){
            if(shikigami.getCurrentHP()<shikigami.getCurrentmaxHP()){
                hurtedShikigami.add(shikigami);
            }
        }
        int size=hurtedShikigami.size();
        if(size!=0){
            int index=(int)(Math.random()*size);
            Shikigami shikigami=shikigamies.get(index);
            getBelongs().heal(3,shikigami);
        }
    }
}
