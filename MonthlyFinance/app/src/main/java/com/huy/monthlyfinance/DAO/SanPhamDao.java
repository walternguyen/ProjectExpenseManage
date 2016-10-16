package com.huy.monthlyfinance.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.NhomSanPham;
import com.huy.monthlyfinance.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class SanPhamDao extends DatabaseHelper {
    SQLiteDatabase db;
    ContentValues values;
    String msg;
    public SanPhamDao(Context context) {
        super(context);
    }
    // ham them san pham
    public void doInserttblSanPham(SanPham s){
        values.put(TenSP,s.getTenSanpham());
        values.put(ManhomSP,s.getMaNhomSanpham());
        values.put(Donvitinh,s.getDonvitinh());
        values.put(Hinhanh,s.getHinhanh());
        if(db.insert(tblSanpham,null,values)==-1){
            msg="Fail to insert in san pham";
        }else{
            msg="insert successful";
        }
    }
    //ham cap nhat san pham
    public int doUpdatetblSanpham(SanPham s){
        values.put(TenSP,s.getTenSanpham());
        values.put(ManhomSP,s.getMaNhomSanpham());
        values.put(Donvitinh,s.getDonvitinh());
        values.put(Hinhanh,s.getHinhanh());
        return db.update(tblSanpham,values,MaSP+"=?",new String[]{String.valueOf(s.getMaSanpham())});
    }
    // ham xoa san pham
    public void doDeleteTblSanpham(SanPham s){
        db.delete(tblSanpham,MaSP+"=?",new String[]{String.valueOf(s.getMaSanpham())});
    }
    // ham lay danh sach  san pham
    public List<SanPham> getAllSanpham(){
        List<SanPham> sanPhamList=new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + tblSanpham;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SanPham s = new SanPham();
                s.setMaSanpham(cursor.getString(0));
                s.setTenSanpham(cursor.getString(1));
                // Adding contact to list
                sanPhamList.add(s);
            } while (cursor.moveToNext());
        }
        // return nhom san pham list
        return sanPhamList;
    }
}
