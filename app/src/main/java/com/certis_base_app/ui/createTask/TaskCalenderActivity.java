package com.certis_base_app.ui.createTask;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.certis_base_app.R;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.custom_views.TriStateCheckBox;

import java.util.HashMap;

public class TaskCalenderActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, TaskCalenderAdapter.UpdateTristateListener
{
    private Toolbar mToolbar;
    private TriStateCheckBox mTristateCheckBox;

    private RecyclerView calenderView;
    private TaskCalenderAdapter taskCalenderAdapter;

    private HashMap<Integer ,Integer> boxIdMap = new HashMap<>();
    private int[] calenderColumns = new int[]{
            R.id.v1,
            R.id.v2,
            R.id.v3,
            R.id.v4,
            R.id.v5,
            R.id.v6,
            R.id.v7,
            R.id.v8,
            R.id.v9,
            R.id.v10,
            R.id.v11};

    private String event = "5:15 - 8:45";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_calender);
        calenderView = findViewById(R.id.calenderView);
        mTristateCheckBox = findViewById(R.id.tsc_check_officer);

        mToolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(mToolbar);
        this.setupToolbar();

        int startHour = Integer.parseInt(event.substring( 0, event.indexOf(":")));
        int endHour = Integer.parseInt(event.substring(event.indexOf("-") + 2, event.lastIndexOf(":")));
        int startMin = Integer.parseInt(event.substring(event.indexOf(":") + 1, event.indexOf("-") - 1));
        int endMin = Integer.parseInt(event.substring(event.lastIndexOf(":")  + 1));

        for(int index  = 0; index < 11; index ++)
        {
            boxIdMap.put(startHour - 2 + index, calenderColumns[index]);
        }

        //for setting scale
        for(int viewId = startHour-2 ; viewId < (startHour-2) + 11 ; viewId++)
        {
            ((TextView)findViewById(boxIdMap.get(viewId))).setText(viewId + "AM");
        }
        taskCalenderAdapter = new TaskCalenderAdapter(this, boxIdMap, startHour, endHour, startMin, endMin, this, this);
        calenderView.setLayoutManager(new LinearLayoutManager(TaskCalenderActivity.this, LinearLayoutManager.VERTICAL, false));
        calenderView.setAdapter(taskCalenderAdapter);
    }

    private void setupToolbar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_calender_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_done:
                Intent createTaskIntent = new Intent(TaskCalenderActivity.this, CreateTaskActivity.class);
                createTaskIntent.putExtra("OFFICERS_COUNT", taskCalenderAdapter.getCheckedAssigneeCount());
                TaskCalenderActivity.this.setResult(RESULT_OK, createTaskIntent);
                TaskCalenderActivity.this.finish();
                break;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

        mTristateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TriStateCheckBox triStateCheckBox = (TriStateCheckBox) buttonView;
                switch (triStateCheckBox.getState()){
                    case TriStateCheckBox.INDETERMINATE:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        taskCalenderAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.CHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        taskCalenderAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.UNCHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.CHECKED);
                        taskCalenderAdapter.selectAllCheckboxes();
                        break;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        taskCalenderAdapter.updateTristateCheckBox();

       /* switch (buttonView.getId()){
            case R.id.tsc_check_officer:
                TriStateCheckBox triStateCheckBox = ((TriStateCheckBox)buttonView);
                switch (triStateCheckBox.getState()){
                    case TriStateCheckBox.INDETERMINATE:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        taskCalenderAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.CHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.UNCHECKED);
                        taskCalenderAdapter.unSelectAllCheckboxes();
                        break;
                    case TriStateCheckBox.UNCHECKED:
                        triStateCheckBox.setState(TriStateCheckBox.CHECKED);
                        taskCalenderAdapter.selectAllCheckboxes();
                        break;
                }
                break;
            default:
                taskCalenderAdapter.updateTristateCheckBox();
                break;
        }*/
    }

    @Override
    public void updateTriStateCheckBox(int newState) {
       mTristateCheckBox.setState(newState);
    }


}