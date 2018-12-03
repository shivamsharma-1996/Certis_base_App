package com.certis_base_app.ui.incidentManagement;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.createTask.CreateTaskActivity_;
import com.certis_base_app.ui.dialog.incident.AddCommentDialog;
import com.certis_base_app.ui.dialog.incident.MarkResolvedDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_incident_management)
public class IncidentManagementActivity extends BaseActivity implements View.OnClickListener, IncidentDetailFragment.InteractionListener,
        IncidentCommentFragment.InteractionListener , IncidentTaskFragment_.InteractionListener{

    private final int CREATE_TASK_REQUEST_CODE = 101;

    @ViewById(R.id.drawer_layout_incident_management)
    DrawerLayout mDrawerLayout;
    @ViewById(R.id.nv_incident_management)
    NavigationView mNavSidePanelView;

    @ViewById(R.id.rv_incident_report)
    RecyclerView rvIncidentReport;
    private IncidentManagementAdapter incidentManagementAdapter;
    private Dialog addCommentDialog = null;

    IncidentDetailFragment_ incidentDetailFragment;
    IncidentCommentFragment_ incidentCommentFragment = new IncidentCommentFragment_();

    @AfterViews
    public void populateViews(){
        incidentManagementAdapter = new IncidentManagementAdapter(IncidentManagementActivity.this, this);
        rvIncidentReport.setLayoutManager(new LinearLayoutManager(this));
        rvIncidentReport.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvIncidentReport.setAdapter(incidentManagementAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_item_list_incident_report:
                IncidentManagementActivity.this.showIncidentReportPanel();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_offficer_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void oncloseClick() {
        if(mDrawerLayout.isDrawerOpen(mNavSidePanelView)){
            mDrawerLayout.closeDrawer(mNavSidePanelView);
        }
    }

    @Override
    public void onAddCommentClick() {
        this.showCommentDialog();
    }

    @Override
    public void onMarkResolvedClick() {
        new MarkResolvedDialog(IncidentManagementActivity.this, new MarkResolvedDialog.OnMarkResolveIncidentListener() {
            @Override
            public void onMarkResolveIncident() {

            }
        }).show();
    }

    @Override
    public void onCreateTaskClick() {
        Intent createTaskIntent = new Intent(IncidentManagementActivity.this, CreateTaskActivity_.class);
        startActivityForResult(createTaskIntent, CREATE_TASK_REQUEST_CODE);
    }

    @Override
    public void onTaskAssociatedClick() {
       // mDrawerLayout.openDrawer(mNavSidePanelView);

     /*   DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mNavSidePanelView.getLayoutParams();
        params.width = 900;
        mNavSidePanelView.setLayoutParams(params);*/

        IncidentTaskFragment_ incidentTaskAssociatedFragment = new IncidentTaskFragment_();
        incidentTaskAssociatedFragment.setInteractionListener(IncidentManagementActivity.this);
        IncidentManagementActivity.this.getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_incident_report, incidentTaskAssociatedFragment, incidentTaskAssociatedFragment.getClass().getSimpleName()).
                commit();
        mDrawerLayout.openDrawer(mNavSidePanelView);

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 500);*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CREATE_TASK_REQUEST_CODE){
            switch (resultCode){
                case RESULT_OK:
            }
        }
    }


    private void showCommentDialog() {
        if(IncidentManagementActivity.this.getSupportFragmentManager().findFragmentByTag(incidentDetailFragment.getClass().getSimpleName())!=null){
            FragmentManager fm = this.getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            if(mDrawerLayout.isDrawerOpen(mNavSidePanelView))
                mDrawerLayout.closeDrawer(mNavSidePanelView);
        }

        addCommentDialog = new AddCommentDialog(this, new AddCommentDialog.OnAddCommentListener() {
            @Override
            public void onAddComment(String commentMessage) {
                addCommentDialog.dismiss();
               /* if(mDrawerLayout.isDrawerOpen(mNavSidePanelView))
                mDrawerLayout.closeDrawer(mNavSidePanelView);
*/
                if(IncidentManagementActivity.this.getSupportFragmentManager().findFragmentByTag(incidentCommentFragment.getClass().getSimpleName())==null){
                    incidentCommentFragment = incidentCommentFragment.getInstance(commentMessage);
                    incidentCommentFragment.setInteractionListener(IncidentManagementActivity.this);
                    FragmentTransaction ft = IncidentManagementActivity.this.getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right).
                            replace(R.id.frame_incident_report, incidentCommentFragment, incidentCommentFragment.getClass().getSimpleName()).
                            commit();

                    if( !mDrawerLayout.isDrawerOpen(mNavSidePanelView))
                    mDrawerLayout.openDrawer(mNavSidePanelView);
                }
                else {
                    incidentCommentFragment.addCommentMessage(commentMessage);
                }
            }
        });
        addCommentDialog.show();
    }

    private void showIncidentReportPanel() {
        if(mDrawerLayout!=null)
        {
            incidentDetailFragment = new IncidentDetailFragment_();
            incidentDetailFragment.setInteractionListener(this);
            this.getSupportFragmentManager().beginTransaction().
                    replace(R.id.frame_incident_report, incidentDetailFragment, incidentDetailFragment.getClass().getSimpleName()).
                    commit();
            mDrawerLayout.openDrawer(mNavSidePanelView);
        }
    }

    @Override
    public void onBackClick() {
        this.closeNavDrawer();
    }

    public void closeNavDrawer(){
        if(mDrawerLayout.isDrawerOpen(mNavSidePanelView)){
            mDrawerLayout.closeDrawer(mNavSidePanelView);
        }
    }



    @Override
    public void onAddComment() {
        this.showCommentDialog();
    }

    @Override
    public void onDrawerCloseClick() {
        this.closeNavDrawer();

    }
}
