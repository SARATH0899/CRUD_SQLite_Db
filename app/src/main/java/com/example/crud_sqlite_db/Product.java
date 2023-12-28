package com.example.crud_sqlite_db;

public class Product {
    private String productId;
    private String productName;
    private String productCategory;
    private String productQuantity;
    private String productAvailability;
    private int productPrice;

    public Product(String productId, String productName, String productCategory,
                   String productQuantity, String productAvailability, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        this.productAvailability = productAvailability;
        this.productPrice = productPrice;
    }

    public String getProductId() {

        return productId;
    }

    public String getProductName() {

        return productName;
    }

    public String getProductCategory() {

        return productCategory;
    }

    public String getProductQuantity() {

        return productQuantity;
    }

    public String getProductAvailability() {

        return productAvailability;
    }

    public int getProductPrice() {

        return productPrice;
    }
}
