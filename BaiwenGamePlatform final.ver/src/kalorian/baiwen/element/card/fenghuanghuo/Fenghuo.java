package kalorian.baiwen.element.card.fenghuanghuo;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.MagicCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Fenghuo extends MagicCard {

    public Fenghuo() {
        super();
        setName("凤火");
        setDescribe("对一个式神造成5点伤害。");
        setLevel(2);
        setHasTarget(true);
        setHasHostileShikigamiTarget(true);
        setHasOwnShikigamiTarget(true);
    }

    @Override
    public void execution(Shikigami target) {
        getBelongs().takeNonBattleDamage(5,target);
        getBelongs().cast(1);
        super.execution(target);
    }
}
