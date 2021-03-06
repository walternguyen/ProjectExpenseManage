package com.huy.monthlyfinance.SupportUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Phuong on 25/08/2016.
 */
public class SupportUtils {
    private static final boolean IS_LOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    public static final int MIN_CURRENCY = 1000;

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int desiredWidth, resultHeight = 0;
        ViewGroup.LayoutParams params;
        View view = null;
        if (listAdapter == null) {
            return;
        }
        desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            resultHeight += view.getMeasuredHeight();
        }
        params = listView.getLayoutParams();
        params.height = resultHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static void setGridViewHeight(GridView gridView, int columns, int itemHeight) {
        ListAdapter listAdapter = gridView.getAdapter();
        int resultHeight = 0;
        ViewGroup.LayoutParams params;
        if (listAdapter == null) {
            return;
        }
        for (int i = 0; i < listAdapter.getCount(); i += columns) {
            resultHeight += itemHeight;
        }
        params = gridView.getLayoutParams();
        params.height = resultHeight + listAdapter.getCount();
        gridView.setLayoutParams(params);
        gridView.requestLayout();
    }

    public static String formatDate(Date date, String format) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date milliSec2Date(long milliSec) {
        return new Date(milliSec);
    }

    public static boolean checkLollipopOrAbove() {
        return IS_LOLLIPOP;
    }

    public static String getPath(Uri uri, Context context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                return cursor.getString(index);
            }
            cursor.close();
        }
        return uri.getPath();
    }

    public static float dip2Pixel(Context context, int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getCountryCode() {
        return Locale.getDefault().getCountry();
    }

    public static String getStringLocalized(Context context, String local, int id) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale savedLocale =
                new Locale(configuration.locale.getLanguage(), configuration.locale.getCountry(), configuration.locale.getVariant());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(new Locale(local));
        } else {
            configuration.locale = new Locale(local);
        }
        resources.updateConfiguration(configuration, null);

        String string = resources.getString(id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(savedLocale);
        } else {
            configuration.locale = savedLocale;
        }
        resources.updateConfiguration(configuration, null);
        return string;
    }

    public static String getNormalDoubleString(double number, String formatString) {
        DecimalFormat format = new DecimalFormat(formatString);
        return format.format(number);
    }

    public static String formatDouble(double d, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(d);
    }

    public static String unicode2NonUnicode(String unicodeString) {
        return Normalizer.normalize(unicodeString, Normalizer.Form.NFD);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
