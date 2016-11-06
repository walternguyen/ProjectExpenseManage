package com.huy.monthlyfinance.Database.DAO;

import android.content.Context;

/**
 * Created by huy nguyen on 9/18/2016.
 */
public class AccountDAO extends BaseDAO{
    private AccountDAO(Context context) {
        super(context);
    }

    private static AccountDAO mInstance;

    public static AccountDAO getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AccountDAO(context);
        }
        return mInstance;
    }
}
