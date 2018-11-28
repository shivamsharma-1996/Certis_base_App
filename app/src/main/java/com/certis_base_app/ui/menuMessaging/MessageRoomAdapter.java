package com.certis_base_app.ui.menuMessaging;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.List;

public class MessageRoomAdapter extends RecyclerView.Adapter {

    private List<String> messageRoomList;
    private View.OnClickListener onClickListener;
    public MessageRoomAdapter(View.OnClickListener onClickListener) {
        this.messageRoomList = messageRoomList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageRoomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_message_room, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageRoomHolder incidentCommentHolder = (MessageRoomHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setData(String commnet) {
        this.messageRoomList.add(commnet);
        notifyDataSetChanged();
    }

    public class MessageRoomHolder extends RecyclerView.ViewHolder{
        public MessageRoomHolder(View itemView) {
            super(itemView);
        }
    }
}
