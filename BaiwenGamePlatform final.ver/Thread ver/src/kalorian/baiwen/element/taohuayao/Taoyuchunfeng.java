package kalorian.baiwen.element.taohuayao;

import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.element.card.MagicCard;

/**
 * description:
 * author:
 * time:2020/9/1
 */
public class Taoyuchunfeng extends MagicCard {

    public Taoyuchunfeng() {
        super();
        setName("桃语春风");
        setLevel(2);
        setDescribe("复活一个己方式神并使其获得迅捷。");
        setHasTarget(true);
        setHasOwnShikigamiTarget(true);
    }

    @Override
    public void execution(Shikigami target) {
        target.resurrection();
        target.getBuff().setFast(true);
        super.execution(target);
    }
}
