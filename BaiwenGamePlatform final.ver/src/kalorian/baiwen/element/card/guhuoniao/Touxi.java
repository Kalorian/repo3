package kalorian.baiwen.element.card.guhuoniao;

import kalorian.baiwen.element.card.BattleCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Touxi extends BattleCard {

    public Touxi() {
        super();
        setName("偷袭");
        setDescribe("在敌方回合获得瞬发。响应：在敌方回合结束时战斗区没有式神，自动使用此牌。");
        setLevel(2);
        setRespond(true);
        setAddATK(3);
        setAddDefense(0);
    }


}
