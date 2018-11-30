package com.certis_base_app.ui.messaging;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.dialog.filter.FilterDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_messaging)
public class MessagingActivity extends BaseActivity implements MessageRoomFragment.ParentInteractionListener {
    MessageRoomFragment_ messageRoomFragment;

    @AfterViews
    public void populatrViews() {
        messageRoomFragment = (MessageRoomFragment_) this.getSupportFragmentManager().findFragmentById(R.id.fragment_message_room);
        messageRoomFragment.setParentInteractionListener(this);
    }

    @Override
    public void onSendBroadcast(String broadcastMsg) {
    }

    @Override
    public void onRoomSelected(String officerName) {
        ((MessageInboxFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_message_inbox)).setMessageRoom(officerName);
    }

}
