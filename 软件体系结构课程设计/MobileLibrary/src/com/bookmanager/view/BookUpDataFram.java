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
			
			JFrame frame = new JFrame("ͼ���޸Ľ���");
			frame.setLayout(null);
			//��6����壬��������λ��
			JPanel pan1 = new JPanel();//�������ϵͳ�������
			JPanel pan2 = new JPanel();//��������û���ݺ����������
			JPanel pan3 = new JPanel();//��������˺ź��ı������
			JPanel pan4 = new JPanel();//��������������������
			JPanel pan5 = new JPanel();//��������ύ���
			JPanel pan6 = new JPanel();//��������ύ���
			//�ı���
			JTextField textId = new JTextField();//�˺�
			JTextField textName = new JTextField();//�û���
			JTextField textAuthor = new JTextField();//
			JTextField textPress = new JTextField();//
			
			//��ʾ��
			JLabel label1 = new JLabel("�� �� �� ��");
			JLabel label2 = new JLabel("ͼ����  ");
			JLabel label3 = new JLabel("ͼ������  ");
			JLabel label4 = new JLabel("��    ��  ");
			JLabel label5 = new JLabel("�� �� ��  ");

			//��ť
			JButton button1 = new JButton("�ύ");
			
			Font font = new Font("����",Font.BOLD,50);//���������С		
			Font f = new Font("����",Font.BOLD,25);//��ʾ�������С
			
			//�����ı���Ĵ�С	
			textId.setPreferredSize(new Dimension(200,30));
			textName.setPreferredSize(new Dimension(200,30));
			textAuthor.setPreferredSize(new Dimension(200,30));
			textPress.setPreferredSize(new Dimension(200,30));
			
			button1.setPreferredSize(new Dimension(90,40));
			
			
			//���ý������������С���������⡢��ʾ��������ı���
			label1.setFont(font);//���ñ�������
			label2.setFont(f);
			textId.setFont(f);
			label3.setFont(f);
			textName.setFont(f);
			label4.setFont(f);
			textAuthor.setFont(f);
			label5.setFont(f);
			textPress.setFont(f);		
			button1.setFont(f);
			
			//�������������
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
			
			//�������λ��
			pan1.setBounds(235,50, 430, 60);
			pan2.setBounds(235,170, 430, 50);
			pan3.setBounds(235,240, 430, 50);
			pan4.setBounds(235,310, 430, 50);			
			pan5.setBounds(235,360,430, 50);		
			pan6.setBounds(450,450,100, 50);
			//������
			frame.add(pan1);
			frame.add(pan2);
			frame.add(pan3);
			frame.add(pan4);
			frame.add(pan5);
			frame.add(pan6);
			
			//�ύ�¼�����
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Book bookMessage = new Book();
					
					String id = textId.getText();
					String name = textName.getText();
					String author = textAuthor.getText();
					String press = textPress.getText();

					//ע��ʱ���ж������ı����ֵ����Ϊ��
					if(StringUtil.isEmpty(id)) {
						JOptionPane.showMessageDialog(null, "ͼ���Ų���Ϊ��");
						return;
					} 
					else if(StringUtil.isEmpty(name)){
						JOptionPane.showMessageDialog(null, "ͼ�����ֲ���Ϊ�գ�");
						return;
					}
					else if(StringUtil.isEmpty(author)){
						JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�");
						return;
					}
					else if(StringUtil.isEmpty(press)){
						JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ�");
						return;
					}
					//���û��������Ϣ��װ��userMessage������
					bookMessage.setBookId(id);
					bookMessage.setBookName(name);
					bookMessage.setAuthor(author);
					bookMessage.setPress(press);
					
					DbUtil dbutil = new DbUtil();
					BookDao bookdao = new BookDao();
					
					try {
						Connection con = dbutil.getCon();
						
						int current = bookdao.update(con,bookMessage);
						
						//��ʾ��������ֵ��1����ӳɹ�
						if(current == 1) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							frame.dispose();
							return;
						} else {
							JOptionPane.showMessageDialog(null, "�޸�ʧ��,����ͼ��Id�Ƿ���ȷ��");
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
		}	
}
