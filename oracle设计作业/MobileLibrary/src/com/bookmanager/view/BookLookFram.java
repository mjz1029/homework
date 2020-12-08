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

		//дһ����¼��ͼ�������
		public static void BookLookFram() {
			
			JFrame frame = new JFrame("ͼ���ѯ");
			
			frame.setLayout(null);
			//��6����壬��������λ��
			JPanel pan1 = new JPanel();//�������ϵͳ�������
			JPanel pan2 = new JPanel();//��������û���ݺ����������
			JPanel pan3 = new JPanel();//��������˺ź��ı������
			JPanel pan4 = new JPanel();//��������������������
			JPanel pan5 = new JPanel();//������ŵ�¼���
			JPanel pan6 = new JPanel();//��Ų�ѯ���
			
			//�ı���
			JTextField textId = new JTextField();//
			JTextField textbookname = new JTextField();//
			JTextField textauthor = new JTextField();//
			
			//��ʾ��
			JLabel label1 = new JLabel("ͼ���ѯ");
			JLabel label2 = new JLabel("ͼ �� ��  ");
			JLabel label3 = new JLabel("��    ��  ");
			JLabel label4 = new JLabel("��    ��  ");
					
			//��ť
			JButton button1 = new JButton("��ѯ");
			
			Font font = new Font("����",Font.BOLD,50);//���������С		
			Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
			Font f2 = new Font("����",Font.BOLD,13);//������������С
			//�����ı���Ĵ�С
			textId.setPreferredSize(new Dimension(200,30));
			textbookname.setPreferredSize(new Dimension(200,30));
			textauthor.setPreferredSize(new Dimension(200,30));
			button1.setPreferredSize(new Dimension(90,40));
					
			//���ý������������С���������⡢��ʾ��������ı���
			label1.setFont(font);//���ñ�������
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textbookname.setFont(f);
			label4.setFont(f);
			textauthor.setFont(f);
			button1.setFont(f);
			
			//�������������
			pan1.add(label1);
			pan2.add(label2);
			pan2.add(textId);
			pan3.add(label3);
			pan3.add(textbookname);
			pan4.add(label4);
			pan4.add(textauthor);
			pan5.add(button1);
			
			//�������λ��
			pan1.setBounds(235,50, 430, 60);
			pan2.setBounds(100,170, 430, 50);
			pan3.setBounds(100,240, 430, 50);
			pan4.setBounds(100,310, 430, 50);		
			pan5.setBounds(250,450,100, 50);//�Ų�ѯ��ť
			//pan6.setBounds(580,170, 430, 210);

			String [] columnNames = { "ͼ��id","ͼ����" ,"������", "����","�Ƿ��ѽ��"};
			DefaultTableModel model = new DefaultTableModel(columnNames,11);
			JTable jTable = new JTable(model);
			
			JScrollPane scrollPane = new JScrollPane(jTable);
			//scrollPane.setPreferredSize(new Dimension(30,10));
			scrollPane.setBounds(580,170, 430, 201);
			
			//����
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									
					String bookid = textId.getText().toString();
					String bookname = textbookname.getText().toString();
					String bookauthor = textauthor.getText().toString();
//					if(StrignUtil.isEmpty(bookid) && StrignUtil.isEmpty(bookname) && StrignUtil.isEmpty(bookauthor)) {
//						JOptionPane.showMessageDialog(null, "����������һ�ֲ�ѯ");	
//					}
					Connection con = null;
					DbUtil dbutil = new DbUtil();			
					BookDao bookdao = new BookDao();	
					Book bookMessage = new Book();
					
					bookMessage.setBookId(bookid);
					bookMessage.setBookName(bookname);
					bookMessage.setAuthor(bookauthor);			
					
					DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
					dtm.setRowCount(0);//��ʼ��Ϊ0��
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
								v.add("��");
							} else {
								v.add("��");
							}
							dtm.addRow(v);
						}
					
					}catch(Exception evt) {
						evt.printStackTrace();
					}
				}
			});
					
			//������
			frame.add(pan1);
			frame.add(pan2);
			frame.add(pan3);
			frame.add(pan4);
			frame.add(pan5);			
			frame.add(scrollPane);
			
			//��������
			frame.setBounds(450, 150, 1150, 650);
			frame.setVisible(true);		
		}		
}
