package kalorian.baiwen.element.shikigami.bean;

import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.fenghuanghuo.Fenghuo;
import kalorian.baiwen.element.card.fenghuanghuo.Fengming;
import kalorian.baiwen.element.card.fenghuanghuo.Fenyu;
import kalorian.baiwen.element.card.fenghuanghuo.Ruixiang;
import kalorian.baiwen.element.shikigami.bean.fenghuanghuo.Basic;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Fenghuanghuo extends Shikigami {

    public Fenghuanghuo() {
        super();
        setName("凤凰火");
        setDescribe("当凤凰火使用法术牌时，投射：造成1点伤害。");

        setCard1(new Fengming());
        setCard2(new Fengming());
        setCard3(new Ruixiang());
        setCard4(new Ruixiang());
        setCard5(new Fenyu());
        setCard6(new Fenyu());
        setCard7(new Fenghuo());
        setCard8(new Fenghuo());

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
    public void takeNonBattleDamage(int damage, Shikigami target) {
        if(getForm().getName().equals("焚羽")){
            super.takeNonBattleDamage(damage+1, target);
        }else{
            super.takeNonBattleDamage(damage, target);
        }
    }

    @Override
    public void takeNonBattleDamage(int damage,Player target) {
        if(getForm().getName().equals("焚羽")){
            super.takeNonBattleDamage(damage+1,target);
        }else{
            super.takeNonBattleDamage(damage,target);
        }
    }
}
