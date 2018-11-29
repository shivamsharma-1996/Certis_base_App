package com.certis_base_app.ui.dialog.message;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.certis_base_app.R;

public class BroadcastDialog extends Dialog {
    private ImageView ivClose;
    private ImageButton ibSend;
    private EditText etInputMsg;

    private InteractionListener mListener;

    public interface InteractionListener {
        void onClose();

        void onSend(String broadcastMsg);
    }

    public BroadcastDialog(@NonNull Context context, InteractionListener interactionListener) {
        super(context);
        this.mListener = interactionListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_broadcast_message, null);
        this.setContentView(view);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = getContext().getResources().getDimensionPixelSize(R.dimen.height_dialog_broadcast);
        getWindow().setAttributes(attributes);
        ivClose = view.findViewById(R.id.iv_close);
        ibSend = view.findViewById(R.id.ib_send);
        etInputMsg = view.findViewById(R.id.et_input_message);

        etInputMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                ibSend.setAlpha(editable.length() > 0 ? 1 : 0.5f);
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClose();
            }
        });

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSend(etInputMsg.getText().toString());
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener = null;
        }
    }
}
