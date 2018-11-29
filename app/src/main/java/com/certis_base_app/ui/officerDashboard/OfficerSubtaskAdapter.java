package com.certis_base_app.ui.officerDashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;

public class OfficerSubtaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_QR_CODE = 0;
    private static final int ITEM_VIEW_TYPE_PHOTO = 1;
    private static final int ITEM_VIEW_TYPE_EMPTY = 2;
    private static final int ITEM_VIEW_TYPE_DESCRIPTION = 3;

    View.OnClickListener onClickListener;

    public OfficerSubtaskAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubtaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subtask_mandatory_description, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SubtaskViewHolder subtaskViewHolder = (SubtaskViewHolder) holder;
        subtaskViewHolder.itemView.setTag(Integer.valueOf(position));
        subtaskViewHolder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class SubtaskViewHolder extends RecyclerView.ViewHolder{
        public SubtaskViewHolder(View itemView) {
            super(itemView);
        }
    }
}
