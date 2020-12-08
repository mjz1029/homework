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

	//дһ����¼���ѯ��ʷ��¼�ķ���
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void HistoryBook() {
		
		JFrame frame = new JFrame("��ʷ��ѯ");
		frame.getContentPane().setLayout(null);
		
		JPanel pan = new JPanel();
		
		Font font = new Font("����",Font.BOLD,50);//���������С		
		Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
		Font f2 = new Font("����",Font.BOLD,13);//������������С

	

		String [] columnNames = { "���", "ͼ����", "�Ƿ��ѹ黹"};
		DefaultTableModel model = new DefaultTableModel(columnNames,6);
		JTable jTable = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(jTable);
		
		scrollPane.setBounds(200,140, 800, 250);
		//frame.setBounds(450, 150, 1150, 650);
	
		Connection con = null;
		DbUtil dbutil = new DbUtil();			
		BookDao bookdao = new BookDao();	
		
		//��ȡ��¼���id
		String loginId = BookMenuFram.loginId();
		//��ȡ��¼����û���
		String loginName = BookMenuFram.loginName();
		
		User userMessage = new User();
		userMessage.setUserId(loginId);
		userMessage.setUsername(loginName);
				
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		dtm.setRowCount(0);//��ʼ��Ϊ0��
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
