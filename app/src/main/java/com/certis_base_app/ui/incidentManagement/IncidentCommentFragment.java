package com.certis_base_app.ui.incidentManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@EFragment(R.layout.fragment_incident_commnet)
public class IncidentCommentFragment extends Fragment {
    @ViewById(R.id.tv_comments_label)
    TextView tvCommentsCount;
    @ViewById(R.id.iv_back)
    ImageView ivBackBtn;
    @ViewById(R.id.rv_incident_commment_list)
    RecyclerView rvIncidentCommentList;
    private IncidentCommentAdapter incidentCommentAdapter;
    private List<String> commentList = new ArrayList<>();
    private static final String COMMNET_MESSAGE_KEY = "Image File Path Key";
    private String commentMessage;


    private InteractionListener mListener;

    public interface InteractionListener{
        void onBackClick();
        void onAddComment();
    }

    public void setInteractionListener(InteractionListener mListener) {
        this.mListener = mListener;
    }

    public IncidentCommentFragment() {
        // Required empty public constructor
    }


    public static IncidentCommentFragment_ getInstance(String commentMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(COMMNET_MESSAGE_KEY, commentMessage);
        IncidentCommentFragment_ fragment = new IncidentCommentFragment_();
        fragment.setArguments(bundle);
        return fragment;
    }

    @AfterViews
    public void populateViews(){

        incidentCommentAdapter = new IncidentCommentAdapter(commentList);
        rvIncidentCommentList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIncidentCommentList.setAdapter(incidentCommentAdapter);

        if (getArguments() != null)
            incidentCommentAdapter.setData(getArguments().getString(COMMNET_MESSAGE_KEY));

        tvCommentsCount.setText(String.format(Locale.getDefault(),"Comments(%s)", incidentCommentAdapter.getItemCount()));
    }

    public void addCommentMessage(String commentMessage){
        incidentCommentAdapter.setData(commentMessage);
        tvCommentsCount.setText(String.format(Locale.getDefault(),"Comments(%s)", incidentCommentAdapter.getItemCount()));
    }

    @Click(R.id.iv_back)
    public void backButtonClick(){
        mListener.onBackClick();
    }

    @Click(R.id.tv_add_comment)
    public void onAddCommentClick(){
        mListener.onAddComment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mListener!=null)
            mListener = null;
    }
}



