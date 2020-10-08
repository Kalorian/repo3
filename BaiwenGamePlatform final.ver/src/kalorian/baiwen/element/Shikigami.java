package kalorian.baiwen.element;

import kalorian.baiwen.element.shikigami.Buff;
import kalorian.baiwen.element.shikigami.Form;
import kalorian.baiwen.element.shikigami.Resurrection;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Shikigami {

    private String name;
    private String describe;
    private Player belongs;

    private int level;

    private Form form;
    private Buff buff;
    private Resurrection resurrection;
    private boolean isInBattleArea;

    private int currentATK;
    private int currentmaxHP;
    private int currentHP;
    private int defense;

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;

    public Shikigami() {
        setBuff(new Buff());
        setResurrection(new Resurrection());
        setLevel(0);
        setDefense(0);
    }

    public void levelUp(){
        level+=1;
    }

    public void attack(){
        if(getBuff().isFast()){
            getBuff().setFast(false);
        }else {
            getBelongs().setFire(getBelongs().getFire()-1);
        }
        battle(0,0);
    }

    public void battle(int addATK,int addDefense){
        setDefense(getDefense());
        if(!getBelongs().getBattleArea().isEmpty()){
            getBelongs().getBattleArea().get(0).goOutBattleArea();
        }
        goInBattleArea();
        if(getBelongs().getHostilePlayer().getBattleArea().size()==0){
            takeBattleDamage(getCurrentATK()+addATK,getBelongs().getHostilePlayer());
        }else {
            int damaged=getBelongs().getHostilePlayer().getBattleArea().get(0).getCurrentATK();
            takeBattleDamage(getCurrentATK()+addATK,getBelongs().getHostilePlayer().getBattleArea().get(0));
            getHurt(damaged);
        }
        belongs.getBelongs().reload();
    }

    public void takeBattleDamage(int damage,Shikigami target){
        target.getHurt(damage);
    }

    public void takeBattleDamage(int damage,Player target){
        target.getHurt(damage);
    }

    public void takeNonBattleDamage(int damage,Shikigami target){
        target.getHurt(damage);
    }

    public void takeNonBattleDamage(int damage,Player target){
        target.getHurt(damage);
    }

    public void getHurt(int damaged){
        if(getCurrentHP()>damaged){
            setCurrentHP(getCurrentHP()-damaged);
        }else {
            die(3);
        }
        belongs.getBelongs().reload();
    }

    public void heal(int count,Shikigami target){
        if(target.getCurrentHP()+count>target.getCurrentmaxHP()){
            target.setCurrentHP(target.getCurrentmaxHP());
        }else {
            target.setCurrentHP(target.getCurrentHP()+count);
        }
        belongs.getBelongs().reload();
    }

    public void heal(int count,Player target){
        if(target.getHP()+count>30){
            target.setHP(30);
        }else {
            target.setHP(target.getHP()+count);
        }
        belongs.getBelongs().reload();
    }

    public void cast(int damage){
        if(getBelongs().getHostilePlayer().getBattleArea().isEmpty()){
            takeNonBattleDamage(damage,getBelongs().getHostilePlayer());
        }
    }

    public void die(int cutdown){
        getResurrection().setDie(true);
        getResurrection().setCutdown(cutdown);
        setForm(null);
        if(isInBattleArea){
            goOutBattleArea();
        }
        belongs.getBelongs().reload();
    }

    public void resurrection(){
        if(resurrection.isDie()){
            resurrection.setCutdown(resurrection.getCutdown()-1);
            if(resurrection.getCutdown()==0){
                getResurrection().setDie(false);
            }
        }
    }

    public void resurrection(Shikigami target){
        target.getResurrection().setDie(false);
    }

    public void getNewForm(Form form){
        setForm(form);
        setCurrentmaxHP(buff.getBuffmaxHP()+form.getBasicmaxHP());
        setCurrentHP(getCurrentmaxHP());
        setCurrentATK(buff.getBuffATK()+form.getBasicATK());
        form.approach();
        belongs.getBelongs().reload();
    }

    public void exitForm(Form form){

    }

    public void goInBattleArea(){
        getBelongs().getBattleArea().add(this);
        getBelongs().getPrepareArea().remove(this);

    }

    public void goOutBattleArea(){
        getBelongs().getPrepareArea().addAll(getBelongs().getBattleArea());
        getBelongs().getBattleArea().clear();

    }

    @Override
    public String toString() {
        if(resurrection.isDie()){
            return "["+getName()+"]("+getLevel()+")(已气绝 复活倒计时："+resurrection.getCutdown()+")";
        }else{
            return getBuff()+"["+getName()+"]("+getLevel()+")[攻"+currentATK+"/血"+currentHP+"("+
                    currentmaxHP+")/盾"+defense+"]"+describe+getForm();
        }
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

    public Player getBelongs() {
        return belongs;
    }

    public void setBelongs(Player belongs) {
        this.belongs = belongs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Resurrection getResurrection() {
        return resurrection;
    }

    public void setResurrection(Resurrection resurrection) {
        this.resurrection = resurrection;
    }

    public boolean isInBattleArea() {
        return isInBattleArea;
    }

    public void setInBattleArea(boolean inBattleArea) {
        isInBattleArea = inBattleArea;
    }

    public int getCurrentATK() {
        return currentATK;
    }

    public void setCurrentATK(int currentATK) {
        this.currentATK = currentATK;
    }

    public int getCurrentmaxHP() {
        return currentmaxHP;
    }

    public void setCurrentmaxHP(int currentmaxHP) {
        this.currentmaxHP = currentmaxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public Card getCard3() {
        return card3;
    }

    public void setCard3(Card card3) {
        this.card3 = card3;
    }

    public Card getCard4() {
        return card4;
    }

    public void setCard4(Card card4) {
        this.card4 = card4;
    }

    public Card getCard5() {
        return card5;
    }

    public void setCard5(Card card5) {
        this.card5 = card5;
    }

    public Card getCard6() {
        return card6;
    }

    public void setCard6(Card card6) {
        this.card6 = card6;
    }

    public Card getCard7() {
        return card7;
    }

    public void setCard7(Card card7) {
        this.card7 = card7;
    }

    public Card getCard8() {
        return card8;
    }

    public void setCard8(Card card8) {
        this.card8 = card8;
    }
}
