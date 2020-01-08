package com.example.yhao.floatwindow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.yhao.fixedfloatwindow.R;
import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator;
import com.lzf.easyfloat.anim.DefaultAnimator;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import com.lzf.easyfloat.interfaces.OnInvokeView;

import java.util.Random;

public class A_Activity extends BaseActivity {

    private static final String TAG = "aaa";

    private static final String FLOATING_TAG = "floating";
    private FloatingCreateView mFloatingCreateView;
    private int mTranslationX;
    private int mScreenWidth;
    private ViewGroup.LayoutParams mLayoutParams;
    private int mScreenHeight;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle("A");

        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();

        mFloatingCreateView = new FloatingCreateView(this);
        decorView.addView(mFloatingCreateView);
        mFloatingCreateView.setVisibility(View.VISIBLE);
        mFloatingCreateView.setTranslation(350, 350);

        RelativeLayout rlTest = findViewById(R.id.rl_test);
        mScreenWidth = Util.getScreenWidth(this);
        mScreenHeight = Util.getScreenHeight(this);

        rlTest.setOnTouchListener(new View.OnTouchListener() {
            boolean isNeedCreateFloat = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        mTranslationX = (int) event.getX();
                        float value = (mTranslationX * 1.0F / mScreenWidth) * 100F;
                        Log.e(TAG, "onTouch:---- " + value);
//                        mFloatingCreateView.setTranslation(-moveX,-moveX);
                        mFloatingCreateView.setTranslation(100 - value, 100 - value);
                        if (value >= 75) {
                            isNeedCreateFloat = true;
                            mFloatingCreateView.setBackgroundSelected();
                        }else {
                            mFloatingCreateView.setBackgroundNormal();
                            isNeedCreateFloat = false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isNeedCreateFloat) {
                            create();
                        }
                        mFloatingCreateView.setTranslation(350, 350);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void change(View view) {
        startActivity(new Intent(this, B_Activity.class));
        finish();
    }

    public void create(View view) {
        create();
    }

    private void create() {
        View appFloatView = EasyFloat.getAppFloatView();
        if (appFloatView == null) {
            EasyFloat.with(this)
                    // 设置浮窗xml布局文件，并可设置详细信息
                    .setLayout(R.layout.layout_esay_float, new OnInvokeView() {
                        @Override
                        public void invoke(View view) {
                            Log.e(TAG, "invoke: " + view);
                            FloatingImageView floatingImageView = view.findViewById(R.id.iv_floating);
                            ConversationEntity conversationEntity = ConversationEntity.obtain("https://avatars1.githubusercontent.com/u/24353536?s=460&v=4", 1);
                            floatingImageView.addConversationEntity(conversationEntity);
                            floatingImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getApplicationContext(), D_Activity.class));
                                }
                            });
                        }
                    })
                    // 设置浮窗显示类型，默认只在当前Activity显示，可选一直显示、仅前台显示、仅后台显示
                    .setShowPattern(ShowPattern.FOREGROUND)
                    // 设置吸附方式，共15种模式，详情参考SidePattern
                    .setSidePattern(SidePattern.RESULT_HORIZONTAL)
                    // 设置浮窗的标签，用于区分多个浮窗
//                .setTag("testFloat")
                    // 设置浮窗是否可拖拽
                    .setDragEnable(true)
                    // 系统浮窗是否包含EditText，仅针对系统浮窗，默认不包含
                    .hasEditText(false)
                    // 设置浮窗固定坐标，ps：设置固定坐标，Gravity属性和offset属性将无效
//                .setLocation(100, 200)
                    // 设置浮窗的对齐方式和坐标偏移量
//                .setGravity(Gravity.BOTTOM, mScreenWidth-100, mScreenHeight -800)
                    .setGravity(Gravity.RIGHT, 0, mScreenHeight - 800)
                    // 设置宽高是否充满父布局，直接在xml设置match_parent属性无效
                    .setMatchParent(false, false)
                    // 设置Activity浮窗的出入动画，可自定义，实现相应接口即可（策略模式），无需动画直接设置为null
                    .setAnimator(new DefaultAnimator())
                    // 设置系统浮窗的出入动画，使用同上
                    .setAppFloatAnimator(new AppFloatDefaultAnimator())
                    // 设置系统浮窗的不需要显示的页面
                    .setFilter(B_Activity.class, E_Activity.class)
                    .registerCallbacks(new OnFloatCallbacks() {
                        @Override
                        public void createdResult(boolean b, String s, View view) {
                            Log.e(TAG, "createdResult: " + b + "--" + s + "v" + view);
                        }

                        @Override
                        public void show(View view) {
                            Log.e(TAG, "show: ");
                        }

                        @Override
                        public void hide(View view) {
                            Log.e(TAG, "hide: " + view);
                        }

                        @Override
                        public void dismiss() {
                            Log.e(TAG, "dismiss: ");
                        }

                        @Override
                        public void touchEvent(View view, MotionEvent motionEvent) {
                            Log.e(TAG, "touchEvent: ");
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                deleteIfNecessary();
                                hideFloatingDeleteView();
                            }
                        }

                        @Override
                        public void drag(View view, MotionEvent motionEvent) {
                            Log.e(TAG, "drag: " + motionEvent.getRawX() + "--" + motionEvent.getY());

                            showFloatingDeleteView((int) motionEvent.getRawX(), (int) motionEvent.getRawY() - view.getHeight());

                        }

                        @Override
                        public void dragEnd(View view) {
                            Log.e(TAG, "dragEnd: ");
                        }
                    })
                    .show();
        }else {
            FloatingImageView floatingImageView = appFloatView.findViewById(R.id.iv_floating);
            floatingImageView.addConversationEntity(ConversationEntity.obtain("xxx", new Random().nextInt(20)));
            EasyFloat.showAppFloat();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}


