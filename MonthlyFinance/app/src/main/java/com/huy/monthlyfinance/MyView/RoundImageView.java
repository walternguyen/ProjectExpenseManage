package com.huy.monthlyfinance.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Phuong on 20/08/2016.
 */
public class RoundImageView extends ImageView {
    private int mColor;
    private static Bitmap mBitmap;
    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.background});
        mColor = typedArray.getColor(0, Color.TRANSPARENT);
    }

    public void setColor(int Color) {
        this.mColor = Color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getDrawable() == null || getWidth() == 0 || getHeight() == 0) return;
        if (mBitmap == null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
            Bitmap drawable = bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
            mBitmap = RoundImageView.getRoundedBitmap(drawable, (getWidth() >= getHeight()) ? getHeight() : getWidth());
        }
        canvas.drawColor(mColor);
        canvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2, (getHeight() - mBitmap.getHeight()) / 2, null);
    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    public static Bitmap getRoundedBitmap(Bitmap src, int radius) {
        Bitmap roundBitmap;
        roundBitmap = (src.getWidth() == radius || src.getHeight() == radius) ? src : Bitmap.createScaledBitmap(src, radius, radius, false);
        Bitmap des = Bitmap.createBitmap(roundBitmap.getWidth(), roundBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(des);
        final Rect rect = new Rect(0, 0, des.getWidth(), des.getHeight());

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(Color.parseColor("#BAB399"));

        canvas.drawCircle(des.getWidth() / 2 + 0.7f, des.getHeight() / 2 + 0.7f, des.getWidth() / 2 - 10f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(roundBitmap, rect, rect, paint);

        return des;
    }
}
