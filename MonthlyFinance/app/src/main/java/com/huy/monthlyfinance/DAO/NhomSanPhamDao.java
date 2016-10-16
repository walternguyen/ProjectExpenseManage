package com.huy.monthlyfinance.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.LinkAddress;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.NhomSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class NhomSanPhamDao extends DatabaseHelper {
    SQLiteDatabase db;
    ContentValues values;
    String msg;
    //Cursor cursor;
    //constructor
    public NhomSanPhamDao(Context context) {
        super(context);
        db=this.getWritableDatabase();
        values=new ContentValues();
    }
    // ham them moi mot nhom san pham
    public void  insertNhomsanpham(NhomSanPham n){
        values.put(TennhomSP,n.getTenNhomSanpham());
        values.put(Hinhanh,n.getHinhanh());
       if(db.insert(tblNhomsanpham,null,values)==-1){
           msg="Fail to insert in Nhomsanpham";
       }else{
           msg="insert successful";
       }
    }
    // ham cap nhat nhom san pham
    public int updateNhomsanpham(NhomSanPham n){
        values.put(TennhomSP,n.getTenNhomSanpham());
        values.put(Hinhanh,n.getHinhanh());
        return db.update(tblNhomsanpham,values,ManhomSP+"=?",new String[]{String.valueOf(n.getMaNhomSanpham())});
    }
    // ham xoa mot nhom san pham
    public void deleteNhomsanpham(NhomSanPham n){
        db.delete(tblNhomsanpham,ManhomSP+"=?",new String[]{String.valueOf(n.getMaNhomSanpham())});
    }
    // ham lay danh sach nhom san pham
    public List<NhomSanPham> getAllNhomsanpham(){
        List<NhomSanPham> nhomSanPhamList=new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + tblNhomsanpham;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                NhomSanPham n = new NhomSanPham();
                n.setMaNhomSanpham(cursor.getString(0));
                n.setTenNhomSanpham(cursor.getString(1));
                n.setHinhanh(cursor.getString(2));
                // Adding contact to list
                nhomSanPhamList.add(n);
            } while (cursor.moveToNext());
        }
        // return nhom san pham list
        return nhomSanPhamList;
    }
}
