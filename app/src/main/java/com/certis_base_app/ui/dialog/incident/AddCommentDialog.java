package com.certis_base_app.ui.dialog.incident;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;


public class AddCommentDialog extends Dialog {
    private ImageView ivClose;
    private EditText etCommentText;
    private TextView tvAddComment;
    private OnAddCommentListener mOnAddCommentListener;

    public interface OnAddCommentListener {
        void onAddComment(String arg);
    }

    public AddCommentDialog(@NonNull Context context, @NonNull OnAddCommentListener onAddCommentListener) {
        super(context);
        this.mOnAddCommentListener = onAddCommentListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_comment, null, false);
        setContentView(view);

        this.ivClose = view.findViewById(R.id.iv_close);
        this.etCommentText = view.findViewById(R.id.et_comment_text);
        this.tvAddComment = view.findViewById(R.id.tv_add);
        this.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommentDialog.this.dismiss();
            }
        });

        this.etCommentText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                tvAddComment.setAlpha(editable.length() > 0 ? 1: 0.5f);
            }
        });

        this.tvAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAddCommentListener.onAddComment(AddCommentDialog.this.etCommentText.getText().toString());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mOnAddCommentListener != null) {
            mOnAddCommentListener = null;
        }
    }
}
