package kalorian.baiwen.element.shikigami;

import kalorian.baiwen.element.Shikigami;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Form {

    private boolean isInForm;
    private String name;
    private String describe;

    private int basicATK;
    private int basicmaxHP;

    private Shikigami belongs;

    public Form() {

    }

    public void approach(){
//      getNewForm和approach关联
    }

    public void exit(){

    }

    public void turnStart(){

    }

    @Override
    public String toString() {
        if(isInForm){
            return "(形态："+getName()+" "+getDescribe()+")";
        }else {
            return "(形态：无)";
        }
    }

    public boolean isInForm() {
        return isInForm;
    }

    public void setInForm(boolean inForm) {
        isInForm = inForm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getBasicATK() {
        return basicATK;
    }

    public void setBasicATK(int basicATK) {
        this.basicATK = basicATK;
    }

    public int getBasicmaxHP() {
        return basicmaxHP;
    }

    public void setBasicmaxHP(int basicmaxHP) {
        this.basicmaxHP = basicmaxHP;
    }

    public Shikigami getBelongs() {
        return belongs;
    }

    public void setBelongs(Shikigami belongs) {
        this.belongs = belongs;
    }
}
