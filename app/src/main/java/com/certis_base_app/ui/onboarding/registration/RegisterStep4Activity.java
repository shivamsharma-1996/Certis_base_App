package com.certis_base_app.ui.onboarding.registration;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.custom_views.ClearableEditText;
import com.certis_base_app.ui.onboarding.login.LandingActivity_;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Pattern;


@EActivity(R.layout.activity_register_step4)
public class RegisterStep4Activity extends BaseActivity {

    private static boolean IS_PASSWORD_VISIBLE = false;

    @ViewById(R.id.pb_registration)
    ProgressBar mProgressBar;
    @ViewById(R.id.btn_create_password)
    Button mCreatePasswordbutton;
    @ViewById(R.id.til_password1)
    TextInputLayout mPasswordInputtlayout1;
    @ViewById(R.id.til_password2)
    TextInputLayout mPasswordInputtlayout2;
    @ViewById(R.id.cet_password1)
    ClearableEditText mPasswordClearInput1;
    @ViewById(R.id.cet_password2)
    ClearableEditText mPasswordClearInput2;
    @ViewById(R.id.iv_password_visibilty)
    ImageView mPasswordVisibility;
    @ViewById(R.id.tv_password_criteria_fail)
    TextView mPasswordCriteriaFail;
    @ViewById(R.id.tv_password_not_matched)
    TextView mPasswordNotMatched;

    public TextWatcher mEmptyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkInputsForEmptyValues();
        }
    };

    public void checkInputsForEmptyValues() {
        password = mPasswordClearInput1.getText().toString().trim();
        confirmPassword = mPasswordClearInput2.getText().toString().trim();

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            mCreatePasswordbutton.setEnabled(false);
            mCreatePasswordbutton.setAlpha(0.5f);
        } else {
            mCreatePasswordbutton.setEnabled(true);
            mCreatePasswordbutton.setAlpha(1.0f);
        }
    }

    String password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(getString(R.string.title_password_creation));
        /*Singleton.showSnackbar(this, findViewById(R.id.scroll_container), 0,
      R.string.camera_photo_submitted, R.string.snackbar_action_dismiss, R.color.colorAccent);*/
    }

    @AfterViews
    public void populateViews() {
        Singleton.showSnackbar(this, findViewById(R.id.scroll_container), 0,
                R.string.camera_photo_submitted, R.string.snackbar_action_dismiss, R.color.colorAccent);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(75);
                    }
                }, 500
        );
        //setting listeners
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCreatePasswordbutton.addTextChangedListener(mEmptyTextWatcher);
    }

    @Click(R.id.iv_password_visibilty)
    public void onPasswordVisibiltyChange() {
        if (IS_PASSWORD_VISIBLE) {
            IS_PASSWORD_VISIBLE = false;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_shown);
            mPasswordClearInput1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mPasswordClearInput2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            IS_PASSWORD_VISIBLE = true;
            mPasswordVisibility.setImageResource(R.drawable.ic_password_hidden);
            mPasswordClearInput1.setInputType(InputType.TYPE_CLASS_TEXT);
            mPasswordClearInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    @Click(R.id.btn_create_password)
    public void onCreatePasswordClick() {
        if (validatePassword()){
            //go to signin
            Intent landingIntent = new Intent(RegisterStep4Activity.this, LandingActivity_.class);
            landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(landingIntent);
            finish();
        }
    }

    private boolean validatePassword() {
        password = mPasswordClearInput1.getText().toString().trim();
        confirmPassword = mPasswordClearInput2.getText().toString().trim();

        if ((password.length() <= 8) ) {
            mPasswordCriteriaFail.setVisibility(View.VISIBLE);
            mPasswordNotMatched.setVisibility(View.GONE);
            return false;
        }
        else if (!password.equals(confirmPassword)) {
            mPasswordNotMatched.setVisibility(View.VISIBLE);
            mPasswordCriteriaFail.setVisibility(View.GONE);
            return false;
        }
        mPasswordNotMatched.setVisibility(View.GONE);
        mPasswordCriteriaFail.setVisibility(View.GONE);
        return true;
    }
}