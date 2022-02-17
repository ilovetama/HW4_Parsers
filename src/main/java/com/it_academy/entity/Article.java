package com.it_academy.entity;

import java.util.List;

public class Article {

  public String title;
  public String author;
  public String url;
  public List<Hotkey> hotkey;
  public String id;

  public void setHotkey(List<Hotkey> hotkeys) {
    this.hotkey = hotkeys;
  }
  public List<Hotkey> getHotkey() {
    return hotkey;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Articles  {" +
        "title='" + title + '\'' + "\n" +
        "            author='" + author + '\'' + "\n" +
        "            url='" + url + '\'' + "\n" +
        "            hotkey='" + hotkey  + '\'' + '}' + "\n";
  }
}
