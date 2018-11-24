package com.certis_base_app.ui.dialog.location;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.ArrayList;
import java.util.List;

public class LocationDialog extends Dialog implements View.OnClickListener {
    Group mGroupOtherLocation;
    EditText etLocationQuery;
    ImageView ivClose, ivOtherLocation;
    TextView tvOtherLocation, tvOherLocationCountry;

    RecyclerView rvLocations;
    private LocationRecyclerviewAdapter locationAdapter;
    private List<String> mLoactionList;

    private OnLocationSelectListener locationSelectListener;

    public interface OnLocationSelectListener {
        void onLocationSelected(String location);
    }

    private TextWatcher mEmptyWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(etLocationQuery.getText().toString())) {
                tvOtherLocation.setText(R.string.dialog_location_label_other);
                mGroupOtherLocation.setVisibility(View.GONE);
            } else {
                tvOtherLocation.setText(s.toString());
                mGroupOtherLocation.setVisibility(View.VISIBLE);
            }
        }
    };

    public LocationDialog(@NonNull Context context, OnLocationSelectListener locationSelectListener) {
        super(context);
        this.locationSelectListener = locationSelectListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_task_location, null);
        this.setContentView(view);
        mGroupOtherLocation = view.findViewById(R.id.group_other_location);
        ivClose = view.findViewById(R.id.iv_close);
        etLocationQuery = view.findViewById(R.id.et_location);
        ivOtherLocation = view.findViewById(R.id.iv_other_location);
        tvOtherLocation = view.findViewById(R.id.tv_other_value);
        tvOherLocationCountry = view.findViewById(R.id.tv_other_location_country);
        rvLocations = view.findViewById(R.id.rv_task_location);
        locationAdapter = new LocationRecyclerviewAdapter(getLocationList(), this);
        rvLocations.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLocations.setAdapter(locationAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //setting listeners
        this.ivOtherLocation.setOnClickListener(this);
        this.tvOtherLocation.setOnClickListener(this);
        this.tvOherLocationCountry.setOnClickListener(this);

        this.etLocationQuery.addTextChangedListener(this.mEmptyWatcher);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationDialog.this.dismiss();
            }
        });
    }

    public List<String> getLocationList() {
        mLoactionList = new ArrayList<>();
        mLoactionList.add("a");
        mLoactionList.add("b");
        mLoactionList.add("c");
        mLoactionList.add("d");
        mLoactionList.add("e");
        return mLoactionList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_location:
            case R.id.tv_other_value:
            case R.id.tv_country_name:
                locationSelectListener.onLocationSelected(etLocationQuery.getText().toString());
                break;
            default:
                locationSelectListener.onLocationSelected(mLoactionList.get(((Integer) v.getTag()).intValue()));
                break;
        }
        LocationDialog.this.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(locationSelectListener!=null){
            locationSelectListener = null;
        }
    }
}
