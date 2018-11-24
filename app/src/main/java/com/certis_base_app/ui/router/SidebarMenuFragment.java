package com.certis_base_app.ui.router;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.certis_base_app.R;
import com.certis_base_app.ui.menuIncidentManagement.IncidentManagementActivity;
import com.certis_base_app.ui.menuMessaging.MessagingActivity;
import com.certis_base_app.ui.menuOfficerDashboard.OfficerMonitoringActivity;
import com.certis_base_app.ui.menuOfficerDashboard.OfficerMonitoringActivity_;
import com.certis_base_app.ui.menuTaskManagement.TaskManagementActivity;
import com.certis_base_app.ui.menuTaskManagement.TaskManagementActivity_;
import com.certis_base_app.ui.onboarding.login.LandingActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_sidebar_menu)
public class SidebarMenuFragment extends Fragment implements View.OnClickListener {

    @ViewById(R.id.iv_officer_dashboard)
    ImageView mOfficerImage;
    @ViewById(R.id.iv_incident)
    ImageView mIncidentImage;
    @ViewById(R.id.iv_task)
    ImageView mTaskImage;
    @ViewById(R.id.iv_message)
    ImageView mMessageImage;
    @ViewById(R.id.tv_officer_dashboard)
    TextView mOfficerText;
    @ViewById(R.id.tv_incident)
    TextView mIncidentText;
    @ViewById(R.id.tv_task)
    TextView mTaskText;
    @ViewById(R.id.tv_message)
    TextView mMessageText;

    public SidebarMenuFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
        init();
        switchUi();
    }

    private void init() {
        mOfficerImage.setOnClickListener(this);
        mIncidentImage.setOnClickListener(this);
        mTaskImage.setOnClickListener(this);
        mMessageImage.setOnClickListener(this);
        mOfficerText.setOnClickListener(this);
        mIncidentText.setOnClickListener(this);
        mTaskText.setOnClickListener(this);
        mMessageText.setOnClickListener(this);
    }

    private void switchUi() {
        int colorSelected = ContextCompat.getColor(getContext(), R.color.colorAccent);
        //int colorUnSelected = ContextCompat.getColor(getContext(), R.color.colorPrimary);

        if(this.getContext() instanceof OfficerMonitoringActivity_){
            mOfficerImage.setColorFilter(colorSelected);
            mOfficerText.setTextColor(colorSelected);
        }
        else if(this.getContext() instanceof IncidentManagementActivity){
            mIncidentImage.setColorFilter(colorSelected);
            mIncidentText.setTextColor(colorSelected);
        }
        else if(this.getContext() instanceof TaskManagementActivity){
            mTaskImage.setColorFilter(colorSelected);
            mTaskText.setTextColor(colorSelected);
        }
        else if(this.getContext() instanceof MessagingActivity){
            mMessageImage.setColorFilter(colorSelected);
            mMessageText.setTextColor(colorSelected);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_officer_dashboard:
            case R.id.tv_officer_dashboard:
                //do
                startActivity(new Intent(getActivity(), OfficerMonitoringActivity_.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.iv_incident:
            case R.id.tv_incident:
                //do
                startActivity(new Intent(getActivity(), IncidentManagementActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.iv_task:
            case R.id.tv_task:
                //do
                startActivity(new Intent(getActivity(), TaskManagementActivity_.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.iv_message:
            case R.id.tv_message:
                //do
                startActivity(new Intent(getActivity(), MessagingActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;

            case R.id.iv_logout:
            case R.id.tv_logout:
                //do
                Intent landingIntent = new Intent(getActivity(), LandingActivity_.class);
                landingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                this.startActivity(landingIntent);
                getActivity().overridePendingTransition(0, 0);
                break;
        }
    }
}
