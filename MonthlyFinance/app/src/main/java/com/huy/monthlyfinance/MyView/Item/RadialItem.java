package com.huy.monthlyfinance.MyView.Item;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.huy.monthlyfinance.MyView.BaseItem;
import com.huy.monthlyfinance.R;

/**
 * Created by Phuong on 11/09/2016.
 */
public class RadialItem extends BaseItem {
    private Bitmap mImage;
    private String mText;
    private OnClickListener mListener;
    public interface OnClickListener {
        void onClick(String data);
        void onLongClick(String data);
    }

    public RadialItem(OnClickListener Listener, String Text, Bitmap Image) {
        this.mImage = Image;
        this.mText = Text;
        this.mListener = Listener;
    }

    @Override
    public void setView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageOption);
        imageView.setImageBitmap(mImage);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(mText);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onLongClick(mText);
                return false;
            }
        });
    }
}
