package com.belichenko.a.messa.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class ViewUtil {

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null) return;
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity, Window window) {
        if (activity == null) return;
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }

    public static void hideKeyboard(Context context, EditText editText) {
        if (context == null || editText == null) return;
        if (((Activity) context).getCurrentFocus() != null
                && ((Activity) context).getCurrentFocus() instanceof EditText) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

}
