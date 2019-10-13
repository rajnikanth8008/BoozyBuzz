package com.rajnikanth.booze;

public class ProductCategoryDO {
    String categoryId;
    String categoryName;
    int categoryPreviewId;

    public ProductCategoryDO(String categoryId, String categoryName, int categoryPreviewId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPreviewId = categoryPreviewId;
    }

    public ProductCategoryDO() {
    }

    public int getCategoryPreviewId() {
        return categoryPreviewId;
    }

    public void setCategoryPreviewId(int categoryPreviewId) {
        this.categoryPreviewId = categoryPreviewId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
