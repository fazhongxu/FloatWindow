package com.example.yhao.floatwindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;

import android.app.Activity;
import android.content.ComponentName;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yhao.fixedfloatwindow.R;

public class BaseActivity extends AppCompatActivity {

    /**
     * 悬浮窗删除View
     */
    private FloatingPopupWindow mFloatingPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 显示悬浮窗删除View
     * @param x
     * @param y
     */
    protected void showFloatingDeleteView(int x, int y) {
        Activity topActivity = BaseApplication.getTopActivity();
        if (mFloatingPopupWindow == null || topActivity.isFinishing()) {
            mFloatingPopupWindow = new FloatingPopupWindow(topActivity);
        }
        mFloatingPopupWindow.contains(x,y);
        if (mFloatingPopupWindow.isShowing()) {
            return;
        }
        mFloatingPopupWindow.show();
    }

    /**
     * 隐藏悬浮窗删除的View
     */
    protected void hideFloatingDeleteView() {
        if (mFloatingPopupWindow != null) {
            mFloatingPopupWindow.dismiss();
            mFloatingPopupWindow = null;
        }
    }
}
