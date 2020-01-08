package com.example.yhao.floatwindow;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lzf.easyfloat.EasyFloat;

public class BaseActivity extends AppCompatActivity {

    /**
     * 悬浮窗删除View
     */
    private FloatingPopupWindow mFloatingPopupWindow;

    /**
     * 是否删除悬浮窗
     */
    private boolean mDeleteIfNecessary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 显示悬浮窗删除View
     *
     * @param x
     * @param y
     */
    protected void showFloatingDeleteView(int x, int y) {
        Activity topActivity = BaseApplication.getTopActivity();
        if (mFloatingPopupWindow == null || topActivity.isFinishing()) {
            mFloatingPopupWindow = new FloatingPopupWindow(topActivity);
        }
        mDeleteIfNecessary = mFloatingPopupWindow.contains(x, y);
        if (mFloatingPopupWindow.isShowing()) {
            return;
        }
        mFloatingPopupWindow.show();
    }

    /**
     * 隐藏删除悬浮窗的View
     */
    protected void hideFloatingDeleteView() {
        if (mFloatingPopupWindow != null) {
            mFloatingPopupWindow.dismiss();
            mFloatingPopupWindow = null;
        }
    }

    /**
     * 如果需要删除悬浮窗 则删除
     */
    protected void deleteIfNecessary() {
        if (mDeleteIfNecessary) {
            EasyFloat.hideAppFloat();
            EasyFloat.dismissAppFloat();
            mDeleteIfNecessary = false;
        }
    }
}
