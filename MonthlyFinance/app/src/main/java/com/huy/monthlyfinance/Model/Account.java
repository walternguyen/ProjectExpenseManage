package com.huy.monthlyfinance.Model;


/**
 * Created by huy nguyen on 9/16/2016.
 */

public class Account extends BaseDTO{

    private String maTaikhoan;

    private String tenTaikhoan;

    private String loaiTaikhoan;

    private String loaiTiente;

    private double tongtien;

    private String ghichu;

    private String maNguoidung;

    private boolean trangthai;

    public Account() {
        super();
    }
    public Account(String maTaikhoan, String tenTaikhoan, String loaiTaikhoan, String loaiTiente, double tongtien, String ghichu, String maNguoidung, boolean trangthai) {
        super();
        this.maTaikhoan = maTaikhoan;
        this.tenTaikhoan = tenTaikhoan;
        this.loaiTaikhoan = loaiTaikhoan;
        this.loaiTiente = loaiTiente;
        this.tongtien = tongtien;
        this.ghichu = ghichu;
        this.maNguoidung = maNguoidung;
        this.trangthai = trangthai;

    }
    public String getMaTaikhoan() {
        return maTaikhoan;
    }

    public void setMaTaikhoan(String maTaikhoan) {
        this.maTaikhoan = maTaikhoan;
    }

    public String getTenTaikhoan() {
        return tenTaikhoan;
    }

    public void setTenTaikhoan(String tenTaikhoan) {
        this.tenTaikhoan = tenTaikhoan;
    }

    public String getLoaiTaikhoan() {
        return loaiTaikhoan;
    }

    public void setLoaiTaikhoan(String loaiTaikhoan) {
        this.loaiTaikhoan = loaiTaikhoan;
    }

    public String getLoaiTiente() {
        return loaiTiente;
    }

    public void setLoaiTiente(String loaiTiente) {
        this.loaiTiente = loaiTiente;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getMaNguoidung() {
        return maNguoidung;
    }

    public void setMaNguoidung(String maNguoidung) {
        this.maNguoidung = maNguoidung;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }


}
