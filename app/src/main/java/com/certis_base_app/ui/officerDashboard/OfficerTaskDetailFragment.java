package com.certis_base_app.ui.officerDashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;


@EFragment(R.layout.fragment_task_detail)
public class OfficerTaskDetailFragment extends BaseFragment implements View.OnClickListener{

    @ViewById(R.id.rv_subtasks_list)
    RecyclerView rvSubtasksList;
    OfficerSubtaskAdapter officerSubtaskAdapter;

    private InteractionListener mListener;

    @Override
    public void onClick(View v) {

    }

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
        officerSubtaskAdapter = new OfficerSubtaskAdapter(OfficerTaskDetailFragment.this);
        rvSubtasksList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSubtasksList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvSubtasksList.setAdapter(officerSubtaskAdapter);
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


