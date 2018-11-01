package com.certis_base_app.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.certis_base_app.R;

public class BaseFragment extends Fragment {
    private Dialog mLoadingLayout;
    
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showLoading() {
        if (mLoadingLayout == null) {
            mLoadingLayout = new Dialog(getContext());
            mLoadingLayout.setContentView(R.layout.dialog_loading_layout);
            mLoadingLayout.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mLoadingLayout.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        mLoadingLayout.show();
    }

    public void hideLoading() {
        if (mLoadingLayout != null) {
            mLoadingLayout.dismiss();
            mLoadingLayout = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}