package com.it_academy.STAXParser.main;

import com.it_academy.STAXParser.app.STAXParserApplication;

import javax.xml.stream.XMLStreamException;

public class STAXParserStarter {

  public static void main(String[] args) throws XMLStreamException {

    new STAXParserApplication().parseAndPrintXML();
  }
}
