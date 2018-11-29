package com.certis_base_app.ui.officerDashboard;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.model.MessageCard;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_officer_message)
public class OfficerMessageFragment extends BaseFragment {
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.rv_message)
    RecyclerView rvMessageView;
    @ViewById(R.id.et_message)
    EditText mMessageInput;
    @ViewById(R.id.iv_send)
    ImageView mSendImage;
    

    private OfficerMessageAdapter messageAdapter;
    List<MessageCard> messageList = new ArrayList<>();

    public TextWatcher mEmptyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            checkInputsForEmptyValues();
        }
    };

    public void checkInputsForEmptyValues() {
        String message = mMessageInput.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            mSendImage.setImageResource(R.drawable.ic_fab_messaging_inactive);
        } else {
            mSendImage.setImageResource(R.drawable.ic_fab_messaging_active);
        }
    }

    public OfficerMessageFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews() {
        this.setupToolbar();
        this.mMessageInput.addTextChangedListener(OfficerMessageFragment.this.mEmptyTextWatcher);

        messageAdapter = new OfficerMessageAdapter(messageList);
        rvMessageView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessageView.setAdapter(messageAdapter);
    }

    private void setupToolbar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OfficerMessageFragment.this.getActivity().onBackPressed();
            }
        });
    }

    @Click(R.id.iv_send)
    public void onMessageClick() {
        String message = mMessageInput.getText().toString().trim();
        if(!TextUtils.isEmpty(message))
        messageAdapter.setMessages(new MessageCard(message, this.getMessageSentTime()));

        rvMessageView.scrollToPosition(messageAdapter.getItemCount() - 1 );
    }

    public String getMessageSentTime(){
        String messageSentTime= new SimpleDateFormat("hh:mm a").format(new Date());
        return messageSentTime;
    }
}
