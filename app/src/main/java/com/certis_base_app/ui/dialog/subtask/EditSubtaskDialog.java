package com.certis_base_app.ui.dialog.subtask;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.model.Subtask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditSubtaskDialog extends Dialog implements View.OnClickListener {
    private EditText etSubtaskTitle;
    private TextView tvSave;
    private TextView tvSubtaskLocation;
    private TextView tvSubtaskType;
    private ImageView ivSubtaskTypeChevron;
    private ImageView ivClose;
    private Switch mSwitchEndTime;
    private Group mGroupEndTime;
    private TextView tvSubtaskEndTime;
    private Button btnPlusEndTime;
    private Button btnMinusEndTime;

    Subtask subtask;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onSubtaskLocationClick(TextView locationWidget);

        void onSubtaskTypeClick(TextView locationWidget);

        void onSubtaskSaveClick(Subtask subtask);
    }

    public enum EndTimeOperation {
        PLUS,
        MINUS
    }

    public EditSubtaskDialog(@NonNull Context context, OnItemClickListener onItemClickListener, Object arg) {
        super(context);
        this.onItemClickListener = onItemClickListener;
        this.subtask = (Subtask) arg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_edit_subtask, null, false);
        this.setContentView(view);
        this.tvSave = view.findViewById(R.id.tv_subtask_save);
        this.ivClose = view.findViewById(R.id.iv_close);
        this.etSubtaskTitle = view.findViewById(R.id.et_subtask_title_value);
        this.tvSubtaskLocation = view.findViewById(R.id.tv_subtask_location_value);
        this.tvSubtaskType = view.findViewById(R.id.tv_subtask_type_value);
        this.ivSubtaskTypeChevron = view.findViewById(R.id.iv_subtask_type_chevron);
        this.mSwitchEndTime = view.findViewById(R.id.switch_subtask_end_time);
        this.mGroupEndTime = view.findViewById(R.id.group_subtask_end_time);
        this.tvSubtaskEndTime = view.findViewById(R.id.tv_subtask_end_time_value);
        this.btnPlusEndTime = view.findViewById(R.id.btn_plus);
        this.btnMinusEndTime = view.findViewById(R.id.btn_minus);

        EditSubtaskDialog.this.setData();
    }

    private void setData() {
        if(this.subtask!=null){
            etSubtaskTitle.setText(subtask.getTitle());
            tvSubtaskLocation.setText(subtask.getTitle());
            tvSubtaskLocation.setText(subtask.getLocation());
            tvSubtaskType.setText(subtask.getType());
            mSwitchEndTime.setChecked(subtask.getEndTime()!="0"?true:false);
            mGroupEndTime.setVisibility(subtask.getEndTime()!="0" ? View.VISIBLE : View.GONE);
            tvSubtaskEndTime.setText(subtask.getEndTime());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.tvSave.setOnClickListener(this);
        this.ivClose.setOnClickListener(this);
        this.mSwitchEndTime.setOnClickListener(this);
        this.tvSubtaskLocation.setOnClickListener(this);
        this.tvSubtaskType.setOnClickListener(this);
        this.ivSubtaskTypeChevron.setOnClickListener(this);
        this.btnPlusEndTime.setOnClickListener(this);
        this.btnMinusEndTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_subtask_save:
                onItemClickListener.onSubtaskSaveClick(new Subtask(etSubtaskTitle.getText().toString(),
                        tvSubtaskLocation.getText().toString(),
                        tvSubtaskType.getText().toString(),
                        mSwitchEndTime.isChecked()!=false ? tvSubtaskEndTime.getText().toString():"0"));
                EditSubtaskDialog.this.dismiss();

                break;
            case R.id.iv_close:
                EditSubtaskDialog.this.dismiss();
                break;
            case R.id.tv_subtask_location_value:
                onItemClickListener.onSubtaskLocationClick(tvSubtaskLocation);
                break;
            case R.id.tv_subtask_type_value:
            case R.id.iv_subtask_type_chevron:
                onItemClickListener.onSubtaskTypeClick(tvSubtaskType);
                break;
            case R.id.switch_subtask_end_time:
                EditSubtaskDialog.this.visibleSubtaskEndTime();
                break;
            case R.id.btn_plus:
                EditSubtaskDialog.this.updateEndTime(EndTimeOperation.PLUS);
                break;
            case R.id.btn_minus:
                EditSubtaskDialog.this.updateEndTime(EndTimeOperation.MINUS);
        }
    }

    private void visibleSubtaskEndTime() {
        if (mSwitchEndTime.isChecked()) {
            mGroupEndTime.setVisibility(View.VISIBLE);
            tvSubtaskEndTime.setText(this.getCurrentTimeInTimeFormat());
        } else {
            mGroupEndTime.setVisibility(View.GONE);
        }
    }

    public void updateEndTime(EndTimeOperation operation) {
        String subTaskEndTime = tvSubtaskEndTime.getText().toString();
        Long subTaskNewEndTimeInMilli;
        switch (operation) {
            case PLUS:
                subTaskNewEndTimeInMilli = this.getSubtaskCurrentEndTimeInMilli() + this.getFiveMinuteInMilli();
                tvSubtaskEndTime.setText(String.format("%s", new SimpleDateFormat("hh:mm a").format(subTaskNewEndTimeInMilli)));
                btnMinusEndTime.setEnabled(true);
                break;
            case MINUS:
                subTaskNewEndTimeInMilli = this.getSubtaskCurrentEndTimeInMilli() - this.getFiveMinuteInMilli();
                tvSubtaskEndTime.setText(String.format("%s", new SimpleDateFormat("hh:mm a").format(subTaskNewEndTimeInMilli)));
                //btnMinusEndTime.setEnabled(getSubtaskCurrentEndTimeInMilli()> getCurrentTimeInMilli()? true: false);
                btnMinusEndTime.setEnabled(subTaskEndTime.equals(subTaskNewEndTimeInMilli) ? true : false);
                break;
        }
    }

    public String getCurrentTimeInTimeFormat() {
        return new SimpleDateFormat("hh:mm a").format(new Date());
    }

    public Long getSubtaskCurrentEndTimeInMilli() {
        try {
            return new SimpleDateFormat("hh:mm a").parse(tvSubtaskEndTime.getText().toString()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Long.valueOf(0);
    }

    public Long getCurrentTimeInMilli() {
        return new Date().getTime();
        //return new SimpleDateFormat("hh:mm a").format(new Date());
    }

    public Long getFiveMinuteInMilli() {
        return new Date(5 * 60 * 1000).getTime();
        //return new SimpleDateFormat("hh:mm a").format(new Date(System.currentTimeMillis()+5*60*1000));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (onItemClickListener != null) {
            onItemClickListener = null;
        }
    }
}
