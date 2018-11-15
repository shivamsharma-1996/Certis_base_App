package com.certis_base_app.ui.menuOfficerDashboard;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;
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

import static com.certis_base_app.utills.MapUtil.createCustomMarker;


@EFragment(R.layout.fragment_officer_map)
public class OfficerMapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    SupportMapFragment mMapFragment;
    boolean isExpand = false;

    private InteractionListener mFullScreenClickListener;

    public interface InteractionListener{
        void onFullScreenClick();
        void onFilterClick();
    }

    public OfficerMapFragment() {
        // Required empty public constructor
    }

    public void setFullScreenListener(InteractionListener mListener) {
        this.mFullScreenClickListener = mListener;
    }

    @AfterViews
    public void populateViews(){
        if(mMapFragment == null){
            mMapFragment = SupportMapFragment.newInstance();
            mMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    final LatLng customMarkerLocationOne = new LatLng(28.5429519, 77.2374969);
                    mMap.addMarker(new MarkerOptions().position(customMarkerLocationOne).icon(BitmapDescriptorFactory.fromBitmap(
                            createCustomMarker(getActivity(),R.drawable.ic_register_flag_india, R.layout.layout_custom_marker_brief, "Shivam Sharma"))));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(customMarkerLocationOne));

                    mMap.setOnMarkerClickListener(OfficerMapFragment.this);
                }
            });
        }

        OfficerMapFragment.this.getChildFragmentManager().
                beginTransaction().
                add(R.id.frame_map, mMapFragment, mMapFragment.getClass().getSimpleName()).
                commit();
    }

    @Click(R.id.fab_fullscreen)
    public void onFullScreenClick(){
        mFullScreenClickListener.onFullScreenClick();
    }

    @Click(R.id.cv_officer_monitoring_filter)
    public void onFilterClick(){
        mFullScreenClickListener.onFilterClick();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(!isExpand)
        {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(getActivity(),R.drawable.ic_register_flag_india,R.layout.layout_custom_marker_detail, "Shivam Sharma")));
            isExpand = true;
        }
        else
        {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(getActivity(),R.drawable.ic_register_flag_india,R.layout.layout_custom_marker_brief, "Shivam Sharma")));
            isExpand = false;
        }
        return true;
    }
}
