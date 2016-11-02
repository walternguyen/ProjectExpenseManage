package com.huy.monthlyfinance.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.ProductGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class ProductGroupDAO extends DatabaseHelper<ProductGroup> {
    //constructor
    public ProductGroupDAO(Context context) {
        super(context);
    }

    // ham them moi mot nhom san pham
    public boolean insertProductGroup(ProductGroup productGroup) {
        mValues.put(productGroupName, productGroup.getTenNhomSanpham());
        mValues.put(productImage, productGroup.getHinhanh());
        boolean result = mWritableDatabase.insert(tblProductGroup, null, mValues) > 0;
        if (result) {
            mMessage = "insert successful";
        } else {
            mMessage = "Fail to insert in Nhomsanpham";
        }
        mValues.clear();
        return result;
    }

    // ham cap nhat nhom san pham
    public int updateProductGroup(ProductGroup n) {
        mValues.put(productGroupName, n.getTenNhomSanpham());
        mValues.put(productImage, n.getHinhanh());
        int result = mReadableDatabase.update(tblProductGroup, mValues, productGroupID + "=?", new String[]{String.valueOf(n.getMaNhomSanpham())});
        mValues.clear();
        return result;
    }

    // ham xoa mot nhom san pham
    public void delete(ProductGroup n) {
        mReadableDatabase.delete(tblProductGroup, productGroupID + "=?", new String[]{String.valueOf(n.getMaNhomSanpham())});
        mValues.clear();
    }

    // ham lay danh sach nhom san pham
    public List<ProductGroup> getAllProductGroup() {
        List<ProductGroup> productGroupList = new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + tblProductGroup;
        Cursor cursor = mReadableDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductGroup productGroup = new ProductGroup();
                productGroup.setMaNhomSanpham(cursor.getString(0));
                productGroup.setTenNhomSanpham(cursor.getString(1));
                productGroup.setHinhanh(cursor.getString(2));
                // Adding contact to list
                productGroupList.add(productGroup);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mValues.clear();
        // return nhom san pham list
        return productGroupList;
    }
}
