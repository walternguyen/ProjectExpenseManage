package com.huy.monthlyfinance.Model;


import java.util.List;

/**
 * Created by huy nguyen on 9/16/2016.
 */

public class AccountDetail extends BaseDTO {

    private String maChitietTaikhoan;

    private String maTaikhoan;

    private double sodudau;

    private double soduhientai;

    private String ngayGiaodich;

    public AccountDetail() {
    }

    public AccountDetail(String maChitietTaikhoan, String maTaikhoan, double sodudau, double soduhientai, String ngayGiaodich) {
        this.maChitietTaikhoan = maChitietTaikhoan;
        this.maTaikhoan = maTaikhoan;
        this.sodudau = sodudau;
        this.soduhientai = soduhientai;
        this.ngayGiaodich = ngayGiaodich;
    }

    public String getMaChitietTaikhoan() {
        return maChitietTaikhoan;
    }

    public void setMaChitietTaikhoan(String maChitietTaikhoan) {
        this.maChitietTaikhoan = maChitietTaikhoan;
    }

    public String getMaTaikhoan() {
        return maTaikhoan;
    }

    public void setMaTaikhoan(String maTaikhoan) {
        this.maTaikhoan = maTaikhoan;
    }

    public double getSodudau() {
        return sodudau;
    }

    public void setSodudau(double sodudau) {
        this.sodudau = sodudau;
    }

    public double getSoduhientai() {
        return soduhientai;
    }

    public void setSoduhientai(double soduhientai) {
        this.soduhientai = soduhientai;
    }

    public String getNgayGiaodich() {
        return ngayGiaodich;
    }

    public void setNgayGiaodich(String ngayGiaodich) {
        this.ngayGiaodich = ngayGiaodich;
    }

}
