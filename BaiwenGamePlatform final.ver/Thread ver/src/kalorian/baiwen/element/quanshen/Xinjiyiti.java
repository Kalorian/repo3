package kalorian.baiwen.element.quanshen;

import kalorian.baiwen.element.card.FormCard;

/**
 * description:
 * author:
 * time:2020/9/1
 */
public class Xinjiyiti extends FormCard {

    public Xinjiyiti() {
        super();
        setName("心技一体");
        setDescribe("增强：本局游戏你每使用过一张【羁绊的价值】，犬神的战斗牌便额外获得+1ATK和+1DEF。");
        setLevel(3);
        setForm(new kalorian.baiwen.element.shikigami.bean.quanshen.Xinjiyiti());
    }
}
