package com.certis_base_app.ui.onboarding.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.custom_views.AutoFitTextureView;
import com.certis_base_app.ui.onboarding.CameraPreviewSubmitActivity_;
import com.certis_base_app.utills.CameraUtil;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity(R.layout.activity_camera_authentication)
public class SigninCameraAuthActivity extends BaseActivity implements CameraUtil.CaptureClickListener {

    public static SigninCameraAuthActivity instance = null;

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;

    private static final String IMAGE_FILE_PATH_KEY = "Image File Path Key";
    private static final int CAMERA_PREVIEW_REQUEST_CODE = 101;
    private static final int CLOSE_BUTTON_RESULT_KEY = 99;
    private CameraUtil mCameraUtil;

    @ViewById(R.id.iv_preview_photo)
    ImageView mPreviewPhoto;
    @ViewById(R.id.aftv_camera_view)
    AutoFitTextureView mCameraAutoFitTextureView;
    @ViewById(R.id.iv_rotate_tablet)
    ImageView mRotateTabletImage;
    @ViewById(R.id.btn_capture_picture)
    Button mCaptureButton;
    @ViewById(R.id.group_image_capture_ui)
    android.support.constraint.Group mImageCaptureUI;
    @ViewById(R.id.group_photo_rejected)
    android.support.constraint.Group mPhotoRejectedUI;
    @ViewById(R.id.group_capture_btn)
    android.support.constraint.Group mCaptureButttonVisibilityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @AfterViews
    public void populateViews(){
        requestCameraPermission();
    }

    private void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(SigninCameraAuthActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SigninCameraAuthActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_CAMERA_PERMISSION);
        }
        else {
            if (mCameraUtil == null)
                mCameraUtil = new CameraUtil(SigninCameraAuthActivity.this, mCameraAutoFitTextureView, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCameraUtil != null)
            mCameraUtil.makeCameraResourceOpen();
    }

    @Click(R.id.btn_capture_picture)
    public void takePicture() {
            mCameraUtil.takePicture(String.valueOf(Singleton.getRandomNumber(1000, 9999999)));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
        instance = null;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PREVIEW_REQUEST_CODE) {
            //String uploadResult = data.getStringExtra(IMAGE_FILE_UPLOAD_RESULT_KEY);
            String imagePath = data!=null?data.getStringExtra(IMAGE_FILE_PATH_KEY):"";

            switch (resultCode) {
                case RESULT_OK:
                    startActivity(new Intent(SigninCameraAuthActivity.this, SigninPasswordActivity_.class));
                    break;

                case RESULT_CANCELED:

                    mPhotoRejectedUI.setVisibility(View.VISIBLE);
                    mCaptureButttonVisibilityController.setVisibility(View.GONE);
                    mPreviewPhoto.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    break;

                case CLOSE_BUTTON_RESULT_KEY:
                    break;

                default:
                    break;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (mCameraUtil == null)
                        mCameraUtil = new CameraUtil(SigninCameraAuthActivity.this, mCameraAutoFitTextureView, this);

                    this.setLayout();
                } else {
                    requestCameraPermission();
                }
                return;
            }
        }
    }

    @Override
    public void onCaptureClick(File imageFile) {
        Intent cameraSubmitPreviewIntent = new Intent(this, CameraPreviewSubmitActivity_.class);
        cameraSubmitPreviewIntent.putExtra(IMAGE_FILE_PATH_KEY, imageFile.getAbsolutePath());
        startActivityForResult(cameraSubmitPreviewIntent, CAMERA_PREVIEW_REQUEST_CODE);
    }
}
