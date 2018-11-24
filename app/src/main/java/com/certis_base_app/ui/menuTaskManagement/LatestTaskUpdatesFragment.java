package com.certis_base_app.ui.menuTaskManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.certis_base_app.R;
import com.certis_base_app.model.LatestTaskUpdate;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_latest_task_updates)
public class LatestTaskUpdatesFragment extends Fragment {

    @ViewById(R.id.rv_latest_task_updates)
    RecyclerView rvLatestTaskUpdates;
    LatestTaskUpdatesAdapter latestTaskUpdatesAdapter;

    public LatestTaskUpdatesFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews(){
        latestTaskUpdatesAdapter = new LatestTaskUpdatesAdapter(this.getPopulatedList(), this.getActivity());
        rvLatestTaskUpdates.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLatestTaskUpdates.setAdapter(latestTaskUpdatesAdapter);
    }

    public List<LatestTaskUpdate> getPopulatedList() {
        List<LatestTaskUpdate> latestTaskUpdates = new ArrayList<>();
        ///latestTaskUpdates.add(new LatestTaskUpdate());
        return latestTaskUpdates;
    }
}
