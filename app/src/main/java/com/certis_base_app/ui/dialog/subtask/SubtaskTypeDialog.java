package com.certis_base_app.ui.dialog.subtask;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

public class SubtaskTypeDialog extends Dialog implements View.OnClickListener {
    private ImageView ivClose;
    private ImageView ivScanQrCode;
    private ImageView ivTakePhoto;
    private ImageView ivEmptySubtask;
    private ImageView ivMandatoryDescription;
    private TextView tvScanQrCode;
    private TextView tvTakePhoto;
    private TextView tvEmptySubtask;
    private TextView tvMandatoryDescription;

    private OnSubtaskTypeSelectListener onSubtaskTypeSelectListener;

    public interface OnSubtaskTypeSelectListener {
        void onSubtaskTypeSelect(String subtaskType);
    }

    public SubtaskTypeDialog(@NonNull Context context, OnSubtaskTypeSelectListener onItemClickListener) {
        super(context);
        this.onSubtaskTypeSelectListener = onItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_subtask_type, null, false);
        this.setContentView(view);
        this.ivClose = view.findViewById(R.id.iv_close);
        this.ivScanQrCode = view.findViewById(R.id.iv_qr_code);
        this.ivTakePhoto = view.findViewById(R.id.iv_photo);
        this.ivEmptySubtask = view.findViewById(R.id.iv_empty_subtask);
        this.ivMandatoryDescription = view.findViewById(R.id.iv_mandatory_description);
        this.tvScanQrCode = view.findViewById(R.id.tv_subtask_qr_code);
        this.tvTakePhoto = view.findViewById(R.id.tv_subtask_photo);
        this.tvEmptySubtask = view.findViewById(R.id.tv_subtask_empty);
        this.tvMandatoryDescription = view.findViewById(R.id.tv_subtask_mandatory_description);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                SubtaskTypeDialog.this.dismiss();
                break;
            case R.id.iv_qr_code:
            case R.id.tv_subtask_qr_code:
                onSubtaskTypeSelectListener.onSubtaskTypeSelect(tvScanQrCode.getText().toString());
                SubtaskTypeDialog.this.dismiss();
                break;
            case R.id.iv_photo:
            case R.id.tv_subtask_photo:
                onSubtaskTypeSelectListener.onSubtaskTypeSelect(tvTakePhoto.getText().toString());
                SubtaskTypeDialog.this.dismiss();
                break;
            case R.id.iv_empty_subtask:
            case R.id.tv_subtask_empty:
                onSubtaskTypeSelectListener.onSubtaskTypeSelect(tvEmptySubtask.getText().toString());
                SubtaskTypeDialog.this.dismiss();
                break;
            case R.id.iv_mandatory_description:
            case R.id.tv_subtask_mandatory_description:
                onSubtaskTypeSelectListener.onSubtaskTypeSelect(tvMandatoryDescription.getText().toString());
                SubtaskTypeDialog.this.dismiss();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.ivClose.setOnClickListener(this);
        this.ivScanQrCode.setOnClickListener(this);
        this.ivTakePhoto.setOnClickListener(this);
        this.ivEmptySubtask.setOnClickListener(this);
        this.ivMandatoryDescription.setOnClickListener(this);
        this.tvScanQrCode.setOnClickListener(this);
        this.tvTakePhoto.setOnClickListener(this);
        this.tvEmptySubtask.setOnClickListener(this);
        this.tvMandatoryDescription.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (onSubtaskTypeSelectListener != null) {
            onSubtaskTypeSelectListener = null;
        }
    }
}
