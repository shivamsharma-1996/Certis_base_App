package com.certis_base_app.ui.onboarding.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.ui.onboarding.registration.RegisterStep1Step2Activity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_landing)
public class LandingActivity extends AppCompatActivity {

    @ViewById(R.id.til_staff_id)
    TextInputLayout staffIdInputLayout;
    @ViewById(R.id.cet_staff_id)
    EditText mStaffIdClearInput;
    @ViewById(R.id.btn_next)
    Button mNextStepButton;
    @ViewById(R.id.tv_create_account)
    TextView mCreateAccount;

    public TextWatcher mEmptyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String staffId = mStaffIdClearInput.getText().toString().trim();
            if (TextUtils.isEmpty(staffId)) {
                mNextStepButton.setEnabled(false);
                mNextStepButton.setAlpha(0.5f);
            } else {
                mNextStepButton.setEnabled(true);
                mNextStepButton.setAlpha(1.0f);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews(){
        //Setting Listeners
        mStaffIdClearInput.addTextChangedListener(mEmptyTextWatcher);
    }

    @Click(R.id.tv_create_account)
    public void onCreateAccountClick(){
        this.startActivity(new Intent(LandingActivity.this, RegisterStep1Step2Activity_.class));
    }
}
