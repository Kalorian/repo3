package kalorian.baiwen.element.shikigami.bean;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.guhuoniao.Ciwuzhizi;
import kalorian.baiwen.element.card.guhuoniao.Jinluan;
import kalorian.baiwen.element.card.guhuoniao.Sanjian;
import kalorian.baiwen.element.card.guhuoniao.Touxi;
import kalorian.baiwen.element.shikigami.bean.guhuoniao.Basic;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Guhuoniao extends Shikigami {

    public Guhuoniao() {
        super();
        setName("姑获鸟");
        setDescribe("姑获鸟攻击后，退回准备区。");

        setCard1(new Sanjian());
        setCard2(new Sanjian());
        setCard3(new Jinluan());
        setCard4(new Jinluan());
        setCard5(new Touxi());
        setCard6(new Touxi());
        setCard7(new Ciwuzhizi());
        setCard8(new Ciwuzhizi());

        getCard1().setBelongs(this);
        getCard2().setBelongs(this);
        getCard3().setBelongs(this);
        getCard4().setBelongs(this);
        getCard5().setBelongs(this);
        getCard6().setBelongs(this);
        getCard7().setBelongs(this);
        getCard8().setBelongs(this);

        setForm(new Basic());

        setCurrentATK(getForm().getBasicATK()+getBuff().getBuffATK());
        setCurrentmaxHP(getForm().getBasicmaxHP()+getBuff().getBuffmaxHP());
        setCurrentHP(getCurrentmaxHP());
    }

    @Override
    public void battle(int addATK, int addDefense) {
        super.battle(addATK,addDefense);
        if(getResurrection().isDie()==false){
            goOutBattleArea();
        }
    }
}
