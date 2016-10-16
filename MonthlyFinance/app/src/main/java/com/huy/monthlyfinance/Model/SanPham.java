package com.huy.monthlyfinance.Model;

import java.util.List;

/**
 * Created by huy nguyen on 9/16/2016.
 */

public class SanPham  {

    private String maSanpham;

    private String tenSanpham;

    private String maNhomSanpham;
    private String donvitinh;

    private String hinhanh;

    public SanPham() {
        super();
    }

    public SanPham(String maSanpham, String tenSanpham, String maNhomSanpham, String donvitinh, String hinhanh) {
        super();
        this.maSanpham = maSanpham;
        this.tenSanpham = tenSanpham;
        this.maNhomSanpham = maNhomSanpham;
        this.donvitinh = donvitinh;
        this.hinhanh = hinhanh;
    }

    public String getMaSanpham() {
        return maSanpham;
    }

    public void setMaSanpham(String maSanpham) {
        this.maSanpham = maSanpham;
    }

    public String getTenSanpham() {
        return tenSanpham;
    }

    public void setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public String getMaNhomSanpham() {
        return maNhomSanpham;
    }

    public void setMaNhomSanpham(String maNhomSanpham) {
        this.maNhomSanpham = maNhomSanpham;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

}
