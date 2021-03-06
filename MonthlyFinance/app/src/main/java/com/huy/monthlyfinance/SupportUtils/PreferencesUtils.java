package com.huy.monthlyfinance.SupportUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Phuong on 06/11/2016.
 */

public class PreferencesUtils {
    private static final String mPreferenceName = "app_preferences";
    private static SharedPreferences mPreferenceInstance;
    private static boolean isInitialized = false;

    public static final String CURRENT_EMAIL = "UserName";
    public static final String CURRENCY = "Currency";
    public static final String isInfoInitialized = "InfoInitialized";
    public static final String AVATAR = "Avatar";

    public static void init(Context context) {
        if (mPreferenceInstance == null) {
            mPreferenceInstance = context.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
            isInitialized = true;
        }
    }

    public static void setValue(String key, Object value) {
        SharedPreferences.Editor editor = mPreferenceInstance.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
            editor.apply();
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
            editor.apply();
        }
    }

    public static String getString(String key, String defValue) {
        if (mPreferenceInstance != null) {
            return mPreferenceInstance.getString(key, defValue);
        }
        return "";
    }

    public static boolean getBoolean(String key, boolean defValue) {
        if (mPreferenceInstance != null) {
            return mPreferenceInstance.getBoolean(key, defValue);
        }
        return false;
    }

    public static boolean isInitialized() {
        return isInitialized;
    }
}
