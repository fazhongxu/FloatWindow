package com.example.yhao.floatwindow;

import android.app.Activity;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.yhao.fixedfloatwindow.R;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

/**
 * 悬浮窗弹窗
 *
 * @author xxl.
 * @date 2019/11/21.
 */
public class FloatingPopupWindow extends PopupWindow {

    //region: 成员变量

    private Activity mActivity;

    private View mRlDeleteArea;

    //endregion

    //region: 构造函数

    public FloatingPopupWindow(Activity activity) {
        super(WRAP_CONTENT, WRAP_CONTENT);
        mActivity = activity;
        setTouchable(true);
        setOutsideTouchable(true);
        setUpLayout();
    }

    //endregion

    //region: 页面视图渲染

    private void setUpLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(R.layout.ui_user_popup, null);
        mRlDeleteArea = view.findViewById(R.id.rl_floating_delete);
        setContentView(view);
    }
    //endregion

    //region: 提供方法

    public void show() {
        if (mActivity == null) {
            return;
        }
        if (!(mActivity).isFinishing()) {
            Window window = mActivity.getWindow();
            View decorView = window.getDecorView();
            showAtLocation(decorView, Gravity.CENTER, 0, 0);
        }
    }

    /**
     * 拖拽的悬浮窗 是否经过删除弹窗区域
     *
     * @param x 拖拽的悬浮窗 x坐标
     * @param y 拖拽的悬浮窗 y坐标
     */
    public void contains(int x, int y) {
        if (mRlDeleteArea == null) {
            return;
        }
        Rect rect = new Rect(mRlDeleteArea.getLeft(), mRlDeleteArea.getTop(), mRlDeleteArea.getRight(), mRlDeleteArea.getBottom());
        boolean contains = rect.contains(x, y);
        if (contains) {
            mRlDeleteArea.setBackgroundResource(R.drawable.shape_floating_delete_confirm_bg);
        } else {
            mRlDeleteArea.setBackgroundResource(R.drawable.shape_floating_delete_bg);
        }

    }

    //endregion



}
