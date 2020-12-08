package com.bookface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.book.dao.AdminDao;
import com.book.dao.UserDao;
import com.book.model.Admin;
import com.book.model.User;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class LogOnFram {
	
	//�ı���
	static JTextField textId = new JTextField();//�˺�
	static JPasswordField passwordfield = new JPasswordField();
	
	 //�����򣬴���û����
	static JComboBox<String> comBox = new JComboBox<String>();

	static String userName = null; //����name�����ڷ��ص���ҳ��ʹ��
	static String adminName = null;
	static String id = null; //����id�����ڷ��ص���ҳ��ʹ��
	
	static JFrame frame = new JFrame("��¼����");
	
	//дһ����¼����ķ���
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void LogOnFram() {
		
		frame.getContentPane().setLayout(null);
		//��6����壬��������λ��
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		JPanel pan5 = new JPanel();
		JPanel pan6 = new JPanel();
		
		comBox.addItem("��ͨ�û�");
		comBox.addItem("����Ա");
		
		//��ʾ��
		JLabel label1 = new JLabel("�������");
		JLabel label2 = new JLabel("��    ��  ");
		JLabel label3 = new JLabel("��    ��  ");
		JLabel label4 = new JLabel("��    ��  ");
		
		//��ť
		JButton button1 = new JButton("��¼");
		JButton button2 = new JButton("ע��");
		
		Font font = new Font("����",Font.BOLD,50);//���������С		
		Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
		
		//�����ı���Ĵ�С
		comBox.setPreferredSize(new Dimension(200,30));
		textId.setPreferredSize(new Dimension(200,30));
		passwordfield.setPreferredSize(new Dimension(200,30));
		button1.setPreferredSize(new Dimension(90,40));
		button2.setPreferredSize(new Dimension(90,40));
		
		//���ý������������С���������⡢��ʾ��������ı���
		label1.setFont(font);//���ñ�������
		label2.setFont(f);
		comBox.setFont(f);
		label3.setFont(f);
		textId.setFont(f);
		label4.setFont(f);
		passwordfield.setFont(f);
		button1.setFont(f);
		button2.setFont(f);
		
		//�������������
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(comBox);
		pan3.add(label3);
		pan3.add(textId);
		pan4.add(label4);
		pan4.add(passwordfield);
		pan5.add(button1);
		pan6.add(button2);

		//�������λ��
		pan1.setBounds(235,50, 430, 60);
		pan2.setBounds(235,170, 430, 50);
		pan3.setBounds(235,240, 430, 50);
		pan4.setBounds(235,310, 430, 50);
		
		pan5.setBounds(330,380,100, 50);
		pan6.setBounds(480,380,100, 50);
		
		//������
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(pan5);
		frame.getContentPane().add(pan6);
		
		//��¼����
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logCheck();	
			}		
		});
		
		//ע�����
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			LoginFram.LoginFram();
			}
		});
		
		//��������
		frame.setBounds(500, 150, 900, 650);
		
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//��ȡ�����ı����ֵ�����������ݿ� ���бȶ�
	public static void logCheck() {
		
		String userId = textId.getText().toString();
		String Password = new String(passwordfield.getPassword());
		
		if(StringUtil.isEmpty(userId)) {
			JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��");
			return;
		}
		if(StringUtil.isEmpty(Password)) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
			return;
		}
		
		Connection con = null;
		DbUtil dbutil = new DbUtil();
		UserDao userdao = new UserDao();

		User userMessage = new User();
		userMessage.setUserId(userId);			
		userMessage.setPassword(Password);
		
		String box = (String)comBox.getSelectedItem();//��ȡ�����������
		
		//���������ѡ������ͨ�û���
		if(box.equals("��ͨ�û�")) {
				try {
				con = dbutil.getCon();				
				User currentUser = userdao.login(con,userMessage);					
				if(currentUser != null ) {
					JOptionPane.showMessageDialog(null, "��¼�ɹ�");
					 //currentUser.getUsername();//��ȡ��¼�ߵ��û���
					//frame.dispose();//�رյ�ǰ��¼����						
					userName = currentUser.getUsername();
					id = currentUser.getUserId();
					
					frame.dispose();//�رյ�ǰ��¼����
					BookMenuFram.BookMenuFram();
					
				} else {
					JOptionPane.showMessageDialog(null, "�û������������");
				}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
		}

		AdminDao admindao = new AdminDao();
		Admin adminMessage = new Admin();
		adminMessage.setAdminId(userId);			
		adminMessage.setPassword(Password);
				
		//���������ѡ���ǹ���Ա��
		if(box.equals("����Ա")) {
				try {
				con = dbutil.getCon();				
				Admin currentUser = admindao.adminLogin(con,adminMessage);					
				if(currentUser != null ) {
					JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ�");
	
					adminName = currentUser.getAdminname();
					id = currentUser.getAdminId();
					frame.dispose();//�رյ�ǰ��¼����
					BookMenuFram.BookMenuFram();
					
				} else {
					JOptionPane.showMessageDialog(null, "�û������������");
				}
				}catch(Exception evt) {
					evt.printStackTrace();
				}
		}
	}
	// �ȶԳɹ��󣬻�ȡ���ݿ��е��û���
	public static String userName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public static String adminName() {
		return adminName;
	}
	public static String id() {
		return id;
	}
}