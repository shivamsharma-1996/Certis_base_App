package com.certis_base_app.ui.onboarding.registration;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.utills.SharedPrefHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_camera_submit_preview)
public class CameraPreviewSubmitActivity extends BaseActivity {

    private static final String IMAGE_FILE_PATH_KEY = "Image File Path Key";
    private static final String IMAGE_FILE_UPLOAD_RESULT_KEY = "Image File Upload Result Key";


    private String imageFilePath;

    @ViewById(R.id.iv_profile_photo)
    ImageView profilePhotoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews() {
        setBackButtionVisible(true);
        setActionBarTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            imageFilePath = bundle.getString(IMAGE_FILE_PATH_KEY);
        }

        if (imageFilePath != null) {
            profilePhotoImage.setImageBitmap(BitmapFactory.decodeFile(imageFilePath));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_photo_submit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String uploadResult = null;
        switch (item.getItemId()) {
            case R.id.nav_submit_photo:

                checkStep3CompletedorNot();

                if (!SharedPrefHandler.isProfilePhoto1Verified()) {
                    uploadResult = "success";
                    /*if (uploadResult.equals("success"))
                        SharedPrefHandler.setProfilePhoto1Verified(true);
                    else if (uploadResult.equals("fail"))
                        SharedPrefHandler.setProfilePhoto1Verified(false);*/
                } else if (!SharedPrefHandler.isProfilePhoto2Verified()) {
                    uploadResult = "success";
                    /*if (uploadResult.equals("success"))
                        SharedPrefHandler.setProfilePhoto2Verified(true);
                    else if (uploadResult.equals("fail"))
                        SharedPrefHandler.setProfilePhoto2Verified(false);*/
                }
                this.sendSubmitResultToBack(uploadResult);
                return true;

            case android.R.id.home:
                super.onBackPressed();

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkStep3CompletedorNot() {
        if (!SharedPrefHandler.isProfilePhoto1Verified() || !SharedPrefHandler.isProfilePhoto2Verified())
            startActivity(new Intent(CameraPreviewSubmitActivity.this, RegisterStep4Activity_.class));
    }

    private void sendSubmitResultToBack(String uploadResult) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(IMAGE_FILE_PATH_KEY, imageFilePath);
        //resultIntent.putExtra(IMAGE_FILE_UPLOAD_RESULT_KEY, uploadResult);

        if(uploadResult.equals("success"))
        setResult(RESULT_OK, resultIntent);
        else if(uploadResult.equals("fail"))
            setResult(RESULT_CANCELED, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
