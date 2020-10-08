package kalorian.baiwen.element.shikigami;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Buff {

    private int buffATK;
    private int buffmaxHP;
    private boolean isFast;

    public Buff(){
        setBuffATK(0);
        setBuffmaxHP(0);
        setFast(false);
    }

    @Override
    public String toString() {
        String str="（特殊效果：";
        if(isFast()){
            str+="[迅捷]";
        }else {
            str+="空";
        }
        str+="）";
        return str;
    }

    public int getBuffATK() {
        return buffATK;
    }

    public void setBuffATK(int buffATK) {
        this.buffATK = buffATK;
    }

    public int getBuffmaxHP() {
        return buffmaxHP;
    }

    public void setBuffmaxHP(int buffmaxHP) {
        this.buffmaxHP = buffmaxHP;
    }

    public boolean isFast() {
        return isFast;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }
}
