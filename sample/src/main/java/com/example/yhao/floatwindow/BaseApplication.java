package com.example.yhao.floatwindow;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lzf.easyfloat.EasyFloat;

import java.util.LinkedList;

/**
 * Created by yhao on 2017/12/18.
 * https://github.com/yhaolpz
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    static LinkedList<Activity> mActivities = new LinkedList<>();


    public static Activity getTopActivity() {
        return mActivities.getLast();
    }

    private static final String TAG = "FloatWindow";

    @Override
    public void onCreate() {
        super.onCreate();
        BlurUtil.init(this);

        registerActivityLifecycleCallbacks(this);

        EasyFloat.init(this, true);

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        mActivities.addLast(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivities.remove(activity);
    }
//
//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setImageResource(R.drawable.icon);
//
//        FloatWindow
//                .with(getApplicationContext())
//                .setView(imageView)
//                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
//                .setHeight(Screen.width, 0.2f)
//                .setX(Screen.width, 0.8f)
//                .setY(Screen.height, 0.3f)
//                .setMoveType(MoveType.slide,0,0)
//                .setMoveStyle(500, new BounceInterpolator())
//                .setFilter(true, A_Activity.class, C_Activity.class)
//                .setViewStateListener(mViewStateListener)
//                .setPermissionListener(mPermissionListener)
//                .setDesktopShow(true)
//                .build();
//
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(BaseApplication.this, "onClick", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private PermissionListener mPermissionListener = new PermissionListener() {
//        @Override
//        public void onSuccess() {
//            Log.d(TAG, "onSuccess");
//        }
//
//        @Override
//        public void onFail() {
//            Log.d(TAG, "onFail");
//        }
//    };
//
//    private ViewStateListener mViewStateListener = new ViewStateListener() {
//        @Override
//        public void onPositionUpdate(int x, int y) {
//            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
//        }
//
//        @Override
//        public void onShow() {
//            Log.d(TAG, "onShow");
//        }
//
//        @Override
//        public void onHide() {
//            Log.d(TAG, "onHide");
//        }
//
//        @Override
//        public void onDismiss() {
//            Log.d(TAG, "onDismiss");
//        }
//
//        @Override
//        public void onMoveAnimStart() {
//            Log.d(TAG, "onMoveAnimStart");
//        }
//
//        @Override
//        public void onMoveAnimEnd() {
//            Log.d(TAG, "onMoveAnimEnd");
//        }
//
//        @Override
//        public void onBackToDesktop() {
//            Log.d(TAG, "onBackToDesktop");
//        }
//    };
}
