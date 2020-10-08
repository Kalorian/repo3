package kalorian.baiwen.element.shikigami;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Resurrection {

    private boolean isDie;
    private int cutdown;

    public Resurrection(){
        setDie(false);
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public int getCutdown() {
        return cutdown;
    }

    public void setCutdown(int cutdown) {
        this.cutdown = cutdown;
    }
}
