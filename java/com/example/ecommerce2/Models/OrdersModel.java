package com.example.ecommerce2.Models;

public class OrdersModel {
    String Name;
    String Productname;
    String Productprice;
    String Productquantity;
    String cityname;
    String homeaddress;
    String phonenumber;

    public OrdersModel() {
    }

    public OrdersModel(String name, String productname, String productprice, String productquantity, String cityname, String homeaddress, String phonenumber) {
        Name = name;
        Productname = productname;
        Productprice = productprice;
        Productquantity = productquantity;
        this.cityname = cityname;
        this.homeaddress = homeaddress;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getProductprice() {
        return Productprice;
    }

    public void setProductprice(String productprice) {
        Productprice = productprice;
    }

    public String getProductquantity() {
        return Productquantity;
    }

    public void setProductquantity(String productquantity) {
        Productquantity = productquantity;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
