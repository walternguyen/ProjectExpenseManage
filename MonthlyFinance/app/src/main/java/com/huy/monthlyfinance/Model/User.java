package com.huy.monthlyfinance.Model;


/**
 * Created by huy nguyen on 9/16/2016.
 */

public class User extends BaseDTO{

    private String maNguoidung;

    private String tenDangnhap;

    private String matkhau;

    private String email;

    public User() {
        super();
    }

    public User(String maNguoidung, String tenDangnhap, String matkhau, String email) {
        super();
        this.maNguoidung = maNguoidung;
        this.tenDangnhap = tenDangnhap;
        this.matkhau = matkhau;
        this.email = email;
    }

    public String getMaNguoidung() {
        return maNguoidung;
    }

    public void setMaNguoidung(String maNguoidung) {
        this.maNguoidung = maNguoidung;
    }

    public String getTenDangnhap() {
        return tenDangnhap;
    }

    public void setTenDangnhap(String tenDangnhap) {
        this.tenDangnhap = tenDangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
