package com.certis_base_app.ui.messaging;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.certis_base_app.R;
import com.certis_base_app.model.MessageCard;
import com.certis_base_app.ui.officerDashboard.OfficerMessageAdapter;
import com.certis_base_app.ui.officerDashboard.OfficerMessageFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_message_inbox)
public class MessageInboxFragment extends Fragment {
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.rv_message_list)
    RecyclerView rvMessageList;
    @ViewById(R.id.et_message)
    EditText mMessageInput;
    @ViewById(R.id.iv_send)
    ImageView ivMessageSend;

    private MessageInboxAdapter messageInboxAdapter;
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
            checkInputForEmptyValues();
        }
    };
    public void checkInputForEmptyValues() {
        String message = mMessageInput.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            ivMessageSend.setImageResource(R.drawable.ic_fab_messaging_inactive);
        } else {
            ivMessageSend.setImageResource(R.drawable.ic_fab_messaging_active);
        }
    }

    public MessageInboxFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews(){
        this.mMessageInput.addTextChangedListener(this.mEmptyTextWatcher);

        messageInboxAdapter = new MessageInboxAdapter(messageList);
        rvMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessageList.setAdapter(messageInboxAdapter);
    }

    public void setMessageRoom(String  officerName){
        mToolbar.setTitle(officerName);
    }

    @Click(R.id.iv_send)
    public void onMessageClick() {
        String message = mMessageInput.getText().toString().trim();
        if(!TextUtils.isEmpty(message))
            messageInboxAdapter.setMessages(new MessageCard(message, this.getMessageSentTime()));

        rvMessageList.scrollToPosition(messageInboxAdapter.getItemCount() - 1 );
    }

    public String getMessageSentTime(){
        String messageSentTime= new SimpleDateFormat("hh:mm a").format(new Date());
        return messageSentTime;
    }

}
