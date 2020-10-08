package kalorian.baiwen.element;

import kalorian.baiwen.element.player.*;
import kalorian.baiwen.frame.MainFrame;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Player {

    private String name;
    private int HP;
    private int defense;

    private boolean isFirst;
    private boolean isInTurn;
    private int fires;
    private int promptTime;
    private int attackTime;

    private Shikigamies shikigamies;
    private Library library;
    private Hand hand;
    private PrepareArea prepareArea;
    private BattleArea battleArea;

    private Player hostilePlayer;
    private MainFrame belongs;

    public Player() {
        setHP(30);
        setDefense(0);
        setFires(0);

        setShikigamies(new Shikigamies());
        getShikigamies().getShikigami1().setBelongs(this);
        getShikigamies().getShikigami2().setBelongs(this);
        getShikigamies().getShikigami3().setBelongs(this);
        getShikigamies().getShikigami4().setBelongs(this);


    }

    public void drawCard(int count){
        belongs.getMessage().append(getName()+"抽了"+count+"张牌\n");
        for(int i=0;i<count;i++){
            if(getHand().size()<getHand().maxHand){
                int index=(int)(Math.random()*getLibrary().size());
                Card card=library.get(index);
                library.remove(index);
                hand.add(card);
                hand.remove(hand.lastIndexOf(null));
            }else {
                belongs.getMessage().append(getName()+"爆牌了\n");
            }
        }
        belongs.reload();
    }

    public void addCard(Card card){
        if(getHand().size()<getHand().maxHand){
            hand.add(card);
            hand.remove(hand.lastIndexOf(null));
            belongs.reload();
            belongs.getMessage().append(getName()+"获得了["+card.getName()+"]\n");
        }else {
            belongs.getMessage().append(getName()+"爆牌了\n");
        }
        belongs.reload();
    }

    public void getHurt(int damaged){
        int currentHP=getHP();
        if(getHP()>damaged){
            setHP(getHP()-damaged);
            getBelongs().getMessage().append(getName()+"HP:"+currentHP+"->"+getHP()+"\n");
        }else {
            die();
        }
    }

    public void die(){
        getBelongs().getMessage().append(getName()+"输掉了游戏\n");
    }

    public void getHurt(){

    }

    public void startTurn(){

    }

    public void endTurn(){

    }

    public void levelUp(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        int origin=getDefense();
        this.defense = defense;
        belongs.getMessage().append(name+"的护盾："+origin+"->"+defense+"\n");
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isInTurn() {
        return isInTurn;
    }

    public void setInTurn(boolean inTurn) {
        isInTurn = inTurn;
    }

    public int getFires() {
        return fires;
    }

    public void setFires(int fires) {
        int origin=getFires();
        this.fires = fires;
        belongs.getMessage().append(name+"的明灯数量："+origin+"->"+fires+"\n");
    }

    public int getPromptTime() {
        return promptTime;
    }

    public void setPromptTime(int promptTime) {
        this.promptTime = promptTime;
    }

    public int getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(int attackTime) {
        this.attackTime = attackTime;
    }

    public Shikigamies getShikigamies() {
        return shikigamies;
    }

    public void setShikigamies(Shikigamies shikigamies) {
        this.shikigamies = shikigamies;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public PrepareArea getPrepareArea() {
        return prepareArea;
    }

    public void setPrepareArea(PrepareArea prepareArea) {
        this.prepareArea = prepareArea;
    }

    public BattleArea getBattleArea() {
        return battleArea;
    }

    public void setBattleArea(BattleArea battleArea) {
        this.battleArea = battleArea;
    }

    public Player getHostilePlayer() {
        return hostilePlayer;
    }

    public void setHostilePlayer(Player hostilePlayer) {
        this.hostilePlayer = hostilePlayer;
    }

    public MainFrame getBelongs() {
        return belongs;
    }

    public void setBelongs(MainFrame belongs) {
        this.belongs = belongs;
    }
}
