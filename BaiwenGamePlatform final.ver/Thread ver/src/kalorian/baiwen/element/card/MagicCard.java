package kalorian.baiwen.element.card;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class MagicCard extends Card {

    private boolean hasHostileShikigamiTarget;
    private boolean hasHostilePlayerTarget;
    private boolean hasOwnShikigamiTarget;
    private boolean hasOwnPlayerTarget;

    public MagicCard(){
        super();
        setType("法术牌");
        setHasHostileShikigamiTarget(false);
        setHasHostilePlayerTarget(false);
        setHasOwnShikigamiTarget(false);
        setHasOwnPlayerTarget(false);
    }

    @Override
    public void execution() {
        getBelongs().getBelongs().getBelongs().getMessage().append(getBelongs().getName()+
                "使用了["+getName()+"]\n");
    }

    @Override
    public void execution(Shikigami target) {
        getBelongs().getBelongs().getBelongs().getMessage().append(getBelongs().getName()+
                "对"+target+"使用了["+getName()+"]\n");
    }

    @Override
    public void execution(Player target) {
        getBelongs().getBelongs().getBelongs().getMessage().append(getBelongs().getName()+
                "对"+target+"使用了["+getName()+"]\n");
    }

    @Override
    public String toString() {
        return getType()+"["+getName()+"]("+getLevel()+"勾)"+getDescribe();
    }

    public boolean isHasHostileShikigamiTarget() {
        return hasHostileShikigamiTarget;
    }

    public void setHasHostileShikigamiTarget(boolean hasHostileShikigamiTarget) {
        this.hasHostileShikigamiTarget = hasHostileShikigamiTarget;
    }

    public boolean isHasHostilePlayerTarget() {
        return hasHostilePlayerTarget;
    }

    public void setHasHostilePlayerTarget(boolean hasHostilePlayerTarget) {
        this.hasHostilePlayerTarget = hasHostilePlayerTarget;
    }

    public boolean isHasOwnShikigamiTarget() {
        return hasOwnShikigamiTarget;
    }

    public void setHasOwnShikigamiTarget(boolean hasOwnShikigamiTarget) {
        this.hasOwnShikigamiTarget = hasOwnShikigamiTarget;
    }

    public boolean isHasOwnPlayerTarget() {
        return hasOwnPlayerTarget;
    }

    public void setHasOwnPlayerTarget(boolean hasOwnPlayerTarget) {
        this.hasOwnPlayerTarget = hasOwnPlayerTarget;
    }
}
