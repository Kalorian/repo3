package kalorian.baiwen.element.shikigami.bean;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.shikigami.bean.quanshen.Basic;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Quanshen extends Shikigami {

    private int XinshenmolianTimes;

    public Quanshen(){
        super();
        setName("犬神");
        setDescribe("当犬神升级时，将一张【心身磨炼】置入手牌。");
        setXinshenmolianTimes(0);

        setCard1(new Jibandejiazhi());
        setCard2(new Jibandejiazhi());
        setCard3(new Xinzhan());
        setCard4(new Xinzhan());
        setCard5(new Ejizhan());
        setCard6(new Ejizhan());
        setCard7(new Xinjiyiti());
        setCard8(new Xinjiyiti());

        getCard1().setBelongs(this);
        getCard2().setBelongs(this);
        getCard3().setBelongs(this);
        getCard4().setBelongs(this);
        getCard5().setBelongs(this);
        getCard6().setBelongs(this);
        getCard7().setBelongs(this);
        getCard8().setBelongs(this);

        setForm(new Basic());

        setCurrentATK(getForm().getBasicATK()+getBuff().getBuffATK()+getXinshenmolianTimes());
        setCurrentmaxHP(getForm().getBasicmaxHP()+getBuff().getBuffmaxHP()+getXinshenmolianTimes());
        setCurrentHP(getCurrentmaxHP());
    }

    @Override
    public void levelUp() {
        super.levelUp();
        getBelongs().addCard(new Xinshenmolian());
        getBelongs().getBelongs().getMessage().append(getBelongs().getName()+"获得了一张【心身磨炼】\n");
    }

    @Override
    public void battle(int addATK, int addDefense) {
        if(getForm().getName().equals("心技一体")){
            super.battle(addATK+XinshenmolianTimes, addDefense+XinshenmolianTimes);
        }else{
            super.battle(addATK, addDefense);
        }
    }

    public int getXinshenmolianTimes() {
        return XinshenmolianTimes;
    }

    public void setXinshenmolianTimes(int xinshenmolianTimes) {
        XinshenmolianTimes = xinshenmolianTimes;
    }
}
