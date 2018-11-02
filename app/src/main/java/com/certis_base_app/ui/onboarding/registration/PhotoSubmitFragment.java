package com.certis_base_app.ui.onboarding.registration;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_photo_submit)
public class PhotoSubmitFragment extends BaseFragment {

    private static final String IMAGE_FILE_PATH_KEY = "Image File Path Key";
    private String imageFilePath;

    @ViewById(R.id.iv_profile_photo)
    ImageView profilePhotoImage;
    @ViewById(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    public PhotoSubmitFragment() {
        // Required empty public constructor
    }

    public static PhotoSubmitFragment_ getInstance(String imageFilePath) {
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_FILE_PATH_KEY, imageFilePath);
        PhotoSubmitFragment_ fragment = new PhotoSubmitFragment_();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Here notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);
    }

    @AfterViews
    public void populateViews() {

        //setOnBackClickListener();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setTitle("");
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayShowCustomEnabled(true);

/*
        mToolbar.setNavigationIcon(R.drawable.ic_close_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().onBackPressed();

                CameraPreviewFragment_ cameraPreviewFragment = new CameraPreviewFragment_();
                cameraPreviewFragment.setImageCapturedListener((RegisterStep3Activity)getActivity());
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, cameraPreviewFragment, CameraPreviewFragment_.class.getName() )
                        //.addToBackStack(PhotoSubmitFragment_.class.getName())
                        .commit();
            }
        });*/
        if (getArguments() != null)
            imageFilePath = getArguments().getString(IMAGE_FILE_PATH_KEY);

        profilePhotoImage.setImageBitmap(BitmapFactory.decodeFile(imageFilePath));


        getView().setFocusableInTouchMode(true);
        getView().setOnKeyListener( new View.OnKeyListener(){
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event ){
                if( keyCode == KeyEvent.KEYCODE_BACK ){
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();                                              //First clear current all the menu items
        inflater.inflate(R.menu.menu_photo_submit, menu);          //Add the new menu items
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_submit_photo:
                Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                //getActivity().onBackPressed();
                setOnBackClickListener();
            default:
                Toast.makeText(getContext(), "hyeeeeee", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setOnBackClickListener() {
        View rootView = this.getView();;
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), RegisterStep3Activity.class));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
