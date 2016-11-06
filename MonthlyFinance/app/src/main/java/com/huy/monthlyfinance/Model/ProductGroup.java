package com.huy.monthlyfinance.Model;

/**
 * Created by huy nguyen on 9/16/2016.
 */
public class ProductGroup extends BaseDTO{
    private String mProductGroupID;
    private String mGroupName;
    private String mGroupImage;

    public ProductGroup() {

    }

    public ProductGroup(String ProductGroupID, String GroupName, String GroupImage) {

        this.mProductGroupID = ProductGroupID;
        this.mGroupName = GroupName;
        this.mGroupImage = GroupImage;
    }

    public String getProductGroupID() {
        return mProductGroupID;
    }

    public void setProductGroupID(String mProductGroupID) {
        this.mProductGroupID = mProductGroupID;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String mGroupName) {
        this.mGroupName = mGroupName;
    }

    public String getGroupImage() {
        return mGroupImage;
    }

    public void setGroupImage(String mGroupImage) {
        this.mGroupImage = mGroupImage;
    }
}