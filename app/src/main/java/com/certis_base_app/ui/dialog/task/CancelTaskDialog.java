package com.certis_base_app.ui.dialog.task;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelTaskDialog extends DialogFragment {

    TextView tvKeepTask, tvCancelTask;

    public CancelTaskDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cancel_task, null);
        tvCancelTask  = dialogView.findViewById(R.id.tv_cancel_task);
        tvKeepTask  = dialogView.findViewById(R.id.tv_keep_task);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        tvCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvKeepTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
}
