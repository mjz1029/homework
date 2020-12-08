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

		//дһ����¼��鿴�û��ķ���
		/**
		 * @wbp.parser.entryPoint
		 */
		public static void UserLookFram() {
			
			JFrame frame = new JFrame("�鿴�û�");		
			frame.getContentPane().setLayout(null);
			//��6����壬��������λ��
			JPanel pan1 = new JPanel();//�������ϵͳ�������
			JPanel pan2 = new JPanel();//��������û���ݺ����������
			JPanel pan3 = new JPanel();//��������˺ź��ı������
			JPanel pan5 = new JPanel();//������ŵ�¼���
			
			//�ı���
			JTextField textId = new JTextField();//
			JTextField textusername = new JTextField();//
			
			//��ʾ��
			JLabel label1 = new JLabel("�û���ѯ");
			JLabel label2 = new JLabel("�� �� ��  ");
			JLabel label3 = new JLabel("�� �� ��  ");
		
			//��ť
			JButton button1 = new JButton("��ѯ");
	
			Font font = new Font("����",Font.BOLD,50);//���������С		
			Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
			Font f2 = new Font("����",Font.BOLD,13);//������������С
			
			//�����ı���Ĵ�С
			textId.setPreferredSize(new Dimension(200,30));
			textusername.setPreferredSize(new Dimension(200,30));
			button1.setPreferredSize(new Dimension(90,40));
					
			//���ý������������С���������⡢��ʾ��������ı���
			label1.setFont(font);//���ñ�������
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textusername.setFont(f);
			button1.setFont(f);
			
			//�������������
			pan1.add(label1);
			pan2.add(label2);
			pan2.add(textId);
			pan3.add(label3);
			pan3.add(textusername);
			pan5.add(button1);
			
			//�������λ��
			pan1.setBounds(343,50, 430, 60);
			pan2.setBounds(100,170, 430, 50);
			pan3.setBounds(100,320, 430, 50);				
			pan5.setBounds(515,470,100, 50);//�Ų�ѯ��ť

			String [] columnNames = { "�û�id", "�û���" };
			DefaultTableModel model = new DefaultTableModel(columnNames,11);
			JTable jTable = new JTable(model);
			
			JScrollPane scrollPane = new JScrollPane(jTable);
			//scrollPane.setPreferredSize(new Dimension(30,10));
			scrollPane.setBounds(580,170, 430, 201);
			
			//�û���ѯ����
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
					dtm.setRowCount(0);//��ʼ��Ϊ0��
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
			
			//������
			frame.getContentPane().add(pan1);
			frame.getContentPane().add(pan2);
			frame.getContentPane().add(pan3);		
			frame.getContentPane().add(pan5);			
			frame.getContentPane().add(scrollPane);
			
			//��������
			frame.setBounds(450, 150, 1150, 650);
			frame.setVisible(true);		
		}		
}
