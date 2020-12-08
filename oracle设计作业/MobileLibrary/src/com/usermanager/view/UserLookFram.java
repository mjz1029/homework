package com.usermanager.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.book.dao.UserDao;
import com.book.model.User;
import com.book.util.DbUtil;

public class UserLookFram {

		//写一个登录后查看用户的方法
		/**
		 * @wbp.parser.entryPoint
		 */
		public static void UserLookFram() {
			
			JFrame frame = new JFrame("查看用户");		
			frame.getContentPane().setLayout(null);
			//开6个面板，方便设置位置
			JPanel pan1 = new JPanel();//用来存放系统名字组件
			JPanel pan2 = new JPanel();//用来存放用户身份和下拉框组件
			JPanel pan3 = new JPanel();//用来存放账号和文本框组件
			JPanel pan5 = new JPanel();//用来存放登录组件
			
			//文本框
			JTextField textId = new JTextField();//
			JTextField textusername = new JTextField();//
			
			//提示框
			JLabel label1 = new JLabel("用户查询");
			JLabel label2 = new JLabel("用 户 号  ");
			JLabel label3 = new JLabel("用 户 名  ");
		
			//按钮
			JButton button1 = new JButton("查询");
	
			Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
			Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
			Font f2 = new Font("宋体",Font.BOLD,13);//结果表中字体大小
			
			//设置文本框的大小
			textId.setPreferredSize(new Dimension(200,30));
			textusername.setPreferredSize(new Dimension(200,30));
			button1.setPreferredSize(new Dimension(90,40));
					
			//设置界面所有字体大小，包括标题、提示框字体和文本框
			label1.setFont(font);//设置标题字体
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textusername.setFont(f);
			button1.setFont(f);
			
			//向面板中添加组件
			pan1.add(label1);
			pan2.add(label2);
			pan2.add(textId);
			pan3.add(label3);
			pan3.add(textusername);
			pan5.add(button1);
			
			//设置面板位置
			pan1.setBounds(343,50, 430, 60);
			pan2.setBounds(100,170, 430, 50);
			pan3.setBounds(100,320, 430, 50);				
			pan5.setBounds(515,470,100, 50);//放查询按钮

			String [] columnNames = { "用户id", "用户名" };
			DefaultTableModel model = new DefaultTableModel(columnNames,11);
			JTable jTable = new JTable(model);
			
			JScrollPane scrollPane = new JScrollPane(jTable);
			//scrollPane.setPreferredSize(new Dimension(30,10));
			scrollPane.setBounds(580,170, 430, 201);
			
			//用户查询监听
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									
					String textid = textId.getText().toString();
					String username = textusername.getText().toString();

					Connection con = null;
					DbUtil dbutil = new DbUtil();			
					UserDao userdao = new UserDao();
					
					User userMessage = new User();
					userMessage.setUserId(textid);
					userMessage.setUsername(username);
					
					DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
					dtm.setRowCount(0);//初始化为0行
					jTable.setFont(f2);
					try {
						con = dbutil.getCon();			
						ResultSet currentUser = userdao.userLook(con,userMessage);						

						while(currentUser.next()) {
							Vector v = new Vector();
							v.add(currentUser.getString("userId"));
							v.add(currentUser.getString("username"));
							dtm.addRow(v);
						}
						
					}catch(Exception evt) {
						evt.printStackTrace();
					}
				}
			});
			
			//添加面板
			frame.getContentPane().add(pan1);
			frame.getContentPane().add(pan2);
			frame.getContentPane().add(pan3);		
			frame.getContentPane().add(pan5);			
			frame.getContentPane().add(scrollPane);
			
			//窗口设置
			frame.setBounds(450, 150, 1150, 650);
			frame.setVisible(true);		
		}		
}
