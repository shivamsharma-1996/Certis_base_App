package com.certis_base_app.ui.menuTaskManagement;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;
import com.certis_base_app.ui.createTask.CreateTaskActivity;
import com.certis_base_app.ui.createTask.CreateTaskActivity_;
import com.certis_base_app.ui.dialog.template.TaskTemplateDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_all_tasks)
public class AllTasksFragment extends BaseFragment implements View.OnClickListener{
    private static final int REQUEST_CODE_TASK_CREATE = 101;
    @ViewById(R.id.rv_all_tasks)
    RecyclerView rvAllTasks;
    private AllTasksAdapter allTasksAdapter;

    private InteractionListener mListener;
    public interface InteractionListener{
        void onTaskItemClick();
    }

    public void setmListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    public AllTasksFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews(){
        allTasksAdapter = new AllTasksAdapter(getActivity(), this);
        rvAllTasks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvAllTasks.setAdapter(allTasksAdapter);
    }

    @Override
    public void onClick(View v) {
        mListener.onTaskItemClick();
    }

    @Click(R.id.iv_create_task)
    public void onCreateTask(){
        new TaskTemplateDialog(AllTasksFragment.this.getContext(), new TaskTemplateDialog.OnTemplateSelectListener() {
            @Override
            public void onTemplateSelected(String template) {
                Intent intent = new Intent(AllTasksFragment.this.getContext(), CreateTaskActivity_.class);
                AllTasksFragment.this.startActivityForResult(intent, REQUEST_CODE_TASK_CREATE);
            }
        }).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null){
            mListener = null;
        }
    }
}
