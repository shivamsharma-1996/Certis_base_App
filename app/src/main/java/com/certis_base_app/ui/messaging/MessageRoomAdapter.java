package com.certis_base_app.ui.messaging;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;

import java.util.List;

public class MessageRoomAdapter extends RecyclerView.Adapter {
    private List<String> messageRoomList;
    private View.OnClickListener onClickListener;

    public MessageRoomAdapter(List<String> messageRoomList, View.OnClickListener onClickListener) {
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
        MessageRoomHolder messageRoomHolder = (MessageRoomHolder) holder;
        messageRoomHolder.itemView.setTag(Integer.valueOf(position));
        messageRoomHolder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return messageRoomList.size();
    }

    public void setData(String commnet) {
        this.messageRoomList.add(commnet);
        notifyDataSetChanged();
    }

    public class MessageRoomHolder extends RecyclerView.ViewHolder {
        public MessageRoomHolder(View itemView) {
            super(itemView);
        }

    }
}
