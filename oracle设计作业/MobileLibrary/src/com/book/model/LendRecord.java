package com.book.model;

public class LendRecord {
	
	private int recordId;//���ﳵ��¼id
	
	private String userId;//�û�id
	
	private String userName;
	
	private String bookId;//ͼ��id
	
	private String bookName;
	
	private int back;//�Ƿ�ӹ��ﳵɾ��, ��Ϊ1, Ĭ��Ϊ0

	
	
	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}
}
