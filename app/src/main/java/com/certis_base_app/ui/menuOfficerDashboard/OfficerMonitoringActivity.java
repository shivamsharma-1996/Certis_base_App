package com.certis_base_app.ui.menuOfficerDashboard;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.dialog.filter.FilterDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_officer_monitoring)
public class OfficerMonitoringActivity extends BaseActivity implements InteractionListener  {
    @ViewById(R.id.toolbar)
    Toolbar mToolBar;
    @ViewById(R.id.frame_map)
    FrameLayout mMapFragment;
    @ViewById(R.id.cl_officer_monitoring)
    ConstraintLayout mOfficerMonitoring;

    private OfficerTaskFragment_ officerTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews() {
        mToolBar.setTitle(R.string.title_officer_monitoring);
        setSupportActionBar(mToolBar);
        setDashboardView();
    }

    private void setDashboardView() {
        OfficerDashboardFragment_ officerDashboardFragment = new OfficerDashboardFragment_();
        officerDashboardFragment.setInteractionListener(this);
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_container, officerDashboardFragment, officerDashboardFragment.getClass().getSimpleName()).
                commit();

        /*officerMapFragment = new OfficerMapFragment_();
        officerMapFragment.setFullScreenListener(this);
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_map, officerMapFragment, officerMapFragment.getClass().getSimpleName()).
                commit();*/
    }

    public void onFilterClick() {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(this.getSupportFragmentManager(), filterDialog.getClass().getSimpleName());
    }


    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        if(this.getSupportFragmentManager().findFragmentByTag(officerTaskFragment.getClass().getSimpleName())!=null)
            ft.remove(officerTaskFragment).commit();
      *//*  if(this.getSupportFragmentManager().findFragmentByTag(officerListFragment.getClass().getSimpleName())!=null)
            mGuidelineDivider.setGuidelinePercent(0.45f);*//*
    }*/

    @Override
    public void onItemClick(String officerId) {
        officerTaskFragment =  OfficerTaskFragment_.newInstance(officerId);
        officerTaskFragment.setInteractionListener(this);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        ft.replace(R.id.frame_container, officerTaskFragment, officerTaskFragment.getClass().getSimpleName()).
                addToBackStack(officerTaskFragment.getClass().getSimpleName()).
                commit();
    }


    public void onFullScreenClick() {
        /*if (!isFullScreen){
           isFullScreen = true;
            mGuidelineDivider.setGuidelinePercent(0.0f);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mOfficerMonitoring);
            constraintSet.connect(R.id.frame_map,ConstraintSet.START,R.id.fragment_sidebar,ConstraintSet.END,0);
            constraintSet.applyTo(mOfficerMonitoring);
        }
        else {
            isFullScreen = false;
            mGuidelineDivider.setGuidelinePercent(0.45f);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mOfficerMonitoring);
            constraintSet.connect(R.id.frame_map,ConstraintSet.START,R.id.guideline_divider,ConstraintSet.START,0);
            constraintSet.applyTo(mOfficerMonitoring);
        }*/
    }



    public void onTaskItemClick() {
       /* OfficerTaskDetailFragment_ officerTaskDetailFragment = new OfficerTaskDetailFragment_();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        ft.replace(R.id.frame_task_detail, officerTaskDetailFragment, officerTaskDetailFragment.getClass().getSimpleName()).
                addToBackStack(officerTaskDetailFragment.getClass().getSimpleName()).
                commit();*/
    }

    public void onOfficerItemClick() {
       /*OfficerProfileFragment_ officerProfileFragment = new OfficerProfileFragment_();
        officerProfileFragment.setmListener(this);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_left);
        ft.replace(R.id.frame_officer_list, officerProfileFragment, officerProfileFragment.getClass().getSimpleName()).
                addToBackStack(officerProfileFragment.getClass().getSimpleName()).
                commit();
        mGuidelineDivider.setGuidelinePercent(0.40f);*/
    }


}
