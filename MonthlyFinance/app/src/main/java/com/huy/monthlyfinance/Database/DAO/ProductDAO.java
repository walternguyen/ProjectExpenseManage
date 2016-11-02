package com.huy.monthlyfinance.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class ProductDAO extends DatabaseHelper<Product> {

    public ProductDAO(Context context) {
        super(context);
    }

    // ham them san pham
    public void doInsertTblProduct(Product product) {
        mValues.put(productName, product.getTenSanpham());
        mValues.put(productGroupID, product.getMaNhomSanpham());
        mValues.put(productCalculationUnit, product.getDonvitinh());
        mValues.put(productImage, product.getHinhanh());
        if (mWritableDatabase.insert(tblProduct, null, mValues) > 0) {
            mMessage = "insert successful";
        } else {
            mMessage = "Fail to insert in san pham";
        }
        mValues.clear();
    }

    //ham cap nhat san pham
    public int doUpdateTblProduct(Product product) {
        mValues.put(productName, product.getTenSanpham());
        mValues.put(productGroupID, product.getMaNhomSanpham());
        mValues.put(productCalculationUnit, product.getDonvitinh());
        mValues.put(productImage, product.getHinhanh());
        int result = mWritableDatabase.update(tblProduct, mValues, productID + "=?", new String[]{String.valueOf(product.getMaSanpham())});
        mValues.clear();
        return result;
    }

    // ham xoa san pham
    public void doDeleteTblProduct(Product s) {
        mWritableDatabase.delete(tblProduct, productID + "=?", new String[]{String.valueOf(s.getMaSanpham())});
        mValues.clear();
    }

    // ham lay danh sach  san pham
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + tblProduct;
        Cursor cursor = mReadableDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product s = new Product();
                s.setMaSanpham(cursor.getString(0));
                s.setTenSanpham(cursor.getString(1));
                // Adding contact to list
                productList.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        mValues.clear();
        // return nhom san pham list
        return productList;
    }
}
