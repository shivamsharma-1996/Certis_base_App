package com.certis_base_app.ui.incidentManagement;

import android.support.constraint.ConstraintLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;
import com.certis_base_app.ui.dialog.photo.ShowPhotoDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_incident_detail)
public class IncidentDetailFragment extends BaseFragment implements View.OnClickListener {
    @ViewById(R.id.iv_overflow)
    ImageView ivOverflow;
    @ViewById(R.id.iv_close)
    ImageView ivClose;
    @ViewById(R.id.view_bg_create_task)
    View mCreateTask;
    @ViewById(R.id.view_bg_mark_resolved)
    View mIncidentMarkResolved;
    @ViewById(R.id.iv_incident_comments_chevron)
    ImageView ivIncidentCommentChevron;
    @ViewById(R.id.tv_incident_comments_label)
    TextView tvIncidentComment;
    @ViewById(R.id.i1)
    ImageView iv1;
    @ViewById(R.id.i2)
    ImageView iv2;
    @ViewById(R.id.cl_incident_associated_tasks)
    ConstraintLayout mTaskAssociatedView;

    private InteractionListener mListener;
    private PopupMenu mPopupMenu;

    public interface InteractionListener {
        void oncloseClick();

        void onAddCommentClick();

        void onMarkResolvedClick();

        void onCreateTaskClick();

        void onTaskAssociatedClick();
    }

    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    public IncidentDetailFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
        mPopupMenu = new PopupMenu(getContext(), IncidentDetailFragment.this.ivOverflow);
        mPopupMenu.getMenuInflater().inflate(R.menu.menu_incident_detail, mPopupMenu.getMenu());
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        break;
                    case R.id.menu_forward:
                        break;
                    case R.id.menu_email:
                        break;
                }
                return true;
            }
        });

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        ivOverflow.setOnClickListener(this);
        ivIncidentCommentChevron.setOnClickListener(this);
        tvIncidentComment.setOnClickListener(this);
        mCreateTask.setOnClickListener(this);
        mIncidentMarkResolved.setOnClickListener(this);
        mTaskAssociatedView.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener = null;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_close:
                mListener.oncloseClick();
                break;
            case R.id.iv_overflow:
                mPopupMenu.show();
                break;
            case R.id.iv_incident_comments_chevron:
            case R.id.tv_incident_comments_label:
                mListener.onAddCommentClick();
                break;
            case R.id.view_bg_create_task:
                IncidentDetailFragment.this.startTaskCreationActivity();
                //mTaskAssociatedView.setVisibility(View.VISIBLE);
                break;
            case R.id.view_bg_mark_resolved:
                IncidentDetailFragment.this.showMarkResolvedDialog();
                break;

            case R.id.i1:
            case R.id.i2:
                IncidentDetailFragment.this.showPhotoDialog();
                break;

            case R.id.cl_incident_associated_tasks:
                IncidentDetailFragment.this.showTaskAssociated();
                break;
        }
    }

    private void showTaskAssociated() {
        mListener.onTaskAssociatedClick();
    }

    private void startTaskCreationActivity() {
        mListener.onCreateTaskClick();
    }

    private void showPhotoDialog() {
        new ShowPhotoDialog(getContext()).show();
    }

    private void showMarkResolvedDialog() {
        mListener.onMarkResolvedClick();
    }


}
