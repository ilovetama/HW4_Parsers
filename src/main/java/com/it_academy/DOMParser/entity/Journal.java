package com.it_academy.DOMParser.entity;

import java.util.List;

public class Journal {

  public String title;
  public Contact contact;
  List<Article> articles;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  @Override
  public String toString() {
    return "Journal   {" +
        "title='" + title + '\'' + '}';
  }
}