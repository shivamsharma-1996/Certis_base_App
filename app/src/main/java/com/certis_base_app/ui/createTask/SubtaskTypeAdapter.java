package com.certis_base_app.ui.createTask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.model.Subtask;
import com.certis_base_app.ui.custom.SwipeViewHolder;

import java.util.List;

public class SubtaskTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_QR_CODE = 0;
    private static final int ITEM_VIEW_TYPE_PHOTO = 1;
    private static final int ITEM_VIEW_TYPE_EMPTY = 2;
    private static final int ITEM_VIEW_TYPE_DESCRIPTION = 3;

    private Context context;
    private View.OnClickListener onClickListener;
    private List<Subtask> subtaskList;

    public SubtaskTypeAdapter(Context context, List<Subtask> subtaskList, View.OnClickListener onClickListener) {
        this.context = context;
        this.subtaskList = subtaskList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubtaskListItemHolder(LayoutInflater.from(context).inflate(R.layout.item_list_subtask_types, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SubtaskListItemHolder subtaskListItemHolder = (SubtaskListItemHolder) holder;
        subtaskListItemHolder.itemView.setOnClickListener(onClickListener);
        subtaskListItemHolder.itemView.setTag(Integer.valueOf(position));

        switch (getItemViewType(position)) {
            case ITEM_VIEW_TYPE_QR_CODE:
                subtaskListItemHolder.subtaskImage.setImageResource(R.drawable.ic_subtask_qrcode);
                subtaskListItemHolder.subtaskType.setText("QR Code Required");
                break;
            case ITEM_VIEW_TYPE_PHOTO:
                subtaskListItemHolder.subtaskImage.setImageResource(R.drawable.ic_subtask_photo);
                subtaskListItemHolder.subtaskType.setText("Photo Required");
                break;
            case ITEM_VIEW_TYPE_EMPTY:
                subtaskListItemHolder.subtaskImage.setImageResource(R.drawable.ic_subtask_empty);
                subtaskListItemHolder.subtaskType.setVisibility(View.GONE);
                break;
            case ITEM_VIEW_TYPE_DESCRIPTION:
                subtaskListItemHolder.subtaskImage.setImageResource(R.drawable.ic_subtask_mandatory_description);
                subtaskListItemHolder.subtaskType.setText("Description Required");
                break;
        }
        subtaskListItemHolder.subtaskTitle.setText(getItem(position).getTitle());

        if (getItem(position).getEndTime() != "0") {
            subtaskListItemHolder.subtaskEndTime.setVisibility(View.VISIBLE);
            subtaskListItemHolder.subtaskEndTime.setText(String.format("Complete by %s", getItem(position).getEndTime()));
        } else {
            subtaskListItemHolder.subtaskEndTime.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String subtaskkType = getItem(position).getType();
        if (subtaskkType.equals(context.getString(R.string.dialog_subtask_type_label_qr_code)))
            return ITEM_VIEW_TYPE_QR_CODE;
        else if (subtaskkType.equals(context.getString(R.string.dialog_subtask_type_label_photo)))
            return ITEM_VIEW_TYPE_PHOTO;
        else if (subtaskkType.equals(context.getString(R.string.dialog_subtask_type_label_empty)))
            return ITEM_VIEW_TYPE_EMPTY;
        else if (subtaskkType.equals(context.getString(R.string.dialog_subtask_type_label_description)))
            return ITEM_VIEW_TYPE_DESCRIPTION;
        else
            return ITEM_VIEW_TYPE_QR_CODE;
    }


    public void setSubtaskList( List<Subtask> subtaskList){
        this.subtaskList = subtaskList;
        notifyDataSetChanged();
    }

    public Subtask getItem(int position) {
        return subtaskList.get(position);
    }

//    public void setData(Subtask subtask) {
//        subtaskList.add(subtask);
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return subtaskList != null ? subtaskList.size() : 0;
    }

    public static class SubtaskListItemHolder extends SwipeViewHolder {
        public View viewBackground;
        public View viewForeground;
        ImageView subtaskImage;
        TextView subtaskTitle;
        TextView subtaskType;
        TextView subtaskEndTime;

        public SubtaskListItemHolder(View itemView) {
            super(itemView);
            this.viewForeground = itemView.findViewById(R.id.view_foreground);
            this.viewBackground = itemView.findViewById(R.id.view_background);
            subtaskImage = itemView.findViewById(R.id.iv_subtask);
            subtaskTitle = itemView.findViewById(R.id.tv_subtask_title);
            subtaskType = itemView.findViewById(R.id.tv_subtask_type);
            subtaskEndTime = itemView.findViewById(R.id.tv_subtask_end_time);
        }
    }
}
