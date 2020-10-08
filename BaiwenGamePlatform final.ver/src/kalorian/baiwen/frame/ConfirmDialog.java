package kalorian.baiwen.frame;

import kalorian.baiwen.element.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * description:
 * author:
 * time:2020/8/19
 */
public class ConfirmDialog extends JDialog {

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Player player1;
    private Player player2;
    private Socket socket;

    public ConfirmDialog(Socket socket, DataInputStream dataInputStream, String player1_name, String player2_name, String player2_ip){

        this.socket=socket;
        this.dataInputStream=dataInputStream;

        Container contentPane = getContentPane();
        setBounds(300,200,400,300);
        setLayout(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JTextArea text=new JTextArea("收到来自"+player2_ip+"（昵称："+player2_name+"）的连接请求，点击\"同意\"即可进入对战");
        text.setEditable(false);
        text.setLineWrap(true);
        text.setBounds(50,40,300,100);
        JButton agree=new JButton("同意");
        agree.setBounds(120,170,60,40);
        JButton refuse=new JButton("拒绝");
        refuse.setBounds(220,170,60,40);

        contentPane.add(text);
        contentPane.add(agree);
        contentPane.add(refuse);

        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    player1=new Player();
                    player2=new Player();
                    player1.setName(player1_name);
                    player2.setName(player2_name);
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    int order=(int)(Math.random()*2);
                    if(order==0){
                        player1.setFirst(true);
                    }else{
                        player1.setFirst(false);
                    }
                    dataOutputStream.writeUTF("agree:"+player1_name+":"+order);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                new MainFrame(socket,dataOutputStream,dataInputStream,player1,player2);
                dispose();
            }
        });

        refuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF("refuse");
                    System.out.println("拒绝了");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        setVisible(true);
    }
}
