package com.certis_base_app.ui.dialog.location;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.ArrayList;
import java.util.List;

public class LocationRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> locationList;
    private View.OnClickListener onClickListener;

    public LocationRecyclerviewAdapter(List<String> locationList, View.OnClickListener onClickListener) {
        this.locationList = locationList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_location, parent,false);
        view.setOnClickListener(onClickListener);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LocationHolder locationHolder = (LocationHolder) holder;
        locationHolder.itemView.setTag(Integer.valueOf(position));
        locationHolder.tvLocation.setText(locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList!=null ? locationList.size() : 0;
    }

    public class LocationHolder extends RecyclerView.ViewHolder{

        TextView tvLocation;
        public LocationHolder(View itemView) {
            super(itemView);
            tvLocation = itemView.findViewById(R.id.tv_location_name);
        }
    }
}
