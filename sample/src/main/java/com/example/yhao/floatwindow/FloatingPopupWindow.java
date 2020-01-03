package com.example.yhao.floatwindow;

import android.app.Activity;
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
 * @author xxl.
 * @date 2019/11/21.
 */
public class FloatingPopupWindow extends PopupWindow {

    private Activity mActivity;

    public FloatingPopupWindow(Activity activity) {
        super(WRAP_CONTENT, WRAP_CONTENT);
        mActivity = activity;
        setTouchable(true);
        setOutsideTouchable(true);
        setUpLayout();
    }

    private void setUpLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(R.layout.ui_user_popup, null);
        RelativeLayout rlRoot = view.findViewById(R.id.ll_root);
        rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);
    }

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

}
