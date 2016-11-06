package com.huy.monthlyfinance.Database.DAO;

import android.content.Context;
import android.database.Cursor;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.ProductGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class ProductGroupDAO extends BaseDAO {
    private static ProductGroupDAO mInstance;

    //constructor
    private ProductGroupDAO(Context context) {
        super(context);
    }

    public static ProductGroupDAO getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ProductGroupDAO(context);
        }
        return mInstance;
    }

    // ham them moi mot nhom san pham
    public boolean insertProductGroup(ProductGroup productGroup) {
        mValues.put(DatabaseHelper.productGroupName, productGroup.getGroupName());
        mValues.put(DatabaseHelper.productImage, productGroup.getGroupImage());
        boolean result = mWritableDatabase.insert(DatabaseHelper.tblProductGroup, null, mValues) > 0;
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
        mValues.put(DatabaseHelper.productGroupName, n.getGroupName());
        mValues.put(DatabaseHelper.productImage, n.getGroupImage());
        int result = mReadableDatabase.update(DatabaseHelper.tblProductGroup, mValues, DatabaseHelper.productGroupID + "=?",
                new String[]{String.valueOf(n.getProductGroupID())});
        mValues.clear();
        return result;
    }

    // ham xoa mot nhom san pham
    public void delete(ProductGroup n) {
        mReadableDatabase.delete(DatabaseHelper.tblProductGroup, DatabaseHelper.productGroupID + "=?",
                new String[]{String.valueOf(n.getProductGroupID())});
        mValues.clear();
    }

    // ham lay danh sach nhom san pham
    public List<ProductGroup> getAllProductGroup() {
        List<ProductGroup> productGroupList = new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + DatabaseHelper.tblProductGroup;
        Cursor cursor = mReadableDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductGroup productGroup = new ProductGroup();
                productGroup.setProductGroupID(cursor.getString(0));
                productGroup.setGroupName(cursor.getString(1));
                productGroup.setGroupImage(cursor.getString(2));
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
