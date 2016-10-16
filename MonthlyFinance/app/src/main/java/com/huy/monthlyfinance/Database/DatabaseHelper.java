package com.huy.monthlyfinance.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huy nguyen on 9/15/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "SQLite";
    private SQLiteDatabase db;
    // Phiên bản
    private static final int DATABASE_VERSION =9;
    //ten co so du lieu
    private static final String DATABASE_NAME = "QuanLyTaiChinh.db";
    // danh sach bang
    public static final String tblSanpham = "SanPham";
    public static final String tblNhomsanpham ="NhomSanPham";
    public static final String tblChitietsanpham ="ChiTietSanPham";
    public static final String tblTaikhoan = "TaiKhoan";
    private static final String tblChitiettaikhoan ="ChiTietTaiKhoan";
    private static final String tblNguoidung ="NguoiDung";
    private static final String tblLichsuchitieu = "LichSuChiTieu";
    // danh sach cac thuoc tinh cua cac bang
    public static final String MaSP="Masanpham";
    public static final String TenSP="Tensanpham";
    public static final String ManhomSP="ManhomSanpham";
    public static final String Donvitinh="Donvitinh";
    public static final String Hinhanh="Hinhanh";
    public static final String TennhomSP="Tennhomsanpham";
    public static final String MachitietSP="Machitietsanpham";
    public static final String Gia="Gia";
    public static final String Soluong="Soluong";
    public static final String MaTK="Mataikhoan";
    public static final String Manguoidung="Manguoidung";
    public static final String TenTK="Tentaikhoan";
    public static final String LoaiTK="Loaitaikhoan";
    public static final String Loaitiente="Loaitiente";
    public static final String Tongtien="Tongtien";
    public static final String Ghichu="Ghichu";
    public static final String Trangthai="Trangthai";
    public static final String MachitietTK="Machitiettaikhoan";
    public static final String Sodudau="Sodudau";
    public static final String Soduhientai="Soduhientai";
    public static final String Ngaygiaodich="Ngaygiaodich";
    public static final String Tendangnhap="Tendangnhap";
    public static final String Matkhau="Matkhau";
    public static final String Email="Email";
    public static final String Malichsumuahang="Malichsumuahang";
    public static final String Ngaymua="Ngaymua";
    public static final String Giatri="Giatri";
    // lenh tao bang
    public static final String CREATE_TABLE_SANPHAM="create table "+tblSanpham+"("+MaSP+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +TenSP+" TEXT NOT NULL"+","+ManhomSP+" INTERGER"+","+Donvitinh+" TEXT"+","+Hinhanh+" TEXT ,"+" FOREIGN KEY("+ManhomSP+") REFERENCES "+tblNhomsanpham+"("+ManhomSP+"))";

    public static final String CREATE_TABLE_NHOMSANPHAM="create table "+tblNhomsanpham+"("+ManhomSP+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +TennhomSP+" TEXT NOT NULL"+","+Hinhanh+" TEXT"+")";
    public static final String CREATE_TABLE_CHITIETSANPHAM="create table "+tblChitietsanpham+"("+MachitietSP+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +Gia+" INTERGER"+","+MaSP+" INTERGER"+","+Malichsumuahang+" INTERGER"+","+Soluong+" INTERGER ,"+" FOREIGN KEY("+MaSP+") REFERENCES "+tblSanpham+"("+MaSP+"),"+" FOREIGN KEY("+Malichsumuahang+") REFERENCES "+tblLichsuchitieu+"("+Malichsumuahang+"))";
    public static final String CREATE_TABLE_TAIKHOAN="create table "+tblTaikhoan+"("+MaTK+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +TenTK+" TEXT NOT NULL"+","+LoaiTK+" TEXT NOT NULL"+","+Loaitiente+" TEXT NOT NULL"+","+Tongtien+" INTERGER"+","+Ghichu+" TEXT"+","
            +Trangthai+" TEXT"+","+Manguoidung+" INTERGER ,"+" FOREIGN KEY("+Manguoidung+") REFERENCES "+tblNguoidung+"("+Manguoidung+"))";
    //bang chi tiet tai khoan
    public static final String CREATE_TABLE_CHITIETTAIKHOAN="create table "+tblChitiettaikhoan+"("+MachitietTK+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +Sodudau+" INTERGER"+","+Malichsumuahang+" INTERGER"+","+Soduhientai+" INTERGER"+","+MaTK+" INTERGER"+","+Ngaygiaodich+" TEXT NOT NULL ,"+" FOREIGN KEY("+MaTK+") REFERENCES "+tblTaikhoan+"("+MaTK+"),"+" FOREIGN KEY("+Malichsumuahang+") REFERENCES "+tblLichsuchitieu+"("+Malichsumuahang+"))";
    //bang nguoi dung
    public static final String CREATE_TABLE_NGUOIDUNG="create table "+tblNguoidung+"("+Manguoidung+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +Tendangnhap+" TEXT NOT NULL"+","+Matkhau+" TEXT NOT NULL"+","+Email+" TEXT NOT NULL"+")";
    //bang lich su chi tieu
    public static final String CREATE_TABLE_LICHSUCHITIEU="create table "+tblLichsuchitieu+"("+Malichsumuahang+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+","
            +Manguoidung+" INTERGER"+","+Ngaymua+" TEXT NOT NULL"+","+Giatri+" INTERGER ,"+" FOREIGN KEY("+Manguoidung+") REFERENCES "+tblNguoidung+"("+Manguoidung+"))";
    // end create database

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       try{
           db.execSQL(CREATE_TABLE_NHOMSANPHAM);
            db.execSQL(CREATE_TABLE_SANPHAM);
           db.execSQL(CREATE_TABLE_CHITIETSANPHAM);
           db.execSQL(CREATE_TABLE_NGUOIDUNG);
           db.execSQL(CREATE_TABLE_TAIKHOAN);
           db.execSQL(CREATE_TABLE_CHITIETTAIKHOAN);
           db.execSQL(CREATE_TABLE_LICHSUCHITIEU);
       }catch(SQLException e){
           e.printStackTrace();
       }
    }
    public void open() {
        try {
            db = getWritableDatabase();
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + tblSanpham);
        db.execSQL("DROP TABLE IF EXISTS " + tblNhomsanpham);
        db.execSQL("DROP TABLE IF EXISTS " + tblChitietsanpham);
        db.execSQL("DROP TABLE IF EXISTS " + tblTaikhoan);
        db.execSQL("DROP TABLE IF EXISTS " + tblChitiettaikhoan);
        db.execSQL("DROP TABLE IF EXISTS " + tblNguoidung);
        db.execSQL("DROP TABLE IF EXISTS " + tblLichsuchitieu);

        // Recreate
        onCreate(db);
    }
    /*Hàm đóng kết nối với database*/
    public void close() {
        if (db != null && db.isOpen()) {
            try {
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * get all row of table with sql command then return cursor
     * cursor move to frist to redy for get data
     */
    public Cursor getAll(String sql){
        open();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    /**
     * insert contentvaluse to table
     *
     * @param values value of data want insert
     * @return index row insert
     */
    public long insert(String table, ContentValues values) {
        open();
        long index = db.insert(table, null, values);
        close();
        return index;
    }
    /**
     * update values to table
     *
     * @return index row update
     */
    public boolean update(String table, ContentValues values, String where) {
        open();
        long index = db.update(table, values, where, null);
        close();
        return index > 0;
    }

    /**
     * delete id row of table
     */
    public boolean delete(String table, String where) {
        open();
        long index = db.delete(table, where, null);
        close();
        return index > 0;
    }
}
