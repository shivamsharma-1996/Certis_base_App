package com.certis_base_app.ui.createTask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;

public class TaskAssigneeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int assignee_count;
    private View.OnClickListener onClickListener;

    public TaskAssigneeAdapter(int assignee_count, View.OnClickListener onClickListener) {
        this.assignee_count = assignee_count;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return new AssigneeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_task_assignee, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(onClickListener);
    }


    @Override
    public int getItemCount() {
        return assignee_count;
    }

    public class AssigneeHolder extends RecyclerView.ViewHolder {

        public AssigneeHolder(View itemView) {
            super(itemView);

        }
    }


}