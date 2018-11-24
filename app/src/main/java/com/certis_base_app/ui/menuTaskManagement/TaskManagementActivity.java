package com.certis_base_app.ui.menuTaskManagement;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_task_management)
public class TaskManagementActivity extends BaseActivity implements AllTasksFragment_.InteractionListener{
    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @AfterViews
    public void populateViews(){
        TaskManagementActivity.this.setupToolbar();
        this.setDashboardView();
    }

    private void setupToolbar() {
        this.mToolbar.setTitle(getString(R.string.title_task_management));
        this.setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_offficer_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setDashboardView() {
        AllTasksFragment_ allTasksFragment = new AllTasksFragment_();
        allTasksFragment.setmListener(TaskManagementActivity.this);
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_all_tasks, allTasksFragment, allTasksFragment.getClass().getSimpleName()).
                commit();
    }

    @Override
    public void onTaskItemClick() {

        mDrawerLayout.openDrawer(Gravity.END);
    }
}
