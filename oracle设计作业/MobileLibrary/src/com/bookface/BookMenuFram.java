package com.bookface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.util.StringUtil;
import com.bookmanager.view.BookAddFram;
import com.bookmanager.view.BookDeleteFram;
import com.bookmanager.view.BookLookFram;
import com.bookmanager.view.BookUpDataFram;
import com.lendbackbook.view.BackBook;
import com.lendbackbook.view.HistoryBook;
import com.lendbackbook.view.LendBook;
import com.usermanager.view.UserDeleteFram;
import com.usermanager.view.UserLookFram;

public class BookMenuFram extends JFrame {
	public BookMenuFram() {
	}
	
	static String loginId = null;
	static String loginName = null;//若是管理员登录，存管理员的名字，否则，存普通用户的名字
	
	//标记用户类别，若是0代表普通用户，1代表管理员，默认是0
	static int flag = 0;
	
	//系统主界面
	public static void BookMenuFram() {
		
		JFrame frame = new JFrame();//主菜单窗口	
		frame.getContentPane().setLayout(null);

		//开6个面板，方便设置位置
		JPanel pan1 = new JPanel();//系统名字
		JPanel pan2 = new JPanel();//图书管理
		JPanel pan3 = new JPanel();//图书管理的操作按钮
		JPanel pan4 = new JPanel();//购物车管理
		JPanel pan5 = new JPanel();//购物车的操作按钮
		JPanel pan6 = new JPanel();//用户管理
		JPanel pan7 = new JPanel();//用户管理的操作按钮
		JPanel pan8 = new JPanel();//放登录退出按钮
		JPanel pan9 = new JPanel();
		
		
		JLabel text = new JLabel();//提示框，不可删除
		text.setText("未登录！");//设置提示未登录
		
		//提示框
		JLabel label1 = new JLabel("流动图书馆");		
		JLabel label2 = new JLabel("图书管理");
		JLabel label3 = new JLabel("借阅管理");
		JLabel label4 = new JLabel("用户管理");
	

		//按钮
		JButton button1 = new JButton("登录");
		JButton button2 = new JButton("图书查询");
		JButton button3 = new JButton("图书添加");
		JButton button4 = new JButton("图书修改");
		JButton button5 = new JButton("图书删除");
		JButton button6 = new JButton("图书借阅");
		JButton button7 = new JButton("图书归还");
		JButton button8 = new JButton("借阅历史查询");
		JButton button9 = new JButton("查询用户");
		JButton button10 = new JButton("删除用户");
		JButton button11 = new JButton("退出");
		
		//设置颜色格式
		Color blacka = new Color(225,225,225);
		Color blackb = new Color(225,225,225);
		Color blackc = new Color(225,225,225);
		Color blackd = new Color(225,225,225);
		
		//设置字体大小对象
		Font font = new Font("宋体",Font.BOLD,60);//标题字体大小		
		Font f = new Font("宋体",Font.BOLD,30);//提示框字体大小
		Font f1 = new Font("宋体",Font.BOLD,20);//text
		
		//设置按钮的大小
		button2.setPreferredSize(new Dimension(300,65));
		button3.setPreferredSize(new Dimension(300,65));
		button4.setPreferredSize(new Dimension(300,65));
		button5.setPreferredSize(new Dimension(300,65));
		button6.setPreferredSize(new Dimension(300,65));
		button7.setPreferredSize(new Dimension(300,65));
		button8.setPreferredSize(new Dimension(300,65));
		button9.setPreferredSize(new Dimension(300,65));
		button10.setPreferredSize(new Dimension(300,65));
		text.setPreferredSize(new Dimension(170,35));//
		
		//设置字体大小
		button2.setFont(f);
		button3.setFont(f);
		button4.setFont(f);
		button5.setFont(f);
		button6.setFont(f);
		button7.setFont(f);
		button8.setFont(f);
		button9.setFont(f);
		button10.setFont(f);
		text.setFont(f1);
		label1.setFont(font);//设置标题字体
		label2.setFont(f);
		label3.setFont(f);
		label4.setFont(f);
		
		//向面板中添加组件
		pan1.add(label1);
		pan1.setBackground(blacka);
		pan1.setBounds(0, 0, 450, 120);//大标题
		
		//pan8定位在右上角，放登录、退出和显示框
		pan8.add(text);
		pan8.add(button1);
		pan8.add(button11);	
		pan8.setBackground(blacka);
		pan8.setBounds(450, 0, 190, 120);
        
		pan2.add(label2);
		pan2.setBackground(blackb);
		pan2.setBounds(0, 120, 200, 284);//图书管理
		
		pan3.add(button2);
		pan3.add(button3);
		pan3.add(button4);
		pan3.add(button5);
		pan3.setBounds(150, 120, 500, 284);
		
		pan4.add(label3);
		pan4.setBackground(blackc);
		pan4.setBounds(0, 404, 200, 213);//借还书
	    
		pan5.add(button6);
		pan5.add(button7);
		pan5.add(button8);
		pan5.setBounds(150, 404, 500, 213);
		
		pan6.add(label4);
		pan6.setBackground(blackd);
		pan6.setBounds(0, 617, 200, 142);//用户管理
	    
		pan7.add(button9);
		pan7.add(button10);
		pan7.setBounds(150, 617, 500, 200);
		
		
		String noLogin = "未登录！";//JVM先查看常量池中有没有，如有，地址指向它，若没有，创建新对象保存
		
		//获取登录后的用户名
		if(StringUtil.isNotEmpty(LogOnFram.userName())) {
		    loginName = LogOnFram.userName();
			text.setText("欢迎您，" + loginName);	
		}
		if(StringUtil.isNotEmpty(LogOnFram.adminName())) {
		    loginName = LogOnFram.adminName();
			text.setText("欢迎您，" + loginName);
			flag = 1;
		}
		//获取登录后的id
		if(StringUtil.isNotEmpty(LogOnFram.id())) {
			loginId = LogOnFram.id();
		}
		
		//登录监听
		button1.addActionListener(new ActionListener() {//登录监听
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {	
					LogOnFram.LogOnFram();
					frame.dispose();//关闭当前登录界面
				
				} else {
					JOptionPane.showMessageDialog(null, "请您先退出！");
				}				
			}			
		});
		
		//各项操作监听,button2--button10
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {	//equals()方法如果不重写，就是比较的字符串内容。而'=='比较的是地址
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
					BookLookFram.BookLookFram();//图书查询
				}
			}	
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
					if(flag == 1) {//如果flag是1，这可以添加图书
						BookAddFram.bookAddFram();//图书添加
					} else {
						JOptionPane.showMessageDialog(null, "管理员才可以执行此操作！");
					}				
				}	
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {	
					if(flag == 1) {//如果flag是1，这可以添加图书
						BookUpDataFram.bookUpDataFram();//修改图书
					} else {
						JOptionPane.showMessageDialog(null, "管理员才可以执行此操作！");
					}
				}
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
					if(flag == 1) {//如果flag是1，这可以添加图书
						BookDeleteFram.BookDeleteFram();//删除图书
					} else {
						JOptionPane.showMessageDialog(null, "管理员才可以执行此操作！");
					}
				}
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {  //办理借书
					LendBook.LendBook();
				}
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {  //办理还书
					BackBook.BackBook();
				}
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {	//历史查询
					HistoryBook.HistoryBook();
				}
			}
		});
		button9.addActionListener(new ActionListener() {//查询用户
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
					if(flag == 1) {//如果flag是1，这可以添加图书
						UserLookFram.UserLookFram();//用户查询
					} else {
						JOptionPane.showMessageDialog(null, "管理员才可以执行此操作！");
					}
					
				}
			}
		});
		button10.addActionListener(new ActionListener() {//删除用户
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
					if(flag == 1) {//如果flag是1，这可以添加图书
						UserDeleteFram.UserDeleteFram();//用户删除
					} else {
						JOptionPane.showMessageDialog(null, "管理员才可以执行此操作！");
					}
				}
			}
		});
		
		button11.addActionListener(new ActionListener() {//退出登录
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "请您先登录！");
					return;
				} else {
//					frame.dispose();
//					LogOnFram.LogOnFram();
					System.exit(0);
				}
			}
		});
		
		//向容器中添加JPanel面板
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(pan5);
		frame.getContentPane().add(pan6);
		frame.getContentPane().add(pan7);
		frame.getContentPane().add(pan8);
		frame.getContentPane().add(pan9);
		
		//窗口设置
		frame.setBounds(310, 100, 650, 800);
		frame.setResizable(false);//设置窗口不能扩大
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static String loginName() {
		return loginName;
	}
	public static String loginId() {
		return loginId;
	}
} 