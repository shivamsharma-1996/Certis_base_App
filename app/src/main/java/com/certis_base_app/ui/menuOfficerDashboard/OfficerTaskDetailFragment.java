package com.certis_base_app.ui.menuOfficerDashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_task_detail)
public class OfficerTaskDetailFragment extends Fragment {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    public OfficerTaskDetailFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
        OfficerTaskDetailFragment.this.setUpToolbar();
    }

    private void setUpToolbar() {
        toolbar.inflateMenu(R.menu.menu_offficer_task_detail);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OfficerTaskDetailFragment.this.getActivity().onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_cancel_task:
                        return true;
                }
                return true;
            }
        });
    }
}
