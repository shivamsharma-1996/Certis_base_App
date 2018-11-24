package com.certis_base_app.ui.menuTaskManagement;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.model.LatestTaskUpdate;

import java.util.List;

public class LatestTaskUpdatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TASK_IN_PROGRESS = 0;
    private static final int TASK_COMPLETED = 1;
    private static final int TASK_INCOMPLETE = 2;
    private static final int TASK_ACKNOWLEGED = 3;

    private List<LatestTaskUpdate> latestTaskUpdateList;
    private Context context;

    public LatestTaskUpdatesAdapter(List<LatestTaskUpdate> latestTaskUpdateList, Context context) {
        this.latestTaskUpdateList = latestTaskUpdateList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestTaskUpdateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_task_update, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LatestTaskUpdateHolder latestTaskUpdateHolder = (LatestTaskUpdateHolder) holder;
        switch (getItemViewType(position)){
            case TASK_ACKNOWLEGED:
                latestTaskUpdateHolder.tvTaskSubtitle.setVisibility(View.GONE);
                latestTaskUpdateHolder.tvTaskStatus.setText("Acknowleged");
                latestTaskUpdateHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context,R.color.greenMixedSuccess));
                break;
            case TASK_COMPLETED:
                latestTaskUpdateHolder.tvTaskSubtitle.setVisibility(View.GONE);
                latestTaskUpdateHolder.tvTaskStatus.setText("Completed");
                latestTaskUpdateHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context,R.color.greenMixedSuccess));
                break;
            case TASK_INCOMPLETE:
                latestTaskUpdateHolder.tvTaskSubtitle.setVisibility(View.GONE);
                latestTaskUpdateHolder.tvTaskStatus.setText("Incomplete");
                latestTaskUpdateHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context,R.color.red));
                break;
            case TASK_IN_PROGRESS:
                latestTaskUpdateHolder.tvTaskStatus.setText("3/5 Completed");
                latestTaskUpdateHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TASK_ACKNOWLEGED;
            case 1:
            case 4:
            case 5:
            case 8:
                return TASK_COMPLETED;
            case 2:
            case 6:
                return TASK_INCOMPLETE;
            case 3:
            case 7:
                return TASK_IN_PROGRESS;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class LatestTaskUpdateHolder extends RecyclerView.ViewHolder {
        TextView tvTaskUpdateTime;
        TextView tvTaskTitle;
        TextView tvTaskSubtitle;
        TextView tvTaskStatus;

        public LatestTaskUpdateHolder(View itemView) {
            super(itemView);
            tvTaskUpdateTime = itemView.findViewById(R.id.tv_task_update_time);
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title);
            tvTaskSubtitle = itemView.findViewById(R.id.tv_task_subtitle);
            tvTaskStatus = itemView.findViewById(R.id.tv_task_status);

        }
    }
}
