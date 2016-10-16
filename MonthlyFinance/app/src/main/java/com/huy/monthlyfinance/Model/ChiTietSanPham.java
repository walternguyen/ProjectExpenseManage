package com.huy.monthlyfinance.Model;



import java.util.List;

/**
 * Created by huy nguyen on 9/16/2016.
 */

public class ChiTietSanPham  {

    private String maChitietSanpham;

    private String maSanpham;

    private double gia;

    private int soluong;

    public ChiTietSanPham( ) {
        super();
    }

    public ChiTietSanPham(String maChitietSanpham, String maSanpham, double gia, int soluong) {
        super();
        this.maChitietSanpham = maChitietSanpham;
        this.maSanpham = maSanpham;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getMaChitietSanpham() {
        return maChitietSanpham;
    }

    public void setMaChitietSanpham(String maChitietSanpham) {
        this.maChitietSanpham = maChitietSanpham;
    }

    public String getMaSanpham() {
        return maSanpham;
    }

    public void setMaSanpham(String maSanpham) {
        this.maSanpham = maSanpham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

}
