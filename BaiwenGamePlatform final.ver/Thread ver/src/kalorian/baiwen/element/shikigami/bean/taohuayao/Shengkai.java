package kalorian.baiwen.element.shikigami.bean.taohuayao;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.player.Shikigamies;
import kalorian.baiwen.element.shikigami.Form;

import java.io.IOException;
import java.util.ArrayList;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Shengkai extends Form {

    public Shengkai() {
        super();
        setInForm(true);
        setName("盛开");
        setDescribe("入场和己方回合开始时，随机为一个己方受伤式神恢复2点生命，将此过程再重复两次。");
        setBasicATK(4);
        setBasicmaxHP(9);
    }

    public void effect(){
        for(int i=0;i<3;i++){
            ArrayList<Shikigami> hurtedShikigami=new ArrayList<Shikigami>();
            Shikigamies shikigamies = getBelongs().getBelongs().getShikigamies();
            for(Shikigami shikigami:shikigamies){
                if(shikigami.getCurrentHP()<shikigami.getCurrentMaxHP()){
                    hurtedShikigami.add(shikigami);
                }
            }
            int size=hurtedShikigami.size();
            if(size!=0){
                int index=(int)(Math.random()*size);
                Shikigami shikigami=shikigamies.get(index);
                getBelongs().heal(2,shikigami);
            }

        }
    }

    @Override
    public void approach() {
        effect();
    }

    @Override
    public void turnStart() {
        effect();
    }

}
