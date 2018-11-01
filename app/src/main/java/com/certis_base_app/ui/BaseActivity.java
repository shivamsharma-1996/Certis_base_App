package com.certis_base_app.ui;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.certis_base_app.R;

public class BaseActivity extends AppCompatActivity {
    private Dialog loadingLayout;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActionBar == null && getSupportActionBar()!=null) {
            mActionBar = getSupportActionBar();
        }
    }

    protected void setActionBarTitle(String title) {
        if(mActionBar!=null)
        mActionBar.setTitle(title);
    }

    protected void setBackButtionVisible(Boolean bool){
        mActionBar.setDisplayShowHomeEnabled(bool);
        mActionBar.setDisplayHomeAsUpEnabled(bool);
        mActionBar.setHomeButtonEnabled(bool);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showLoading() {
        if (loadingLayout == null) {
            loadingLayout = new Dialog(this);
            loadingLayout.setContentView(R.layout.dialog_loading_layout);
            loadingLayout.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loadingLayout.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        loadingLayout.show();
    }

    public void hideLoading() {
        if (loadingLayout != null) {
            loadingLayout.dismiss();
            loadingLayout = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
