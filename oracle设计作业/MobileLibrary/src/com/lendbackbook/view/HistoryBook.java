package com.lendbackbook.view;

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
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;
import com.bookface.BookMenuFram;
import com.bookface.LogOnFram;

public class HistoryBook {

	//写一个登录后查询历史记录的方法
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void HistoryBook() {
		
		JFrame frame = new JFrame("历史查询");
		frame.getContentPane().setLayout(null);
		
		JPanel pan = new JPanel();
		
		Font font = new Font("宋体",Font.BOLD,50);//标题字体大小		
		Font f = new Font("宋体",Font.BOLD,25);//提示框字体大小
		Font f2 = new Font("宋体",Font.BOLD,13);//结果表中字体大小

	

		String [] columnNames = { "序号", "图书名", "是否已归还"};
		DefaultTableModel model = new DefaultTableModel(columnNames,6);
		JTable jTable = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(jTable);
		
		scrollPane.setBounds(200,140, 800, 250);
		//frame.setBounds(450, 150, 1150, 650);
	
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
				
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		dtm.setRowCount(0);//初始化为0行
		jTable.setFont(f2);
		try {
			con = dbutil.getCon();			
			ResultSet currentbook = bookdao.listHistory(con,userMessage);
			
			while(currentbook.next()) {
				Vector v = new Vector();
				v.add(currentbook.getString("recordId"));
				v.add(currentbook.getString("bookName"));
				v.add(currentbook.getString("back"));
				
				dtm.addRow(v);
			}
		
		}catch(Exception evt) {
			evt.printStackTrace();
		}
	
		frame.getContentPane().add(scrollPane);
		frame.setBounds(450, 150, 1150, 650);
		frame.setVisible(true);		
	}
}
