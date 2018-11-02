package com.certis_base_app.ui.onboarding.registration;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.constraint.Group;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;
import com.certis_base_app.ui.custom_views.AutoFitTextureView;
import com.certis_base_app.utills.CameraUtil;
import com.certis_base_app.utills.SharedPrefHandler;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EFragment(R.layout.fragment_camera_preview)
public class CameraPreviewFragment extends BaseFragment implements CameraUtil.CaptureClickListener{

    private CameraUtil mCameraUtil;

    @ViewById(R.id.aftv_camera_view)
    AutoFitTextureView mCameraAutoFitTextureView;
    @ViewById(R.id.iv_rotate_tablet)
    ImageView mRotateTabletImage;
    @ViewById(R.id.group_image_capture_ui)
    Group mImageCaptureUi;

    private ImageCapturedListener imageCapturedListener;

    public interface ImageCapturedListener{
        void onImageCaptured(File imageFilePath);
    }

    public CameraPreviewFragment() {
        // Required empty public constructor
    }

    public void setImageCapturedListener(ImageCapturedListener imageCapturedListener) {
        this.imageCapturedListener = imageCapturedListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (!(context instanceof CaptureClickListener)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        captureClickListener = (CaptureClickListener) context;*/
    }

    @AfterViews
    public void populateViews() {
        if (mCameraUtil == null)
            mCameraUtil = new CameraUtil(getContext(), mCameraAutoFitTextureView, this);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#33000000"));

        this.setLayout();
    }


    @Click(R.id.btn_capture_picture)
    public void takePicture(){
        if(!SharedPrefHandler.isProfilePhoto1Verified() || !SharedPrefHandler.isProfilePhoto2Verified())
        mCameraUtil.takePicture(String.valueOf(Singleton.getRandomNumber(1000,9999999)));
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
        /*if (mCameraUtil != null)
            mCameraUtil.makeCameraResourceClosed();*/
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
                mImageCaptureUi.setVisibility(View.GONE);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                mRotateTabletImage.setVisibility(View.GONE);
                mImageCaptureUi.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onCaptureClick(File imageFilePath) {
        imageCapturedListener.onImageCaptured(imageFilePath);
    }


}
