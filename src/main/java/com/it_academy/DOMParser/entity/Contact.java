package com.it_academy.DOMParser.entity;

public class Contact {

    private String address;
    private String tel;
    private String email;
    private String url;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Contacts  {" +
            "address='" + address + '\'' + "\n" +
            "            tel='" + tel + '\'' + "\n" +
            "            email='" + email + '\'' + "\n" +
            "            url='" + url + '\'' +
            '}';
    }
}
