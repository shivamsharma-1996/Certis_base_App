package com.certis_base_app.ui.menuIncidentManagement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.List;

public class IncidentCommentAdapter extends RecyclerView.Adapter {

    private List<String> commentList;
    public IncidentCommentAdapter(List<String> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IncidentCommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_incident_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IncidentCommentHolder incidentCommentHolder = (IncidentCommentHolder) holder;
        incidentCommentHolder.tvCommentText.setText(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void setData(String commnet) {
        this.commentList.add(commnet);
        notifyDataSetChanged();
    }

    public class IncidentCommentHolder extends RecyclerView.ViewHolder{
        TextView tvCommentText;
        public IncidentCommentHolder(View itemView) {
            super(itemView);
            tvCommentText = itemView.findViewById(R.id.tv_comment_text);
        }
    }
}
