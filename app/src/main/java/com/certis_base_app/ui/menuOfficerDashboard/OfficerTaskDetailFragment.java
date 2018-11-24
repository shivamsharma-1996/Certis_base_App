package com.certis_base_app.ui.menuOfficerDashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_task_detail)
public class OfficerTaskDetailFragment extends Fragment {

    private InteractionListener mListener;
    public interface InteractionListener{
        void oncloseClick();
        void onCancelTaskClick();
    }

    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    public OfficerTaskDetailFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
    }

    @Click(R.id.iv_close)
    public void onCloseClick(){
        mListener.oncloseClick();
    }

    @Click(R.id.tv_cancel_task)
    public void onCancelTask(){
        mListener.onCancelTaskClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null){
            mListener = null;
        }
    }
}
