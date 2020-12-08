 package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.model.Book;
import com.book.model.User;
import com.book.util.StringUtil;

public class BookDao {
	
	/**
	 * ͼ�����
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Book book) throws Exception {
		
		//����ͼ��ǰ ���bookId�Ƿ��Ѿ����ڣ������ڣ�����0
		String sqlid = "select * from book where bookId = ?";		
		PreparedStatement pstmtid = (PreparedStatement) con.prepareStatement(sqlid);	
		pstmtid.setString(1, book.getBookId());
		ResultSet rs = pstmtid.executeQuery();
		if(rs.next()) {
			return 0;
		}
		
		String sql = "insert into book values(?,?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
		pstmt.setString(1, book.getBookId());		
		pstmt.setString(2, book.getBookName());		
		pstmt.setString(3, book.getPress());		
		pstmt.setString(4, book.getAuthor());		
		//pstmt.setString(5, book.getTypeId());		
		pstmt.setInt(5, book.getLend());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * ͼ���ѯ
	 * @param con
	 * @param bookMessage
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Book bookMessage) throws Exception {
		
		//��book���bookType������
		//StringBuffer sb = new StringBuffer("select * from book,booktype where book.typeId=booktype.typeId");
		StringBuffer sb = new StringBuffer("select * from book where bookId = bookId ");
		
		//��sb����ӣ�������ģ����ѯ
		if(StringUtil.isNotEmpty(bookMessage.getBookId())) { 
			sb.append(" and bookId like '%" + bookMessage.getBookId() + "%'");
		}
		
		//������ģ����ѯ
		if(StringUtil.isNotEmpty(bookMessage.getBookName())) { 
			sb.append(" and book.bookName like '%" + bookMessage.getBookName() + "%'");
		}
		
		//������ģ����ѯ
		if(StringUtil.isNotEmpty(bookMessage.getAuthor())) {
			sb.append(" and book.author like '%" + bookMessage.getAuthor() + "%'");
		}
		
//		//��ͼ������ģ����ѯ
//		if(StringUtil.isNotEmpty(bookMessage.getTypeId())) {
//			sb.append(" and book.typeId like '%" + bookMessage.getTypeId() + "%'");
//		}
		
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//��ʷ��¼��ѯ
	public ResultSet listHistory(Connection con, User userMessage) throws Exception {
		
		String sql = "select recordId,userName, bookName, (case back when 0 then '��' when 1 then '��' end) as back from lendrecord where userName = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, userMessage.getUsername());
		return pstmt.executeQuery();
	}
	
	/**
	 * ͼ��ɾ��
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id) throws Exception {
		
		String sql = "delete from book where bookId = ?";		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);	
		pstmt.setString(1, id);
	
		return pstmt.executeUpdate();
	}
	
	/**
	 * ͼ���޸�
	 * @param con
	 * @param bookMessage1
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Book bookMessage1) throws Exception {
		
		String sql = "update book set bookName=?, press=?, author=? where bookId=?";
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
		pstmt.setString(1, bookMessage1.getBookName());
		pstmt.setString(2, bookMessage1.getPress());
		pstmt.setString(3, bookMessage1.getAuthor());
		pstmt.setString(4, bookMessage1.getBookId());
		return pstmt.executeUpdate();
	}
	
	//����id��ͼ���Ƿ����,�����ڣ�����0�����򷵻�1
	public ResultSet bookCheck(Connection con, String id) throws Exception{
		
		//����ͼ��ǰ ���bookId�Ƿ��Ѿ����ڣ������ڣ�����0
		String sqlid = "select * from book where bookId = ?";		
		PreparedStatement pstmtid = (PreparedStatement) con.prepareStatement(sqlid);	
		pstmtid.setString(1, id);
		ResultSet rs = pstmtid.executeQuery();
		return rs;
	}
	//
	/**
	  * ͼ�����
	  * �޸�book���е�lend�ֶκ� ���ļ�¼��lendrecord���е�back�ֶ�  lend=1,back=0
	 * @param con
	 * @param textid
	 * @param bookName
	 * @param userMessage
	 * @return
	 * @throws Exception
	 */
	public int lend (Connection con, String textid, String bookName, User userMessage) throws Exception {
		//���޸�book����ͼ���״̬
		String sql = "update book set lend=? where bookId=?";
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
		pstmt.setString(1, "1");
		pstmt.setString(2, textid);
		pstmt.executeUpdate();
		
		//������ļ�¼��(lendrecord) ��Ϣ
		String insql = "insert into lendrecord (userId,userName,bookId,bookName,back)values(?,?,?,?,?)";
		PreparedStatement lendpstmt = (PreparedStatement) con.prepareStatement(insql);
		
		lendpstmt.setString(1, userMessage.getUserId());
		lendpstmt.setString(2, userMessage.getUsername());
		lendpstmt.setString(3, textid);	
		lendpstmt.setString(4, bookName);
		lendpstmt.setString(5, "0");
		lendpstmt.executeUpdate();
		return 0;//�ɹ�
	}
	
	//ͼ��黹
	/**
	  * �޸�book���е�lend�ֶκ� ���ļ�¼��lendrecord���е�back�ֶ� lend=0,back=1
	 * @param con
	 * @param textid
	 * @param userMessage
	 * @return
	 * @throws Exception
	 */
	public int back (Connection con, String textid, User userMessage) throws Exception {
		
		
		//���޸�book����ͼ���״̬
		String sql = "update book set lend=? where bookId=?";
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
		pstmt.setString(1, "0");
		pstmt.setString(2, textid);
		pstmt.executeUpdate();
		
		//�޸Ľ��ļ�¼��(lendrecord) ��Ϣ ��back=1,��ʾ�Ѿ��黹
		String insql = "update lendrecord set back=? where bookId=? and userName=? ";
		PreparedStatement lendpstmt = (PreparedStatement) con.prepareStatement(insql);
		
		lendpstmt.setString(1, "1");
		lendpstmt.setString(2, textid);
		lendpstmt.setString(3, userMessage.getUsername());
		lendpstmt.executeUpdate();
		
		return 0;//�ɹ�
	}
}
