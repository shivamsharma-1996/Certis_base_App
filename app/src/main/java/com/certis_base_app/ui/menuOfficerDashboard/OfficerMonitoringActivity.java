package com.certis_base_app.ui.menuOfficerDashboard;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.dialog.FilterDiaog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_officer_monitoring)
public class OfficerMonitoringActivity extends BaseActivity implements OfficerListFragment.OfficerItemClickListener,
        OfficerMapFragment.InteractionListener,OfficerProfileFragment.InteractionListener  {

    @ViewById(R.id.toolbar)
    Toolbar mToolBar;
    @ViewById(R.id.guideline_divider)
    Guideline mGuidelineDivider;
    @ViewById(R.id.frame_map)
    FrameLayout mMapFragment;
    @ViewById(R.id.cl_officer_monitoring)
    ConstraintLayout mOfficerMonitoring;

    private boolean isFullScreen = false;

    OfficerListFragment_ officerListFragment;
    OfficerMapFragment_ officerMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void populateViews() {
        mToolBar.setTitle(R.string.title_officer_monitoring);
        setDashboardView();
    }

    private void setDashboardView() {
        officerListFragment = new OfficerListFragment_();
        officerListFragment.setOfficerItemClickListener(this);
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_officer_list, officerListFragment, officerListFragment.getClass().getSimpleName()).
                commit();

        officerMapFragment = new OfficerMapFragment_();
        officerMapFragment.setFullScreenListener(this);
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_map, officerMapFragment, officerMapFragment.getClass().getSimpleName()).
                commit();
    }

    @Override
    public void onOfficerItemClick() {

        OfficerProfileFragment_ officerProfileFragment = new OfficerProfileFragment_();
        officerProfileFragment.setmListener(this);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_left);
        ft.replace(R.id.frame_officer_list, officerProfileFragment, officerProfileFragment.getClass().getSimpleName()).
                addToBackStack(officerProfileFragment.getClass().getSimpleName()).
                commit();
        mGuidelineDivider.setGuidelinePercent(0.40f);
    }

    @Override
    public void onFullScreenClick() {
        if (!isFullScreen){
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
        }
    }

    @Override
    public void onFilterClick() {
        FilterDiaog filterDiaog = new FilterDiaog();
        filterDiaog.show(this.getSupportFragmentManager(), filterDiaog.getClass().getSimpleName());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(this.getSupportFragmentManager().findFragmentByTag(officerListFragment.getClass().getSimpleName())!=null)
            mGuidelineDivider.setGuidelinePercent(0.45f);

    }

    @Override
    public void onTaskItemClick() {
        OfficerTaskDetailFragment_ officerTaskDetailFragment = new OfficerTaskDetailFragment_();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        ft.replace(R.id.frame_task_detail, officerTaskDetailFragment, officerTaskDetailFragment.getClass().getSimpleName()).
                addToBackStack(officerTaskDetailFragment.getClass().getSimpleName()).
                commit();
    }
}
