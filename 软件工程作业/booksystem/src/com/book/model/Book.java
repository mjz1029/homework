package com.book.model;

public class Book {
	
	private String bookId;//ͼ��id
	private String bookName;//ͼ����
	private String press;//ͼ�������
	private String author;//ͼ������
	//private String typeId;//ͼ�����id
	private int lend;//ͼ���Ƿ񱻽�, ��Ϊ1, Ĭ��Ϊ0

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

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public int getLend() {
		return lend;
	}

	public void setLend(int lend) {
		this.lend = lend;
	}

}
