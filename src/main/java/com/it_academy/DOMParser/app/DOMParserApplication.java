package com.it_academy.DOMParser.app;

import com.it_academy.DOMParser.entity.Article;
import com.it_academy.DOMParser.entity.Contact;
import com.it_academy.DOMParser.entity.Journal;
import com.it_academy.DOMParser.utils.DOMParserUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DOMParserApplication {

  private static final String XML_PATH = "journal.xml";
  private final List<Contact> contacts = new ArrayList<>();
  private final List<Journal> journals = new ArrayList<>();
  private final List<Article> articles = new ArrayList<>();

  private void setContactsWithXMLChildNodeValues(Contact contact, Node node) {
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
  }

  private void setContactsWithXMLNodeValues(NodeList nodeList) {
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

  private void setArticleWithXMLChildNodeValues(Article article, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    switch (node.getNodeName()) {
      case "title" -> article.setTitle(content);
      case "author" -> article.setAuthor(content);
      case "url" -> article.setUrl(content);
      case "hotkeys" -> article.setHotkeys(setHotkeysWithChildNodes(node));
    }
  }

  private void setArticleWithXMLNodeValues(NodeList nodeList) {
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

  private Journal setTitleWithXMLChildNodeValues(Journal journal, Node node) {
    String content = node
        .getLastChild()
        .getTextContent()
        .trim();
    if ("journaltitle".equals(node.getNodeName())) {
      journal.setTitle(content);
    }
    return journal;
  }

  private void setTitleWithXMLNodeValues(NodeList nodeList) {
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

  private List<String> setHotkeysWithChildNodes(Node childNode) {
    List<String> hotkeys = new ArrayList<>();
    NodeList childNodesHotkeys = childNode.getChildNodes();
    Stream<Node> stream = IntStream.range(0, childNodesHotkeys.getLength())
        .mapToObj(childNodesHotkeys::item);
    stream.forEach(node -> {
      if (node instanceof Element) {
        hotkeys.add(node.getTextContent());
      }
    });
    return hotkeys;
  }

  public void DOMParserXML() {
    Document document = DOMParserUtils.parseXMLDocument(XML_PATH);
    NodeList nodeTitle = document.getElementsByTagName("journaltitle");
    NodeList nodeContacts = document.getElementsByTagName("contacts");
    NodeList nodeArticles = document.getElementsByTagName("article");
    setTitleWithXMLNodeValues(nodeTitle);
    setContactsWithXMLNodeValues(nodeContacts);
    setArticleWithXMLNodeValues(nodeArticles);
    System.out.println(journals);
    System.out.println(contacts);
    System.out.println(articles);
  }
}
