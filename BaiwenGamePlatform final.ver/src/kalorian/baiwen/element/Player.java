package kalorian.baiwen.element;

import kalorian.baiwen.element.player.*;
import kalorian.baiwen.frame.LevelUpDialog;
import kalorian.baiwen.frame.MainFrame;

import java.util.ArrayList;

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
    private Player hostilePlayer;

    private int promptTime;
    private int attackTime;
    private int fire;
    private boolean isInTurn;

    private Shikigamies shikigamies;
    private Library library;
    private Hand hand;
    private BattleArea battleArea;
    private PrepareArea prepareArea;
    private ArrayList<Card> temporaryCardList;

    private ArrayList<Card> promptList;
    private ArrayList<Card> respondList;

    private MainFrame belongs;

    public Player(){
        setHP(30);
        setDefense(0);

        setShikigamies(new Shikigamies());
        getShikigamies().getShikigami1().setBelongs(this);
        getShikigamies().getShikigami2().setBelongs(this);
        getShikigamies().getShikigami3().setBelongs(this);
        getShikigamies().getShikigami4().setBelongs(this);

        setAttackTime(0);
        setPromptTime(0);
        setFire(0);

        setLibrary(new Library(getShikigamies()));
        setHand(new Hand());
        setPromptList(new ArrayList<Card>());
        setRespondList(new ArrayList<Card>());

        setBattleArea(new BattleArea());
        getBattleArea().setBelongs(this);
        setPrepareArea(new PrepareArea(getShikigamies()));
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
            belongs.handPanel_reload();
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

    public void startTurn(int turn){
        belongs.getMessage().append(getName()+"的回合开始了\n");
        if(turn==1){
            setFire(1);
        }else {
            setFire(2);
        }
        setDefense(0);
        setAttackTime(1);
        setPromptTime(1);
        if(!getBattleArea().isEmpty()){
            getPrepareArea().addAll(getBattleArea());
            getBattleArea().clear();
        }
        for(Card card:getPromptList()){
            card.setPrompt(false);
        }
        for(Shikigami shikigami:getShikigamies()){
            shikigami.setDefense(0);
            if(shikigami.getForm().getName().equals("丰实")|shikigami.getForm().getName().equals("盛开")){
                shikigami.getForm().turnStart();
            }
        }
        resurrectionCheck();
        drawCard(1);
        levelUp();
        if(turn==6|turn==13){
            levelUp();
        }
        getBelongs().reload();
    }

    public void levelUp(){
        for(Shikigami shikigami:getShikigamies()){
            if(shikigami.getLevel()<3){
                LevelUpDialog levelUpDialog = new LevelUpDialog(this);
                Shikigami shikigami_target=levelUpDialog.getTarget();
                shikigami_target.levelUp();
                break;
            }
        }
        getBelongs().reload();
    }

    public void resurrectionCheck(){
        for(Shikigami shikigami:getShikigamies()){
            shikigami.resurrection();
        }
        getBelongs().reload();
    }

    public void endTurn(){
        belongs.getMessage().append(getName()+"的回合结束了了\n");
        getHostilePlayer().setInTurn(true);
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
        this.defense = defense;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public Player getHostilePlayer() {
        return hostilePlayer;
    }

    public void setHostilePlayer(Player hostilePlayer) {
        this.hostilePlayer = hostilePlayer;
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

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public boolean isInTurn() {
        return isInTurn;
    }

    public void setInTurn(boolean inTurn) {
        isInTurn = inTurn;
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

    public BattleArea getBattleArea() {
        return battleArea;
    }

    public void setBattleArea(BattleArea battleArea) {
        this.battleArea = battleArea;
    }

    public PrepareArea getPrepareArea() {
        return prepareArea;
    }

    public void setPrepareArea(PrepareArea prepareArea) {
        this.prepareArea = prepareArea;
    }

    public ArrayList<Card> getTemporaryCardList() {
        return temporaryCardList;
    }

    public void setTemporaryCardList(ArrayList<Card> temporaryCardList) {
        this.temporaryCardList = temporaryCardList;
    }

    public ArrayList<Card> getPromptList() {
        return promptList;
    }

    public void setPromptList(ArrayList<Card> promptList) {
        this.promptList = promptList;
    }

    public ArrayList<Card> getRespondList() {
        return respondList;
    }

    public void setRespondList(ArrayList<Card> respondList) {
        this.respondList = respondList;
    }

    public MainFrame getBelongs() {
        return belongs;
    }

    public void setBelongs(MainFrame belongs) {
        this.belongs = belongs;
    }
}
