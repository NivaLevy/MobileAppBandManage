package com.example.nivalevi.mobileappbandmanage.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import com.example.nivalevi.mobileappbandmanage.R;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class ScreenViewsUtils {
    public static float dpFromPx(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int pxFromDp(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
        //int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, <HEIGHT>, getResources().getDisplayMetrics());
    }

    @TargetApi(19)
    public static void setTranslucentStatus(boolean on, Activity activity) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public static void setActionBarViewForActionBarActivity(Toolbar toolbar, AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ScreenViewsUtils.setTranslucentStatus(true, activity);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        if (Build.BRAND.compareTo("Amazon") != 0) {
            tintManager.setStatusBarTintEnabled(true);
            //tintManager.setStatusBarAlpha(.1f);
            tintManager.setStatusBarTintResource(R.color.bandmanage_blue_color);
        }

        toolbar.setBackgroundColor(activity.getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(activity.getResources().getColor(R.color.bandmanage_blue_color));
        toolbar.setSubtitleTextColor(activity.getResources().getColor(R.color.bandmanage_blue_color));

        activity.setSupportActionBar(toolbar);

        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setIcon(R.drawable.bm_heart);

    }

}
