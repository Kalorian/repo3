package kalorian.baiwen.thread;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Shikigami;
import kalorian.baiwen.frame.MainFrame;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * description:
 * author:
 * time:2020/9/8
 */
public class GetMessage extends Thread {

    private MainFrame belongs;
    private DataInputStream dataInputStream;

    public GetMessage(DataInputStream dataInputStream,MainFrame mainFrame){
        this.belongs=mainFrame;
        this.dataInputStream=dataInputStream;
    }

    @Override
    public void run() {
        while(true){
            String str="";
            try {
                str=dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] split = str.split(":", 2);
            switch (split[0]){
                case "chat":
                    belongs.getMessage().append(belongs.getPlayer2().getName()+":"+split[1]);
                    break;
                case  "levelUp":
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split[1])){
                            shikigami.levelUp();
                            break;
                        }
                    }
                    break;
                case "battle":
                    String[] split1 = split[1].split(",", 3);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split1[0])){
                            shikigami.battle(Integer.valueOf(split1[1]),Integer.valueOf(split1[2]));
                        }
                        break;
                    }
                    break;
                case "healShikigami":
                    String[] split2 = split[1].split(",", 4);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split2[0])){
                            if(split2[1].equals("1")){
                                Shikigami target=belongs.getPlayer1().getShikigamies().get(Integer.valueOf(split2[2]));
                                shikigami.heal(Integer.valueOf(split2[3]),target);
                            }else {
                                Shikigami target=belongs.getPlayer1().getShikigamies().get(Integer.valueOf(split2[2]));
                                shikigami.heal(Integer.valueOf(split2[3]),target);
                            }
                        }
                       break;
                    }
                    break;
                case "healPlayer":
                    String[] split3 = split[1].split(",", 3);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split3[0])){
                            if(split3[1].equals("1")){
                                shikigami.heal(Integer.valueOf(split3[2]),belongs.getPlayer1());
                            }else {
                                shikigami.heal(Integer.valueOf(split3[2]),belongs.getPlayer2());
                            }
                        }
                        break;
                    }
                    break;
                case "nonBattleDamageShikigami":
                    String[] split4 = split[1].split(",", 4);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split4[0])){
                            if(split4[1].equals("1")){
                                Shikigami target=belongs.getPlayer1().getShikigamies().get(Integer.valueOf(split4[2]));
                                shikigami.takeNonBattleDamage(Integer.valueOf(split4[3]),target);
                            }else {
                                Shikigami target=belongs.getPlayer2().getShikigamies().get(Integer.valueOf(split4[2]));
                                shikigami.takeNonBattleDamage(Integer.valueOf(split4[3]),target);
                            }
                        }
                    }
                    break;
                case "nonBattleDamagePlayer":
                    String[] split5 = split[1].split(",", 3);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split5[0])){
                            if(split5[1].equals("1")){
                                shikigami.takeNonBattleDamage(Integer.valueOf(split5[2]),belongs.getPlayer1());
                            }else {
                                shikigami.takeNonBattleDamage(Integer.valueOf(split5[2]),belongs.getPlayer2());
                            }
                        }
                        break;
                    }
                    break;
                case "drawCard":
                    belongs.getPlayer2().drawCard(Integer.valueOf(split[1]));
                    break;
                case "useCard":
                    Card card=belongs.getPlayer2().getHand().get(Integer.valueOf(split[1]));
                    belongs.getPlayer2().playCard(card);
                    break;
                case "cast":
                    String[] split6 = split[1].split(",", 2);
                    for(Shikigami shikigami:belongs.getPlayer2().getShikigamies()){
                        if(shikigami.getNumber()==Integer.valueOf(split6[0])){
                            shikigami.cast(Integer.valueOf(split6[1]));
                        }
                        break;
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public MainFrame getBelongs() {
        return belongs;
    }

    public void setBelongs(MainFrame belongs) {
        this.belongs = belongs;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
}
