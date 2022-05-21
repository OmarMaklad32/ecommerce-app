package com.example.ecommerce2.Models;

public class Cartmodel {
    String Productname;
    String Productprice;
    String Productquantity;

    public Cartmodel() {
    }

    public Cartmodel(String productname, String productprice, String productquantity) {
        Productname = productname;
        Productprice = productprice;
        Productquantity = productquantity;
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
}
