package kalorian.baiwen.frame;

import kalorian.baiwen.element.Player;
import kalorian.baiwen.element.Shikigami;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author:
 * time:2020/9/5
 */
public class LevelUpDialog extends JDialog {

    private Shikigami target;

    public LevelUpDialog(Player player){
        Container contentPane = getContentPane();
        setBounds(400,300,400,300);
        setLayout(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JPanel jPanel=new JPanel(new GridLayout(1,4));
        jPanel.setBounds(30,20,340,220);
        jPanel.setBorder(BorderFactory.createTitledBorder("选择升级角色"));

        JRadioButton jb1=new JRadioButton(player.getShikigamies().getShikigami1().getName()+
                "("+player.getShikigamies().getShikigami1().getLevel()+"勾)");
        JRadioButton jb2=new JRadioButton(player.getShikigamies().getShikigami2().getName()+
                "("+player.getShikigamies().getShikigami2().getLevel()+"勾)");
        JRadioButton jb3=new JRadioButton(player.getShikigamies().getShikigami3().getName()+
                "("+player.getShikigamies().getShikigami3().getLevel()+"勾)");
        JRadioButton jb4=new JRadioButton(player.getShikigamies().getShikigami4().getName()+
                "("+player.getShikigamies().getShikigami4().getLevel()+"勾)");
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(jb1);
        buttonGroup.add(jb2);
        buttonGroup.add(jb3);
        buttonGroup.add(jb4);

        int l1=player.getShikigamies().getShikigami1().getLevel();
        int l2=player.getShikigamies().getShikigami2().getLevel();
        int l3=player.getShikigamies().getShikigami3().getLevel();
        int l4=player.getShikigamies().getShikigami4().getLevel();
        if(!(l1==l2&&l3==l4&&l1==l3)){
            if(l2>l1||l2>l3||l2>l4){
                jb2.setEnabled(false);
            }
            if(l3>l2||l3>l1||l3>l4){
                jb3.setEnabled(false);
            }
            if(l4>l2||l4>l3||l1>l2){
                jb1.setEnabled(false);
            }
            if(l1>l2||l1>l3||l1>l4){
                jb1.setEnabled(false);
            }
        }

        HashMap<JRadioButton,Shikigami> hashMap=new HashMap<JRadioButton,Shikigami>();
        hashMap.put(jb1,player.getShikigamies().getShikigami1());
        hashMap.put(jb2,player.getShikigamies().getShikigami2());
        hashMap.put(jb3,player.getShikigamies().getShikigami3());
        hashMap.put(jb4,player.getShikigamies().getShikigami4());

        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        contentPane.add(jPanel);

        JButton confirm=new JButton("升级");
        confirm.setBounds(160,250,80,40);
        contentPane.add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry<JRadioButton,Shikigami> entry:hashMap.entrySet()){
                    if(entry.getKey().isSelected()){
                        target=entry.getValue();
                        dispose();
                    }
                }
            }
        });

    }

    public Shikigami getTarget() {
        return target;
    }
}
