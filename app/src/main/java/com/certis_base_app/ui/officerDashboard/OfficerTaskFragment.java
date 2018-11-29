package com.certis_base_app.ui.officerDashboard;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.Guideline;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.model.Task;
import com.certis_base_app.ui.BaseFragment;
import com.certis_base_app.ui.dialog.task.CancelTaskDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.certis_base_app.utills.MapUtil.createCustomMarker;

@EFragment(R.layout.fragment_officer_task)
public class OfficerTaskFragment extends BaseFragment implements View.OnClickListener, GoogleMap.OnMarkerClickListener, OfficerTaskDetailFragment.InteractionListener{
    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @ViewById(R.id.nav_view_side_panel_right)
    View mSidePanelView;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.rv_officer_tasks)
    RecyclerView rvOfficerTasks;
    @ViewById(R.id.guideline)
    Guideline mGuidelineDivider;

    private OfficerTasksAdapter mOfficerTaskAdapter;

    private GoogleMap mMap;
    SupportMapFragment mMapFragment;

    private static final String OFFICER_KEY = "OFFICER_KEY";
    private String officerId;

    private InteractionListener mListener;
    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    public OfficerTaskFragment(){
        // Required empty public constructor
    }

    public static OfficerTaskFragment_ newInstance(String arg) {
        Bundle bundle = new Bundle();
        bundle.putString(OFFICER_KEY, arg);
        OfficerTaskFragment_ officerTaskFragment = new OfficerTaskFragment_();
        officerTaskFragment.setArguments(bundle);
        return officerTaskFragment;
    }

    @AfterViews
    public void populateViews(){
        OfficerTaskFragment.this.setUpToolbar();
        OfficerTaskFragment.this.setMapView();

        if(getArguments()!=null){
            officerId = getArguments().getString(OFFICER_KEY);
        }

        if (mOfficerTaskAdapter == null) {
            mOfficerTaskAdapter = new OfficerTasksAdapter(getContext(), getPopulateTaskList(), this);
            rvOfficerTasks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvOfficerTasks.setAdapter(mOfficerTaskAdapter);
        }
        //((OfficerMonitoringActivity)getActivity()).getSupportActionBar().hide();
    }

    private void setMapView() {
        if(mMapFragment == null){
            mMapFragment = SupportMapFragment.newInstance();
            mMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    final LatLng customMarkerLocationOne = new LatLng(28.5429519, 77.2374969);
                    mMap.addMarker(new MarkerOptions().position(customMarkerLocationOne).icon(BitmapDescriptorFactory.fromBitmap(
                            createCustomMarker(getActivity(),R.mipmap.ic_launcher_round, R.layout.layout_custom_marker_detail, officerId))));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(customMarkerLocationOne));

                    mMap.setOnMarkerClickListener(OfficerTaskFragment.this);
                }
            });
        }
        OfficerTaskFragment.this.getChildFragmentManager().
                beginTransaction().
                add(R.id.frame_map, mMapFragment, mMapFragment.getClass().getSimpleName()).
                commit();
    }

    private void setUpToolbar () {
        toolbar.inflateMenu(R.menu.menu_offficer_profile);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OfficerTaskFragment.this.getActivity().onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_search:
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_officer_profile_tasks:
                //mListener.onTaskItemClick();
                OfficerTaskDetailFragment_ officerTaskDetailFragment = new OfficerTaskDetailFragment_();
                officerTaskDetailFragment.setInteractionListener(this);
                this.getChildFragmentManager().beginTransaction().
                        replace(R.id.frame_side_panel_right, officerTaskDetailFragment, officerTaskDetailFragment.getClass().getSimpleName()).
                        commit();
                mDrawerLayout.openDrawer(mSidePanelView);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    public List<Task> getPopulateTaskList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("0","0", "0", 0));
        taskList.add(new Task("Open Gate 27","Completed", "07:00 - 07:45 AM", 100));
        taskList.add(new Task("Unattended Bag Detected","1 Incomplete Subtask", "08:00 - 08:45 AM", 10));
        taskList.add(new Task("0","0", "0", 0));
        taskList.add(new Task("Open Gate 27","3/5 Completed", "08:00 - 08:15 AM", 60));
        taskList.add(new Task("Open Gate C22","3 Subtasks", "09:30 - 9:55 AM", 100));
        return taskList;
    }


    @Click(R.id.fab_officer_message)
    public void onFabMessageClick(){
        /*  DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mSidePanelView.getLayoutParams();
        params.width = 1000;
        mSidePanelView.setLayoutParams(params);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OfficerMessageFragment_ officerChatFragment = new OfficerMessageFragment_();
                getChildFragmentManager().beginTransaction().
                        replace(R.id.frame_side_panel_right, officerChatFragment, officerChatFragment.getClass().getSimpleName()).
                        commit();
                mDrawerLayout.openDrawer(mSidePanelView);
            }
        }, 2000);*/

        OfficerMessageFragment_ officerChatFragment = new OfficerMessageFragment_();
        getChildFragmentManager().beginTransaction().
                replace(R.id.frame_side_panel_right, officerChatFragment, officerChatFragment.getClass().getSimpleName()).
                commit();
        mDrawerLayout.openDrawer(mSidePanelView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null){
            mListener = null;
        }
        //((OfficerMonitoringActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void oncloseClick() {
        if(mDrawerLayout.isDrawerOpen(mSidePanelView)){
            mDrawerLayout.closeDrawer(mSidePanelView);
        }
    }

    @Override
    public void onCancelTaskClick() {
        CancelTaskDialog cancelTaskDialog = new CancelTaskDialog();
        cancelTaskDialog.show(this.getChildFragmentManager(), cancelTaskDialog.getClass().getSimpleName());
    }
}


