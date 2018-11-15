package com.certis_base_app.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_ALL = 0;
    private static final int ITEM_VIEW_HEADER = 1;
    private static final int ITEM_VIEW_ITEM = 2;

    private Context context;
    private List<String> itemList;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public FilterAdapter(Context context, List<String> itemList, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.context = context;
        this.itemList = itemList;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView;
        switch (viewType) {
            case ITEM_VIEW_HEADER:
                itemView = inflater.inflate(R.layout.heading_list_filter, parent, false);
                return new HeaderViewHolder(itemView);
            case ITEM_VIEW_ALL:
                itemView = inflater.inflate(R.layout.item_list_filter_all, parent, false);
                return new ItemAllViewHolder(itemView);
            case ITEM_VIEW_ITEM:
            default:
                itemView = inflater.inflate(R.layout.item_list_filter_item, parent, false);
                return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_VIEW_HEADER:
                //((HeaderViewHolder) holder).tvHeader.setText(position == 1? R.string.dialog_filter_header_status : R.string.dialog_filter_header_groups);
                ((HeaderViewHolder) holder).filterListHeader.setText(itemList.get(position));
                break;
            case ITEM_VIEW_ALL:
                ((ItemAllViewHolder) holder).filterListItemAll.setText(itemList.get(position));
                break;
            case ITEM_VIEW_ITEM:
                default:
                    ((ItemViewHolder) holder).filterListItemCheckBox.setText(itemList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_VIEW_ALL;
            case 1:
            case 4:
                return ITEM_VIEW_HEADER;
            default:
                return ITEM_VIEW_ITEM;
        }
    }


    private class ItemAllViewHolder extends RecyclerView.ViewHolder {
        TextView filterListItemAll;

        ItemAllViewHolder(View view) {
            super(view);
            filterListItemAll = view.findViewById(R.id.tv_item_filter_all);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox filterListItemCheckBox;

        ItemViewHolder(View view) {
            super(view);
            this.filterListItemCheckBox = view.findViewById(R.id.cb_item_filter);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView filterListHeader;

        HeaderViewHolder(View view) {
            super(view);
            this.filterListHeader = view.findViewById(R.id.tv_header);
        }
    }
}
