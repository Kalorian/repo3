package kalorian.baiwen.element.shikigami.bean;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.taohuayao.Fengshi;
import kalorian.baiwen.element.card.taohuayao.Shengkai;
import kalorian.baiwen.element.card.taohuayao.Taoyuchunfeng;
import kalorian.baiwen.element.card.taohuayao.Taozhixinxi;
import kalorian.baiwen.element.shikigami.bean.taohuayao.Basic;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Taohuayao extends Shikigami {

    public Taohuayao() {
        super();
        setName("桃花妖");
        setDescribe("桃花妖治疗或复活己方式神时，使该式神攻+1。");

        setCard1(new Taozhixinxi());
        setCard2(new Taozhixinxi());
        setCard3(new Fengshi());
        setCard4(new Fengshi());
        setCard5(new Taoyuchunfeng());
        setCard6(new Taoyuchunfeng());
        setCard7(new Shengkai());
        setCard8(new Shengkai());

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
        setCurrentMaxHP(getForm().getBasicmaxHP()+getBuff().getBuffmaxHP());
        setCurrentHP(getCurrentMaxHP());
    }

    @Override
    public void resurrection(Shikigami target) {
        super.resurrection(target);
        target.getBuff().setBuffATK(target.getBuff().getBuffATK()+1);
    }

    @Override
    public void heal(int count, Shikigami target) {
        super.heal(count, target);
        target.getBuff().setBuffATK(target.getBuff().getBuffATK()+1);
    }
}
