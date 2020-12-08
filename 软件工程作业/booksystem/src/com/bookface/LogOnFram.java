package com.bookface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.book.dao.AdminDao;
import com.book.dao.UserDao;
import com.book.model.Admin;
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class LogOnFram {
	
	//文本框
	static JTextField textId = new JTextField();//账号
	static JPasswordField passwordfield = new JPasswordField();
	
	 //下拉框，存放用户身份
	static JComboBox<String> comBox = new JComboBox<String>();

	static String userName = null; //定义name，便于返回到主页面使用
	static String adminName = null;
	static String id = null; //定义id，便于返回到主页面使用
	
	static JFrame frame = new JFrame("登录界面");
	
	//写一个登录界面的方法
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void LogOnFram() {
		
		frame.getContentPane().setLayout(null);
		//开6个面板，方便设置位置
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		JPanel pan5 = new JPanel();
		JPanel pan6 = new JPanel();
		
		comBox.addItem("普通用户");
		comBox.addItem("管理员");
		
		//提示框
		JLabel label1 = new JLabel("在线书店");
		JLabel label2 = new JLabel("身    份  ");
		JLabel label3 = new JLabel("账    号  ");
		JLabel label4 = new JLabel("密    码  ");
		
		//按钮
		JButton button1 = new JButton("登录");
		JButton button2 = new JButton("注册");
		
		Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
		Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
		
		//设置文本框的大小
		comBox.setPreferredSize(new Dimension(200,30));
		textId.setPreferredSize(new Dimension(200,30));
		passwordfield.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
		button2.setPreferredSize(new Dimension(90,40));
		
		//设置界面所有字体大小，包括标题、提示框字体和文本框
		label1.setFont(font);//设置标题字体
		label2.setFont(f);
		comBox.setFont(f);
		label3.setFont(f);
		textId.setFont(f);
		label4.setFont(f);
		passwordfield.setFont(f);
		button1.setFont(f);
		button2.setFont(f);
		
		//向面板中添加组件
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(comBox);
		pan3.add(label3);
		pan3.add(textId);
		pan4.add(label4);
		pan4.add(passwordfield);
		pan5.add(button1);
		pan6.add(button2);

		//设置面板位置
		pan1.setBounds(235,50, 430, 60);
		pan2.setBounds(235,170, 430, 50);
		pan3.setBounds(235,240, 430, 50);
		pan4.setBounds(235,310, 430, 50);
		
		pan5.setBounds(330,380,100, 50);
		pan6.setBounds(480,380,100, 50);
		
		//添加面板
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(pan5);
		frame.getContentPane().add(pan6);
		
		//登录监听
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logCheck();	
			}		
		});
		
		//注册监听
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			LoginFram.LoginFram();
			}
		});
		
		//窗口设置
		frame.setBounds(500, 150, 900, 650);
		
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//获取输入文本框的值，并传到数据库 进行比对
	public static void logCheck() {
		
		String userId = textId.getText().toString();
		String Password = new String(passwordfield.getPassword());
		
		if(StringUtil.isEmpty(userId)) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}
		if(StringUtil.isEmpty(Password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		
		Connection con = null;
		DbUtil dbutil = new DbUtil();
		UserDao userdao = new UserDao();

		User userMessage = new User();
		userMessage.setUserId(userId);			
		userMessage.setPassword(Password);
		
		String box = (String)comBox.getSelectedItem();//获取下拉框的文字
		
		//如果下拉框选的是普通用户，
		if(box.equals("普通用户")) {
				try {
				con = dbutil.getCon();				
				User currentUser = userdao.login(con,userMessage);					
				if(currentUser != null ) {
					JOptionPane.showMessageDialog(null, "登录成功");
					 //currentUser.getUsername();//获取登录者的用户名
					//frame.dispose();//关闭当前登录界面						
					userName = currentUser.getUsername();
					id = currentUser.getUserId();
					
					frame.dispose();//关闭当前登录界面
					BookMenuFram.BookMenuFram();
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
		}

		AdminDao admindao = new AdminDao();
		Admin adminMessage = new Admin();
		adminMessage.setAdminId(userId);			
		adminMessage.setPassword(Password);
				
		//如果下拉框选的是管理员，
		if(box.equals("管理员")) {
				try {
				con = dbutil.getCon();				
				Admin currentUser = admindao.adminLogin(con,adminMessage);					
				if(currentUser != null ) {
					JOptionPane.showMessageDialog(null, "管理员登录成功");
	
					adminName = currentUser.getAdminname();
					id = currentUser.getAdminId();
					frame.dispose();//关闭当前登录界面
					BookMenuFram.BookMenuFram();
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
		}
	}
	// 比对成功后，获取数据库中的用户名
	public static String userName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public static String adminName() {
		return adminName;
	}
	public static String id() {
		return id;
	}
}