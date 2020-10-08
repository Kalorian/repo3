package kalorian.baiwen.element.card;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.shikigami.Form;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class FormCard extends Card {

    private Form form;

    public FormCard() {
        super();
        setType("形态牌");
    }

    @Override
    public void execution() {
        getBelongs().getNewForm(form);
        getBelongs().getBelongs().getBelongs().getMessage().append(getBelongs().getName()+"获得了形态["+
                getName()+"]\n");
    }

    @Override
    public String toString() {
        return getType()+"["+getName()+"]("+getLevel()+"勾)"+getDescribe()+
                " (攻/"+getForm().getBasicATK()+"血"+getForm().getBasicmaxHP()+")";
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
