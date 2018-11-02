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
    private static final int CLOSE_BUTTON_RESULT_KEY = 99;

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

                if (!SharedPrefHandler.isProfilePhoto1Verified()) {
                    uploadResult = "success";
                } else if (!SharedPrefHandler.isProfilePhoto2Verified()) {
                    uploadResult = "success";
                }
                else{
                    uploadResult = "success";          //camera signin  case
                }
                this.sendSubmitResultToBack(uploadResult);
                return true;

            case android.R.id.home:
                //super.onBackPressed();
                this.sendSubmitResultToBack("cancel");
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void sendSubmitResultToBack(String uploadResult) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(IMAGE_FILE_PATH_KEY, imageFilePath);

        if(uploadResult.equals("success"))
        setResult(RESULT_OK, resultIntent);
        else if(uploadResult.equals("fail"))
            setResult(RESULT_CANCELED, resultIntent);
        else if(uploadResult.equals("cancel"))
            setResult(CLOSE_BUTTON_RESULT_KEY, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
