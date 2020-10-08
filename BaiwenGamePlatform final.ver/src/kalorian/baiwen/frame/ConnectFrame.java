package kalorian.baiwen.frame;

import kalorian.baiwen.element.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * description:
 * author:
 * time:2020/9/2
 */
public class ConnectFrame extends JFrame{

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private ServerSocket serverSocket;
    private Socket socket;

    private Player player1;
    private Player player2;

    public ConnectFrame(){

        Container contentPane = getContentPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setBounds(400,300,600,400);
        setTitle("对战连接");

        JLabel name=new JLabel("昵称：");
        name.setBounds(70,40,60,40);
        JTextField input_name=new JTextField();
        input_name.setBounds(130,40,400,40);

        JLabel ip=new JLabel("IP地址：");
        ip.setBounds(70,110,60,40);
        JTextField input_ip=new JTextField();
        input_ip.setBounds(130,110,400,40);

        JButton connect=new JButton("开始连接");
        connect.setBounds(90,170,140,45);
        JButton connected=new JButton("等待连接");
        connected.setBounds(240,170,140,45);
        JButton exit=new JButton("退出");
        exit.setBounds(390,170,140,45);

        JTextArea tips=new JTextArea("您的本地IP地址为："+localAddress()+
                "\n对战双方打开该窗口后 双方输入各自昵称\n随后一方输入\"等待连接\"\n"
                +"一方输入对方IP地址后点击\"开始连接\"即可" +
                "\n等待对方同意即可进入对战界面\n");
        tips.setEditable(false);
        tips.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(tips);
        jScrollPane.setBounds(110,230,400,100);

        contentPane.add(name);
        contentPane.add(input_name);
        contentPane.add(ip);
        contentPane.add(input_ip);
        contentPane.add(connect);
        contentPane.add(connected);
        contentPane.add(exit);
        contentPane.add(jScrollPane);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1_name =input_name.getText();
                player1.setName(player1_name);
                String player1_ip=input_ip.getText();
                connected.setEnabled(false);
                tips.append("正在请求同意连接...\n");
                try {
                    socket=new Socket(player1_ip,8998);
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(player1_name+"@BW@"+player1_ip);
                    dataInputStream=new DataInputStream(socket.getInputStream());
                    String message = dataInputStream.readUTF();
                    if(message.startsWith("agree")){
                        player1=new Player();
                        player2=new Player();
                        player1.setName(player1_name);
                        String[] split = message.split(":");
                        String player2_name=split[1];
                        player2.setName(player2_name);
                        int order=Integer.parseInt(split[2]);
                        if(order==0){
                            player2.setFirst(false);
                        }else {
                            player2.setFirst(true);
                        }
                        new MainFrame(socket,dataOutputStream,dataInputStream,player1,player2);
                    }
                } catch (IOException e1) {
                    tips.append("请求连接失败\n");
                    connected.setEnabled(true);
                }
            }
        });

        connected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1_name=input_name.getText();
                tips.append("正在等待连接...\n");
                try {
                    serverSocket=new ServerSocket(8998);
                    socket=serverSocket.accept();
                    dataInputStream=new DataInputStream(socket.getInputStream());
                    String message=dataInputStream.readUTF();
                    String[] split = message.split("@BW@");
                    String player2_name=split[0];
                    String player2_ip=split[1];
                    new ConfirmDialog(socket,dataInputStream,player1_name,player2_name,player2_ip);
                } catch (IOException e1) {
                    tips.append("没有连接\n");
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public String localAddress(){
        InetAddress ip;
        String localip;
        try {
            ip=InetAddress.getLocalHost();
            localip=ip.getHostAddress();
        } catch (UnknownHostException e) {
            localip="找不到本地IP";
        }
        return localip;
    }
}
