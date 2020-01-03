package com.example.yhao.floatwindow;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.yhao.fixedfloatwindow.R;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;

import java.util.List;

public class A_Activity extends BaseActivity {

    private static final String TAG = "aaa";

    private static final String FLOATING_TAG = "floating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle("A");


        final FloatingImageView floatingImageView = new FloatingImageView(getApplicationContext());
        floatingImageView.setImageResource(R.drawable.icon);
        ConversationEntity conversationEntity = ConversationEntity.obtain("https://avatars1.githubusercontent.com/u/24353536?s=460&v=4", 1);
        floatingImageView.addConversationEntity(conversationEntity);

        IFloatWindow iFloatWindow = FloatWindow.get();
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
        }


        floatingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ConversationEntity> conversationEntities = floatingImageView.getConversationEntities();
//                Toast.makeText(A_Activity.this, "onClick"+conversationEntities.size(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),D_Activity.class));
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
        public void onPositionUpdate(boolean isClick,int x, int y) {
            Log.d(TAG, "onPositionUpdate: isClick="+isClick +"--x="+ x + " y=" + y);
            if (!isClick) {
                IFloatWindow iFloatWindow = FloatWindow.get();
                showFloatingDeleteView();
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
            if (floatingImageView== null) {
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
    }
}
