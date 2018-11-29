package com.certis_base_app.ui.messaging;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.certis_base_app.R;
import com.certis_base_app.ui.BaseFragment;
import com.certis_base_app.ui.dialog.filter.FilterDialog;
import com.certis_base_app.ui.dialog.message.BroadcastDialog;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_message_room)
public class MessageRoomFragment extends BaseFragment implements View.OnClickListener {
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.ib_broadcast)
    ImageButton ibBroadcast;
    @ViewById(R.id.rv_message_room)
    RecyclerView rvMessageRoomList;
    private MessageRoomAdapter messageRoomAdapter;
    private List<String> mMessageRoomList = new ArrayList<>();

    private BroadcastDialog broadcastDialog;
    private ParentInteractionListener parentInteractionListener;

    public interface ParentInteractionListener{
        void onSendBroadcast(String broadcastMsg);
        void onRoomSelected(String officerName);
    }

    public void setParentInteractionListener(ParentInteractionListener parentInteractionListener) {
        this.parentInteractionListener = parentInteractionListener;
    }

    public MessageRoomFragment() {

    }

    @AfterViews
    public void populateViews() {
        messageRoomAdapter = new MessageRoomAdapter(this.getPopulatedList(), this);
        rvMessageRoomList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessageRoomList.setAdapter(messageRoomAdapter);

        this.setupToolbar();
    }

    private void setupToolbar() {
        mToolbar.inflateMenu(R.menu.menu_message_room);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_filter:
                        MessageRoomFragment.this.showFilterDialog();
                        return true;
                }
                return true;
            }
        });
    }

    private void showFilterDialog() {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(getActivity().
                getSupportFragmentManager(),filterDialog.getClass().getSimpleName()); //showing filterdialog from fragment over activity with the help of - getActivity().getSupportFragmentManager()
    }

    @Click(R.id.ib_broadcast)
    public void showBroadCastDialog(){
        broadcastDialog = new BroadcastDialog(getContext(), new BroadcastDialog.InteractionListener() {
            @Override
            public void onClose() {
                broadcastDialog.dismiss();
            }

            @Override
            public void onSend(String broadcastMsg) {

                broadcastDialog.dismiss();
                parentInteractionListener.onSendBroadcast(broadcastMsg);
            }
        });
        broadcastDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cl_list_message_room:
                parentInteractionListener.onRoomSelected(mMessageRoomList.get(((Integer)view.getTag()).intValue()));
                break;
        }
    }


    public List<String> getPopulatedList(){
        mMessageRoomList = new ArrayList<>();
        mMessageRoomList.add("a");
        mMessageRoomList.add("b");
        mMessageRoomList.add("c");
        mMessageRoomList.add("d");
        mMessageRoomList.add("e");
        mMessageRoomList.add("f");
        mMessageRoomList.add("g");
        mMessageRoomList.add("h");
        return mMessageRoomList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(parentInteractionListener!=null){
            parentInteractionListener = null;
        }
    }
}
