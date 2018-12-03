package com.certis_base_app.ui.incidentManagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;


public class IncidentManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private View.OnClickListener onClickListener;

    public IncidentManagementAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IncidentReportHolder(LayoutInflater.from(context).inflate(R.layout.item_list_incident_report, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((IncidentReportHolder) holder).itemView.setTag(Integer.valueOf(position));
        ((IncidentReportHolder) holder).itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class IncidentReportHolder extends RecyclerView.ViewHolder {

        public IncidentReportHolder(View itemView) {
            super(itemView);
        }
    }

}
