package kalorian.baiwen.element;

/**
 * description:
 * author:
 * time:2020/9/7
 */
public class Card {

    private String name;
    private String describe;
    private int level;
    private String type;

    private int libraryIndex;

    private Shikigami belongs;

    public Card(){

    }

    public void execution(){

    }

    public void execution(Player target){

    }

    public void execution(Shikigami target){

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLibraryIndex() {
        return libraryIndex;
    }

    public void setLibraryIndex(int libraryIndex) {
        this.libraryIndex = libraryIndex;
    }

    public Shikigami getBelongs() {
        return belongs;
    }

    public void setBelongs(Shikigami belongs) {
        this.belongs = belongs;
    }
}
