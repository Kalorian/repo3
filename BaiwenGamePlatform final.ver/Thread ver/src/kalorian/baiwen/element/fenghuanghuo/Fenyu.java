package kalorian.baiwen.element.fenghuanghuo;

import kalorian.baiwen.element.card.FormCard;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class Fenyu extends FormCard {

    public Fenyu() {
        super();
        setName("焚羽");
        setLevel(2);
        setDescribe("凤凰火造成的所有非战斗伤害+1。");
        setForm(new kalorian.baiwen.element.shikigami.bean.fenghuanghuo.Fenyu());
    }

}
