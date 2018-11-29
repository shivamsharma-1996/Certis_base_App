package com.certis_base_app.ui.taskManagement;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.certis_base_app.R;

public class AllTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private View.OnClickListener onClickListener;
    private static final int ITEM_TASK_IN_PROGRESSS = 0;
    private static final int ITEM_TASK_COMPLETE = 1;
    private static final int ITEM_TASK_INCOMPLETE = 2;
    private static final int ITEM_TASK_UNASSIGNED = 3;
    private static final int ITEM_TASK_ASSIGNED = 4;

    public AllTasksAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskHolder taskHolder = (TaskHolder) holder;
        taskHolder.itemView.setOnClickListener(onClickListener);
        taskHolder.itemView.setTag(Integer.valueOf(position));

        switch (getItemViewType(position)){
            case ITEM_TASK_IN_PROGRESSS:
                break;
            case ITEM_TASK_COMPLETE:
                taskHolder.taskTitle.setText("Defective Security Door/Fence");
                taskHolder.taskOfficerName.setText("Amanda Johnson Daisy J...");
                taskHolder.taskStatus.setVisibility(View.GONE);
                taskHolder.subtaskStatus.setText("5/5 Completed");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    taskHolder.taskProgressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(taskHolder.itemView.getContext(), R.color.taskCompleted)));
                }
                taskHolder.subtaskStatus.setTextColor(ContextCompat.getColor(context, R.color.taskCompleted));
                taskHolder.taskOfficerName.setCompoundDrawables(null, null, null, null);
                taskHolder.taskTimeLeft.setText("2h 20m");
                taskHolder.taskTimeLeft.setBackgroundColor(ContextCompat.getColor(context, R.color.greyTranslucent));
                taskHolder.taskTimeLeft.setTextColor(ContextCompat.getColor(context, R.color.greyDark));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    taskHolder.taskTimeLeft.setCompoundDrawableTintList(ContextCompat.getColorStateList(context, R.color.greyDark));
                }
                taskHolder.taskProgressBar.setProgress(100);
                taskHolder.taskTimeToStart.setVisibility(View.GONE);
                break;
            case ITEM_TASK_INCOMPLETE:
                taskHolder.taskStatus.setVisibility(View.GONE);
                taskHolder.taskIncompleteCause.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    taskHolder.taskProgressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(taskHolder.itemView.getContext(), R.color.red)));
                }
                taskHolder.subtaskStatus.setText("1 Incomplete Subtask");
                taskHolder.subtaskStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
                taskHolder.taskOfficerName.setCompoundDrawables(null, null, null, null);
                taskHolder.taskTimeToStart.setVisibility(View.GONE);
                break;
            case ITEM_TASK_UNASSIGNED:
                taskHolder.taskTitle.setText("Kerbside Enforcement Ops");
                taskHolder.taskProgressBar.setVisibility(View.GONE);
                taskHolder.subtaskStatus.setVisibility(View.GONE);
                taskHolder.taskStatus.setVisibility(View.GONE);
                taskHolder.taskTimeLeft.setVisibility(View.GONE);
                taskHolder.taskIncompleteCause.setVisibility(View.GONE);
                taskHolder.taskOfficerId.setVisibility(View.INVISIBLE);
                taskHolder.taskOfficerName.setText("Unassigned");
                taskHolder.taskTimeToStart.setText("15m to start");
                break;

            case ITEM_TASK_ASSIGNED:
                taskHolder.taskTitle.setText("Anti-Touting Ops");
                taskHolder.taskOfficerName.setText("Briandito Priambodo di…");
                taskHolder.taskTimeToStart.setText("15m to start");
                taskHolder.taskProgressBar.setVisibility(View.GONE);
                taskHolder.taskTimeLeft.setVisibility(View.GONE);
                taskHolder.taskStatus.setVisibility(View.GONE);
                taskHolder.taskIncompleteCause.setVisibility(View.GONE);
                taskHolder.subtaskStatus.setText("Inactive");
                taskHolder.taskTimeToStart.setText("2h 45m to start");
                taskHolder.subtaskStatus.setTextColor(ContextCompat.getColor(context, R.color.greyDark));
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return ITEM_TASK_IN_PROGRESSS;
            case 1:
                return ITEM_TASK_COMPLETE;
            case 2:
                return ITEM_TASK_INCOMPLETE;
            case 3:
                return ITEM_TASK_UNASSIGNED;
            case 4:
            case 5:
            case 6:
            case 7:
                default:
                return ITEM_TASK_ASSIGNED;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        TextView taskTime;
        TextView taskTimeToStart;
        TextView taskTitle;
        TextView taskOfficerName;
        TextView taskOfficerId;
        TextView taskStatus;
        TextView subtaskStatus;
        TextView taskTimeLeft;
        TextView taskIncompleteCause;
        ProgressBar taskProgressBar;



        public TaskHolder(View itemView) {
            super(itemView);
            taskTime = itemView.findViewById(R.id.tv_task_time);
            taskTimeToStart = itemView.findViewById(R.id.tv_task_time_to_start);
            taskTitle = itemView.findViewById(R.id.tv_task_title);
            taskOfficerName = itemView.findViewById(R.id.tv_task_officer_name);
            taskOfficerId = itemView.findViewById(R.id.tv_task_officer_id);
            taskStatus = itemView.findViewById(R.id.tv_task_status);
            subtaskStatus = itemView.findViewById(R.id.tv_subtask_status);
            taskTimeLeft = itemView.findViewById(R.id.tv_task_time_left);
            taskIncompleteCause = itemView.findViewById(R.id.tv_task_incomplete_cause);
            taskProgressBar = itemView.findViewById(R.id.pb_task);
        }
    }
}
