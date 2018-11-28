package com.certis_base_app.ui.onboarding.registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
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

@EActivity(R.layout.activity_register_step1step2)
public class RegisterStep1Step2Activity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;

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

    private CountryCodeSpinnerAdapter countrySelectorAdapter;
    private List<CountryCode> countryNameList;
    private String countryValue;
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

        if (TextUtils.isEmpty(staffId) || TextUtils.isEmpty(phoneNo) || (Integer)mCountrySelector.getSelectedItem()== 0) {
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
        setActionBarTitle(getString(R.string.title_account_registration));
    }

    @AfterViews
    public void populateViews() {
        if (countryNameList == null) {
            countryNameList = new ArrayList<>();
        }
        if (countrySelectorAdapter == null) {
            Integer[] integers=new Integer[]{0, R.drawable.ic_register_flag_india};
            String[] strings=new String[]{"Country","India"};
            countrySelectorAdapter = new CountryCodeSpinnerAdapter(this, integers, strings);
            //countrySelectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCountrySelector.setAdapter(countrySelectorAdapter);
        }
        if (otpSelectedEdittextBackground == null)
            otpSelectedEdittextBackground = getResources().getDrawable(R.drawable.bg_otp_semi_round_color_primary_selected);
        if (otpUnSelectedEdittextBackground == null)
            otpUnSelectedEdittextBackground = getResources().getDrawable(R.drawable.bg_otp_semi_round_gray_unselected);


        //Setting Listeners
        mStaffIdClearInput.addTextChangedListener(mEmptyTextWatcher);
        mPhoneNoClearInput.addTextChangedListener(mEmptyTextWatcher);
        mCountrySelector.setOnItemSelectedListener(this);

        mOtpEdittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Click(R.id.tv_resend_code)
    public void resendCode() {
        resetOtpView();
    }

    @Click(R.id.btn_next)
    public void nextButtonClick() {
        setActionBarTitle(getString(R.string.title_mobile_verification));
        setBackButtionVisible(true);

        Singleton.hideKeyboard(RegisterStep1Step2Activity.this);

//        String staffID = staffIdInputLayout.getEditText().getText().toString();
//        String phoneNo = phoneNoInputLayout.getEditText().getText().toString();


        if(((TextView) mCountrySelector.getSelectedView()).getText().equals("Country"))
        {
           // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }


        resetOtpView();
        mRegistrationViewFlipper.showNext();  //changing view
        updateProgressBar();
    }

    @Override
    public void onBackPressed() {
        switch (mRegistrationViewFlipper.getCurrentView().getId()) {
            case R.id.layout_registration_step_1:
                super.onBackPressed();
                break;
            case R.id.layout_registration_step_2:
                setActionBarTitle(getString(R.string.title_account_registration));
                setBackButtionVisible(false);
                mRegistrationViewFlipper.showPrevious();
                updateProgressBar();
                break;
            default:
                super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setActionBarTitle(getString(R.string.title_account_registration));
                setBackButtionVisible(false);
                mRegistrationViewFlipper.showPrevious();
                updateProgressBar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void verifyOtp() {
        otpUserInput = mOtpEdittext1.getText().toString() + mOtpEdittext2.getText().toString() +
                mOtpEdittext3.getText().toString() + mOtpEdittext4.getText().toString() +
                mOtpEdittext5.getText().toString() + mOtpEdittext6.getText().toString();

        if (otpUserInput.length() == 6) {
            if (otpResponse.equals(otpUserInput)) {
                Singleton.hideKeyboard(RegisterStep1Step2Activity.this);

                setVerifiedSuccessOtpUI();
                resetOtpUnSelectedEdittextBackground();
                this.mOtpEdittext1.setEnabled(false);
                this.mOtpEdittext2.setEnabled(false);
                this.mOtpEdittext3.setEnabled(false);
                this.mOtpEdittext4.setEnabled(false);
                this.mOtpEdittext5.setEnabled(false);
                this.mOtpEdittext6.setEnabled(false);

                requestCameraPermission();

            } else {
                Singleton.hideKeyboard(RegisterStep1Step2Activity.this);
                Singleton.showSnackbar(RegisterStep1Step2Activity.this, findViewById(R.id.container_scrollview),
                        R.drawable.ic_snackbar_error_red, R.string.snackbar_failed_message_text, R.string.snackbar_action_dismiss, R.color.snackBarActionColor);
            }
        }
    }

    private void setVerifiedSuccessOtpUI() {
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

    public void resetOtpUnSelectedEdittextBackground() {
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

    private void updateProgressBar() {
        switch (EnumProgressLevel.lookupByCode(mRegistrationProgressBar.getProgress())) {
            case STEP1:
                newProgressValue = mRegistrationProgressBar.getProgress() + PROGRESS_EACH_STEP_VALUE;
                mRegistrationCurrentStep.setText(getString(R.string.text_registration_step_2));
                break;
            case STEP2:
                newProgressValue = mRegistrationProgressBar.getProgress() - PROGRESS_EACH_STEP_VALUE;
                mRegistrationCurrentStep.setText(getString(R.string.text_registration_step_1));
                break;
            case STEP3:
                break;
            case STEP4:
                break;
            default:
                break;
        }
        mRegistrationProgressBar.setProgress(newProgressValue);
    }

//    private List<CountryCode> getPopulatedCountryList() {
//        if (countryNameList != null) {
//            countryNameList.add(new CountryCode("Country", 0));
//            countryNameList.add(new CountryCode("Singapore", R.drawable.ic_register_flag_india));
//           /* countryNameList.add("India");
//            countryNameList.add("Malaysia");*/
//        }
//        return countryNameList;
//    }

    private void requestCameraPermission() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(RegisterStep1Step2Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterStep1Step2Activity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CODE_CAMERA_PERMISSION);
                }
                else
                    RegisterStep1Step2Activity.this.startActivity(new Intent(RegisterStep1Step2Activity.this, RegisterStep3Activity_.class));
            }
        }, 500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    RegisterStep1Step2Activity.this.startActivity(new Intent(RegisterStep1Step2Activity.this, RegisterStep3Activity_.class));
                } else {
                    requestCameraPermission();
                }
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //registrationViewFlipper.removeOnLayoutChangeListener(onStepChangeListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (TextUtils.isEmpty(mStaffIdClearInput.getText().toString().trim()) || TextUtils.isEmpty(mPhoneNoClearInput.getText().toString().trim()) || (Integer)mCountrySelector.getSelectedItem()== 0) {
            mNextStepButton.setEnabled(false);
            mNextStepButton.setAlpha(0.5f);
        }
        else {
        mNextStepButton.setEnabled(true);
        mNextStepButton.setAlpha(1.0f);
    }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
