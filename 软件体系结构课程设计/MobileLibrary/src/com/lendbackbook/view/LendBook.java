package com.lendbackbook.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.dao.BookDao;
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;
import com.bookface.BookMenuFram;
import com.bookface.LogOnFram;

public class LendBook {

	
	public static void LendBook() {
		
		JFrame frame = new JFrame("借 出 图 书");		
		frame.setLayout(null);
		//开6个面板，方便设置位置
		JPanel pan1 = new JPanel();//用来存放系统名字组件
		JPanel pan2 = new JPanel();//用来存放用户身份和下拉框组件
		JPanel pan5 = new JPanel();//用来存放登录组件
		
		//文本框
		JTextField textId = new JTextField();//
		
		//提示框
		JLabel label1 = new JLabel("借 出 图 书");
		JLabel label2 = new JLabel("图 书 帐 号  ");
	
		//按钮
		JButton button1 = new JButton("添加");

		Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
		Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
		
		//设置文本框的大小
		textId.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
				
		//设置界面所有字体大小，包括标题、提示框字体和文本框
		label1.setFont(font);//设置标题字体
		label2.setFont(f);
		textId.setFont(f);
		button1.setFont(f);
		
		//向面板中添加组件
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(textId);
		pan5.add(button1);
		
		//设置面板位置
		pan1.setBounds(140,50, 430, 60);
		pan2.setBounds(130,170, 430, 50);			
		pan5.setBounds(340,300,100, 50);//放查询按钮

		//添加面板
		frame.add(pan1);
		frame.add(pan2);		
		frame.add(pan5);			
		
		//图书监听
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String textid = textId.getText();
				if(StringUtil.isEmpty(textid)) {
					JOptionPane.showMessageDialog(null, "图书账号不能为空！");
					return;
				}

				Connection con = null;
				DbUtil dbutil = new DbUtil();			
				BookDao bookdao = new BookDao();
				
				//获取登录后的id
				String loginId = BookMenuFram.loginId();
				//获取登录后的用户名
				String loginName = BookMenuFram.loginName();
				
				User userMessage = new User();
				userMessage.setUserId(loginId);
				userMessage.setUsername(loginName);
				
				try {
					con = dbutil.getCon();
					
					//检查输入的图书id是否存在，是否被借出
					ResultSet currentBook = bookdao.bookCheck(con, textid);
					if(currentBook.next()) {//该书存在
					//	if(currentBook.getInt("lend") == 1) {
					//		JOptionPane.showMessageDialog(null, "该书已经被借出，请输入借阅其他图书！");
					//		return;
					//	}
						String bookName = currentBook.getString("bookName");
						
						int lendBook = bookdao.lend(con,textid,bookName,userMessage);//
						
						if(lendBook == 0) {
							JOptionPane.showMessageDialog(null, "借书成功");						
						} else {
							JOptionPane.showMessageDialog(null, "未找到该图书，请输入正确的图书ID！");
						}	
						
					} else {
						JOptionPane.showMessageDialog(null, "未找到该图书，请输入正确的图书ID！");
						return;
					}					
					
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
		});
	
		//窗口设置
		frame.setBounds(500, 150, 750, 550);
		frame.setVisible(true);		
	}		
}
