package com.certis_base_app.ui.onboarding.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.certis_base_app.R;
import com.certis_base_app.ui.custom.ClearableEditText;
import com.certis_base_app.ui.officerDashboard.OfficerMonitoringActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_password_signin)
public class SigninPasswordActivity extends AppCompatActivity {

    private static boolean IS_PASSWORD_VISIBLE = false;

    @ViewById(R.id.til_password)
    TextInputLayout mPasswordInputLayout;
    @ViewById(R.id.cet_password)
    ClearableEditText mPasswordClearInput;
    @ViewById(R.id.btn_next)
    Button mNextStepButton;
    @ViewById(R.id.tv_create_account)
    TextView mCreateAccount;
    @ViewById(R.id.iv_password_visibilty)
    ImageView mPasswordVisibility;
    @ViewById(R.id.tv_forgot_password)
    TextView mForgotPasssword;

    public TextWatcher mEmptyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String password = mPasswordClearInput.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                mNextStepButton.setEnabled(false);
                mNextStepButton.setAlpha(0.5f);
            } else {
                mNextStepButton.setEnabled(true);
                mNextStepButton.setAlpha(1.0f);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String password = mPasswordClearInput.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
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
        mPasswordClearInput.addTextChangedListener(mEmptyTextWatcher);
    }

    @Click(R.id.iv_password_visibilty)
    public void onPasswordVisibiltyChange() {
        if (!IS_PASSWORD_VISIBLE) {
            IS_PASSWORD_VISIBLE = true;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_shown);
            mPasswordClearInput.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            IS_PASSWORD_VISIBLE = false;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_hidden);
            mPasswordClearInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Click(R.id.btn_next)
    public void onNextButtonClick(){
        /*if(SigninCameraAuthActivity_.instance != null) {
            try {
                SigninCameraAuthActivity_.instance.finish();
            } catch (Exception e) {}
        }
        if(LandingActivity_.instance != null) {
            try {
                SigninCameraAuthActivity_.instance.finish();
            } catch (Exception e) {}
        }*/
        Intent dashboardIntent = new Intent(this, OfficerMonitoringActivity_.class);
        dashboardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(dashboardIntent);
    }
}
