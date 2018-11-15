package com.certis_base_app.ui.menuOfficerDashboard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.model.Task;

import java.util.List;

public class OfficerTasksAdapter extends Adapter<ViewHolder> {
    private List<Task> taskList;
    private OnClickListener onClickListener;
    private Context context;

    private static final int HEADING_TASKS = 0;
    private static final int ITEM_TASKS = 1;
    private static final int SUB_HEADING_IN_PROGRESS = 2;
    private static final int ITEM_SUB_HEADING_TASKS = 3;

    public OfficerTasksAdapter(Context context, List<Task> taskList, OnClickListener onClickListener) {
        this.context = context;
        this.taskList = taskList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (i == HEADING_TASKS)
            return new HeadingViewHolder(inflater.inflate(R.layout.heading_officer_profile_task_list, viewGroup, false));
        else if (i == SUB_HEADING_IN_PROGRESS)
            return new SubHeadingViewHolder(inflater.inflate(R.layout.heading_officer_profile_task_list2, viewGroup, false));
        else {
            View view = inflater.inflate(R.layout.item_officer_profile_tasks, viewGroup, false);
            view.setOnClickListener(this.onClickListener);
            return new OfficerTaskViewHolder(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        OfficerTaskViewHolder taskViewHolder;
        String taskStatus;

        switch (getItemViewType(position)) {
            case HEADING_TASKS:
                ((HeadingViewHolder) viewHolder).taskHeading.setText(R.string.officer_label_task_header);
                return;
            case SUB_HEADING_IN_PROGRESS:
                ((SubHeadingViewHolder) viewHolder).taskSubHeading.setText(R.string.officer_label_task_sub_header);
                return;
            case ITEM_TASKS  :
            case ITEM_SUB_HEADING_TASKS:
                taskViewHolder = (OfficerTaskViewHolder) viewHolder;
                taskViewHolder.taskStatus.setText(taskList.get(position).getStatus());
                taskViewHolder.taskTitle.setText(taskList.get(position).getTitle());
                taskViewHolder.taskTimeInBetween.setText(taskList.get(position).getTaskTimeInBetween());
                taskViewHolder.taskProgressBar.setProgress(taskList.get(position).getProgress());

                taskStatus = taskList.get(position).getStatus();
                switch (taskStatus) {
                    case "Completed":
                        taskViewHolder.taskProgressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.taskCompleted)));
                        taskViewHolder.taskStatus.setTextColor(ContextCompat.getColor(context,R.color.taskCompleted));
                        break;
                    case "1 Incomplete Subtask":
                        taskViewHolder.taskProgressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.taskInCompleted)));
                        taskViewHolder.taskStatus.setTextColor(ContextCompat.getColor(context,R.color.taskInCompleted));
                        break;
                    case "3/5 Completed":
                        taskViewHolder.taskProgressBar.setVisibility(View.GONE);
                        break;

                    case "0":
                    default:
                        taskViewHolder.taskStatus.setVisibility(View.GONE);
                        taskViewHolder.taskPendingAcknowldgement.setVisibility(View.VISIBLE);
                        taskViewHolder.taskProgressBar.setVisibility(View.GONE);
                        taskViewHolder.itemOfficerTask.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_item_officer_task_red));
                        break;
                }

            default:
                return;
        }
    }

    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return HEADING_TASKS;
        } else if (i == 1 || i == 2) {
            return ITEM_TASKS;
        } else if (i == 3) {
            return SUB_HEADING_IN_PROGRESS;
        } else {
            return ITEM_SUB_HEADING_TASKS;
        }
    }

    private class HeadingViewHolder extends ViewHolder {
        TextView taskHeading;

        HeadingViewHolder(View view) {
            super(view);
            this.taskHeading = view.findViewById(R.id.tv_heading_text);
        }
    }

    private class SubHeadingViewHolder extends ViewHolder {
        TextView taskSubHeading;

        SubHeadingViewHolder(View view) {
            super(view);
            this.taskSubHeading = view.findViewById(R.id.tv_heading_text);
        }
    }

    private class OfficerTaskViewHolder extends ViewHolder {
        ConstraintLayout itemOfficerTask;
        TextView taskTitle;
        TextView taskStatus;
        TextView taskTimeInBetween;
        ProgressBar taskProgressBar;
        TextView taskPendingAcknowldgement;

        OfficerTaskViewHolder(View view) {
            super(view);
            itemOfficerTask = view.findViewById(R.id.cv_officer_profile_tasks);
            taskTitle = view.findViewById(R.id.tv_task_title);
            taskStatus = view.findViewById(R.id.tv_task_status);
            taskTimeInBetween = view.findViewById(R.id.tv_task_time_between);
            taskProgressBar = view.findViewById(R.id.pb_task);
            taskPendingAcknowldgement = view.findViewById(R.id.tv_pending_acknowledgment);
        }
    }

}
