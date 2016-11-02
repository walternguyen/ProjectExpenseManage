package com.huy.monthlyfinance.Model;



import java.util.List;

/**
 * Created by huy nguyen on 9/16/2016.
 */

public class ExpensesHistory extends BaseDTO{

    private String maLichsuMuahang;

    private String maChitietSanpham;

    private String maChitietTaikhoan;

    private String maNguoidung;

    private String ngaymua;

    private double giatri;

    public ExpensesHistory() {
        super();
    }

    public ExpensesHistory(String maLichsuMuahang, String maChitietSanpham, String maChitietTaikhoan, String maNguoidung, String ngaymua, double giatri) {
        super();
        this.maLichsuMuahang = maLichsuMuahang;
        this.maChitietSanpham = maChitietSanpham;
        this.maChitietTaikhoan = maChitietTaikhoan;
        this.maNguoidung = maNguoidung;
        this.ngaymua = ngaymua;
        this.giatri = giatri;
    }

    public String getMaLichsuMuahang() {
        return maLichsuMuahang;
    }

    public void setMaLichsuMuahang(String maLichsuMuahang) {
        this.maLichsuMuahang = maLichsuMuahang;
    }

    public String getMaChitietSanpham() {
        return maChitietSanpham;
    }

    public void setMaChitietSanpham(String maChitietSanpham) {
        this.maChitietSanpham = maChitietSanpham;
    }

    public String getMaChitietTaikhoan() {
        return maChitietTaikhoan;
    }

    public void setMaChitietTaikhoan(String maChitietTaikhoan) {
        this.maChitietTaikhoan = maChitietTaikhoan;
    }

    public String getMaNguoidung() {
        return maNguoidung;
    }

    public void setMaNguoidung(String maNguoidung) {
        this.maNguoidung = maNguoidung;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public double getGiatri() {
        return giatri;
    }

    public void setGiatri(double giatri) {
        this.giatri = giatri;
    }

}
