package com.huy.monthlyfinance.Model;


/**
 * Created by huy nguyen on 9/16/2016.
 */

public class AccountDetail extends BaseDTO {
    private String mAccountDetailID;
    private String mAccountID;
    private double mInitialBalance;
    private double mCurrentBalance;
    private String mTransactionDate;

    public AccountDetail() {
    }

    public AccountDetail(String AccountDetailID, String AccountID, double InitialBalance, double CurrentBalance, String TransactionDate) {
        this.mAccountDetailID = AccountDetailID;
        this.mAccountID = AccountID;
        this.mInitialBalance = InitialBalance;
        this.mCurrentBalance = CurrentBalance;
        this.mTransactionDate = TransactionDate;
    }

    public String getAccountDetailID() {
        return mAccountDetailID;
    }

    public void setAccountDetailID(String mAccountDetailID) {
        this.mAccountDetailID = mAccountDetailID;
    }

    public String getAccountID() {
        return mAccountID;
    }

    public void setAccountID(String mAccountID) {
        this.mAccountID = mAccountID;
    }

    public double getInitialBalance() {
        return mInitialBalance;
    }

    public void setInitialBalance(double mInitialBalance) {
        this.mInitialBalance = mInitialBalance;
    }

    public double getCurrentBalance() {
        return mCurrentBalance;
    }

    public void setCurrentBalance(double mCurrentBalance) {
        this.mCurrentBalance = mCurrentBalance;
    }

    public String getTransactionDate() {
        return mTransactionDate;
    }

    public void setTransactionDate(String mTransactionDate) {
        this.mTransactionDate = mTransactionDate;
    }

}
