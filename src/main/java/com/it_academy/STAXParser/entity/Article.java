package com.it_academy.STAXParser.entity;

import java.util.List;

public class Article {
  private String id;
  private String title;
  private String author;
  private String url;
  private List<String> hotkeys;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<String> getHotkeys() {
    return hotkeys;
  }

  public void setHotkeys(List<String> hotkeys) {
    this.hotkeys = hotkeys;
  }

  @Override
  public String toString() {
    return "Article   {" +
        "id='" + id + '\'' + "\n" +
        "           title='" + title + '\'' + "\n" +
        "           author='" + author + '\'' + "\n" +
        "           url='" + url + '\'' + "\n" +
        "           hotkeys=" + hotkeys + '}' + "\n";
  }
}
