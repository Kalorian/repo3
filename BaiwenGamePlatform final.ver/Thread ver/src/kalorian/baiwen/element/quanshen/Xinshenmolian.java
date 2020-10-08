package kalorian.baiwen.element.quanshen;

import kalorian.baiwen.element.card.MagicCard;
import kalorian.baiwen.element.shikigami.bean.Quanshen;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Xinshenmolian extends MagicCard {

    private Quanshen belongs;

    public Xinshenmolian() {
        super();
        setName("心身磨炼");
        setDescribe("使犬神永久+1ATK和+1HP。");
        setLevel(1);
    }

    @Override
    public void execution() {
        getBelongs().setXinshenmolianTimes(getBelongs().getXinshenmolianTimes()+1);
        getBelongs().setCurrentHP(getBelongs().getCurrentHP()+1);
        super.execution();
    }

    @Override
    public Quanshen getBelongs() {
        return belongs;
    }

    public void setBelongs(Quanshen belongs) {
        this.belongs = belongs;
    }
}
