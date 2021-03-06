package com.huy.monthlyfinance.MyView.Item.ListItem;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huy.monthlyfinance.Model.Product;
import com.huy.monthlyfinance.R;
import com.huy.monthlyfinance.SupportUtils.SupportUtils;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Phuong on 16/12/2016.
 */

public class BoughtProduct_1 extends BaseItem {
    private Bitmap mBitmap;
    private Product mItem;
    private String mGroup;
    private boolean isFocused;
    private static final int[] mCircleDrawables = {R.drawable.circle_blue_1, R.drawable.circle_blue_2, R.drawable.circle_dark_blue,
            R.drawable.circle_dark_gray_1, R.drawable.circle_dark_gray_2, R.drawable.circle_light_green_1, R.drawable.circle_light_green_2,
            R.drawable.circle_dark_red, R.drawable.circle_orange, R.drawable.circle_pink_1, R.drawable.circle_gray};

    private static HashMap<String, Integer> mMapRes = new HashMap<>();

    public BoughtProduct_1(Bitmap bitmap, Product item, String group, boolean isFocused) {
        this.mBitmap = bitmap;
        this.mItem = item;
        this.mGroup = group;
        this.isFocused = isFocused;
    }

    @Override
    public void setView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imgIcon);
        imageView.setImageBitmap(mBitmap);

        String productId = this.mItem.getProductID();
        int resId = -1;
        if (!mMapRes.containsKey(productId)) {
            if (!mMapRes.containsKey(productId)) {
                int max = mCircleDrawables.length - 1;
                Random random = new Random();
                resId = mCircleDrawables[random.nextInt(max)];
                mMapRes.put(productId, resId);
            }
        } else {
            resId = mMapRes.get(productId);
        }
        imageView.setBackgroundResource(resId);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtGroup = (TextView) view.findViewById(R.id.txtGroup);
        txtName.setText(SupportUtils.getCountryCode().toLowerCase().contains("us") ? mItem.getProductNameEN() : mItem.getProductNameVI());
        txtGroup.setText(mGroup);
        final ImageView imageView1 = (ImageView) view.findViewById(R.id.iconCheck);
        imageView1.setVisibility(isFocused ? View.VISIBLE : View.GONE);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFocused = !isFocused;
                imageView1.setVisibility(isFocused ? View.VISIBLE : View.GONE);
            }
        });
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public Product getItem() {
        return mItem;
    }
}
