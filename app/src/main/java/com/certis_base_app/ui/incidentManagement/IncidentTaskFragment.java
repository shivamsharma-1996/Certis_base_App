package com.certis_base_app.ui.incidentManagement;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_incident_task_associated)
public class IncidentTaskFragment extends BaseFragment implements View.OnClickListener {

    @ViewById(R.id.rv_incident_task_list)
    RecyclerView rvIncidentTaskList;
    private IncidentTaskAdapter incidentTaskAdapter;

    private InteractionListener mListener;
    public interface InteractionListener{
        void onDrawerCloseClick();
    }


    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    @AfterViews
    public void populateViews(){
        incidentTaskAdapter = new IncidentTaskAdapter(this);
        rvIncidentTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIncidentTaskList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvIncidentTaskList.setAdapter(incidentTaskAdapter);
    }

    @Click(R.id.iv_back)
    public void onBackButtonClick(){
        mListener.onDrawerCloseClick();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null){
            mListener = null;
        }
    }
}
