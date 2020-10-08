package kalorian.baiwen.frame;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * description:
 * author:
 * time:2020/9/6
 */
public class MainFrame extends JFrame {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Player player1;
    private Player player2;

    private ArrayList<Card> selection;

    private JTextArea message;

    private int turn;

    public MainFrame(Socket socket, DataOutputStream dataOutputStream, DataInputStream dataInputStream,
                     Player player1, Player player2){
        this.player1=player1;
        this.player2=player2;
        this.socket=socket;
        this.dataOutputStream=dataOutputStream;
        this.dataInputStream=dataInputStream;

        elementInit();
        mainFrameInit();

        gameRun();
    }

    public void elementInit(){
        player1.setBelongs(this);
        player2.setBelongs(this);

        selection=new ArrayList<>();
        turn=0;
    }

    public void mainFrameInit(){

    }

    public void gameRun(){
        gameStart();

        while(true){
            turnRun(player1);
        }
    }

    public void gameStart(){
        message.append("游戏开始\n");
        if(player1.isFirst()){
            message.append(player1.getName()+"获得了先手\n"+player2.getName()+"是后手\n");
            player2.setDefense(5);
        }else {
            message.append(player2.getName()+"获得了先手\n"+player1.getName()+"是后手\n");
            player1.setDefense(5);
        }
//        1、先后顺序确定
        int thisRandom=(int)(Math.random()*player1.getShikigamies().size());
        Shikigami target1=player1.getShikigamies().get(thisRandom);
        target1.levelUp();
        if(player1.isFirst()){
            try {
                dataOutputStream.writeUTF(String.valueOf(thisRandom));
                int thatRandom=Integer.valueOf(dataInputStream.readUTF());
                Shikigami target2=player2.getShikigamies().get(thatRandom);
                target2.levelUp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                int thatRandom=Integer.valueOf(dataInputStream.readUTF());
                Shikigami target2=player2.getShikigamies().get(thatRandom);
                target2.levelUp();
                dataOutputStream.writeUTF(String.valueOf(thisRandom));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reload();
//        2、双方随机升级一位式神
        for(int i=0;i<5;i++){
            int index=(int)(Math.random()*player1.getLibrary().getCount().size());
            Card card=player1.getLibrary().get(player1.getLibrary().getCount().get(index));
            player1.getLibrary().getCount().remove(index);
            selection.add(card);
        }
        SelectDialog selectDialog=new SelectDialog(selection,player1);
        player1.getHand().addCard(selectDialog.getCardArrayList());
        if(player1.isFirst()){
            for(Card card:selectDialog.getCardArrayList()){
                int index=card.getLibraryIndex();
                try {
                    dataOutputStream.writeUTF(String.valueOf(index));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            message.append(player1.getName()+"抽取了五张牌\n");
            for(int i=0;i<5;i++){
                try {
                    int index=Integer.valueOf(dataInputStream.readUTF());
                    Card card=player2.getLibrary().get(player2.getLibrary().getCount().get(index));
                    player2.getHand().addCard(card);
                    player2.getLibrary().getCount().remove(index);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            message.append(player2.getName()+"抽取了五张牌\n");
        }else {
            for(int i=0;i<5;i++){
                try {
                    int index=Integer.valueOf(dataInputStream.readUTF());
                    Card card=player2.getLibrary().get(player2.getLibrary().getCount().get(index));
                    player2.getHand().addCard(card);
                    player2.getLibrary().getCount().remove(index);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.append(player2.getName()+"抽取了五张牌\n");
                for(Card card:selectDialog.getCardArrayList()){
                    int index=card.getLibraryIndex();
                    try {
                        dataOutputStream.writeUTF(String.valueOf(index));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                message.append(player1.getName()+"抽取了五张牌\n");
            }
        }
        reload();
//        3、双方抽五张牌，换牌
        if(player1.isFirst()){
            player1.setInTurn(true);
            player2.setInTurn(false);
        }

//        4、第一回合开始
    }

    public void turnRun(Player player){
        turn+=1;
        if(turn==1){
            player.setFires(1);
        }else{
            player.setFires(2);
        }
        player.setAttackTime(1);
        player.setPromptTime(1);
        if(!player.getBattleArea().isEmpty()){
            player.getBattleArea().get(0).goOutBattleArea();
        }
//        1、回合开始时的初始化
        for(Shikigami shikigami:player.getShikigamies()){
            if(shikigami.getResurrection().isDie()){
                shikigami.resurrection();
            }
        }
//        2、复活
        if(player1.isInTurn()){
            for(Shikigami shikigami:player.getShikigamies()){
                if(shikigami.getForm().getName().equals("丰实")|shikigami.getForm().getName().equals("盛开")){
                    shikigami.getForm().turnStart();
                }
            }
        }
//        3、回合开始形态
//        4、抽牌
//        5、升级（开始倒计时）
//        6、操作时间
//        7、回合结束判定
//        8、回合结束时的处理

        /*turn+=1;
        while(player1.isFirst()){
            if(!player1.isFirst()){

            }
        }*/
    }

    public void reload(){}

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(JTextArea message) {
        this.message = message;
    }
}
