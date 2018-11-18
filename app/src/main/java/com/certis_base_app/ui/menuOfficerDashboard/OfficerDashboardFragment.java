package com.certis_base_app.ui.menuOfficerDashboard;


import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.model.Officer;
import com.certis_base_app.model.OfficerWrapper;
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

@EFragment(R.layout.fragment_officer_monitoring)
public class OfficerDashboardFragment extends Fragment implements View.OnClickListener, GoogleMap.OnMarkerClickListener{
    @ViewById(R.id.rv_officer_dashboard)
    RecyclerView rvOfficerDasboard;
    @ViewById(R.id.guideline)
    Guideline mGuidelineDivider;
    @ViewById(R.id.cl_officer_dashboard)
    ConstraintLayout mOfficerMonitoring;

    private GoogleMap mMap;
    SupportMapFragment mMapFragment;
    private OfficerDashboardAdapter mOfficerDashboardAdapter;
    List<Officer> populateList=new ArrayList<>();
    private boolean isExpand = false;
    private boolean isFullScreen = false;

    private InteractionListener mListener;


    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }


    public OfficerDashboardFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
        OfficerDashboardFragment.this.setMapView();

        if(mOfficerDashboardAdapter == null){
            mOfficerDashboardAdapter = new OfficerDashboardAdapter(getActivity(), getPopulateList(), this);
            rvOfficerDasboard.setLayoutManager(new LinearLayoutManager(getContext()));
            rvOfficerDasboard.setAdapter(this.mOfficerDashboardAdapter);
        }
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
                            createCustomMarker(getActivity(),R.mipmap.ic_launcher, R.layout.layout_custom_marker_brief, "Shivam Sharma"))));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(customMarkerLocationOne));

                    mMap.setOnMarkerClickListener(OfficerDashboardFragment.this);
                }
            });
        }
        OfficerDashboardFragment.this.getChildFragmentManager().
                beginTransaction().
                add(R.id.frame_map, mMapFragment, mMapFragment.getClass().getSimpleName()).
                commit();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(!isExpand)
        {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(getActivity(),R.mipmap.ic_launcher,R.layout.layout_custom_marker_detail, "Shivam Sharma")));
            isExpand = true;
        }
        else
        {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(getActivity(),R.mipmap.ic_launcher,R.layout.layout_custom_marker_brief, "Shivam Sharma")));
            isExpand = false;
        }
        return true;
    }

    @Click(R.id.fab_fullscreen)
    public void onFullScreenClick() {
        if (!isFullScreen){
           isFullScreen = true;
            mGuidelineDivider.setGuidelinePercent(0.0f);
        }
        else {
            isFullScreen = false;
            mGuidelineDivider.setGuidelinePercent(0.45f);
        }
    }

    @Click(R.id.cv_officer_monitoring_filter)
    public void onFilterClick(){
        mListener.onFilterClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cl_officer_monitoring:
                mListener.onItemClick(populateList.get(((Integer) view.getTag()).intValue()).getName());
                break;
        }
    }

    public List<Officer> getPopulateList() {
        populateList.add(new Officer("a"));
        populateList.add(new Officer("b"));
        populateList.add(new Officer("c"));
        populateList.add(new Officer("d"));
        populateList.add(new Officer("e"));
        populateList.add(new Officer("f"));
        populateList.add(new Officer("g"));
        populateList.add(new Officer("h"));
        return populateList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener != null){
            mListener = null;
        }
    }
}
