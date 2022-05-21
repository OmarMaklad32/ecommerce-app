package com.example.ecommerce2;

public class ProductModel {
    String ProductImage;
    String Productdescrption;
    String Productname;
    String Productprice;

    public ProductModel() {
    }

    public ProductModel(String productImage, String productdescrption, String productname, String productprice) {
        ProductImage = productImage;
        Productdescrption = productdescrption;
        Productname = productname;
        Productprice = productprice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductdescrption() {
        return Productdescrption;
    }

    public void setProductdescrption(String productdescrption) {
        Productdescrption = productdescrption;
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
}
