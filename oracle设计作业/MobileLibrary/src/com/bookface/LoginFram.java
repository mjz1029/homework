package com.bookface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.book.dao.UserDao;
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class LoginFram {

	//写一个注册界面的方法
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void LoginFram() {
		
		JFrame frame = new JFrame("注册界面");
		frame.getContentPane().setLayout(null);
		//开6个面板，方便设置位置
		JPanel pan1 = new JPanel();//用来存放系统名字组件
		JPanel pan2 = new JPanel();//用来存放用户身份和下拉框组件
		JPanel pan3 = new JPanel();//用来存放账号和文本框组件
		JPanel pan4 = new JPanel();//用来存放密码和密码框组件
		JPanel repan = new JPanel();//用来存放重复密码和密码框组件
		JPanel pan5 = new JPanel();//用来存放提交组件
	
		//文本框
		JTextField textId = new JTextField();//账号
		JTextField textName = new JTextField();//用户名
		JPasswordField passwordfield = new JPasswordField();
		JPasswordField repasswordfield = new JPasswordField();
		
		//提示框
		JLabel label1 = new JLabel("注 册 界 面");
		JLabel label2 = new JLabel("账    号  ");
		JLabel label3 = new JLabel("用 户 名  ");
		JLabel label4 = new JLabel("密    码  ");

		JLabel label5 = new JLabel("重复密码  ");
		
		//按钮
		JButton button1 = new JButton("提交");
		
		Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
		Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
		
		//设置文本框的大小	
		textId.setPreferredSize(new Dimension(200,30));
		textName.setPreferredSize(new Dimension(200,30));
		passwordfield.setPreferredSize(new Dimension(200,30));
		repasswordfield.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
		
		
		//设置界面所有字体大小，包括标题、提示框字体和文本框
		label1.setFont(font);//设置标题字体
		label2.setFont(f);
		textId.setFont(f);
		label3.setFont(f);
		textName.setFont(f);
		label4.setFont(f);
		passwordfield.setFont(f);
		label5.setFont(f);
		repasswordfield.setFont(f);
		button1.setFont(f);
		
		//向面板中添加组件
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(textId);
		pan3.add(label3);
		pan3.add(textName);
		pan4.add(label4);
		pan4.add(passwordfield);
		repan.add(label5);
		repan.add(repasswordfield);//重复密码
		pan5.add(button1);

		
		//设置面板位置
		pan1.setBounds(235,50, 430, 60);
		pan2.setBounds(235,170, 430, 50);
		pan3.setBounds(235,240, 430, 50);
		pan4.setBounds(235,310, 430, 50);
		repan.setBounds(235,380, 430, 50);
		pan5.setBounds(450,440,100, 50);
		
		//添加面板
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(repan);
		frame.getContentPane().add(pan5);
		
		//提交事件监听
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userMessage = new User();
				String id = textId.getText().toString();
				String name = textName.getText().toString();

				String password = new String(passwordfield.getPassword());
				String repassword = new String(repasswordfield.getPassword());
				
				//判断两次密码输入是否一致
				if(!password.equals(repassword)) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入");
					return;
				}
				//注册时，判断输入文本框的值不能为空
				if(StringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null, "账号不能为空");
					return;
				} 
				else if(StringUtil.isEmpty(name)){
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				else if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(null, "密码不能为空！");
					return;
				}
				
				//将用户输入的信息封装到userMessage类里面
				userMessage.setUserId(id);
				userMessage.setUsername(name);
				userMessage.setPassword(password);
				
				DbUtil dbutil = new DbUtil();
				UserDao userdao = new UserDao();
				
				//账号不能相同
		
				try {
					Connection con = dbutil.getCon();
					
					int current = userdao.add(con,userMessage);
					
					//提示框，若返回值是0，注册成功
					if(current != 0) {
						JOptionPane.showMessageDialog(null, "注册成功");
						frame.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "账号已存在！");
						return;
					}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
			
		});
		
		//窗口设置
		frame.setBounds(500, 150, 950, 650);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
