package com.certis_base_app.ui.menuMessaging;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_message_room)
public class MessageRoomFragment extends BaseFragment implements View.OnClickListener {

    @ViewById(R.id.rv_message_room_list)
    RecyclerView rvMessageRoomList;
    private MessageRoomAdapter messageRoomAdapter;

    public MessageRoomFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void populateViews(){
        messageRoomAdapter = new MessageRoomAdapter(this);
        rvMessageRoomList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessageRoomList.setAdapter(messageRoomAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
