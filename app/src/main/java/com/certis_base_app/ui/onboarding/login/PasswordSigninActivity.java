package com.certis_base_app.ui.onboarding.login;

import android.media.Image;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.ui.custom_views.ClearableEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_password_signin)
public class PasswordSigninActivity extends AppCompatActivity {

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


    @Click(R.id.iv_password_visibilty)
    public void onPasswordVisibiltyChange() {
        if (IS_PASSWORD_VISIBLE) {
            IS_PASSWORD_VISIBLE = false;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_shown);
            mPasswordClearInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            IS_PASSWORD_VISIBLE = true;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_hidden);
            mPasswordClearInput.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }


}
