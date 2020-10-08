package kalorian.baiwen.element.card.guhuoniao;

import kalorian.baiwen.element.card.FormCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Jinluan extends FormCard {

    public Jinluan() {
        super();
        setName("金鸾");
        setDescribe("当其他己方式神攻击后，本回合此牌获得瞬发。");
        setLevel(2);
        setForm(new kalorian.baiwen.element.shikigami.bean.guhuoniao.Jinluan());
    }
}
