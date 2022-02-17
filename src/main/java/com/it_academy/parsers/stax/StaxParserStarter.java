package com.it_academy.parsers.stax;

import com.it_academy.entity.Article;
import com.it_academy.entity.Contact;

import com.it_academy.entity.Hotkey;
import com.it_academy.entity.Journal;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxParserStarter {
  public static void main(String[] args) throws XMLStreamException {
    List<Journal> titleList = null;
    List<Contact> contactList = null;
    List<Article> articleList = null;
    List<Hotkey> hotkeyList = null;
    Journal currTitle = null;
    Contact currContact = null;
    Article currArticle = null;
    Hotkey currHotkey = null;
    String tagContent = null;
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader =
        factory.createXMLStreamReader(
            ClassLoader.getSystemResourceAsStream("journal.xml"));

    while (reader.hasNext()) {
      int event = reader.next();

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          //get title
          if ("journaltitle".equals(reader.getLocalName())) {
            currTitle = new Journal();
            currTitle.setTitle(reader.getAttributeValue(0));
          }
          if ("journal".equals(reader.getLocalName())) {
            titleList = new ArrayList<>();
          }
          //get contacts
          if ("address".equals(reader.getLocalName())) {
            currContact = new Contact();
            currContact.setAddress(reader.getAttributeValue(0));
          }
          if ("contacts".equals(reader.getLocalName())) {
            contactList = new ArrayList<>();
          }
          //get article
          if ("article".equals(reader.getLocalName())) {
            currArticle = new Article();
            currArticle.setId(reader.getAttributeValue(0));
          }
          if ("articles".equals(reader.getLocalName())) {
            articleList = new ArrayList<>();
          }
          //gethotkey
          if ("hotkeys".equals(reader.getLocalName())) {
            hotkeyList = new ArrayList<>();
          }
          if ("hotkey".equals(reader.getLocalName())) {
            currHotkey = new Hotkey();
            currHotkey.setHotkey(reader.getAttributeValue(0));
          }
          break;

        case XMLStreamConstants.CHARACTERS:
          tagContent = reader.getText().trim();
          break;

        case XMLStreamConstants.END_ELEMENT:
          switch (reader.getLocalName()) {
            //get title
            case "journal" -> titleList.add(currTitle);
            case "journaltitle" -> currTitle.setTitle(tagContent);
            //get contacts
            case "contacts" -> contactList.add(currContact);
            case "address" -> currContact.setAddress(tagContent);
            case "tel" -> currContact.setTel(tagContent);
            case "email" -> currContact.setEmail(tagContent);
            case "journalurl" -> currContact.setUrl(tagContent);
            //get article
            case "articles" -> articleList.add(currArticle);
            case "ID" -> currArticle.setId(tagContent);
            case "title" -> currArticle.setTitle(tagContent);
            case "author" -> currArticle.setAuthor(tagContent);
            case "url" -> currArticle.setUrl(tagContent);

            //get hotkey
            case "hotkeys" -> hotkeyList.add(currHotkey);
            case "hotkey" -> currHotkey.setHotkey(tagContent);
          }
          break;

        case XMLStreamConstants.START_DOCUMENT:
          titleList = new ArrayList<>();
          contactList = new ArrayList<>();
          articleList = new ArrayList<>();
          hotkeyList = new ArrayList<>();
          break;
      }

    }

    //Print the employee list populated from XML
    for (Journal journal : titleList) {
      System.out.println(journal);
    }
    for (Contact contact : contactList) {
      System.out.println(contact);
    }
    for (Article article : articleList) {
      System.out.println(article);
    }
    for (Hotkey hotkey : hotkeyList) {
      System.out.println(hotkey);
    }
  }
}
