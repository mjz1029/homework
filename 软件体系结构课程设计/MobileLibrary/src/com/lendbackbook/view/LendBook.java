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
		
		JFrame frame = new JFrame("�� �� ͼ ��");		
		frame.setLayout(null);
		//��6����壬��������λ��
		JPanel pan1 = new JPanel();//�������ϵͳ�������
		JPanel pan2 = new JPanel();//��������û���ݺ����������
		JPanel pan5 = new JPanel();//������ŵ�¼���
		
		//�ı���
		JTextField textId = new JTextField();//
		
		//��ʾ��
		JLabel label1 = new JLabel("�� �� ͼ ��");
		JLabel label2 = new JLabel("ͼ �� �� ��  ");
	
		//��ť
		JButton button1 = new JButton("���");

		Font font = new Font("����",Font.BOLD,50);//���������С		
		Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
		
		//�����ı���Ĵ�С
		textId.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
				
		//���ý������������С���������⡢��ʾ��������ı���
		label1.setFont(font);//���ñ�������
		label2.setFont(f);
		textId.setFont(f);
		button1.setFont(f);
		
		//�������������
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(textId);
		pan5.add(button1);
		
		//�������λ��
		pan1.setBounds(140,50, 430, 60);
		pan2.setBounds(130,170, 430, 50);			
		pan5.setBounds(340,300,100, 50);//�Ų�ѯ��ť

		//������
		frame.add(pan1);
		frame.add(pan2);		
		frame.add(pan5);			
		
		//ͼ�����
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String textid = textId.getText();
				if(StringUtil.isEmpty(textid)) {
					JOptionPane.showMessageDialog(null, "ͼ���˺Ų���Ϊ�գ�");
					return;
				}

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
				
				try {
					con = dbutil.getCon();
					
					//��������ͼ��id�Ƿ���ڣ��Ƿ񱻽��
					ResultSet currentBook = bookdao.bookCheck(con, textid);
					if(currentBook.next()) {//�������
					//	if(currentBook.getInt("lend") == 1) {
					//		JOptionPane.showMessageDialog(null, "�����Ѿ���������������������ͼ�飡");
					//		return;
					//	}
						String bookName = currentBook.getString("bookName");
						
						int lendBook = bookdao.lend(con,textid,bookName,userMessage);//
						
						if(lendBook == 0) {
							JOptionPane.showMessageDialog(null, "����ɹ�");						
						} else {
							JOptionPane.showMessageDialog(null, "δ�ҵ���ͼ�飬��������ȷ��ͼ��ID��");
						}	
						
					} else {
						JOptionPane.showMessageDialog(null, "δ�ҵ���ͼ�飬��������ȷ��ͼ��ID��");
						return;
					}					
					
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
		});
	
		//��������
		frame.setBounds(500, 150, 750, 550);
		frame.setVisible(true);		
	}		
}
