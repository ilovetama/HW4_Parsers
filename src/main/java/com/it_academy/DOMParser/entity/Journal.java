package com.it_academy.DOMParser.entity;

public class Journal {

  private String title;

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Journal   {" +
        "title='" + title + '\'' + '}';
  }
}