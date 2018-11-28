package com.certis_base_app.ui.menuIncidentManagement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.certis_base_app.R;
import java.util.List;

public class IncidentTaskAdapter extends RecyclerView.Adapter {

    private View.OnClickListener onClickListener;
    public IncidentTaskAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IncidentTaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_incident_task_associated, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IncidentTaskHolder incidentTaskHolder = (IncidentTaskHolder) holder;
        incidentTaskHolder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class IncidentTaskHolder extends RecyclerView.ViewHolder{
        public IncidentTaskHolder(View itemView) {
            super(itemView);
        }
    }
}
