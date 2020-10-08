package kalorian.baiwen.element.taohuayao;

import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.MagicCard;

/**
 * description:
 * author:
 * time:2020/9/1
 */
public class Taozhixinxi extends MagicCard {

    public Taozhixinxi() {
        super();
        setName("桃之馨息");
        setLevel(1);
        setDescribe("为一个角色恢复5点生命值。");
        setHasTarget(true);
        setHasHostileShikigamiTarget(true);
        setHasHostilePlayerTarget(true);
        setHasOwnShikigamiTarget(true);
        setHasOwnPlayerTarget(true);
    }

    @Override
    public void execution(Player target) {
        getBelongs().heal(5,target);
        super.execution(target);
    }

    @Override
    public void execution(Shikigami target) {
        getBelongs().heal(5,target);
        super.execution(target);
    }
}
