package com.certis_base_app.ui.menuTaskManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.certis_base_app.R;

public class LatestTaskUpdatesFragment extends Fragment {

    public LatestTaskUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_task_updates, container, false);
    }

}
