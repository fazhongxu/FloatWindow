package com.example.yhao.floatwindow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yhao.fixedfloatwindow.R;

/**
 * @author xxl.
 * @date 2020/01/03.
 */
public class E_Activity extends BaseActivity {

    public static final String CONVERSATION_INFO = "conversation_info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e);
        setTitle("E");
        Intent intent = getIntent();
        ConversationEntity conversationEntity = (ConversationEntity) intent.getSerializableExtra(CONVERSATION_INFO);
        if (conversationEntity == null) {
            finish();
            return;
        }
        TextView tvChatInfo = findViewById(R.id.tv_chat_info);
        tvChatInfo.setText("会话头像："+conversationEntity.getAvatar()+"\n会话类型："+conversationEntity.getConversationType());
    }
}
