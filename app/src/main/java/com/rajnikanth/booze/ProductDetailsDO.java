package com.rajnikanth.booze;

public class ProductDetailsDO {
    String productId;
    String productTitle;
    String productTag;
    String productAge;
    String productPrice;
    String productDescription;
    int productPreviewId;

    public ProductDetailsDO(String productId, String productTitle, String productTag, String productAge, String productPrice, String productDescription, int productPreviewId) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productTag = productTag;
        this.productAge = productAge;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productPreviewId = productPreviewId;
    }

    public ProductDetailsDO() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }

    public String getProductAge() {
        return productAge;
    }

    public void setProductAge(String productAge) {
        this.productAge = productAge;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPreviewId() {
        return productPreviewId;
    }

    public void setProductPreviewId(int productPreviewId) {
        this.productPreviewId = productPreviewId;
    }
}
