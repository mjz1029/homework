package com.usermanager.view;

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

import com.book.dao.UserDao;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class UserDeleteFram {

		//дһ����¼��ɾ���û�����
		public static void UserDeleteFram() {
			
			JFrame frame = new JFrame("ɾ���û�");		
			frame.setLayout(null);
			//��6����壬��������λ��
			JPanel pan1 = new JPanel();//
			JPanel pan2 = new JPanel();//
			JPanel pan5 = new JPanel();//������ŵ�¼���
			
			//�ı���
			JTextField textId = new JTextField();//
			
			//��ʾ��
			JLabel label1 = new JLabel("�û�ɾ��");
			JLabel label2 = new JLabel("�� �� ��  ");
		
			//��ť
			JButton button1 = new JButton("ɾ��");
	
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
			
			//�û���ѯ����
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									
					String textid = textId.getText().toString();
					if(StringUtil.isEmpty(textid)) {
						JOptionPane.showMessageDialog(null, "�û��˺Ų���Ϊ�գ�");
						return;
					}

					Connection con = null;
					DbUtil dbutil = new DbUtil();			
					UserDao userdao = new UserDao();
					
					try {
						con = dbutil.getCon();			
						int currentUser = userdao.userDelete(con,textid);//���Ҳ�ɾ��������ֵΪ1
						
						if(currentUser == 1) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");						
						} else {
							JOptionPane.showMessageDialog(null, "δ�ҵ����û���");
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
