package com.certis_base_app.ui.menuOfficerDashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.certis_base_app.R;
import com.certis_base_app.model.OfficerWrapper;
import java.util.List;

public class OfficerMonitoringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<OfficerWrapper> officersList;
    private View.OnClickListener onClickListener;

    public OfficerMonitoringAdapter(Context context, List<OfficerWrapper> officersList, View.OnClickListener onClickListener) {
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
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public int getItemViewType(int i) {
        return i;
    }

    private class OfficerMonitoringViewHolder extends RecyclerView.ViewHolder {

        public OfficerMonitoringViewHolder(View itemView) {
            super(itemView);

        }
    }
}