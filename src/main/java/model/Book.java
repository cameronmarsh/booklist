package model;

import org.json.simple.JSONObject;

import java.util.Date;

public class Book {
    private String title;
    private String author;
    private Date published;
    private boolean read;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublished(Date published) {
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

    public Date getPublished() {
        return published;
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
