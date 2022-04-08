package com.it_academy.STAXParser.app;

import com.it_academy.STAXParser.entity.Article;
import com.it_academy.STAXParser.entity.Contact;
import com.it_academy.STAXParser.entity.Journal;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class STAXParserApplication {

  private List<Journal> titleList = null;
  private List<Contact> contactList = null;
  private List<Article> articleList = null;
  private List<String> hotkeyList = null;
  private Journal currTitle = null;
  private Contact currContact = null;
  private Article currArticle = null;
  private String tagContent = null;

  private void ParseStartElement(XMLStreamReader reader) throws XMLStreamException {

    switch (reader.getLocalName()){
      case "journaltitle" -> {
        currTitle = new Journal();
        currTitle.setTitle(reader.getAttributeValue(0));
      }
      case "journal" -> titleList = new ArrayList<>();
      case "address" -> {
        currContact = new Contact();
        currContact.setAddress(reader.getAttributeValue(0));
      }
      case "contacts" -> contactList = new ArrayList<>();
      case "article" -> {
        currArticle = new Article();
        currArticle.setId(reader.getAttributeValue(0));
      }
      case "articles" -> articleList = new ArrayList<>();
      case "hotkey" -> {
        hotkeyList.add(reader.getElementText());
        currArticle.setHotkeys(hotkeyList);
      }
      case "hotkeys" -> hotkeyList = new ArrayList<>();
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

  public void parseAndPrintXML() throws XMLStreamException {
    STAXParserXML();
    titleList.forEach(System.out::println);
    contactList.forEach(System.out::println);
    articleList.forEach(System.out::println);
  }
}



