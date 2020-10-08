package kalorian.baiwen.frame;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * description:
 * author:
 * time:2020/8/31
 */
public class MainFrame extends JFrame {

    private MainFrame mainFrame;

    private Player player1;
    private Player player2;

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private Container c;
    private JLabel player_name2;
    private JLabel hp2;
    private JLabel hand2;
    private JLabel cardLibrary2;

    private JPanel mainJPanel2;
    private JPanel mainJPanel1;
    private JTextArea panel_prepareArea2;
    private JTextArea panel_battleArea2;
    private JTextArea panel_prepareArea1;
    private JTextArea panel_battleArea1;

    private JLabel player_name1;
    private JLabel hp1;
    private JLabel hand1;
    private JLabel cardLibrary1;
    private JLabel resttime;

    private JPanel attackPanel;
    private JRadioButton shikigami_jb1;
    private JRadioButton shikigami_jb2;
    private JRadioButton shikigami_jb3;
    private JRadioButton shikigami_jb4;
    private ButtonGroup shikigami_buttonGroup;
    private Map<JRadioButton,Shikigami> attackMap;

    private JButton battle;
    private JLabel battleopportunity;

    private JTextArea message;
    private JScrollPane chat;
    private JTextField inputMessage;
    private JButton send;

    private JPanel handPanel;
    private JRadioButton card1;
    private JRadioButton card2;
    private JRadioButton card3;
    private JRadioButton card4;
    private JRadioButton card5;
    private JRadioButton card6;
    private JRadioButton card7;
    private JRadioButton card8;
    private JRadioButton card9;
    private JRadioButton card10;
    private JRadioButton card11;
    private JRadioButton card12;
    private ButtonGroup card_buttonGroup;
    private ArrayList<JRadioButton> handButton;
    private Map<JRadioButton,Card> handMap;
    private Object target;

    private JButton playCard;
    private JLabel fire;
    private JLabel prompt;
    private JButton wiki;
    private JButton endTurn;

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
        this.mainFrame=this;

        gameRun();
    }

    public MainFrame(){
        player1=new Player();
        player2=new Player();
        elementInit();
        mainFrameInit();
    }

    public void elementInit(){
        player1.setHostilePlayer(player2);
        player2.setHostilePlayer(player1);
        player1.setBelongs(this);
        player2.setBelongs(this);

        turn=0;

        for(Card card:player1.getLibrary()){
            if(card.getName().equals("伞剑")|card.getName().equals("金鸾")){
                player1.getPromptList().add(card);
            }
            if(card.getName().equals("偷袭")){
                player1.getRespondList().add(card);
            }
        }

    }

    public void mainFrameInit(){
        c=getContentPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("百闻牌对战平台试作");
        setLayout(null);
        setBounds(200,100,1000,800);
        setResizable(false);

        player_name2=new JLabel("玩家姓名："+player2.getName());
        hp2=new JLabel("生命值："+player2.getHP()+"(盾"+player2.getDefense()+")");
        hand2=new JLabel("手牌数量："+player2.getHand().size());
        cardLibrary2=new JLabel("牌堆剩余卡牌："+player2.getLibrary().size());
        player_name2.setBounds(10,10,200,40);
        hp2.setBounds(210,10,100,40);
        hand2.setBounds(310,10,100,40);
        cardLibrary2.setBounds(410,10,100,40);
        c.add(player_name2);
        c.add(hp2);
        c.add(hand2);
        c.add(cardLibrary2);

        mainJPanel2=new JPanel();
        mainJPanel1=new JPanel();
        mainJPanel2.setBorder(BorderFactory.createLineBorder(Color.black,2));
        mainJPanel1.setBorder(BorderFactory.createLineBorder(Color.black,2));
        panel_prepareArea2=new JTextArea(iterator_area(player2.getPrepareArea()));
        panel_prepareArea2.setBorder(BorderFactory.createTitledBorder("准备区"));
        panel_battleArea2=new JTextArea(iterator_area(player2.getBattleArea()));
        panel_battleArea2.setBorder(BorderFactory.createTitledBorder("战斗区"));
        panel_prepareArea1=new JTextArea(iterator_area(player1.getPrepareArea()));
        panel_prepareArea1.setBorder(BorderFactory.createTitledBorder("准备区"));
        panel_battleArea1=new JTextArea(iterator_area(player1.getBattleArea()));
        panel_battleArea1.setBorder(BorderFactory.createTitledBorder("战斗区"));
        mainJPanel2.setLayout(null);
        mainJPanel1.setLayout(null);
        mainJPanel2.setBounds(10,50,690,235);
        mainJPanel1.setBounds(10,290,690,235);
        panel_prepareArea2.setBounds(20,10,600,160);
        panel_battleArea2.setBounds(20,180,600,40);
        panel_prepareArea1.setBounds(20,60,600,160);
        panel_battleArea1.setBounds(20,10,600,40);
        mainJPanel2.add(panel_prepareArea2);
        mainJPanel2.add(panel_battleArea2);
        mainJPanel1.add(panel_prepareArea1);
        mainJPanel1.add(panel_battleArea1);
        c.add(mainJPanel2);
        c.add(mainJPanel1);

        player_name1=new JLabel("玩家姓名："+player1.getName());
        hp1=new JLabel("生命值："+player1.getHP()+"(盾"+player1.getDefense()+")");
        hand1=new JLabel("手牌数量："+player1.getHand().size());
        cardLibrary1=new JLabel("牌堆剩余卡牌："+player1.getLibrary().size());
        resttime=new JLabel("剩余时间：");
        player_name1.setBounds(10,530,200,40);
        hp1.setBounds(210,530,100,40);
        hand1.setBounds(310,530,100,40);
        cardLibrary1.setBounds(410,530,150,40);
        resttime.setBounds(560,530,100,40);
        c.add(player_name1);
        c.add(hp1);
        c.add(hand1);
        c.add(cardLibrary1);
        c.add(resttime);

        attackPanel=new JPanel();
        attackPanel.setBounds(30,580,600,50);
        attackPanel.setBorder(BorderFactory.createEtchedBorder());
        attackPanel.setLayout(new GridLayout(1,4));
        shikigami_jb1=new JRadioButton(player1.getShikigamies().getShikigami1().getName());
        shikigami_jb2=new JRadioButton(player1.getShikigamies().getShikigami2().getName());
        shikigami_jb3=new JRadioButton(player1.getShikigamies().getShikigami3().getName());
        shikigami_jb4=new JRadioButton(player1.getShikigamies().getShikigami4().getName());
        shikigami_buttonGroup=new ButtonGroup();
        shikigami_buttonGroup.add(shikigami_jb1);
        shikigami_buttonGroup.add(shikigami_jb2);
        shikigami_buttonGroup.add(shikigami_jb3);
        shikigami_buttonGroup.add(shikigami_jb4);
        attackPanel.add(shikigami_jb1);
        attackPanel.add(shikigami_jb2);
        attackPanel.add(shikigami_jb3);
        attackPanel.add(shikigami_jb4);
        c.add(attackPanel);

        attackMap=new HashMap<JRadioButton,Shikigami>();
        attackMap.put(shikigami_jb1,player1.getShikigamies().getShikigami1());
        attackMap.put(shikigami_jb2,player1.getShikigamies().getShikigami2());
        attackMap.put(shikigami_jb3,player1.getShikigamies().getShikigami3());
        attackMap.put(shikigami_jb4,player1.getShikigamies().getShikigami4());

        battle=new JButton("出击");
        battle.setBounds(640,580,150,40);
        battleopportunity=new JLabel("本回合剩余出击次数："+player1.getAttackTime());
        battleopportunity.setBounds(640,630,150,30);
        c.add(battle);
        c.add(battleopportunity);

        message=new JTextArea();
        chat=new JScrollPane(message);
        chat.setBounds(720,50,240,480);
        inputMessage=new JTextField();
        inputMessage.setBounds(720,530,180,40);
        send=new JButton("发送");
        send.setBounds(900,530,60,40);
        c.add(chat);
        c.add(inputMessage);
        c.add(send);

        handPanel=new JPanel();
        handPanel.setBounds(30,650,600,80);
        handPanel.setLayout(new GridLayout(2,6));
        handPanel.setBorder(BorderFactory.createEtchedBorder());
        card1=new JRadioButton();
        card2=new JRadioButton();
        card3=new JRadioButton();
        card4=new JRadioButton();
        card5=new JRadioButton();
        card6=new JRadioButton();
        card7=new JRadioButton();
        card8=new JRadioButton();
        card9=new JRadioButton();
        card10=new JRadioButton();
        card11=new JRadioButton();
        card12=new JRadioButton();
        card_buttonGroup=new ButtonGroup();
        card_buttonGroup.add(card1);
        card_buttonGroup.add(card2);
        card_buttonGroup.add(card3);
        card_buttonGroup.add(card4);
        card_buttonGroup.add(card5);
        card_buttonGroup.add(card6);
        card_buttonGroup.add(card7);
        card_buttonGroup.add(card8);
        card_buttonGroup.add(card9);
        card_buttonGroup.add(card10);
        card_buttonGroup.add(card11);
        card_buttonGroup.add(card12);
        handPanel.add(card1);
        handPanel.add(card2);
        handPanel.add(card3);
        handPanel.add(card4);
        handPanel.add(card5);
        handPanel.add(card6);
        handPanel.add(card7);
        handPanel.add(card8);
        handPanel.add(card9);
        handPanel.add(card10);
        handPanel.add(card11);
        handPanel.add(card12);
        c.add(handPanel);

        handButton=new ArrayList<>();
        handButton.add(card1);
        handButton.add(card2);
        handButton.add(card3);
        handButton.add(card4);
        handButton.add(card5);
        handButton.add(card6);
        handButton.add(card7);
        handButton.add(card8);
        handButton.add(card9);
        handButton.add(card10);
        handButton.add(card11);
        handButton.add(card12);

        handMap=new HashMap<JRadioButton,Card>();
        for(int i=0;i<handButton.size();i++){
            handMap.put(handButton.get(i),player1.getHand().get(i));
        }


        playCard=new JButton("出牌");
        playCard.setBounds(640,660,150,40);
        fire=new JLabel("剩余明灯数量："+player1.getFire());
        fire.setBounds(640,700,150,30);
        prompt=new JLabel("瞬发："+player1.getPromptTime());
        prompt.setBounds(800,700,150,30);
        c.add(prompt);
        c.add(fire);
        c.add(playCard);

        battle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry<JRadioButton,Shikigami> entry:attackMap.entrySet()){
                    if(entry.getKey().isSelected()){
                        entry.getValue().attack();
                    }
                }
                attackPanel_reload();
                panel_reload();
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputMessage.getText();
                inputMessage.setText("");
                message.append(player1.getName()+"："+text+"\n");
            }
        });

        playCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry<JRadioButton,Card> entry:handMap.entrySet()){
                    if(entry.getKey().isSelected()){
                        Card card=entry.getValue();
                        if(!card.isPrompt()|player1.getPromptTime()==0){
                            player1.setFire(player1.getFire()-1);
                        }
                        if(card.isHasTarget()){
                            new HandDialog(mainFrame,card);
                            if(target.getClass().getName().equals("Shikigami")){
                                card.execution((Shikigami)target);
                            }else {
                                card.execution((Player) target);
                            }
                        }else {
                            card.execution();
                        }
                        break;

                    }
                }
                handPanel_reload();
                panel_reload();
            }
        });
        attackPanel_reload();
        handPanel_reload();

        setVisible(true);
    }

    public void gameRun(){
        turnCheck();

        gameStart();

    }

    public void turnCheck(){
        if(player1.isFirst()){
            player1.setInTurn(true);
        }else {
            player1.setInTurn(false);
            player1.setDefense(5);
        }
    }

    public void gameStart(){
        int random=(int)(Math.random()*player1.getShikigamies().size());
        Shikigami target=player1.getShikigamies().get(random);
        target.levelUp();

        ArrayList<Card> cardArrayList=new ArrayList<Card>();
        for(int i=0;i<5;i++){
            int index=(int)(Math.random()*player1.getLibrary().size());
            Card card=player1.getLibrary().get(index);
            player1.getLibrary().remove(card);
            cardArrayList.add(card);
        }
        SelectDialog selectDialog = new SelectDialog(cardArrayList,player1);
        ArrayList<Card> newList=selectDialog.getCardArrayList();
        player1.getHand().addAll(newList);
        reload();
    }

    public void reload(){
        panel_reload();
        attackPanel_reload();
        handPanel_reload();
    }

    public void panel_reload(){
        hp2.setText("生命值："+player2.getHP()+"(盾"+player2.getDefense()+")");
        hand2.setText("手牌数量："+player2.getHand().size());
        cardLibrary2.setText("牌堆剩余卡牌："+player2.getLibrary().size());

        panel_prepareArea2.setText(iterator_area(player2.getPrepareArea()));
        panel_battleArea2.setText(iterator_area(player2.getBattleArea()));
        panel_prepareArea1.setText(iterator_area(player2.getPrepareArea()));
        panel_battleArea1.setText(iterator_area(player2.getBattleArea()));

        hp1.setText("生命值："+player1.getHP()+"(盾"+player1.getDefense()+")");
        fire.setText("剩余明灯数量："+player1.getFire());
        prompt.setText("瞬发："+player1.getPromptTime());
    }

    public void attackPanel_reload(){
        for(Map.Entry<JRadioButton,Shikigami> entry:attackMap.entrySet()){
            if(player1.getAttackTime()!=0&&entry.getValue().getLevel()!=0&&
                    entry.getValue().getResurrection().isDie()&&(player1.getFire()!=0|entry.getValue().getBuff().isFast())){
                entry.getKey().setEnabled(true);
            }else{
                entry.getKey().setEnabled(false);
            }
        }
    }

    public void handPanel_reload(){
        for(int i=0;i<handButton.size();i++){
            handMap.replace(handButton.get(i),player1.getHand().get(i));
        }
        for(Map.Entry<JRadioButton,Card> entry:handMap.entrySet()){
            JRadioButton jRadioButton=entry.getKey();
            Card card=entry.getValue();
            if(card!=null){
                jRadioButton.setText(card.getName());
                if(!card.getBelongs().getResurrection().isDie()&&card.getLevel()>card.getBelongs().getLevel()&&
                        !(player1.getFire()==0&&player1.getPromptTime()==0)&&!(player1.getFire()==0&&!card.isPrompt())){
                    jRadioButton.setEnabled(true);
                }else {
                    jRadioButton.setEnabled(false);
                }
            }else {
                jRadioButton.setText("");
                jRadioButton.setEnabled(false);
            }

        }
        hand1.setText("手牌数量："+player1.getHand().size());
        cardLibrary1.setText("牌堆剩余卡牌："+player1.getLibrary().size());
    }

    public String iterator_area(ArrayList lists) {
        String str = "";
        for (Iterator<Shikigami> list = lists.iterator(); list.hasNext(); ) {
            str = str + list.next() + "\n";
        }
        return str;
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

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

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(JTextArea message) {
        this.message = message;
    }
}
