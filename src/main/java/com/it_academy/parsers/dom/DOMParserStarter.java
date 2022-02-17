package com.it_academy.parsers.dom;

import com.it_academy.entity.Article;
import com.it_academy.entity.Contact;
import com.it_academy.entity.Hotkey;
import com.it_academy.entity.Journal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParserStarter {

  private static final String XML_PATH = "journal.xml";
  private static List<Contact> contacts = new ArrayList<>();
  private static List<Journal> journals = new ArrayList<>();
  private static List<Article> articles = new ArrayList<>();
  private static List<Hotkey> hotkeys = new ArrayList<>();

  private static Contact setContactsWithXMLChildNodeValues(Contact contact, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    switch (node.getNodeName()) {

      case "address" -> contact.setAddress(content);
      case "tel" -> contact.setTel(content);
      case "email" -> contact.setEmail(content);
      case "journalurl" -> contact.setUrl(content);
    }
    return contact;
  }

  private static void setContactsWithXMLNodeValues(NodeList nodeList) {

    DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
      Contact contact = new Contact();
      if (node instanceof Element) {
        setContactsWithXMLChildNodeValues(contact, node);
        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
          if (childNode instanceof Element) {
            setContactsWithXMLChildNodeValues(contact, childNode);
          }
        });
        contacts.add(contact);
      }
    });
  }

  private static Article setArticleWithXMLChildNodeValues(Article article, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    switch (node.getNodeName()) {

      case "title" -> article.setTitle(content);
      case "author" -> article.setAuthor(content);
      case "url" -> article.setUrl(content);
      case "hotkey" -> article.setHotkey(article.getHotkey());

    }
    System.out.println(article.getHotkey());
    return article;
  }

  private static void setArticleWithXMLNodeValues(NodeList nodeList) {

    DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
      if (node instanceof Element) {
        Article article = new Article();
        setArticleWithXMLChildNodeValues(article, node);
        article.setId(node.getAttributes().
            getNamedItem("ID").getNodeValue());

        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
          if (childNode instanceof Element) {
            setArticleWithXMLChildNodeValues(article, childNode);
          }
        });
        articles.add(article);
      }
    });
  }

  private static Journal setTitleWithXMLChildNodeValues(Journal journal, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    switch (node.getNodeName()) {

      case "journaltitle" -> journal.setTitle(content);
    }
    return journal;
  }

  private static void setTitleWithXMLNodeValues(NodeList nodeList) {

    DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
      Journal journal = new Journal();
      if (node instanceof Element) {
        setTitleWithXMLChildNodeValues(journal, node);
        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
          if (childNode instanceof Element) {
            setTitleWithXMLChildNodeValues(journal, childNode);
          }
        });
        journals.add(journal);
      }
    });
  }

  private static Hotkey setHotkeyWithXMLChildNodeValues(Hotkey hotkey, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    switch (node.getNodeName()) {

      case "hotkey" -> hotkey.setHotkey(content);
    }
    return hotkey;
  }

  private static void setHotkeyWithXMLNodeValues(NodeList nodeList) {

    DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
      Hotkey hotkey = new Hotkey();
      if (node instanceof Element) {
        setHotkeyWithXMLChildNodeValues(hotkey, node);
        NodeList childNodes = node.getChildNodes();
        DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
          if (childNode instanceof Element) {
            setHotkeyWithXMLChildNodeValues(hotkey, childNode);
          }
        });
        hotkeys.add(hotkey);
      }
    });
  }

  public static void main(String[] args) {
    Document document = DOMParserUtils.parseXMLDocument(XML_PATH);
    NodeList nodeTitle = document.getElementsByTagName("journaltitle");
    NodeList nodeContacts = document.getElementsByTagName("contacts");
    NodeList nodeArticles = document.getElementsByTagName("article");
    NodeList nodeHotkeys = document.getElementsByTagName("hotkey");
    setTitleWithXMLNodeValues(nodeTitle);
    setContactsWithXMLNodeValues(nodeContacts);
    setArticleWithXMLNodeValues(nodeArticles);
    setHotkeyWithXMLNodeValues(nodeHotkeys);
    System.out.println(journals.toString());
    System.out.println(contacts.toString());
    System.out.println(articles.toString());
    System.out.println(hotkeys.toString());


  }
}
