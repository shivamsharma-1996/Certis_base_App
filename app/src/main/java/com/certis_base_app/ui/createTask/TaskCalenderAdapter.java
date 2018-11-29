package com.certis_base_app.ui.createTask;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.enums.CalenderItemType;
import com.certis_base_app.ui.custom.TriStateCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.certis_base_app.utills.Constants.CALENDER_CELL_COUNT;

public class TaskCalenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private HashMap<Integer, Integer> boxIdMap;
    private int startHour, endHour, startMin, endMin;

    private final static int COLUMN_COUNT = CALENDER_CELL_COUNT;
    private int boxWidth;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    private UpdateTristateListener updateTristateListener;


    public interface UpdateTristateListener {
        void updateTriStateCheckBox(int newState);
    }


    public TaskCalenderAdapter(Context context, HashMap<Integer, Integer> boxIdMap, int startHour, int endHour, int startMin, int endMin, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, UpdateTristateListener updateTristateListener) {
        this.context = context;
        this.boxIdMap = boxIdMap;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMin = startMin;
        this.endMin = endMin;
        this.onCheckedChangeListener = onCheckedChangeListener;
        this.updateTristateListener =updateTristateListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (CalenderItemType.lookupByCode(viewType)) {
            case FIRST_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_calender_row, parent, false);
                return new FirstRowViewHolder(view);

            default:
            case OTHER_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_calender_row, parent, false);
                return new SecondRowViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return CalenderItemType.FIRST_ROW.getCode();
        else
            return CalenderItemType.OTHER_ROW.getCode();

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(Integer.valueOf(position));
        switch (CalenderItemType.lookupByCode(getItemViewType(position))) {
            case FIRST_ROW:
                FirstRowViewHolder firstRowViewHolder = (FirstRowViewHolder) holder;
                firstRowViewHolder.mOfficerName.setVisibility(View.INVISIBLE);
                firstRowViewHolder.mAttendence.setVisibility(View.INVISIBLE);
                firstRowViewHolder.mCheckOfficer.setVisibility(View.INVISIBLE);
                break;

            case OTHER_ROW:
                final int[] marginStart = new int[1];
                final int[] marginEnd = new int[1];
                final SecondRowViewHolder secondRowViewHolder = (SecondRowViewHolder) holder;
                checkBoxes.add(secondRowViewHolder.mCheckOfficer);
                secondRowViewHolder.mCheckOfficer.setOnCheckedChangeListener(onCheckedChangeListener);

                if(position == 3) {
                    final View boxView = secondRowViewHolder.itemView.findViewById(boxIdMap.get(endHour));
                    ViewTreeObserver vto = boxView.getViewTreeObserver();
                    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            boxView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            boxWidth = boxView.getMeasuredWidth();

                            switch (startMin) {
                                case 15:
                                    marginStart[0] = (boxWidth * 1) / 4;
                                    break;
                                case 30:
                                    marginStart[0] = (boxWidth * 2) / 4;
                                    break;
                                case 45:
                                    marginStart[0] = (boxWidth * 3) / 4;
                                    break;
                                case 00:
                                    marginStart[0] = (boxWidth * 4) / 4;
                                    break;
                            }
                            switch (endMin) {
                                case 15:
                                    marginEnd[0] = boxWidth - (boxWidth * 1) / 4;
                                    break;
                                case 30:
                                    marginEnd[0] = boxWidth - (boxWidth * 2) / 4;
                                    break;
                                case 45:
                                    marginEnd[0] = boxWidth - (boxWidth * 3) / 4;
                                    break;
                                case 00:
                                    marginEnd[0] = boxWidth - (boxWidth * 4) / 4;
                                    break;
                            }

                            ConstraintLayout layout = secondRowViewHolder.itemView.findViewById(R.id.cl_item_calender_row);
                            ConstraintSet set = new ConstraintSet();
                            View view = LayoutInflater.from(context).inflate(R.layout.layout_calender_dynamic_view, null);
                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                            view.setLayoutParams(layoutParams);
                            layout.addView(view);

                            if (endHour - 2 < COLUMN_COUNT)
                                if (marginEnd[0] > 0)
                                    set.connect(view.getId(), ConstraintSet.END, boxIdMap.get(endHour + 1), ConstraintSet.START, marginEnd[0]);
                                else
                                    set.connect(view.getId(), ConstraintSet.END, boxIdMap.get(endHour), ConstraintSet.START, marginEnd[0]);
                            else
                                set.connect(view.getId(), ConstraintSet.END, secondRowViewHolder.itemView.getId(), ConstraintSet.END, 0);
                            set.connect(view.getId(), ConstraintSet.START, boxIdMap.get(startHour), ConstraintSet.START, marginStart[0]);
                            set.connect(view.getId(), ConstraintSet.BOTTOM, secondRowViewHolder.itemView.getId(), ConstraintSet.BOTTOM, 0);
                            set.connect(view.getId(), ConstraintSet.TOP, secondRowViewHolder.itemView.getId(), ConstraintSet.TOP, 0);
                            set.applyTo(layout);

                            set.clone(layout);
                        }
                    });
                }
                break;
        }
    }

    public int getCheckedAssigneeCount() {
        int officerCount=0;
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                officerCount++;
            }
        }
        return officerCount;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void selectAllCheckboxes() {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(true);
        }
    }

    public void unSelectAllCheckboxes() {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(false);
        }
    }

    public void updateTristateCheckBox() {
        int checkBoxCount = 9;
        for (CheckBox checkBox : checkBoxes) {
            if (!checkBox.isChecked()) {
                checkBoxCount = checkBoxCount - 1;
            }
        }
        if (checkBoxCount == 9) {
            updateTristateListener.updateTriStateCheckBox(TriStateCheckBox.CHECKED);
        } else if (checkBoxCount == 0) {
            updateTristateListener.updateTriStateCheckBox(TriStateCheckBox.UNCHECKED);
        } else {
            updateTristateListener.updateTriStateCheckBox(TriStateCheckBox.INDETERMINATE);
        }
    }

    private class FirstRowViewHolder extends RecyclerView.ViewHolder {
        TextView mOfficerName, mAttendence;
        CheckBox mCheckOfficer;

        public FirstRowViewHolder(View itemView) {
            super(itemView);
            mOfficerName = itemView.findViewById(R.id.tv_officer_name);
            mAttendence = itemView.findViewById(R.id.tv_available);
            mCheckOfficer = itemView.findViewById(R.id.check_officer);
        }
    }

    private static class SecondRowViewHolder extends RecyclerView.ViewHolder {
        TextView mOfficerName, mAttendence;
        CheckBox mCheckOfficer;
        public SecondRowViewHolder(View itemView) {
            super(itemView);
            mOfficerName = itemView.findViewById(R.id.tv_officer_name);
            mAttendence = itemView.findViewById(R.id.tv_available);
            mCheckOfficer = itemView.findViewById(R.id.check_officer);
        }
    }
}