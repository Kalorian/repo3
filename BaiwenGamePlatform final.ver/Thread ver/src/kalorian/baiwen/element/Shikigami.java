package kalorian.baiwen.element;

import kalorian.baiwen.element.shikigami.Buff;
import kalorian.baiwen.element.shikigami.Form;
import kalorian.baiwen.element.shikigami.Resurrection;

import java.io.IOException;

/**
 * description:
 * author:
 * time:2020/9/7
 */
public class Shikigami {

    private String name;
    private String describe;
    private int level;
    private int currentATK;
    private int currentHP;
    private int currentMaxHP;
    private int defense;
    private int number;

    private Resurrection resurrection;
    private Buff buff;
    private Form form;

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;

    private Player belongs;

    public Shikigami() {
        setLevel(0);

    }

    public void attack(){
        if(getBuff().isFast()){
            getBuff().setFast(false);
        }else {
            getBelongs().setFires(getBelongs().getFires()-1);
        }
        battle(0,0);
    }

    public void battle(int addATK,int addDefense){
        belongs.getBelongs().getMessage().append(name+"发动了攻击\n");
        setDefense(getDefense());
        if(!belongs.getBattleArea().isEmpty()){
            getBelongs().getBattleArea().get(0).goOutBattleArea();
        }
        goInBattleArea();
        if(belongs.getHostilePlayer().getBattleArea().size()==0){
            takeBattleDamage(getCurrentATK()+addATK,getBelongs().getHostilePlayer());
        }else {
            int damaged=getBelongs().getHostilePlayer().getBattleArea().get(0).getCurrentATK();
            takeBattleDamage(getCurrentATK()+addATK,getBelongs().getHostilePlayer().getBattleArea().get(0));
            getHurt(damaged);
        }
        try {
            belongs.getBelongs().getDataOutputStream().writeUTF("battle:"+number+","+addATK+","+addDefense);
        } catch (IOException e) {
            e.printStackTrace();
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

    }



    public void levelUp(){
        int origin=getLevel();
        setLevel(origin+1);
        belongs.getBelongs().getMessage().append(name+"的等级："+origin+"->"+getLevel()+"\n");
    }

    public void heal(int count,Shikigami target){
        if(target.getCurrentHP()+count>target.getCurrentMaxHP()){
            target.setCurrentHP(target.getCurrentMaxHP());
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

    public void resurrection(){
        if(resurrection.isDie()){
            int origin=resurrection.getCutdown();
            resurrection.setCutdown(origin-1);
            belongs.getBelongs().getMessage().append(name+"的复活倒计时："+origin+"->"+resurrection.getCutdown()+"\n");
            if(resurrection.getCutdown()==0){
                getResurrection().setDie(false);
                belongs.getBelongs().getMessage().append(name+"复活了\n");
            }
        }
    }

    public void resurrection(Shikigami target){
        target.getResurrection().setDie(false);
    }

    public void getNewForm(Form form){
        setForm(form);
        setCurrentMaxHP(buff.getBuffmaxHP()+form.getBasicmaxHP());
        setCurrentHP(getCurrentMaxHP());
        setCurrentATK(buff.getBuffATK()+form.getBasicATK());
        form.approach();
        belongs.getBelongs().reload();
    }

    public void goInBattleArea(){
        getBelongs().getBattleArea().add(this);
        getBelongs().getPrepareArea().remove(this);
    }

    public void goOutBattleArea(){
        getBelongs().getPrepareArea().addAll(getBelongs().getBattleArea());
        getBelongs().getBattleArea().clear();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentATK() {
        return currentATK;
    }

    public void setCurrentATK(int currentATK) {
        this.currentATK = currentATK;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getCurrentMaxHP() {
        return currentMaxHP;
    }

    public void setCurrentMaxHP(int currentMaxHP) {
        this.currentMaxHP = currentMaxHP;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Resurrection getResurrection() {
        return resurrection;
    }

    public void setResurrection(Resurrection resurrection) {
        this.resurrection = resurrection;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
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

    public Player getBelongs() {
        return belongs;
    }

    public void setBelongs(Player belongs) {
        this.belongs = belongs;
    }
}
