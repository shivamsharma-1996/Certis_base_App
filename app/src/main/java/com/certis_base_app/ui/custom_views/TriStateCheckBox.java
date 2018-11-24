package com.certis_base_app.ui.custom_views;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.certis_base_app.R;

public class TriStateCheckBox extends AppCompatCheckBox {
    public static final int INDETERMINATE = -1;
    public static final int UNCHECKED = 0;
    public static final int CHECKED = 1;
    public int state;

    public TriStateCheckBox(Context context) {
        super(context);
        init();
    }

    public TriStateCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TriStateCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        state = UNCHECKED;
        updateBtn();

        setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            // checkbox status is changed from uncheck to checked.
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (state) {
                    case INDETERMINATE:
                        state = UNCHECKED;
                        break;
                    case UNCHECKED:
                        state = CHECKED;
                        break;
                    case CHECKED:
                        state = INDETERMINATE;
                        break;
                }
                updateBtn();
            }
        });

    }

    private void updateBtn() {
        int btnDrawable = R.drawable.ic_checkbox_not_checked;
        switch (state) {
            case INDETERMINATE:
                btnDrawable = R.drawable.ic_indeterminate_checkbox;
                break;
            case UNCHECKED:
                btnDrawable = R.drawable.ic_checkbox_not_checked;
                break;
            case CHECKED:
                btnDrawable = R.drawable.ic_checkbox_checked;
                break;
        }
        setButtonDrawable(btnDrawable);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        updateBtn();
    }
}