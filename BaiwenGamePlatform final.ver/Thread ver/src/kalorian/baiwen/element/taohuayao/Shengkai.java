package kalorian.baiwen.element.taohuayao;

import kalorian.baiwen.element.card.FormCard;

/**
 * description:
 * author:
 * time:2020/9/1
 */
public class Shengkai extends FormCard {

    public Shengkai() {
        super();
        setName("盛开");
        setDescribe("入场和己方回合开始时，随机为一个己方受伤式神恢复2点生命，将此过程再重复两次。");
        setLevel(3);
        setForm(new kalorian.baiwen.element.shikigami.bean.taohuayao.Shengkai());
    }
}
