 package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.model.Book;
import com.book.model.User;
import com.book.util.StringUtil;

public class BookDao {
	
	/**
	 * 图书添加
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Book book) throws Exception {
		
		//插入图书前 检查bookId是否已经存在，若存在，返回0
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
	 * 图书查询
	 * @param con
	 * @param bookMessage
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Book bookMessage) throws Exception {
		
		//将book表和bookType表连接
		//StringBuffer sb = new StringBuffer("select * from book,booktype where book.typeId=booktype.typeId");
		StringBuffer sb = new StringBuffer("select * from book where bookId = bookId ");
		
		//向sb中添加，并进行模糊查询
		if(StringUtil.isNotEmpty(bookMessage.getBookId())) { 
			sb.append(" and bookId like '%" + bookMessage.getBookId() + "%'");
		}
		
		//按书名模糊查询
		if(StringUtil.isNotEmpty(bookMessage.getBookName())) { 
			sb.append(" and book.bookName like '%" + bookMessage.getBookName() + "%'");
		}
		
		//按作者模糊查询
		if(StringUtil.isNotEmpty(bookMessage.getAuthor())) {
			sb.append(" and book.author like '%" + bookMessage.getAuthor() + "%'");
		}
		
//		//按图书类型模糊查询
//		if(StringUtil.isNotEmpty(bookMessage.getTypeId())) {
//			sb.append(" and book.typeId like '%" + bookMessage.getTypeId() + "%'");
//		}
		
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//历史记录查询
	public ResultSet listHistory(Connection con, User userMessage) throws Exception {
		
		String sql = "select recordId,userName, bookName, (case back when 0 then '否' when 1 then '是' end) as back from lendrecord where userName = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, userMessage.getUsername());
		return pstmt.executeQuery();
	}
	
	/**
	 * 图书删除
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
	 * 图书修改
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
	
	//检查该id的图书是否存在,若存在，返回0，否则返回1
	public ResultSet bookCheck(Connection con, String id) throws Exception{
		
		//插入图书前 检查bookId是否已经存在，若存在，返回0
		String sqlid = "select * from book where bookId = ?";		
		PreparedStatement pstmtid = (PreparedStatement) con.prepareStatement(sqlid);	
		pstmtid.setString(1, id);
		ResultSet rs = pstmtid.executeQuery();
		return rs;
	}
	//
	/**
	  * 图书借阅
	  * 修改book表中的lend字段和 借阅记录表（lendrecord）中的back字段  lend=1,back=0
	 * @param con
	 * @param textid
	 * @param bookName
	 * @param userMessage
	 * @return
	 * @throws Exception
	 */
	public int lend (Connection con, String textid, String bookName, User userMessage) throws Exception {
		//先修改book表中图书的状态
		String sql = "update book set lend=? where bookId=?";
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
		pstmt.setString(1, "1");
		pstmt.setString(2, textid);
		pstmt.executeUpdate();
		
		//插入借阅记录表(lendrecord) 信息
		String insql = "insert into lendrecord (userId,userName,bookId,bookName,back)values(?,?,?,?,?)";
		PreparedStatement lendpstmt = (PreparedStatement) con.prepareStatement(insql);
		
		lendpstmt.setString(1, userMessage.getUserId());
		lendpstmt.setString(2, userMessage.getUsername());
		lendpstmt.setString(3, textid);	
		lendpstmt.setString(4, bookName);
		lendpstmt.setString(5, "0");
		lendpstmt.executeUpdate();
		return 0;//成功
	}
	
	//图书归还
	/**
	  * 修改book表中的lend字段和 借阅记录表（lendrecord）中的back字段 lend=0,back=1
	 * @param con
	 * @param textid
	 * @param userMessage
	 * @return
	 * @throws Exception
	 */
	public int back (Connection con, String textid, User userMessage) throws Exception {
		
		
		//先修改book表中图书的状态
		String sql = "update book set lend=? where bookId=?";
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
		pstmt.setString(1, "0");
		pstmt.setString(2, textid);
		pstmt.executeUpdate();
		
		//修改借阅记录表(lendrecord) 信息 ，back=1,表示已经归还
		String insql = "update lendrecord set back=? where bookId=? and userName=? ";
		PreparedStatement lendpstmt = (PreparedStatement) con.prepareStatement(insql);
		
		lendpstmt.setString(1, "1");
		lendpstmt.setString(2, textid);
		lendpstmt.setString(3, userMessage.getUsername());
		lendpstmt.executeUpdate();
		
		return 0;//成功
	}
}
