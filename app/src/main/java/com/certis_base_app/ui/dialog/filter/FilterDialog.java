package com.certis_base_app.ui.dialog.filter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.ui.custom_views.TriStateCheckBox;

import java.util.ArrayList;
import java.util.List;

public class FilterDialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener{

    RecyclerView rvFilterList;
    FilterAdapter filterAdapter;

    TextView tvApply, tvCancel;
    public FilterDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_filter, null);
        tvApply  = dialogView.findViewById(R.id.tv_apply);
        tvCancel  = dialogView.findViewById(R.id.tv_cancel);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        /*builder.setTitle(getString(R.string.dialog_filter_heading)).
                setPositiveButton(getString(R.string.dialog_filter_apply), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).
                setNegativeButton(getString(R.string.dialog_filter_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        rvFilterList = dialogView.findViewById(R.id.rv_filter_list);
        filterAdapter = new FilterAdapter(getContext(), getPopulatedList(), this);
        rvFilterList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvFilterList.setAdapter(filterAdapter);
        return dialog;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {

    }*/

    public List<String> getPopulatedList() {
        List<String> populatedList = new ArrayList<>();
        populatedList.add("Select All");
        populatedList.add("STATUS");
        populatedList.add("Online");
        populatedList.add("Offline");
        populatedList.add("ATSU");
        populatedList.add("ATSU/T1");
        populatedList.add("ATSU/T2");
        populatedList.add("Terminal 2 SOC/OC");
        populatedList.add("ATSU/T1/A1");
        populatedList.add("ATSU/T1/A2");
        return populatedList;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.tsc_check:
                TriStateCheckBox triStateCheckBox = ((TriStateCheckBox)buttonView);
                switch (triStateCheckBox.getState()){
                    case TriStateCheckBox.INDETERMINATE:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        filterAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.CHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        filterAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.UNCHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.CHECKED);
                        filterAdapter.selectAllCheckboxes();
                        break;
                }
                break;
                default:
                    filterAdapter.updateTristateCheckBox();
                    break;
        }
    }
}
