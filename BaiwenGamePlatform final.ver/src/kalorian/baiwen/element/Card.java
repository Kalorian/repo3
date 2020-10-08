package kalorian.baiwen.element;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class Card {

    private String name;
    private String type;
    private String describe;

    private int level;
    private int cost;
    private boolean hasTarget;

    private boolean isPrompt;
    private boolean isRespond;

    private Shikigami belongs;
    private boolean isInHand;

    public Card(){
        setCost(1);
        setHasTarget(false);
        setPrompt(false);
        setRespond(false);
        setDescribe("");
    }

    public void execution(){

    }

    public void execution(Player target){

    }

    public void execution(Shikigami target){

    }

    public void endTurnRespond(){

    }

    @Override
    public String toString() {
        return getType()+"["+getName()+"]("+getLevel()+"勾)"+getDescribe();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public boolean isPrompt() {
        return isPrompt;
    }

    public void setPrompt(boolean prompt) {
        isPrompt = prompt;
    }

    public boolean isRespond() {
        return isRespond;
    }

    public void setRespond(boolean respond) {
        isRespond = respond;
    }

    public Shikigami getBelongs() {
        return belongs;
    }

    public void setBelongs(Shikigami belongs) {
        this.belongs = belongs;
    }

    public boolean isInHand() {
        return isInHand;
    }

    public void setInHand(boolean inHand) {
        isInHand = inHand;
    }
}
