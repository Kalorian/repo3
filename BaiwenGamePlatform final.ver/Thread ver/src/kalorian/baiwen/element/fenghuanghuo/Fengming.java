package kalorian.baiwen.element.fenghuanghuo;

import kalorian.baiwen.element.card.MagicCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Fengming extends MagicCard {

    public Fengming(){
        setName("凤鸣");
        setDescribe("瞬发、对敌方牌手造成2点伤害。");
        setLevel(1);
        setPrompt(true);

    }

    @Override
    public void execution() {
        getBelongs().cast(2);
        getBelongs().cast(1);
        super.execution();
    }
}
