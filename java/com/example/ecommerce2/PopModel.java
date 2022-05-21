package com.example.ecommerce2;

public class PopModel {
    String Productimage;
    String Productdescrption;
    String productprice;
    String productname;

    public PopModel() {
    }

    public PopModel(String productimage, String productdescrption, String productprice, String productname) {
        Productimage = productimage;
        Productdescrption = productdescrption;
        this.productprice = productprice;
        this.productname = productname;
    }

    public String getProductimage() {
        return Productimage;
    }

    public void setProductimage(String productimage) {
        Productimage = productimage;
    }

    public String getProductdescrption() {
        return Productdescrption;
    }

    public void setProductdescrption(String productdescrption) {
        Productdescrption = productdescrption;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
