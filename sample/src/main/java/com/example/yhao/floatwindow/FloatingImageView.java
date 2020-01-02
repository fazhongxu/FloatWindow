package com.example.yhao.floatwindow;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义悬浮的ImageView
 *
 * @author xxl.
 * @date 2020/01/02.
 */
public class FloatingImageView extends AppCompatImageView {

    //region:成员变量

    private List<ConversationEntity> mConversationEntities = new ArrayList<>();

    //endregion

    //region: 构造函数

    public FloatingImageView(Context context) {
        super(context);
    }

    public FloatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //endregion

    //region: get or set

    /**
     * 设置会话实体
     *
     * @param conversationEntity
     */
    public void addConversationEntity(ConversationEntity conversationEntity) {
        if (mConversationEntities.contains(conversationEntity)) {
            return;
        }
        mConversationEntities.add(conversationEntity);
    }

    /**
     * 获取会话实体
     *
     * @return
     */
    public List<ConversationEntity> getConversationEntities() {
        return mConversationEntities;
    }
    //endregion
}
