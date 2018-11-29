package com.certis_base_app.ui.officerDashboard;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.model.Officer;
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
    private OfficerDashboardAdapter mOfficerDashboardAdapter;

    private OfficerItemClickListener mOfficerItemClickListener;
    private List<Officer> officerList;

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
            mOfficerDashboardAdapter = new OfficerDashboardAdapter(getActivity(), officerList, this);
            rvOfficerDasboard.setLayoutManager(new LinearLayoutManager(getContext()));
            rvOfficerDasboard.setAdapter(this.mOfficerDashboardAdapter);
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
