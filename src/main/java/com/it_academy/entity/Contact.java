package com.it_academy.entity;

public class Contact {

    public String address;
    public String tel;
    public String email;
    public String url;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
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
