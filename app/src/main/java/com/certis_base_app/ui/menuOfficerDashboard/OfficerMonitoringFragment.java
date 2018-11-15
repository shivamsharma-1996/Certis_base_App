package com.certis_base_app.ui.menuOfficerDashboard;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.certis_base_app.utills.MapUtil.createCustomMarker;

@EFragment(R.layout.fragment_officer_monitoring)
public class OfficerMonitoringFragment extends Fragment implements View.OnClickListener, GoogleMap.OnMarkerClickListener{
    @ViewById(R.id.rv_officer_dashboard)
    RecyclerView rvOfficerDasboard;

    private GoogleMap mMap;
    SupportMapFragment mMapFragment;

    private OfficerMonitoringAdapter mOfficerMonitoringAdapter;
    private List<OfficerWrapper> officerList;

    boolean isExpand = false;


    public OfficerMonitoringFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof InteractionListener) {
//            interactionListener = (InteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement InteractionListener");
//        }
//    }

    @AfterViews
    public void populateViews() {
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

                    mMap.setOnMarkerClickListener(OfficerMonitoringFragment.this);
                }
            });
        }

        OfficerMonitoringFragment.this.getChildFragmentManager().
                beginTransaction().
                add(R.id.frame_map, mMapFragment, mMapFragment.getClass().getSimpleName()).
                commit();

        if(mOfficerMonitoringAdapter == null){
            mOfficerMonitoringAdapter = new OfficerMonitoringAdapter(getActivity(), officerList, this);
            rvOfficerDasboard.setLayoutManager(new LinearLayoutManager(getContext()));
            rvOfficerDasboard.setAdapter(this.mOfficerMonitoringAdapter);
        }
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


    @Override
    public void onClick(View v) {

    }
}
