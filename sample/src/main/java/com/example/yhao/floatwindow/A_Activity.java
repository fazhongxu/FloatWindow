package com.example.yhao.floatwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

import androidx.customview.widget.ViewDragHelper;

import com.example.yhao.fixedfloatwindow.R;
import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator;
import com.lzf.easyfloat.anim.DefaultAnimator;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.ViewStateListener;

import java.util.List;

public class A_Activity extends BaseActivity {

    private static final String TAG = "aaa";

    private static final String FLOATING_TAG = "floating";
    private FloatingCreateView mFloatingCreateView;
    private int mTranslationX;
    private int mScreenWidth;
    private ViewGroup.LayoutParams mLayoutParams;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle("A");
//        create();

        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();

        mFloatingCreateView = new FloatingCreateView(this);
        decorView.addView(mFloatingCreateView);

//        mFloatingCreateView = findViewById(R.id.fcv_floating_create_view);
//        mFloatingCreateView.setVisibility(View.VISIBLE);
        mFloatingCreateView.setVisibility(View.VISIBLE);

        mFloatingCreateView.setTranslation(350, 350);


        RelativeLayout rlTest = findViewById(R.id.rl_test);
        mScreenWidth = Util.getScreenWidth(this);
        rlTest.setOnTouchListener(new View.OnTouchListener() {

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
                            mFloatingCreateView.setBackgroundSelected();
                        }else {
                            mFloatingCreateView.setBackgroundNormal();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mFloatingCreateView.setTranslation(350, 350);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(boolean isClick, int x, int y) {
            Log.d(TAG, "onPositionUpdate: isClick=" + isClick + "--x=" + x + " y=" + y);
            if (!isClick) {
                IFloatWindow iFloatWindow = FloatWindow.get();
                final FloatingImageView floatingImageView = (FloatingImageView) iFloatWindow.getView();
                try {
                    showFloatingDeleteView(x, y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //startActivity(new Intent(getApplicationContext(), D_Activity.class));
            }
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
            IFloatWindow iFloatWindow = FloatWindow.get();
            if (iFloatWindow == null) {
                return;
            }
            FloatingImageView floatingImageView = (FloatingImageView) iFloatWindow.getView();
            if (floatingImageView == null) {
                return;
            }
            List<ConversationEntity> conversationEntities = floatingImageView.getConversationEntities();
            if (conversationEntities == null || conversationEntities.size() == 0) {
                iFloatWindow.hide();
            }
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
            deleteIfNecessary();
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
            hideFloatingDeleteView();
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };

    public void change(View view) {
        startActivity(new Intent(this, B_Activity.class));
        finish();
    }

    public void create(View view) {
        create();
    }

    private void create() {
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
                .setGravity(Gravity.RIGHT, 0, 200)
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
//                        hideFloatingDeleteView();
//                        deleteIfNecessary();
                    }
                })
                .show();


    /*private void create() {
        final FloatingImageView floatingImageView = new FloatingImageView(getApplicationContext());
        floatingImageView.setImageResource(R.drawable.icon);
        ConversationEntity conversationEntity = ConversationEntity.obtain("https://avatars1.githubusercontent.com/u/24353536?s=460&v=4", 1);
        floatingImageView.addConversationEntity(conversationEntity);
        IFloatWindow iFloatWindow = FloatWindow.get();
        floatingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), D_Activity.class));
            }
        });
        if (iFloatWindow == null) {
            FloatWindow
                    .with(getApplicationContext())
                    .setView(floatingImageView)
                    .setWidth(Screen.width, 0.13f)
                    .setHeight(Screen.width, 0.13f)
                    .setX(Screen.width, 0.87f)
                    .setY(Screen.height, 0.3f)
                    .setMoveType(MoveType.slide, 0, 0)
                    .setMoveStyle(220, new AccelerateInterpolator())
                    .setFilter(true, A_Activity.class, C_Activity.class)
                    .setViewStateListener(mViewStateListener)
                    .setPermissionListener(mPermissionListener)
                    .setDesktopShow(false)
                    .build();
            FloatWindow.get().show();
        } else {
            iFloatWindow.updateX(Screen.width, 0.87f);
            iFloatWindow.updateY(Screen.height, 0.3f);
            iFloatWindow.setDestroyTag(false);
            iFloatWindow.show();
        }


    }
*/
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.e(TAG, "onDestroy: ");
//    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}


