package com.bookmanager.view;

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
import javax.swing.JTextField;

import com.book.dao.BookDao;
import com.book.model.Book;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class BookUpDataFram {

	public static void bookUpDataFram() {
			
			JFrame frame = new JFrame("图书修改界面");
			frame.setLayout(null);
			//开6个面板，方便设置位置
			JPanel pan1 = new JPanel();//用来存放系统名字组件
			JPanel pan2 = new JPanel();//用来存放用户身份和下拉框组件
			JPanel pan3 = new JPanel();//用来存放账号和文本框组件
			JPanel pan4 = new JPanel();//用来存放密码和密码框组件
			JPanel pan5 = new JPanel();//用来存放提交组件
			JPanel pan6 = new JPanel();//用来存放提交组件
			//文本框
			JTextField textId = new JTextField();//账号
			JTextField textName = new JTextField();//用户名
			JTextField textAuthor = new JTextField();//
			JTextField textPress = new JTextField();//
			
			//提示框
			JLabel label1 = new JLabel("修 改 界 面");
			JLabel label2 = new JLabel("图书编号  ");
			JLabel label3 = new JLabel("图书名字  ");
			JLabel label4 = new JLabel("作    者  ");
			JLabel label5 = new JLabel("出 版 社  ");

			//按钮
			JButton button1 = new JButton("提交");
			
			Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
			Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
			
			//设置文本框的大小	
			textId.setPreferredSize(new Dimension(200,30));
			textName.setPreferredSize(new Dimension(200,30));
			textAuthor.setPreferredSize(new Dimension(200,30));
			textPress.setPreferredSize(new Dimension(200,30));
			
			button1.setPreferredSize(new Dimension(90,40));
			
			
			//设置界面所有字体大小，包括标题、提示框字体和文本框
			label1.setFont(font);//设置标题字体
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textName.setFont(f);
			label4.setFont(f);
			textAuthor.setFont(f);
			label5.setFont(f);
			textPress.setFont(f);		
			button1.setFont(f);
			
			//向面板中添加组件
			pan1.add(label1);
			pan2.add(label2);
			pan2.add(textId);
			pan3.add(label3);
			pan3.add(textName);
			pan4.add(label4);
			pan4.add(textAuthor);			
			pan5.add(label5);
			pan5.add(textPress);
			pan6.add(button1);
			
			//设置面板位置
			pan1.setBounds(235,50, 430, 60);
			pan2.setBounds(235,170, 430, 50);
			pan3.setBounds(235,240, 430, 50);
			pan4.setBounds(235,310, 430, 50);			
			pan5.setBounds(235,360,430, 50);		
			pan6.setBounds(450,450,100, 50);
			//添加面板
			frame.add(pan1);
			frame.add(pan2);
			frame.add(pan3);
			frame.add(pan4);
			frame.add(pan5);
			frame.add(pan6);
			
			//提交事件监听
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Book bookMessage = new Book();
					
					String id = textId.getText();
					String name = textName.getText();
					String author = textAuthor.getText();
					String press = textPress.getText();

					//注册时，判断输入文本框的值不能为空
					if(StringUtil.isEmpty(id)) {
						JOptionPane.showMessageDialog(null, "图书编号不能为空");
						return;
					} 
					else if(StringUtil.isEmpty(name)){
						JOptionPane.showMessageDialog(null, "图书名字不能为空！");
						return;
					}
					else if(StringUtil.isEmpty(author)){
						JOptionPane.showMessageDialog(null, "作者不能为空！");
						return;
					}
					else if(StringUtil.isEmpty(press)){
						JOptionPane.showMessageDialog(null, "出版社不能为空！");
						return;
					}
					//将用户输入的信息封装到userMessage类里面
					bookMessage.setBookId(id);
					bookMessage.setBookName(name);
					bookMessage.setAuthor(author);
					bookMessage.setPress(press);
					
					DbUtil dbutil = new DbUtil();
					BookDao bookdao = new BookDao();
					
					try {
						Connection con = dbutil.getCon();
						
						int current = bookdao.update(con,bookMessage);
						
						//提示框，若返回值是1，添加成功
						if(current == 1) {
							JOptionPane.showMessageDialog(null, "修改成功");
							frame.dispose();
							return;
						} else {
							JOptionPane.showMessageDialog(null, "修改失败,请检查图书Id是否正确！");
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
		}	
}
