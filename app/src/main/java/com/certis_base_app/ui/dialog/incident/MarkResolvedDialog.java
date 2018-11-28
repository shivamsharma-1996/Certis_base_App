package com.certis_base_app.ui.dialog.incident;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.certis_base_app.R;


public class MarkResolvedDialog extends Dialog {

    private TextView tvCancel, tvMarkResolved;
    private OnMarkResolveIncidentListener mOnMarkResolveIncidentListener;

    public interface OnMarkResolveIncidentListener {
        void onMarkResolveIncident();
    }

    public MarkResolvedDialog(@NonNull Context context, @NonNull OnMarkResolveIncidentListener onMarkResolveIncidentListener) {
        super(context);
        this.mOnMarkResolveIncidentListener = onMarkResolveIncidentListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View view = getLayoutInflater().inflate(R.layout.dialog_mark_resolved, null, false);
        setContentView(view);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvMarkResolved = view.findViewById(R.id.tv_resolve_incident);

        this.getWindow().setLayout(500, getWindow().getAttributes().height);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MarkResolvedDialog.this.dismiss();
            }
        });
        this.tvMarkResolved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MarkResolvedDialog.this.mOnMarkResolveIncidentListener.onMarkResolveIncident();
                MarkResolvedDialog.this.dismiss();
            }
        });
    }
}
