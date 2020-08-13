package com.boz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author boz
 * @date 2019/8/28
 */
public class Jsq {
    public static void main(String[] args) {
        final JFrame frm = new JFrame();
        frm.setBounds(300, 300, 500, 500);
        frm.setTitle("我的窗口");

        //设置网格布局
        frm.setLayout(new GridLayout(3,2,10,5));

        Container c = frm.getContentPane();  // frm中包含一个内容窗格， 需要获取内容窗格，再设置背景颜色，直接设置frm的背景颜色会被内容窗格挡住
        c.setBackground(Color.lightGray);

        frm.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

        //button
        JButton button = new JButton();
        button.setText("计算");




        //text
        final JTextField jtf = new JTextField("", 30); 		// 创建文本行组件, 30 列
        final JTextField jpf = new JTextField("", 30);   // 创建密码文本行组件, 30 列
        final JTextField jtf3 = new JTextField("", 30);   // 创建密码文本行组件, 30 列
//		JTextArea jta = new JTextArea("您好", 10, 30);               // 创建文本区组件,10行，30列
//		JScrollPane jsp = new JScrollPane(jta);                    // 创建滚动窗格，其显示内容是文本区对象
//        jta.setLineWrap(true); // 设置自动换行

        JLabel label1 = new JLabel("数字1：");
        JLabel label2 = new JLabel("数字2：");
        JLabel label3 = new JLabel("结果：");



        label1.setBounds(0,10,50,20);
        jpf.setBounds(40, 10, 140, 20);
        label2.setBounds(0,40,50,20);
        jtf.setBounds(40, 40, 140, 20);
        label3.setBounds(0,70,50,20);
        jtf3.setBounds(40, 70, 140, 20);
//        jsp.setBounds(40, 70, 160, 100);

        button.setBounds(350, 400, 100, 40);




        // 把组件添加进窗口f中
        frm.add(jpf);
        frm.add(jtf);
//        frm.add(jsp);
        frm.add(button); // 添加了按钮会把背景颜色挡住，可以通过面板来调节
        frm.add(label1);
        frm.add(label2);
        frm.add(label3);
        frm.add(jtf3);


        frm.addWindowListener(new MyTestWin());

        //添加一个活动监听
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(frm,"弹出框","Alert",JOptionPane.WARNING_MESSAGE);
                System.out.println("退出, 按钮干的");
                System.out.println(jtf.getText());
                System.out.println(jpf.getText());
//               System.exit(0);
                int t1 = Integer.parseInt(jtf.getText());
                int t2 = Integer.parseInt(jpf.getText());
                String t3 = String.valueOf(t1*t2);
                jtf3.setText(t3);
            }
        });


        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
}

class MyTestWin extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
//        System.out.println("Window closing"+e.toString());
        System.out.println("我关了");
        System.exit(0);
    }
    @Override
    public void windowActivated(WindowEvent e) {
        //每次获得焦点 就会触发
        System.out.println("我活了");
    }
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("我开了");
    }
}