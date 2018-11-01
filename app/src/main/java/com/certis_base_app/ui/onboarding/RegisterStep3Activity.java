package com.certis_base_app.ui.onboarding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.utills.CameraUtil;

import java.io.File;

public class RegisterStep3Activity extends BaseActivity implements CameraPreviewFragment.ImageCapturedListener{

    private CameraPreviewFragment_ cameraPreviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);

        if (savedInstanceState == null ) {
            if(cameraPreviewFragment == null) {
                cameraPreviewFragment = new CameraPreviewFragment_();
                cameraPreviewFragment.setImageCapturedListener(this);
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, cameraPreviewFragment, CameraPreviewFragment_.class.getName() )
                    .commit();
        }
    }


    @Override
    public void onImageCaptured(File imageFilePath) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, PhotoSubmitFragment_.getInstance(imageFilePath.getAbsolutePath()), PhotoSubmitFragment_.class.getName())
                .addToBackStack(PhotoSubmitFragment_.class.getName())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getSupportFragmentManager().findFragmentByTag(CameraPreviewFragment_.class.getName())!=null){
                    super.onBackPressed();
                }
                else {
                    getSupportActionBar().setHomeAsUpIndicator(null);
                    cameraPreviewFragment = new CameraPreviewFragment_();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, cameraPreviewFragment, CameraPreviewFragment_.class.getName() )
                            .commit();
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(this, "cccccccccccccccc", Toast.LENGTH_SHORT).show();
            if(getSupportFragmentManager().findFragmentByTag(CameraPreviewFragment_.class.getName())!=null){
                super.onBackPressed();
            }
            else if(getSupportFragmentManager().findFragmentByTag(PhotoSubmitFragment.class.getName())!=null){
              /*  cameraPreviewFragment = (CameraPreviewFragment_) getSupportFragmentManager().findFragmentByTag(PhotoSubmitFragment.class.getName());
                FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(cameraPreviewFragment);
                fragTransaction.attach(cameraPreviewFragment);
                fragTransaction.commit();

                        .replace(R.id.frame_container, cameraPreviewFragment, CameraPreviewFragment_.class.getName() )
                        .commit();
            }

        Fragment currentFragment = getFragmentManager().findFragmentByTag("FRAGMENT");
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commit();*/}
    }
}
