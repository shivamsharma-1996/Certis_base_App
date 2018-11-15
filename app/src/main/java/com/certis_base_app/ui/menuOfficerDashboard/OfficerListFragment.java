package com.certis_base_app.ui.menuOfficerDashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;
import com.certis_base_app.model.OfficerWrapper;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EFragment(R.layout.fragment_officer_list)
public class OfficerListFragment extends BaseFragment implements View.OnClickListener{

    @ViewById(R.id.rv_officer_dashboard)
    RecyclerView rvOfficerDasboard;

    private OfficerMonitoringAdapter mOfficerMonitoringAdapter;
    private List<OfficerWrapper> officerList;

    private OfficerItemClickListener mOfficerItemClickListener;

    public interface OfficerItemClickListener{
        void onOfficerItemClick();
    }


    public void setOfficerItemClickListener(OfficerItemClickListener mOfficerItemClickListener) {
        this.mOfficerItemClickListener = mOfficerItemClickListener;
    }

    public OfficerListFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews(){
            mOfficerMonitoringAdapter = new OfficerMonitoringAdapter(getActivity(), officerList, this);
            rvOfficerDasboard.setLayoutManager(new LinearLayoutManager(getContext()));
            rvOfficerDasboard.setAdapter(this.mOfficerMonitoringAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cl_officer_monitoring:
                mOfficerItemClickListener.onOfficerItemClick();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mOfficerItemClickListener!=null){
            mOfficerItemClickListener = null;
        }
    }
}
