package com.certis_base_app.ui.officerDashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.certis_base_app.R;
import com.certis_base_app.model.MessageCard;
import java.util.Calendar;
import java.util.List;


public class OfficerMessageAdapter extends Adapter<ViewHolder> {
    private Calendar currentDate;
    private List<MessageCard> messageCardList;

    public OfficerMessageAdapter(List<MessageCard> list) {
        this.messageCardList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_officer_message_sent, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MessageViewHolder messageViewHolder = (MessageViewHolder) viewHolder;
        messageViewHolder.itemView.setTag(Integer.valueOf(i));
        if(i==0)
            messageViewHolder.date.setVisibility(View.VISIBLE);
        else
            messageViewHolder.date.setVisibility(View.GONE);
        messageViewHolder.message.setText(messageCardList.get(i).getMessageText());
        messageViewHolder.messageSentTime.setText(messageCardList.get(i).getMessageSentTime());
    }

    public int getItemCount() {
        return this.messageCardList != null ? this.messageCardList.size() : 0;
    }

    public void setMessages(MessageCard messages) {
        this.messageCardList.add(messages);
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends ViewHolder {
        TextView acknowledgedText;
        TextView date;
        TextView message;
        TextView messageSentTime;

        MessageViewHolder(View view) {
            super(view);
            this.date = view.findViewById(R.id.tv_date);
            this.message = view.findViewById(R.id.tv_message);
            this.messageSentTime = view.findViewById(R.id.tv_sent_time);
            this.acknowledgedText = view.findViewById(R.id.tv_acknowledged);
        }
    }
}
