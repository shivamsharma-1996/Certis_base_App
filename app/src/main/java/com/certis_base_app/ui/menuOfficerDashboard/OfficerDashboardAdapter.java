package com.certis_base_app.ui.menuOfficerDashboard;

import android.content.Context;
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
import com.certis_base_app.model.Officer;
import com.certis_base_app.model.OfficerWrapper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OfficerDashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SUBTASK_IN_PROGRESS = 0;
    private static final int SUBTASK_INCOMPLETE = 1;
    private static final int SUBTASK_COMPLETE = 2;
    private static final int NO_SUBTASK = 3;
    private static final int OFFICER_OFFLINE = 4;

    private Context context;
    private List<Officer> officersList;
    private View.OnClickListener onClickListener;

    public OfficerDashboardAdapter(Context context, List<Officer> officersList, View.OnClickListener onClickListener) {
        this.context = context;
        this.officersList = officersList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_officer_monitoring_list, parent, false);
        view.setOnClickListener(onClickListener);
        return new OfficerMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OfficerMonitoringViewHolder officerMonitoringViewHolder = (OfficerMonitoringViewHolder) holder;
        officerMonitoringViewHolder.itemView.setTag(position);
        switch (getItemViewType(position)) {
            case SUBTASK_IN_PROGRESS:
                break;
            case SUBTASK_INCOMPLETE:
                officerMonitoringViewHolder.tvOfficerName.setText("Jon Peterson");
                officerMonitoringViewHolder.tvTaskStatus.setText("1 Incomplete");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    officerMonitoringViewHolder.taskProgress.setProgressTintList(ContextCompat.getColorStateList(context, R.color.red));
                }
                officerMonitoringViewHolder.taskProgress.setProgress(80);
                officerMonitoringViewHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context, R.color.red));

                break;
            case SUBTASK_COMPLETE:
                officerMonitoringViewHolder.tvOfficerName.setText("Paul Yeoh");
                officerMonitoringViewHolder.tvTaskStatus.setText("Completed");
                officerMonitoringViewHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context, R.color.greenMixedSuccess));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    officerMonitoringViewHolder.taskProgress.setProgressTintList(ContextCompat.getColorStateList(context, R.color.greenMixedSuccess));
                }
                officerMonitoringViewHolder.taskProgress.setProgress(100);

                break;
            case NO_SUBTASK:
                officerMonitoringViewHolder.tvOfficerName.setText("Samuel Chandra");
                officerMonitoringViewHolder.tvTaskStatus.setText("No Subtask");
                officerMonitoringViewHolder.tvTaskTitle.setText("Patrol T4 Zone D");
                officerMonitoringViewHolder.tvTaskStatus.setTextColor(ContextCompat.getColor(context, R.color.textPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    officerMonitoringViewHolder.taskProgress.setProgressTintList(ContextCompat.getColorStateList(context, R.color.greenMixedSuccess));
                }
                officerMonitoringViewHolder.taskProgress.setProgress(0);
                break;
            case OFFICER_OFFLINE:
                officerMonitoringViewHolder.tvOfficerName.setText("Benjamin Chang");
                officerMonitoringViewHolder.taskProgress.setVisibility(View.GONE);
                officerMonitoringViewHolder.tvTaskStatus.setVisibility(View.GONE);
                officerMonitoringViewHolder.tvTaskTitle.setText("Last seen 11:06 AM");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return SUBTASK_IN_PROGRESS;
            case 1:
                return SUBTASK_INCOMPLETE;
            case 2:
                return SUBTASK_COMPLETE;
            case 3:
                return NO_SUBTASK;
            case 4:
            default:
                return OFFICER_OFFLINE;
        }
    }

    private class OfficerMonitoringViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civOfficerImage;
        ProgressBar taskProgress;
        TextView tvOfficerName;
        TextView tvOfficerId;
        TextView tvTaskStatus;
        TextView tvTaskTitle;

        public OfficerMonitoringViewHolder(View itemView) {
            super(itemView);
            civOfficerImage = itemView.findViewById(R.id.civ_officer_image);
            taskProgress = itemView.findViewById(R.id.pb_task);
            tvOfficerName = itemView.findViewById(R.id.tv_officer_name);
            tvOfficerId = itemView.findViewById(R.id.tv_officer_id);
            tvTaskStatus = itemView.findViewById(R.id.tv_task_status);
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title);
        }
    }
}