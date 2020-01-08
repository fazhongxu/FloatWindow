package com.example.yhao.floatwindow;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yhao.fixedfloatwindow.R;
import com.lzf.easyfloat.EasyFloat;

import java.util.List;
import java.util.Random;

public class C_Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        setTitle("C");

    }

    public void back(View view) {
        finish();
    }

    public void add(View view) {
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

    }


}
