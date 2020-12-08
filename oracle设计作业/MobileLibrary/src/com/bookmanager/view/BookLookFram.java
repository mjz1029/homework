package com.bookmanager.view;

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

import com.book.dao.BookDao;
import com.book.model.Book;
import com.book.util.DbUtil;

public class BookLookFram {

		//写一个登录后图书管理方法
		public static void BookLookFram() {
			
			JFrame frame = new JFrame("图书查询");
			
			frame.setLayout(null);
			//开6个面板，方便设置位置
			JPanel pan1 = new JPanel();//用来存放系统名字组件
			JPanel pan2 = new JPanel();//用来存放用户身份和下拉框组件
			JPanel pan3 = new JPanel();//用来存放账号和文本框组件
			JPanel pan4 = new JPanel();//用来存放密码和密码框组件
			JPanel pan5 = new JPanel();//用来存放登录组件
			JPanel pan6 = new JPanel();//存放查询结果
			
			//文本框
			JTextField textId = new JTextField();//
			JTextField textbookname = new JTextField();//
			JTextField textauthor = new JTextField();//
			
			//提示框
			JLabel label1 = new JLabel("图书查询");
			JLabel label2 = new JLabel("图 书 号  ");
			JLabel label3 = new JLabel("书    名  ");
			JLabel label4 = new JLabel("作    者  ");
					
			//按钮
			JButton button1 = new JButton("查询");
			
			Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
			Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
			Font f2 = new Font("宋体",Font.BOLD,13);//结果表中字体大小
			//设置文本框的大小
			textId.setPreferredSize(new Dimension(200,30));
			textbookname.setPreferredSize(new Dimension(200,30));
			textauthor.setPreferredSize(new Dimension(200,30));
			button1.setPreferredSize(new Dimension(90,40));
					
			//设置界面所有字体大小，包括标题、提示框字体和文本框
			label1.setFont(font);//设置标题字体
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textbookname.setFont(f);
			label4.setFont(f);
			textauthor.setFont(f);
			button1.setFont(f);
			
			//向面板中添加组件
			pan1.add(label1);
			pan2.add(label2);
			pan2.add(textId);
			pan3.add(label3);
			pan3.add(textbookname);
			pan4.add(label4);
			pan4.add(textauthor);
			pan5.add(button1);
			
			//设置面板位置
			pan1.setBounds(235,50, 430, 60);
			pan2.setBounds(100,170, 430, 50);
			pan3.setBounds(100,240, 430, 50);
			pan4.setBounds(100,310, 430, 50);		
			pan5.setBounds(250,450,100, 50);//放查询按钮
			//pan6.setBounds(580,170, 430, 210);

			String [] columnNames = { "图书id","图书名" ,"出版社", "作者","是否已借出"};
			DefaultTableModel model = new DefaultTableModel(columnNames,11);
			JTable jTable = new JTable(model);
			
			JScrollPane scrollPane = new JScrollPane(jTable);
			//scrollPane.setPreferredSize(new Dimension(30,10));
			scrollPane.setBounds(580,170, 430, 201);
			
			//监听
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									
					String bookid = textId.getText().toString();
					String bookname = textbookname.getText().toString();
					String bookauthor = textauthor.getText().toString();
//					if(StrignUtil.isEmpty(bookid) && StrignUtil.isEmpty(bookname) && StrignUtil.isEmpty(bookauthor)) {
//						JOptionPane.showMessageDialog(null, "请任意输入一种查询");	
//					}
					Connection con = null;
					DbUtil dbutil = new DbUtil();			
					BookDao bookdao = new BookDao();	
					Book bookMessage = new Book();
					
					bookMessage.setBookId(bookid);
					bookMessage.setBookName(bookname);
					bookMessage.setAuthor(bookauthor);			
					
					DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
					dtm.setRowCount(0);//初始化为0行
					jTable.setFont(f2);
					try {
						con = dbutil.getCon();			
						ResultSet currentbook = bookdao.list(con,bookMessage);
						
						while(currentbook.next()) {
							Vector v = new Vector();
							v.add(currentbook.getString("bookId"));
							v.add(currentbook.getString("bookName"));
							v.add(currentbook.getString("press"));
							v.add(currentbook.getString("author"));
							if(currentbook.getString("lend").equals("1")) {
								v.add("是");
							} else {
								v.add("否");
							}
							dtm.addRow(v);
						}
					
					}catch(Exception evt) {
						evt.printStackTrace();
					}
				}
			});
					
			//添加面板
			frame.add(pan1);
			frame.add(pan2);
			frame.add(pan3);
			frame.add(pan4);
			frame.add(pan5);			
			frame.add(scrollPane);
			
			//窗口设置
			frame.setBounds(450, 150, 1150, 650);
			frame.setVisible(true);		
		}		
}
