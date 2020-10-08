package kalorian.baiwen.frame;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * description:
 * author:
 * time:2020/9/5
 */
public class SelectDialog extends JDialog {

    private ArrayList<Card> cardArrayList;
    private Player player;
    private int restTime;
    private int time;
    private String string;

    private JLabel jl1;
    private JLabel jl2;

    public SelectDialog(ArrayList<Card> cardArrayList, Player player){
        this.cardArrayList =cardArrayList;
        restTime=3;
        time=35;
        if(player.isFirst()){
            string="先";
        }else {
            string="后";
        }

        Container contentPane = getContentPane();
        setBounds(400,300,400,300);
        setLayout(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JPanel jPanel=new JPanel(new GridLayout(1,5));
        jPanel.setBounds(30,20,340,150);
        jPanel.setBorder(BorderFactory.createTitledBorder("更换起始手牌(你是"+string+"手)"));

        JRadioButton jb1=new JRadioButton(cardArrayList.get(0).getName());
        JRadioButton jb2=new JRadioButton(cardArrayList.get(1).getName());
        JRadioButton jb3=new JRadioButton(cardArrayList.get(2).getName());
        JRadioButton jb4=new JRadioButton(cardArrayList.get(3).getName());
        JRadioButton jb5=new JRadioButton(cardArrayList.get(4).getName());
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(jb1);
        buttonGroup.add(jb2);
        buttonGroup.add(jb3);
        buttonGroup.add(jb4);
        buttonGroup.add(jb5);

        HashMap<JRadioButton,Card> hashMap=new HashMap<JRadioButton,Card>();
        hashMap.put(jb1,cardArrayList.get(0));
        hashMap.put(jb2,cardArrayList.get(1));
        hashMap.put(jb3,cardArrayList.get(2));
        hashMap.put(jb4,cardArrayList.get(3));
        hashMap.put(jb5,cardArrayList.get(4));

        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        contentPane.add(jPanel);

        jl1=new JLabel("剩余更换次数："+restTime);
        jl2=new JLabel("剩余时间："+time);
        jl1.setBounds(30,210,120,40);
        jl2.setBounds(200,210,120,40);
        contentPane.add(jl1);
        contentPane.add(jl2);

        JButton confirm=new JButton("确定");
        JButton cancel=new JButton("取消");
        confirm.setBounds(100,250,80,40);
        cancel.setBounds(220,250,80,40);
        contentPane.add(confirm);
        contentPane.add(cancel);



        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry<JRadioButton,Card> entry:hashMap.entrySet()){
                    if(entry.getKey().isSelected()){
                       Card card1=entry.getValue();
                       int index=cardArrayList.lastIndexOf(card1);
                       int library_index=(int)(Math.random()*player.getLibrary().getCount().size());
                       Card card2=player.getLibrary().get(player.getLibrary().getCount().get(library_index));
                       player.getLibrary().getCount().add(card1.getLibraryIndex());
                       player.getLibrary().getCount().remove(library_index);
                       cardArrayList.set(index,card2);
                       hashMap.replace(entry.getKey(),card2);
                        entry.getKey().setText(cardArrayList.get(index).getName());
                        restTime-=1;
                        jl1.setText("剩余更换次数："+restTime);
                        break;
                    }
                }
                if(restTime==0){
                    dispose();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time-=1;
                    jl2.setText("剩余时间："+time);
                    if(time==0){
                        dispose();
                    }
                }
            }
        });
        thread.start();

        setVisible(true);
    }


    public ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }
}
