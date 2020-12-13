/*      Created by IntelliJ IDEA.
        User: Harry Mao
        Date: 2020/11/17
        Time: 20:10
        To change this template use File | Settings | File Templates.
*/
package com.book;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String press;
    private Double price;

    /**
     * @return the bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName the bookName to set
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the press
     */
    public String getPress() {
        return press;
    }

    /**
     * @param press the press to set
     */
    public void setPress(String press) {
        this.press = press;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }


}