package com.huy.monthlyfinance.Database.DAO;

import android.content.Context;
import android.database.Cursor;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class ProductDAO extends BaseDAO {
    private static ProductDAO mInstance;
    private ProductDAO(Context context) {
        super(context);
    }

    public static ProductDAO getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ProductDAO(context);
        }
        return mInstance;
    }

    // ham them san pham
    public void doInsertTblProduct(Product product) {
        mValues.put(DatabaseHelper.productName, product.getProductName());
        mValues.put(DatabaseHelper.productGroupID, product.getProductGroupID());
        mValues.put(DatabaseHelper.productCalculationUnit, product.getUnitCalculation());
        mValues.put(DatabaseHelper.productImage, product.getProductImage());
        if (mWritableDatabase.insert(DatabaseHelper.tblProduct, null, mValues) > 0) {
            mMessage = "insert successful";
        } else {
            mMessage = "Fail to insert in san pham";
        }
        mValues.clear();
    }

    //ham cap nhat san pham
    public int doUpdateTblProduct(Product product) {
        mValues.put(DatabaseHelper.productName, product.getProductName());
        mValues.put(DatabaseHelper.productGroupID, product.getProductGroupID());
        mValues.put(DatabaseHelper.productCalculationUnit, product.getUnitCalculation());
        mValues.put(DatabaseHelper.productImage, product.getProductImage());
        int result = mWritableDatabase.update(DatabaseHelper.tblProduct, mValues, DatabaseHelper.productID + "=?",
                new String[]{String.valueOf(product.getProductID())});
        mValues.clear();
        return result;
    }

    // ham xoa san pham
    public void doDeleteTblProduct(Product s) {
        mWritableDatabase.delete(DatabaseHelper.tblProduct, DatabaseHelper.productID + "=?", new String[]{String.valueOf(s.getProductID())});
        mValues.clear();
    }

    // ham lay danh sach  san pham
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + DatabaseHelper.tblProduct;
        Cursor cursor = mReadableDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product s = new Product();
                s.setProductID(cursor.getString(0));
                s.setProductName(cursor.getString(1));
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
