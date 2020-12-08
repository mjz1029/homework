package com.bookface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.util.StringUtil;
import com.bookmanager.view.BookAddFram;
import com.bookmanager.view.BookDeleteFram;
import com.bookmanager.view.BookLookFram;
import com.bookmanager.view.BookUpDataFram;
import com.lendbackbook.view.BackBook;
import com.lendbackbook.view.HistoryBook;
import com.lendbackbook.view.LendBook;
import com.usermanager.view.UserDeleteFram;
import com.usermanager.view.UserLookFram;

public class BookMenuFram extends JFrame {
	public BookMenuFram() {
	}
	
	static String loginId = null;
	static String loginName = null;//���ǹ���Ա��¼�������Ա�����֣����򣬴���ͨ�û�������
	
	//����û��������0������ͨ�û���1�������Ա��Ĭ����0
	static int flag = 0;
	
	//ϵͳ������
	public static void BookMenuFram() {
		
		JFrame frame = new JFrame();//���˵�����	
		frame.getContentPane().setLayout(null);

		//��6����壬��������λ��
		JPanel pan1 = new JPanel();//ϵͳ����
		JPanel pan2 = new JPanel();//ͼ�����
		JPanel pan3 = new JPanel();//ͼ�����Ĳ�����ť
		JPanel pan4 = new JPanel();//���ﳵ����
		JPanel pan5 = new JPanel();//���ﳵ�Ĳ�����ť
		JPanel pan6 = new JPanel();//�û�����
		JPanel pan7 = new JPanel();//�û�����Ĳ�����ť
		JPanel pan8 = new JPanel();//�ŵ�¼�˳���ť
		JPanel pan9 = new JPanel();
		
		
		JLabel text = new JLabel();//��ʾ�򣬲���ɾ��
		text.setText("δ��¼��");//������ʾδ��¼
		
		//��ʾ��
		JLabel label1 = new JLabel("����ͼ���");		
		JLabel label2 = new JLabel("ͼ�����");
		JLabel label3 = new JLabel("���Ĺ���");
		JLabel label4 = new JLabel("�û�����");
	

		//��ť
		JButton button1 = new JButton("��¼");
		JButton button2 = new JButton("ͼ���ѯ");
		JButton button3 = new JButton("ͼ�����");
		JButton button4 = new JButton("ͼ���޸�");
		JButton button5 = new JButton("ͼ��ɾ��");
		JButton button6 = new JButton("ͼ�����");
		JButton button7 = new JButton("ͼ��黹");
		JButton button8 = new JButton("������ʷ��ѯ");
		JButton button9 = new JButton("��ѯ�û�");
		JButton button10 = new JButton("ɾ���û�");
		JButton button11 = new JButton("�˳�");
		
		//������ɫ��ʽ
		Color blacka = new Color(225,225,225);
		Color blackb = new Color(225,225,225);
		Color blackc = new Color(225,225,225);
		Color blackd = new Color(225,225,225);
		
		//���������С����
		Font font = new Font("����",Font.BOLD,60);//���������С		
		Font f = new Font("����",Font.BOLD,30);//��ʾ�������С
		Font f1 = new Font("����",Font.BOLD,20);//text
		
		//���ð�ť�Ĵ�С
		button2.setPreferredSize(new Dimension(300,65));
		button3.setPreferredSize(new Dimension(300,65));
		button4.setPreferredSize(new Dimension(300,65));
		button5.setPreferredSize(new Dimension(300,65));
		button6.setPreferredSize(new Dimension(300,65));
		button7.setPreferredSize(new Dimension(300,65));
		button8.setPreferredSize(new Dimension(300,65));
		button9.setPreferredSize(new Dimension(300,65));
		button10.setPreferredSize(new Dimension(300,65));
		text.setPreferredSize(new Dimension(170,35));//
		
		//���������С
		button2.setFont(f);
		button3.setFont(f);
		button4.setFont(f);
		button5.setFont(f);
		button6.setFont(f);
		button7.setFont(f);
		button8.setFont(f);
		button9.setFont(f);
		button10.setFont(f);
		text.setFont(f1);
		label1.setFont(font);//���ñ�������
		label2.setFont(f);
		label3.setFont(f);
		label4.setFont(f);
		
		//�������������
		pan1.add(label1);
		pan1.setBackground(blacka);
		pan1.setBounds(0, 0, 450, 120);//�����
		
		//pan8��λ�����Ͻǣ��ŵ�¼���˳�����ʾ��
		pan8.add(text);
		pan8.add(button1);
		pan8.add(button11);	
		pan8.setBackground(blacka);
		pan8.setBounds(450, 0, 190, 120);
        
		pan2.add(label2);
		pan2.setBackground(blackb);
		pan2.setBounds(0, 120, 200, 284);//ͼ�����
		
		pan3.add(button2);
		pan3.add(button3);
		pan3.add(button4);
		pan3.add(button5);
		pan3.setBounds(150, 120, 500, 284);
		
		pan4.add(label3);
		pan4.setBackground(blackc);
		pan4.setBounds(0, 404, 200, 213);//�軹��
	    
		pan5.add(button6);
		pan5.add(button7);
		pan5.add(button8);
		pan5.setBounds(150, 404, 500, 213);
		
		pan6.add(label4);
		pan6.setBackground(blackd);
		pan6.setBounds(0, 617, 200, 142);//�û�����
	    
		pan7.add(button9);
		pan7.add(button10);
		pan7.setBounds(150, 617, 500, 200);
		
		
		String noLogin = "δ��¼��";//JVM�Ȳ鿴����������û�У����У���ַָ��������û�У������¶��󱣴�
		
		//��ȡ��¼����û���
		if(StringUtil.isNotEmpty(LogOnFram.userName())) {
		    loginName = LogOnFram.userName();
			text.setText("��ӭ����" + loginName);	
		}
		if(StringUtil.isNotEmpty(LogOnFram.adminName())) {
		    loginName = LogOnFram.adminName();
			text.setText("��ӭ����" + loginName);
			flag = 1;
		}
		//��ȡ��¼���id
		if(StringUtil.isNotEmpty(LogOnFram.id())) {
			loginId = LogOnFram.id();
		}
		
		//��¼����
		button1.addActionListener(new ActionListener() {//��¼����
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {	
					LogOnFram.LogOnFram();
					frame.dispose();//�رյ�ǰ��¼����
				
				} else {
					JOptionPane.showMessageDialog(null, "�������˳���");
				}				
			}			
		});
		
		//�����������,button2--button10
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {	//equals()�����������д�����ǱȽϵ��ַ������ݡ���'=='�Ƚϵ��ǵ�ַ
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
					BookLookFram.BookLookFram();//ͼ���ѯ
				}
			}	
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
					if(flag == 1) {//���flag��1����������ͼ��
						BookAddFram.bookAddFram();//ͼ�����
					} else {
						JOptionPane.showMessageDialog(null, "����Ա�ſ���ִ�д˲�����");
					}				
				}	
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {	
					if(flag == 1) {//���flag��1����������ͼ��
						BookUpDataFram.bookUpDataFram();//�޸�ͼ��
					} else {
						JOptionPane.showMessageDialog(null, "����Ա�ſ���ִ�д˲�����");
					}
				}
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
					if(flag == 1) {//���flag��1����������ͼ��
						BookDeleteFram.BookDeleteFram();//ɾ��ͼ��
					} else {
						JOptionPane.showMessageDialog(null, "����Ա�ſ���ִ�д˲�����");
					}
				}
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {  //�������
					LendBook.LendBook();
				}
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {  //������
					BackBook.BackBook();
				}
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {	//��ʷ��ѯ
					HistoryBook.HistoryBook();
				}
			}
		});
		button9.addActionListener(new ActionListener() {//��ѯ�û�
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
					if(flag == 1) {//���flag��1����������ͼ��
						UserLookFram.UserLookFram();//�û���ѯ
					} else {
						JOptionPane.showMessageDialog(null, "����Ա�ſ���ִ�д˲�����");
					}
					
				}
			}
		});
		button10.addActionListener(new ActionListener() {//ɾ���û�
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
					if(flag == 1) {//���flag��1����������ͼ��
						UserDeleteFram.UserDeleteFram();//�û�ɾ��
					} else {
						JOptionPane.showMessageDialog(null, "����Ա�ſ���ִ�д˲�����");
					}
				}
			}
		});
		
		button11.addActionListener(new ActionListener() {//�˳���¼
			public void actionPerformed(ActionEvent e) {
				String getText = text.getText().toString();//1
				if(getText.equals(noLogin)) {
					JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
					return;
				} else {
//					frame.dispose();
//					LogOnFram.LogOnFram();
					System.exit(0);
				}
			}
		});
		
		//�����������JPanel���
		frame.getContentPane().add(pan1);
		frame.getContentPane().add(pan2);
		frame.getContentPane().add(pan3);
		frame.getContentPane().add(pan4);
		frame.getContentPane().add(pan5);
		frame.getContentPane().add(pan6);
		frame.getContentPane().add(pan7);
		frame.getContentPane().add(pan8);
		frame.getContentPane().add(pan9);
		
		//��������
		frame.setBounds(310, 100, 650, 800);
		frame.setResizable(false);//���ô��ڲ�������
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static String loginName() {
		return loginName;
	}
	public static String loginId() {
		return loginId;
	}
} 