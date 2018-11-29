package com.certis_base_app.ui.officerDashboard;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.enums.OfficerTaskListType;
import com.certis_base_app.model.Officer;
import com.certis_base_app.model.OfficerProfileCard;
import com.certis_base_app.model.OfficerTaskWrapper;
import com.certis_base_app.model.Task;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EFragment(R.layout.fragment_officer_profile)
public class OfficerProfileFragment extends BaseFragment implements View.OnClickListener {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.rv_officer_tasks)
    RecyclerView rvOfficerTasks;
    private OfficerTasksAdapter mOfficerTaskAdapter;
    ArrayList<OfficerTaskWrapper> mOfficerTaskListWrapper;

    private InteractionListener mListener;
    public interface InteractionListener{
        void onTaskItemClick();
    }

    public OfficerProfileFragment() {
        // Required empty public constructor
    }

    public void setmListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @AfterViews
    public void populateViews() {

        OfficerProfileFragment.this.setUpToolbar();

            if (mOfficerTaskAdapter == null) {
                mOfficerTaskAdapter = new OfficerTasksAdapter(getContext(), getPopulateTaskList(), this);
                rvOfficerTasks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                rvOfficerTasks.setAdapter(mOfficerTaskAdapter);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setUpToolbar () {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OfficerProfileFragment.this.getActivity().onBackPressed();
                }
            });

            toolbar.inflateMenu(R.menu.menu_offficer_profile);
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
                mListener.onTaskItemClick();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null){
            mListener = null;
        }
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
}

