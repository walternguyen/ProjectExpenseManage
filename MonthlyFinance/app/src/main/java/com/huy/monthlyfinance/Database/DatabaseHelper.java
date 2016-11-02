package com.huy.monthlyfinance.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huy.monthlyfinance.Model.BaseDTO;

import java.util.List;

/**
 * Created by huy nguyen on 9/15/2016.
 */
public abstract class DatabaseHelper<T extends BaseDTO> extends SQLiteOpenHelper {
    protected static SQLiteDatabase mReadableDatabase;
    protected static SQLiteDatabase mWritableDatabase;
    protected static boolean mIsCreatedDatabase;
    protected ContentValues mValues;
    protected String mMessage;
    // database version
    private static final int DATABASE_VERSION = 9;
    //ten co so du lieu
    private static final String DATABASE_NAME = "QuanLyTaiChinh.db";

    // tables
    protected static final String tblProduct = "Product";
    protected static final String tblProductGroup = "ProductGroup";
    private static final String tblProductDetail = "ChiTietSanPham";
    private static final String tblAccount = "TaiKhoan";
    private static final String tblAccountDetail = "ChiTietTaiKhoan";
    private static final String tblUser = "NguoiDung";
    private static final String tblExpensesHistory = "LichSuChiTieu";

    // Product table
    protected static final String productID = "MaSanPham";
    protected static final String productName = "TenSanPham";
    protected static final String productGroupID = "MaNhomSanPham";
    protected static final String productCalculationUnit = "DonViTinh";
    protected static final String productImage = "HinhAnh";
    protected static final String productGroupName = "TenNhomSanPham";

    //Product Detail table
    private static final String productDetailID = "MaChiTietSanPham";
    private static final String productCost = "Gia";
    private static final String productQuantity = "SoLuong";

    //Account table
    private static final String accountID = "MaTaiKhoan";
    private static final String userID = "MaNguoiDung";
    private static final String accountName = "Tentaikhoan";
    private static final String accountType = "Loaitaikhoan";
    private static final String accountConcurrency = "LoaiTienTe";
    private static final String accountCurrentCash = "TongTien";
    private static final String accountNote = "GhiChu";
    private static final String accountState = "TrangThai";
    private static final String accountDetailID = "MaChiTietTaiKhoan";
    private static final String accountInitBalance = "SoDuDau";
    private static final String accountCurrentBalance = "SoDuHienTai";
    private static final String accountTransactionDate = "NgayGiaoDich";

    //User table
    private static final String userLoginName = "TenDangNhap";
    private static final String userPassword = "MatKhau";
    private static final String userEmail = "Email";

    //Expense History table
    private static final String expenseHistoryID = "MaLichSuChiTieu";
    private static final String expenseDate = "NgayMua";
    private static final String expenseTotalCost = "GiaTri";

    // Create table commands
    private static final String CREATE_TABLE_PRODUCT =
            "create table " + tblProduct + "(" + productID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + productName + " TEXT NOT NULL" + "," + productGroupID + " INTEGER" + "," + productCalculationUnit +
                    " TEXT" + "," + productImage + " TEXT ," + " FOREIGN KEY(" + productGroupID + ") REFERENCES " +
                    tblProductGroup + "(" + productGroupID + "))";

    private static final String CREATE_TABLE_PRODUCT_GROUP =
            "create table " + tblProductGroup + "(" + productGroupID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + productGroupName + " TEXT NOT NULL" + "," + productImage + " TEXT" + ")";

    private static final String CREATE_TABLE_PRODUCT_DETAIL =
            "create table " + tblProductDetail + "(" + productDetailID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + productCost + " INTEGER" + "," + productID + " INTEGER" + "," + expenseHistoryID + " INTEGER" + ","
                    + productQuantity + " INTEGER ," + " FOREIGN KEY(" + productID + ") REFERENCES " + tblProduct
                    + "(" + productID + ")," + " FOREIGN KEY(" + expenseHistoryID + ") REFERENCES " + tblExpensesHistory
                    + "(" + expenseHistoryID + "))";

    private static final String CREATE_TABLE_ACCOUNT =
            "create table " + tblAccount + "(" + accountID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + accountName + " TEXT NOT NULL" + "," + accountType + " TEXT NOT NULL" + "," + accountConcurrency
                    + " TEXT NOT NULL" + "," + accountCurrentCash + " INTEGER" + "," + accountNote + " TEXT" + ","
            + accountState + " TEXT" + "," + userID + " INTEGER ," + " FOREIGN KEY(" + userID + ") " +
                    "REFERENCES " + tblUser + "(" + userID + "))";

    private static final String CREATE_TABLE_ACCOUNT_DETAIL =
            "create table " + tblAccountDetail + "(" + accountDetailID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + accountInitBalance + " INTEGER" + "," + expenseHistoryID + " INTEGER" + "," + accountCurrentBalance + " INTEGER" + ","
                    + accountID + " INTEGER" + "," + accountTransactionDate + " TEXT NOT NULL ," + " FOREIGN KEY(" + accountID + ")" +
                    " REFERENCES " + tblAccount + "(" + accountID + "),"
                    + " FOREIGN KEY(" + expenseHistoryID + ") REFERENCES " + tblExpensesHistory + "(" + expenseHistoryID + "))";

    private static final String CREATE_TABLE_USER =
            "create table " + tblUser + "(" + userID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + userLoginName + " TEXT NOT NULL" + "," + userPassword + " TEXT NOT NULL" + "," + userEmail + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_EXPENSE_HISTORY =
            "create table " + tblExpensesHistory + "(" + expenseHistoryID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ","
            + userID + " INTEGER" + "," + expenseDate + " TEXT NOT NULL" + "," + expenseTotalCost + " INTEGER ,"
                    + " FOREIGN KEY(" + userID + ") REFERENCES " + tblUser + "(" + userID + "))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mIsCreatedDatabase = false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!mIsCreatedDatabase) {
            try {
                db.execSQL(CREATE_TABLE_PRODUCT_GROUP);
                db.execSQL(CREATE_TABLE_PRODUCT);
                db.execSQL(CREATE_TABLE_PRODUCT_DETAIL);
                db.execSQL(CREATE_TABLE_USER);
                db.execSQL(CREATE_TABLE_ACCOUNT);
                db.execSQL(CREATE_TABLE_ACCOUNT_DETAIL);
                db.execSQL(CREATE_TABLE_EXPENSE_HISTORY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mIsCreatedDatabase = true;
        }

        if (mReadableDatabase == null) {
            mReadableDatabase = this.getReadableDatabase();
        }
        if (mWritableDatabase == null) {
            mWritableDatabase = this.getWritableDatabase();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + tblProduct);
        db.execSQL("DROP TABLE IF EXISTS " + tblProductGroup);
        db.execSQL("DROP TABLE IF EXISTS " + tblProductDetail);
        db.execSQL("DROP TABLE IF EXISTS " + tblAccount);
        db.execSQL("DROP TABLE IF EXISTS " + tblAccountDetail);
        db.execSQL("DROP TABLE IF EXISTS " + tblUser);
        db.execSQL("DROP TABLE IF EXISTS " + tblExpensesHistory);

        // Recreate
        onCreate(db);
    }
}
