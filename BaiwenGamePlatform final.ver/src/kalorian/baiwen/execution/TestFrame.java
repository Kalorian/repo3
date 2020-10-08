package kalorian.baiwen.execution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/**
 * description:
 * author:
 * time:2020/9/8
 */
public class TestFrame extends JFrame {

    public int i;

    public TestFrame(){
        Container contentPane = getContentPane();
        setLayout(new GridLayout(1,3));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400,300,500,350);
        i=0;

        JButton jb0=new JButton("0");
        JButton jb1=new JButton("1");
        JButton jb2=new JButton("2");
        contentPane.add(jb0);
        contentPane.add(jb1);
        contentPane.add(jb2);

        jb0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=0;
            }
        });

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=1;
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=2;
            }
        });

        setVisible(true);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                i=2;
            }
        });
        thread.start();

        while(i!=2){
            System.out.print("");
        }

        System.out.println("现在是2");
    }
}
