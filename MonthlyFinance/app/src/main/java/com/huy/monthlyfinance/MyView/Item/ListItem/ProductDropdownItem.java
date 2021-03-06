package com.huy.monthlyfinance.MyView.Item.ListItem;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.huy.monthlyfinance.Model.Product;
import com.huy.monthlyfinance.R;
import com.huy.monthlyfinance.SupportUtils.SupportUtils;

/**
 * Created by Phuong on 01/10/2016.
 */

public class ProductDropdownItem extends BaseItem {
    private Bitmap mBitmap;
    private Product mProduct;
    private boolean isFocused;
    private ImageButton mIconCheck;

    public ProductDropdownItem(Bitmap mBitmap, Product mProduct, boolean isFocused) {
        this.mBitmap = mBitmap;
        this.mProduct = mProduct;
        this.isFocused = isFocused;
    }

    public void setListener(OnSelectListener mListener) {
        this.mListener = mListener;
    }

    public interface OnSelectListener {
        void onSelect(ProductDropdownItem item);
    }

    private OnSelectListener mListener;

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Product getProduct() {
        return mProduct;
    }

    @Override
    public void setView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageIcon);
        imageView.setImageBitmap(mBitmap);
        TextView textView = (TextView) view.findViewById(R.id.textName);
        textView.setText(SupportUtils.getDeviceLanguage().toLowerCase().contains("en")?
                mProduct.getProductNameEN() : mProduct.getProductNameVI());
        mIconCheck = (ImageButton) view.findViewById(R.id.iconCheck);
        mIconCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFocused) {
                    mIconCheck.setVisibility(View.VISIBLE);
                    isFocused = true;

                    if (mListener != null) {
                        mListener.onSelect(getThis());
                    }
                }
            }
        });
        mIconCheck.setVisibility(isFocused ? View.VISIBLE : View.GONE);
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
        if (mIconCheck != null) {
            mIconCheck.setVisibility(isFocused ? View.VISIBLE : View.GONE);
        }
    }

    public boolean isFocused() {
        return isFocused;
    }

    public ProductDropdownItem getThis() {
        return this;
    }
}
