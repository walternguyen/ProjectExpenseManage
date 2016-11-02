package com.huy.monthlyfinance.Model;

import java.util.List;

/**
 * Created by huy nguyen on 9/16/2016.
 */
public class ProductGroup extends BaseDTO{
    private String maNhomSanpham;
    private String tenNhomSanpham;
    private String hinhanh;

    public ProductGroup() {

    }

    public ProductGroup(String maNhomSanpham, String tenNhomSanpham, String hinhanh) {

        this.maNhomSanpham = maNhomSanpham;
        this.tenNhomSanpham = tenNhomSanpham;
        this.hinhanh = hinhanh;
    }

    public String getMaNhomSanpham() {
        return maNhomSanpham;
    }

    public void setMaNhomSanpham(String maNhomSanpham) {
        this.maNhomSanpham = maNhomSanpham;
    }

    public String getTenNhomSanpham() {
        return tenNhomSanpham;
    }

    public void setTenNhomSanpham(String tenNhomSanpham) {
        this.tenNhomSanpham = tenNhomSanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}