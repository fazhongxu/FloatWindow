package com.example.yhao.floatwindow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yhao.fixedfloatwindow.R;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;

import java.util.List;

/**
 * @author xxl.
 * @date 2020/01/03.
 */
public class D_Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        setTitle("D");

        RecyclerView recyclerView = findViewById(R.id.v_recycler);
        View vBackground = findViewById(R.id.v_background);
        vBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Adapter adapter = new Adapter();

        IFloatWindow iFloatWindow = FloatWindow.get();
        if (iFloatWindow == null) {
            finish();
            return;
        }
        FloatingImageView floatingImageView = (FloatingImageView) iFloatWindow.getView();
        if (floatingImageView == null) {
            finish();
            return;
        }
        List<ConversationEntity> conversationEntities = floatingImageView.getConversationEntities();
        adapter.setConversationEntities(conversationEntities);
        recyclerView.setAdapter(adapter);
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        public List<ConversationEntity> mConversationEntities;

        public void setConversationEntities(List<ConversationEntity> conversationEntities) {
            mConversationEntities = conversationEntities;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflater.inflate(R.layout.recycler_item_conversation, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder,
                                     final int position) {
            holder.mType.setText("会话类型" + mConversationEntities.get(position).getConversationType());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(D_Activity.this, "Click" + holder.mType.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IFloatWindow floatWindow = FloatWindow.get();
                    if (floatWindow == null) {
                        return;
                    }
                    FloatingImageView floatingImageView = (FloatingImageView) floatWindow.getView();
                    if (floatingImageView == null) {
                        return;
                    }
                    floatingImageView.removeConversationEntity(mConversationEntities.get(position));
                    notifyDataSetChanged();
                    if (getItemCount() == 0) {
                        finish();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mConversationEntities.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            TextView mType;
            ImageView mIvDelete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mType = itemView.findViewById(R.id.tv_type);
                mIvDelete = itemView.findViewById(R.id.iv_delete);
            }
        }
    }
}
