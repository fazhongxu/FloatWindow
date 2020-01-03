package com.yhao.floatwindow;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;

import androidx.annotation.RequiresApi;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by yhao on 2017/12/22.
 * https://github.com/yhaolpz
 */

class Util {


    static View inflate(Context applicationContext, int layoutId) {
        LayoutInflater inflate = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflate.inflate(layoutId, null);
    }

    private static Point sPoint;

    static int getScreenWidth(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.x;
    }

    static int getScreenHeight(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.y;
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    static int getHasVirtualScreenHeight(Context context) {
        int dpi = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            DisplayMetrics dm = new DisplayMetrics();
            @SuppressWarnings("rawtypes")
            Class c;
            try {
                c = Class.forName("android.view.Display");
                @SuppressWarnings("unchecked")
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, dm);
                dpi = dm.heightPixels;
            } catch (Exception e) {
                e.printStackTrace();
                dpi = getScreenHeight(context);
            }
        }
        return dpi;
    }

    static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    static boolean isViewVisible(View view) {
        return view.getGlobalVisibleRect(new Rect());
    }
}
