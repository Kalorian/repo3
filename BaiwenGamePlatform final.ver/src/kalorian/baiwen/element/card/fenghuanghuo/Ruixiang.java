package kalorian.baiwen.element.card.fenghuanghuo;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.MagicCard;
import kalorian.baiwen.element.player.Shikigamies;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Ruixiang extends MagicCard {

    public Ruixiang() {
        super();
        setName("瑞翔");
        setDescribe("对所有敌方式神造成1点伤害。");
        setLevel(1);
    }

    @Override
    public void execution() {
        Shikigamies shikigamies = getBelongs().getBelongs().getHostilePlayer().getShikigamies();
        for (Shikigami shikigami:shikigamies){
            if(shikigami.getResurrection().isDie()!=true)
            getBelongs().takeNonBattleDamage(1,shikigami);
        }
        getBelongs().cast(1);
        super.execution();
    }
}
