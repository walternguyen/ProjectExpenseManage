package com.huy.monthlyfinance.Model;

/**
 * Created by huy nguyen on 9/16/2016.
 */

public class Product extends BaseDTO{
    private String mProductID;
    private String mProductName;
    private String mProductGroupID;
    private String mUnitCalculation;
    private String mProductImage;

    public Product() {
        super();
    }

    public Product(String ProductID, String ProductName, String ProductGroupID, String UnitCalculation, String ProductImage) {
        super();
        this.mProductID = ProductID;
        this.mProductName = ProductName;
        this.mProductGroupID = ProductGroupID;
        this.mUnitCalculation = UnitCalculation;
        this.mProductImage = ProductImage;
    }

    public String getProductID() {
        return mProductID;
    }

    public void setProductID(String mProductID) {
        this.mProductID = mProductID;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getProductGroupID() {
        return mProductGroupID;
    }

    public void setProductGroupID(String mProductGroupID) {
        this.mProductGroupID = mProductGroupID;
    }

    public String getUnitCalculation() {
        return mUnitCalculation;
    }

    public void setUnitCalculation(String mUnitCalculation) {
        this.mUnitCalculation = mUnitCalculation;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String mProductImage) {
        this.mProductImage = mProductImage;
    }
}
