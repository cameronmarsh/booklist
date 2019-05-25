package model;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;

public class Book {
    private String title;
    private String author;
    private String published;
    private boolean read;


    public Book(String title, String author, String datePublished, boolean read) {
        this.title = title;
        this.author = author;
        this.published = datePublished;
        this.read = read;
    }

    public Book() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublished() {
        return this.published;
    }

    public boolean isRead() {
        return read;
    }

    public JSONObject asJson(){
        JSONObject bookJson = new JSONObject();
        bookJson.put("title", this.title);
        bookJson.put("author", this.author);
        bookJson.put("published", this.published.toString());
        bookJson.put("read", this.read);

        return bookJson;
    }
}
