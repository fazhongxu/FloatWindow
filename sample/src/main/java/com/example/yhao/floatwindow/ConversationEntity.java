package com.example.yhao.floatwindow;

import java.io.Serializable;

/**
 * 会话实体类
 *
 * @author xxl.
 * @date 2020/01/02.
 */
public class ConversationEntity implements Serializable {

    //region: 成员变量

    /**
     * 会话头像
     */
    private String avatar;

    /**
     * 会话类型
     */
    private int conversationType;

    //endregion

    //region: 构造函数

    private ConversationEntity(String avatar, int conversationType) {
        this.avatar = avatar;
        this.conversationType = conversationType;
    }

    public static ConversationEntity obtain(String avatar,
                                     int conversationType) {
        return new ConversationEntity(avatar, conversationType);
    }

    //endregion

    //region: get or set

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getConversationType() {
        return conversationType;
    }

    public void setConversationType(int conversationType) {
        this.conversationType = conversationType;
    }
    //endregion

}
