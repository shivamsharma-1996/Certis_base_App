package com.certis_base_app.ui.dialog.photo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.certis_base_app.R;

public class ShowPhotoDialog extends Dialog {

    private TextView tvCancel;

    public ShowPhotoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_show_photo, null,false);
        setContentView(view);
        this.getWindow().setLayout(500, getWindow().getAttributes().height);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPhotoDialog.this.dismiss();
            }
        });
    }


}
