package com.example.yhao.floatwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yhao.fixedfloatwindow.R;

/**
 * 悬浮窗创建的View
 *
 * @author xxl.
 * @date 2020/01/07.
 */
public class FloatingCreateView extends RelativeLayout {
    private RelativeLayout mRlFloatingBg;

    //region: 构造函数

    public FloatingCreateView(Context context) {
        this(context, null);
    }

    public FloatingCreateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingCreateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.ui_create_floating_view, this);
        mRlFloatingBg = findViewById(R.id.rl_floating_create_bg);
    }

    //endregion

    //region: 提供方法

    public void setTranslation(float translationX,float translationY) {
        mRlFloatingBg.setTranslationX(translationX);
        mRlFloatingBg.setTranslationY(translationY);
    }


    public void setBackgroundNormal() {
        mRlFloatingBg.setBackgroundResource(R.drawable.shape_floating_create_normal);
    }
    public void setBackgroundSelected() {
        mRlFloatingBg.setBackgroundResource(R.drawable.shape_floating_create_selected);
    }


    //endregion

}
