package kalorian.baiwen.element.card;

import kalorian.baiwen.element.Card;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class BattleCard extends Card {

    private int addATK;
    private int addDefense;

    public BattleCard(){
        super();
        setType("战斗牌");
    }

    @Override
    public void execution() {
        getBelongs().battle(getAddATK(),getAddDefense());
        getBelongs().getBelongs().getBelongs().getMessage().append(getBelongs().getName()+
                "使用了["+getName()+"]\n");
    }

    @Override
    public String toString() {
        return getType()+"["+getName()+"]("+getLevel()+"勾)"+getDescribe()+" (攻/"+getAddATK()+"盾"+getAddDefense()+")";
    }

    public int getAddATK() {
        return addATK;
    }

    public void setAddATK(int addATK) {
        this.addATK = addATK;
    }

    public int getAddDefense() {
        return addDefense;
    }

    public void setAddDefense(int addDefense) {
        this.addDefense = addDefense;
    }
}
