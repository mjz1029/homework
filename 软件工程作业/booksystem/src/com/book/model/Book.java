package com.book.model;

public class Book {
	
	private String bookId;//图书id
	private String bookName;//图书名
	private String press;//图书出版社
	private String author;//图书作者
	//private String typeId;//图书类别id
	private int lend;//图书是否被借, 是为1, 默认为0

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
