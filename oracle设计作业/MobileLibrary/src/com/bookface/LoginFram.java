package com.bookface;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.book.dao.UserDao;
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class LoginFram {

	//дһ��ע�����ķ���
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void LoginFram() {
		
		JFrame frame = new JFrame("ע�����");
		frame.getContentPane().setLayout(null);
		//��6����壬��������λ��
		JPanel pan1 = new JPanel();//�������ϵͳ�������
		JPanel pan2 = new JPanel();//��������û���ݺ����������
		JPanel pan3 = new JPanel();//��������˺ź��ı������
		JPanel pan4 = new JPanel();//��������������������
		JPanel repan = new JPanel();//��������ظ��������������
		JPanel pan5 = new JPanel();//��������ύ���
	
		//�ı���
		JTextField textId = new JTextField();//�˺�
		JTextField textName = new JTextField();//�û���
		JPasswordField passwordfield = new JPasswordField();
		JPasswordField repasswordfield = new JPasswordField();
		
		//��ʾ��
		JLabel label1 = new JLabel("ע �� �� ��");
		JLabel label2 = new JLabel("��    ��  ");
		JLabel label3 = new JLabel("�� �� ��  ");
		JLabel label4 = new JLabel("��    ��  ");

		JLabel label5 = new JLabel("�ظ�����  ");
		
		//��ť
		JButton button1 = new JButton("�ύ");
		
		Font font = new Font("����",Font.BOLD,50);//���������С		
		Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
		
		//�����ı���Ĵ�С	
		textId.setPreferredSize(new Dimension(200,30));
		textName.setPreferredSize(new Dimension(200,30));
		passwordfield.setPreferredSize(new Dimension(200,30));
		repasswordfield.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
		
		
		//���ý������������С���������⡢��ʾ��������ı���
		label1.setFont(font);//���ñ�������
		label2.setFont(f);
		textId.setFont(f);
		label3.setFont(f);
		textName.setFont(f);
		label4.setFont(f);
		passwordfield.setFont(f);
		label5.setFont(f);
		repasswordfield.setFont(f);
		button1.setFont(f);
		
		//�������������
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(textId);
		pan3.add(label3);
		pan3.add(textName);
		pan4.add(label4);
		pan4.add(passwordfield);
		repan.add(label5);
		repan.add(repasswordfield);//�ظ�����
		pan5.add(button1);

		
		//�������λ��
		pan1.setBounds(235,50, 430, 60);
		pan2.setBounds(235,170, 430, 50);
		pan3.setBounds(235,240, 430, 50);
		pan4.setBounds(235,310, 430, 50);
		repan.setBounds(235,380, 430, 50);
		pan5.setBounds(450,440,100, 50);
		
		//������
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(repan);
		frame.getContentPane().add(pan5);
		
		//�ύ�¼�����
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userMessage = new User();
				String id = textId.getText().toString();
				String name = textName.getText().toString();

				String password = new String(passwordfield.getPassword());
				String repassword = new String(repasswordfield.getPassword());
				
				//�ж��������������Ƿ�һ��
				if(!password.equals(repassword)) {
					JOptionPane.showMessageDialog(null, "������������벻һ�£�����������");
					return;
				}
				//ע��ʱ���ж������ı����ֵ����Ϊ��
				if(StringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��");
					return;
				} 
				else if(StringUtil.isEmpty(name)){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
					return;
				}
				else if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
					return;
				}
				
				//���û��������Ϣ��װ��userMessage������
				userMessage.setUserId(id);
				userMessage.setUsername(name);
				userMessage.setPassword(password);
				
				DbUtil dbutil = new DbUtil();
				UserDao userdao = new UserDao();
				
				//�˺Ų�����ͬ
		
				try {
					Connection con = dbutil.getCon();
					
					int current = userdao.add(con,userMessage);
					
					//��ʾ��������ֵ��0��ע��ɹ�
					if(current != 0) {
						JOptionPane.showMessageDialog(null, "ע��ɹ�");
						frame.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "�˺��Ѵ��ڣ�");
						return;
					}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
			
		});
		
		//��������
		frame.setBounds(500, 150, 950, 650);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
