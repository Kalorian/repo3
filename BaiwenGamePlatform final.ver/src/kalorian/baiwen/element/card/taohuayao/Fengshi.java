package kalorian.baiwen.element.card.taohuayao;

import kalorian.baiwen.element.card.FormCard;

/**
 * description:
 * author:
 * time:2020/9/1
 */
public class Fengshi extends FormCard {

    public Fengshi() {
        super();
        setName("丰实");
        setDescribe("入场和己方回合开始时，随机为一个己方受伤式神恢复3点生命。");
        setLevel(2);
        setForm(new kalorian.baiwen.element.shikigami.bean.taohuayao.Fengshi());
    }
}
