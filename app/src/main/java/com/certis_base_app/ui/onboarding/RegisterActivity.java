package com.certis_base_app.ui.onboarding;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.certis_base_app.utills.Constants.PROGRESS_EACH_STEP_VALUE;

@EActivity(R.layout.activity_main)
public class RegisterActivity extends BaseActivity {

    @ViewById(R.id.vf_registration)
    ViewFlipper mRegistrationViewFlipper;
    @ViewById(R.id.til_staff_id)
    TextInputLayout staffIdInputLayout;
    @ViewById(R.id.til_phone_no)
    TextInputLayout phoneNoInputLayout;
    @ViewById(R.id.cet_staff_id)
    EditText mStaffIdClearInput;
    @ViewById(R.id.cet_phone_no)
    EditText mPhoneNoClearInput;
    @ViewById(R.id.btn_next)
    Button mNextStepButton;
    @ViewById(R.id.spinner_countryCodes)
    Spinner mCountrySelector;
    @ViewById(R.id.pb_registration)
    ProgressBar mRegistrationProgressBar;
    @ViewById(R.id.tv_current_registration_step)
    TextView mRegistrationCurrentStep;

    private ArrayAdapter<String> countrySelectorAdapter;
    private List<String> countryNameList;
    private int newProgressValue = 0;

    @ViewById(R.id.et_otp_1)
    EditText mOtpEdittext1;
    @ViewById(R.id.et_otp_2)
    EditText mOtpEdittext2;
    @ViewById(R.id.et_otp_3)
    EditText mOtpEdittext3;
    @ViewById(R.id.et_otp_4)
    EditText mOtpEdittext4;
    @ViewById(R.id.et_otp_5)
    EditText mOtpEdittext5;
    @ViewById(R.id.et_otp_6)
    EditText mOtpEdittext6;
    @ViewById(R.id.group_otp_success_ui)
    Group mOtpSuccessUI;
    @ViewById(R.id.group_default_otp_botttom_ui)
    Group mOtpdefaultBottomUI;
    private String otpResponse = "123456", otpUserInput = "";
    private Drawable otpSelectedEdittextBackground, otpUnSelectedEdittextBackground;

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
        String staffId = mStaffIdClearInput.getText().toString().trim();
        String phoneNo = mPhoneNoClearInput.getText().toString().trim();

        if (TextUtils.isEmpty(staffId) || TextUtils.isEmpty(phoneNo)) {
            mNextStepButton.setEnabled(false);
            mNextStepButton.setAlpha(0.5f);
        } else {
            mNextStepButton.setEnabled(true);
            mNextStepButton.setAlpha(1.0f);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews() {
        if (countryNameList == null) {
            countryNameList = new ArrayList<>();
        }
        if (countrySelectorAdapter == null) {
            countrySelectorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getPopulatedCountryList());
            countrySelectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCountrySelector.setAdapter(countrySelectorAdapter);
        }
        if(otpSelectedEdittextBackground == null)
            otpSelectedEdittextBackground = getResources().getDrawable(R.drawable.bg_otp_semi_round_color_primary_selected);
        if(otpUnSelectedEdittextBackground == null)
            otpUnSelectedEdittextBackground = getResources().getDrawable(R.drawable.bg_otp_semi_round_gray_unselected);

        mCountrySelector.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) mCountrySelector.getSelectedView()).setTextColor(ResourcesCompat.getColor(getResources(), R.color.textPrimary, null));
            }
        });

        //Setting Listeners
        mStaffIdClearInput.addTextChangedListener(mEmptyTextWatcher);
        mPhoneNoClearInput.addTextChangedListener(mEmptyTextWatcher);


        mOtpEdittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext1.getText().toString().length() == 1) {
                    mOtpEdittext2.requestFocus();
//                    mOtpEdittext1.setBackground(otpUnSelectedEdittextBackground);
//                    mOtpEdittext2.setBackground(otpSelectedEdittextBackground);
                }
            }
        });

        mOtpEdittext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (mOtpEdittext2.getText().toString().length() == 0 && count == 1) {
                    //resetOtpUnSelectedEdittextBackground();
                    mOtpEdittext1.requestFocus();
                    //mOtpEdittext1.setBackground(otpSelectedEdittextBackground);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext2.getText().toString().length() == 1) {
                    mOtpEdittext3.requestFocus();
                    verifyOtp();
//                    mOtpEdittext2.setBackground(otpUnSelectedEdittextBackground);
//                    mOtpEdittext3.setBackground(otpSelectedEdittextBackground);
                }
            }
        });

        mOtpEdittext3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (mOtpEdittext3.getText().toString().length() == 0 && count == 1) {
                    //resetOtpUnSelectedEdittextBackground();
                    mOtpEdittext2.requestFocus();
                    //mOtpEdittext2.setBackground(otpSelectedEdittextBackground);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext3.getText().toString().length() == 1) {
                    mOtpEdittext4.requestFocus();
                    verifyOtp();
//                    mOtpEdittext3.setBackground(otpUnSelectedEdittextBackground);
//                    mOtpEdittext4.setBackground(otpSelectedEdittextBackground);
                }
            }
        });

        mOtpEdittext4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (mOtpEdittext4.getText().toString().length() == 0 && count == 1) {
                    //resetOtpUnSelectedEdittextBackground();
                    mOtpEdittext3.requestFocus();
                    //mOtpEdittext3.setBackground(otpSelectedEdittextBackground);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext4.getText().toString().length() == 1) {
                    mOtpEdittext5.requestFocus();
                    verifyOtp();
//                    mOtpEdittext4.setBackground(otpUnSelectedEdittextBackground);
////                    mOtpEdittext5.setBackground(otpSelectedEdittextBackground);
                }
            }
        });

        mOtpEdittext5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (mOtpEdittext5.getText().toString().length() == 0 && count == 1) {
                    //resetOtpUnSelectedEdittextBackground();
                    mOtpEdittext4.requestFocus();
                    // mOtpEdittext4.setBackground(otpSelectedEdittextBackground);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext5.getText().toString().length() == 1) {
                    mOtpEdittext6.requestFocus();
                    verifyOtp();

//                    mOtpEdittext5.setBackground(otpUnSelectedEdittextBackground);
//                    mOtpEdittext6.setBackground(otpSelectedEdittextBackground);
                }
            }
        });

        mOtpEdittext6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (mOtpEdittext6.getText().toString().length() == 0 && count == 1) {
                    //resetOtpUnSelectedEdittextBackground();
                    mOtpEdittext5.requestFocus();
                    //  mOtpEdittext5.setBackground(otpSelectedEdittextBackground);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mOtpEdittext6.getText().toString().length() == 1) {
                    verifyOtp();
                }
            }
        });
    }

    private void verifyOtp() {

        otpUserInput = mOtpEdittext1.getText().toString() + mOtpEdittext2.getText().toString() +
                mOtpEdittext3.getText().toString() + mOtpEdittext4.getText().toString() +
                mOtpEdittext5.getText().toString() + mOtpEdittext6.getText().toString();

        if(otpUserInput.length() == 6)
        {
            if (otpResponse.equals(otpUserInput))
            {
                Singleton.hideKeyboard(RegisterActivity.this);

                setOtpUIVerified();
                resetOtpUnSelectedEdittextBackground();
                this.mOtpEdittext1.setEnabled(false);
                this.mOtpEdittext2.setEnabled(false);
                this.mOtpEdittext3.setEnabled(false);
                this.mOtpEdittext4.setEnabled(false);
                this.mOtpEdittext5.setEnabled(false);
                this.mOtpEdittext6.setEnabled(false);
            }
            else
            {
                Singleton.hideKeyboard(RegisterActivity.this);
                Singleton.showSnackbar(RegisterActivity.this,  findViewById(R.id.container_scrollview),
                        R.drawable.snackbar_error_red, R.string.otp_snackbar_failed_message_text, R.string.otp_snackbar_action_text, R.color.snackBarActionColor );
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Click(R.id.tv_resend_code)
    public void resendCode() {
        resetOtpView();
    }

    private void setOtpUIVerified() {
        int otpBoxTextColorVerified = getResources().getColor(R.color.greenMixedSuccess);
        this.mOtpEdittext1.setTextColor(otpBoxTextColorVerified);
        this.mOtpEdittext2.setTextColor(otpBoxTextColorVerified);
        this.mOtpEdittext3.setTextColor(otpBoxTextColorVerified);
        this.mOtpEdittext4.setTextColor(otpBoxTextColorVerified);
        this.mOtpEdittext5.setTextColor(otpBoxTextColorVerified);
        this.mOtpEdittext6.setTextColor(otpBoxTextColorVerified);
        this.findViewById(R.id.view_otp_success).setBackground(getResources().getDrawable(R.drawable.bg_otp_success_semi_round_outline));
        mOtpSuccessUI.setVisibility(View.VISIBLE);
        mOtpdefaultBottomUI.setVisibility(View.GONE);
    }

    public void resetOtpUnSelectedEdittextBackground(){
        mOtpEdittext1.setBackground(otpUnSelectedEdittextBackground);
        mOtpEdittext2.setBackground(otpUnSelectedEdittextBackground);
        mOtpEdittext3.setBackground(otpUnSelectedEdittextBackground);
        mOtpEdittext4.setBackground(otpUnSelectedEdittextBackground);
        mOtpEdittext5.setBackground(otpUnSelectedEdittextBackground);
        mOtpEdittext6.setBackground(otpUnSelectedEdittextBackground);
    }

    public void resetOtpView() {
        int otpBoxTextColorUnVerified = getResources().getColor(R.color.textNormal);
        this.mOtpEdittext1.setTextColor(otpBoxTextColorUnVerified);
        this.mOtpEdittext2.setTextColor(otpBoxTextColorUnVerified);
        this.mOtpEdittext3.setTextColor(otpBoxTextColorUnVerified);
        this.mOtpEdittext4.setTextColor(otpBoxTextColorUnVerified);
        this.mOtpEdittext5.setTextColor(otpBoxTextColorUnVerified);
        this.mOtpEdittext6.setTextColor(otpBoxTextColorUnVerified);
        mOtpEdittext1.setText(null);
        mOtpEdittext2.setText(null);
        mOtpEdittext3.setText(null);
        mOtpEdittext4.setText(null);
        mOtpEdittext5.setText(null);
        mOtpEdittext6.setText(null);
        this.mOtpEdittext1.setEnabled(true);
        this.mOtpEdittext2.setEnabled(true);
        this.mOtpEdittext3.setEnabled(true);
        this.mOtpEdittext4.setEnabled(true);
        this.mOtpEdittext5.setEnabled(true);
        this.mOtpEdittext6.setEnabled(true);
        this.findViewById(R.id.view_otp_success).setBackground(null);
        mOtpdefaultBottomUI.setVisibility(View.VISIBLE);
        mOtpSuccessUI.setVisibility(View.GONE);

        mOtpEdittext1.requestFocus();
        Singleton.openKeyboard(this);
    }

    @Click(R.id.btn_next)
    public void nextButtonClick() {
        String staffID = staffIdInputLayout.getEditText().getText().toString();
        String phoneNo = phoneNoInputLayout.getEditText().getText().toString();

        if (TextUtils.isEmpty(staffID)) {
            staffIdInputLayout.setError("Please enter a valid email");
            return;
        }
        if (TextUtils.isEmpty(phoneNo)) {
            phoneNoInputLayout.setError("Please enter a valid phone number");
            staffIdInputLayout.setError(null);
            return;
        }
        staffIdInputLayout.setError(null);
        staffIdInputLayout.setError(null);

        resetOtpView();
        mRegistrationViewFlipper.showNext();  //changing view
        updateProgressBar();
    }


    private void updateProgressBar() {
        switch (ProgressLevel.lookupByCode(mRegistrationProgressBar.getProgress())) {
            case STEP1:
                newProgressValue = mRegistrationProgressBar.getProgress() + PROGRESS_EACH_STEP_VALUE;
                mRegistrationCurrentStep.setText(getString(R.string.text_registration_step_2));
                break;
            case STEP2:
                newProgressValue = mRegistrationProgressBar.getProgress() - PROGRESS_EACH_STEP_VALUE;
                mRegistrationCurrentStep.setText(getString(R.string.text_registration_step_1));
                break;
        }
        mRegistrationProgressBar.setProgress(newProgressValue);
    }


    @Override
    public void onBackPressed() {
        switch (mRegistrationViewFlipper.getCurrentView().getId()) {
            case R.id.layout_registration_step_1:
                super.onBackPressed();
                break;
            case R.id.layout_registration_step_2:
                mRegistrationViewFlipper.showPrevious();
                updateProgressBar();
                break;
            default:
                super.onBackPressed();
        }
    }

    private List<String> getPopulatedCountryList() {
        if (countryNameList != null) {
            countryNameList.add("Country");
            countryNameList.add("Singapore");
            countryNameList.add("India");
            countryNameList.add("Malaysia");
        }
        return countryNameList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //registrationViewFlipper.removeOnLayoutChangeListener(onStepChangeListener);
    }
}
