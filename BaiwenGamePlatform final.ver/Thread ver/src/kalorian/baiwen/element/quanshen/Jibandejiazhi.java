package kalorian.baiwen.element.quanshen;

import kalorian.baiwen.element.card.MagicCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Jibandejiazhi extends MagicCard {

    public Jibandejiazhi() {
        super();
        setName("羁绊的价值");
        setDescribe("恢复犬神所有生命。");
        setLevel(1);
    }

    @Override
    public void execution() {
        getBelongs().setCurrentHP(getBelongs().getCurrentmaxHP());
        super.execution();
    }
}
