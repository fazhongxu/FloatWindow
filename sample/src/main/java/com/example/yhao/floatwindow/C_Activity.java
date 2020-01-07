package com.example.yhao.floatwindow;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yhao.fixedfloatwindow.R;
import com.lzf.easyfloat.EasyFloat;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;

import java.util.List;
import java.util.Random;

public class C_Activity extends BaseActivity {

    private IFloatWindow mIFloatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        setTitle("C");

        mIFloatWindow = FloatWindow.get();
    }

    public void back(View view) {
        finish();
    }

    public void add(View view) {
        /*if (mIFloatWindow != null) {
            FloatingImageView floatingImageView = (FloatingImageView) mIFloatWindow.getView();
            Random random = new Random();
            int conversationType = random.nextInt(100);
            ConversationEntity conversationEntity = ConversationEntity.obtain("https://avatars1.githubusercontent.com/u/24353536?s=460&v=4", conversationType);
            if (floatingImageView == null) {
                return;
            }
            floatingImageView.addConversationEntity(conversationEntity);
            mIFloatWindow.show();
            Toast.makeText(this, "添加成功"+floatingImageView.getConversationEntities().size(), Toast.LENGTH_SHORT).show();
        }*/
        View v = EasyFloat.getAppFloatView();
        FloatingImageView floatingImageView = v.findViewById(R.id.iv_floating);
        if (floatingImageView != null) {
            Random random = new Random();
            int conversationType = random.nextInt(100);
            ConversationEntity conversationEntity = ConversationEntity.obtain("https://avatars1.githubusercontent.com/u/24353536?s=460&v=4", conversationType);
            floatingImageView.addConversationEntity(conversationEntity);
        }

    }

    public void get(View view) {
        View v = EasyFloat.getAppFloatView();
        if (v == null) {
            return;
        }
        FloatingImageView floatingImageView = v.findViewById(R.id.iv_floating);
        if (floatingImageView != null) {
            List<ConversationEntity> conversationEntities = floatingImageView.getConversationEntities();
            Toast.makeText(this, "个数"+conversationEntities.size(), Toast.LENGTH_SHORT).show();

        }
        /*if (mIFloatWindow != null) {
            FloatingImageView floatingImageView = (FloatingImageView) mIFloatWindow.getView();
            if (floatingImageView == null) {
                return;
            }
            List<ConversationEntity> conversationEntities = floatingImageView.getConversationEntities();
            Toast.makeText(this, "个数"+conversationEntities.size(), Toast.LENGTH_SHORT).show();
        }*/

    }


}
