package com.certis_base_app.ui.onboarding.registration;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.Group;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.custom_views.AutoFitTextureView;
import com.certis_base_app.utills.CameraUtil;
import com.certis_base_app.utills.SharedPrefHandler;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity(R.layout.activity_register_step3)
public class RegisterStep3Activity extends BaseActivity implements CameraUtil.CaptureClickListener {

    private static final String IMAGE_FILE_PATH_KEY = "Image File Path Key";
    private static final int CAMERA_PREVIEW_REQUEST_CODE = 101;
    private static final String IMAGE_FILE_UPLOAD_RESULT_KEY = "Image File Upload Result Key";

    private CameraUtil mCameraUtil;

    @ViewById(R.id.iv_preview_photo)
    ImageView mPreviewPhoto;
    @ViewById(R.id.iv_profile_photo_1)
    ImageView mProfilePhoto1;
    @ViewById(R.id.iv_profile_photo_2)
    ImageView mProfilePhoto2;
    @ViewById(R.id.aftv_camera_view)
    AutoFitTextureView mCameraAutoFitTextureView;
    @ViewById(R.id.iv_rotate_tablet)
    ImageView mRotateTabletImage;
    @ViewById(R.id.btn_capture_picture)
    Button mCaptureButton;
    @ViewById(R.id.group_image_capture_ui)
    Group mImageCaptureUI;
    @ViewById(R.id.group_photo_rejected)
    Group mPhotoRejectedUI;
    @ViewById(R.id.group_capture_btn)
    Group mCaptureButttonVisibilityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews() {
        if (mCameraUtil == null)
            mCameraUtil = new CameraUtil(this, mCameraAutoFitTextureView, this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().setStatusBarColor(Color.parseColor("#33000000"));

        this.setLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCameraUtil != null)
            mCameraUtil.makeCameraResourceOpen();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCameraUtil != null)
            mCameraUtil.makeCameraResourceClosed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraUtil != null)
            mCameraUtil.makeCameraResourceClosed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.setLayout();
    }

    private void setLayout() {
        int deviceOrientation = getResources().getConfiguration().orientation;
        switch (deviceOrientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                mRotateTabletImage.setVisibility(View.VISIBLE);
                mImageCaptureUI.setVisibility(View.GONE);
                mCaptureButttonVisibilityController.setVisibility(View.GONE);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                mRotateTabletImage.setVisibility(View.GONE);
                mImageCaptureUI.setVisibility(View.VISIBLE);
                mCaptureButttonVisibilityController.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Click(R.id.btn_capture_picture)
    public void takePicture() {
        if (!SharedPrefHandler.isProfilePhoto1Verified() || !SharedPrefHandler.isProfilePhoto2Verified())
            mCameraUtil.takePicture(String.valueOf(Singleton.getRandomNumber(1000, 9999999)));
        else
            startActivity(new Intent(RegisterStep3Activity.this, RegisterStep4Activity_.class));

        //Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PREVIEW_REQUEST_CODE) {
            //String uploadResult = data.getStringExtra(IMAGE_FILE_UPLOAD_RESULT_KEY);
            String imagePath = data!=null?data.getStringExtra(IMAGE_FILE_PATH_KEY):"";

            switch (resultCode) {
                case RESULT_OK:
                    if (!SharedPrefHandler.isProfilePhoto1Verified()){
                        SharedPrefHandler.setProfilePhoto1Verified(true);
                        mProfilePhoto1.setImageResource(R.drawable.iv_profile_photo_validation_success);
                        Singleton.showSnackbar(this, findViewById(R.id.cl_container),0, R.string.snackbar_photo_accepted, R.string.snackbar_action_dismiss, R.color.colorAccent);
                    }
                    else if (!SharedPrefHandler.isProfilePhoto2Verified()){
                        SharedPrefHandler.setProfilePhoto2Verified(true);
                        mProfilePhoto2.setImageResource(R.drawable.iv_profile_photo_validation_success);

                        RegisterStep3Activity.this.sendToPassowrdSetupPage();
                    }
                    break;

                case RESULT_CANCELED:
                    if (!SharedPrefHandler.isProfilePhoto1Verified()){
                        mProfilePhoto1.setImageResource(R.drawable.iv_profile_photo_validation_fail);
                    }
                    else if (!SharedPrefHandler.isProfilePhoto2Verified()){
                        mProfilePhoto2.setImageResource(R.drawable.iv_profile_photo_validation_fail);
                    }

                    SharedPrefHandler.setProfilePhoto1Verified(false);
                    SharedPrefHandler.setProfilePhoto2Verified(false);
                    mPhotoRejectedUI.setVisibility(View.VISIBLE);
                    mCaptureButttonVisibilityController.setVisibility(View.GONE);
                    mPreviewPhoto.setImageBitmap(BitmapFactory.decodeFile(imagePath));

                    break;
            }
        }
    }

    private void sendToPassowrdSetupPage() {
        //Intent landingIntent = new
    }

    @Click(R.id.btn_retake_photo)
    public void OnRetakeClick(){

        mProfilePhoto1.setImageResource(R.drawable.ic_camera_profile_photo_validation_default);
        mProfilePhoto2.setImageResource(R.drawable.ic_camera_profile_photo_validation_default);

        mPhotoRejectedUI.setVisibility(View.GONE);
        mCaptureButttonVisibilityController.setVisibility(View.VISIBLE);
        mPreviewPhoto.setVisibility(View.GONE);
    }

    @Override
    public void onCaptureClick(File imageFile) {
        Intent cameraSubmitPreviewIntent = new Intent(this, CameraPreviewSubmitActivity_.class);
        cameraSubmitPreviewIntent.putExtra(IMAGE_FILE_PATH_KEY, imageFile.getAbsolutePath());
        startActivityForResult(cameraSubmitPreviewIntent, CAMERA_PREVIEW_REQUEST_CODE);
    }
}
