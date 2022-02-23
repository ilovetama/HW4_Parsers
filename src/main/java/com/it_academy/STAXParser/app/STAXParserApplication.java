package com.it_academy.STAXParser.app;

import com.it_academy.STAXParser.entity.Article;
import com.it_academy.STAXParser.entity.Contact;
import com.it_academy.STAXParser.entity.Journal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXParserApplication {

  private List<Journal> titleList = null;
  private List<Contact> contactList = null;
  private List<Article> articleList = null;
  private List<String> hotkeyList = null;
  private Journal currTitle = null;
  private Contact currContact = null;
  private Article currArticle = null;
  private String tagContent = null;
  private final Journal journal = new Journal();
  private final Contact contact = new Contact();

  private void ParseStartElement(XMLStreamReader reader) throws XMLStreamException {
    if ("journaltitle".equals(reader.getLocalName())) {
      currTitle = new Journal();
      currTitle.setTitle(reader.getAttributeValue(0));
    }
    if ("journal".equals(reader.getLocalName())) {
      titleList = new ArrayList<>();
    }
    if ("address".equals(reader.getLocalName())) {
      currContact = new Contact();
      currContact.setAddress(reader.getAttributeValue(0));
    }
    if ("contacts".equals(reader.getLocalName())) {
      contactList = new ArrayList<>();
    }
    if ("article".equals(reader.getLocalName())) {
      currArticle = new Article();
      currArticle.setId(reader.getAttributeValue(0));
    }
    if ("articles".equals(reader.getLocalName())) {
      articleList = new ArrayList<>();
    }
    if ("hotkeys".equals(reader.getLocalName())) {
      hotkeyList = new ArrayList<>();
    }
    if ("hotkey".equals(reader.getLocalName())) {
      hotkeyList.add(reader.getElementText());
      currArticle.setHotkeys(hotkeyList);
    }
  }

  private void ParseEndElement(XMLStreamReader reader) {
    switch (reader.getLocalName()) {
      case "journal" -> titleList.add(currTitle);
      case "journaltitle" -> currTitle.setTitle(tagContent);
      case "contacts" -> contactList.add(currContact);
      case "address" -> currContact.setAddress(tagContent);
      case "tel" -> currContact.setTel(tagContent);
      case "email" -> currContact.setEmail(tagContent);
      case "journalurl" -> currContact.setUrl(tagContent);
      case "articles" -> articleList.add(currArticle);
      case "ID" -> currArticle.setId(tagContent);
      case "title" -> currArticle.setTitle(tagContent);
      case "author" -> currArticle.setAuthor(tagContent);
      case "url" -> currArticle.setUrl(tagContent);
    }
  }

  private void STAXParserXML() throws XMLStreamException {
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader =
        factory.createXMLStreamReader(
            ClassLoader.getSystemResourceAsStream("journal.xml"));
    while (reader.hasNext()) {
      int event = reader.next();
      switch (event) {
        case XMLStreamConstants.START_ELEMENT -> ParseStartElement(reader);
        case XMLStreamConstants.CHARACTERS -> tagContent = reader.getText().trim();
        case XMLStreamConstants.END_ELEMENT -> ParseEndElement(reader);
      }
    }
  }

  public void ParseAndPrintXML() throws XMLStreamException {
    STAXParserXML();
    for (Journal journal : titleList) {
      System.out.println(journal);
    }
    for (Contact contact : contactList) {
      System.out.println(contact);
    }
    for (Article article : articleList) {
      System.out.println(article);
    }
  }
}



