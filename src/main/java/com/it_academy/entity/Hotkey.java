package com.it_academy.entity;

import java.util.List;

public class Hotkey {

  public String hotkey;

  public String getHotkey() {
    return hotkey;
  }

  public void setHotkey(String hotkey) {
    this.hotkey = hotkey;
  }

  @Override
  public String toString() {
    return "Hotkey    {" +
        "hotkey='" + hotkey + '\'' + '}' + "\n";
  }
}
