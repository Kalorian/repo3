package kalorian.baiwen.frame;

import kalorian.baiwen.element.Card;
import kalorian.baiwen.element.card.MagicCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * description:
 * author:
 * time:2020/9/3
 */
public class HandDialog extends JDialog {

    private MagicCard card;
    private MainFrame mainFrame;

    public HandDialog(MainFrame mainFrame, Card card){
        this.mainFrame=mainFrame;
        this.card=(MagicCard) card;

        Container contentPane = getContentPane();
        setBounds(400,300,400,300);
        setLayout(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JPanel jPanel=new JPanel(new GridLayout(2,5));
        jPanel.setBounds(30,20,340,220);
        jPanel.setBorder(BorderFactory.createTitledBorder("选择目标"));

        JRadioButton jb1=new JRadioButton("玩家："+mainFrame.getPlayer2().getName());
        JRadioButton jb2=new JRadioButton(mainFrame.getPlayer2().getShikigamies().getShikigami1().getName());
        JRadioButton jb3=new JRadioButton(mainFrame.getPlayer2().getShikigamies().getShikigami2().getName());
        JRadioButton jb4=new JRadioButton(mainFrame.getPlayer2().getShikigamies().getShikigami3().getName());
        JRadioButton jb5=new JRadioButton(mainFrame.getPlayer2().getShikigamies().getShikigami4().getName());
        JRadioButton jb6=new JRadioButton("玩家："+mainFrame.getPlayer1().getName());
        JRadioButton jb7=new JRadioButton(mainFrame.getPlayer1().getShikigamies().getShikigami1().getName());
        JRadioButton jb8=new JRadioButton(mainFrame.getPlayer1().getShikigamies().getShikigami2().getName());
        JRadioButton jb9=new JRadioButton(mainFrame.getPlayer1().getShikigamies().getShikigami3().getName());
        JRadioButton jb10=new JRadioButton(mainFrame.getPlayer1().getShikigamies().getShikigami4().getName());

        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(jb1);
        buttonGroup.add(jb2);
        buttonGroup.add(jb3);
        buttonGroup.add(jb4);
        buttonGroup.add(jb5);
        buttonGroup.add(jb6);
        buttonGroup.add(jb7);
        buttonGroup.add(jb8);
        buttonGroup.add(jb9);
        buttonGroup.add(jb10);
        ArrayList<JRadioButton> arrayList=new ArrayList<>();
        arrayList.add(jb1);
        arrayList.add(jb2);
        arrayList.add(jb3);
        arrayList.add(jb4);
        arrayList.add(jb5);
        arrayList.add(jb6);
        arrayList.add(jb7);
        arrayList.add(jb8);
        arrayList.add(jb9);
        arrayList.add(jb10);

        HashMap<JRadioButton,Object> hashMap=new HashMap<>();
        hashMap.put(jb1,mainFrame.getPlayer2());
        hashMap.put(jb2,mainFrame.getPlayer2().getShikigamies().getShikigami1());
        hashMap.put(jb3,mainFrame.getPlayer2().getShikigamies().getShikigami2());
        hashMap.put(jb4,mainFrame.getPlayer2().getShikigamies().getShikigami3());
        hashMap.put(jb5,mainFrame.getPlayer2().getShikigamies().getShikigami4());
        hashMap.put(jb6,mainFrame.getPlayer1());
        hashMap.put(jb7,mainFrame.getPlayer1().getShikigamies().getShikigami1());
        hashMap.put(jb8,mainFrame.getPlayer1().getShikigamies().getShikigami2());
        hashMap.put(jb9,mainFrame.getPlayer1().getShikigamies().getShikigami3());
        hashMap.put(jb10,mainFrame.getPlayer1().getShikigamies().getShikigami4());

        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);
        jPanel.add(jb7);
        jPanel.add(jb8);
        jPanel.add(jb9);
        jPanel.add(jb10);
        contentPane.add(jPanel);

        if(!((MagicCard) card).isHasHostilePlayerTarget()){
            jb1.setEnabled(false);
        }
        if(!((MagicCard) card).isHasHostileShikigamiTarget()){
            jb2.setEnabled(false);
            jb3.setEnabled(false);
            jb4.setEnabled(false);
            jb5.setEnabled(false);
        }
        if(!((MagicCard) card).isHasOwnPlayerTarget()){
            jb6.setEnabled(false);
        }if(!((MagicCard) card).isHasOwnShikigamiTarget()){
            jb7.setEnabled(false);
            jb8.setEnabled(false);
            jb9.setEnabled(false);
            jb10.setEnabled(false);
        }

        JButton confirm=new JButton("确认");
        JButton cancel=new JButton("取消");
        confirm.setBounds(100,250,80,40);
        cancel.setBounds(220,250,80,40);
        contentPane.add(confirm);
        contentPane.add(cancel);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JRadioButton jRadioButton:arrayList){
                    if(jRadioButton.isSelected()){
                        mainFrame.setTarget(hashMap.get(jRadioButton));
                    }
                    break;
                }
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
