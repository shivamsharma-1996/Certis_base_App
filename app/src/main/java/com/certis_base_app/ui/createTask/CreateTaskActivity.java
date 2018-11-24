package com.certis_base_app.ui.createTask;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;
import com.certis_base_app.model.Subtask;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.dialog.general.CreateTaskListDialog;
import com.certis_base_app.ui.dialog.location.LocationDialog;
import com.certis_base_app.ui.dialog.subtask.EditSubtaskDialog;
import com.certis_base_app.ui.dialog.subtask.SubtaskListAdapter;
import com.certis_base_app.ui.dialog.subtask.SubtaskTypeDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@EActivity(R.layout.activity_create_task)
public class CreateTaskActivity extends BaseActivity implements View.OnClickListener {
    @ViewById(R.id.tv_create_task)
    TextView tvCreateTask;
    @ViewById(R.id.et_task_title)
    TextView etTaskTitle;
    @ViewById(R.id.tv_create_task_catagory_label)
    TextView tvTaskCatagory;
    @ViewById(R.id.tv_create_task_subcatagory_label)
    TextView tvTaskSubcatagory;
    @ViewById(R.id.tv_create_task_type_label)
    TextView tvTaskType;
    @ViewById(R.id.tv_create_task_description_label)
    TextView tvTaskDescription;
    @ViewById(R.id.tv_create_task_location_label)
    TextView tvTaskLocation;
    @ViewById(R.id.tv_create_task_start_date)
    TextView tvAddStartDate;
    @ViewById(R.id.tv_create_task_start_time)
    TextView tvAddStartTime;
    @ViewById(R.id.tv_create_task_end_date)
    TextView tvAddEndDate;
    @ViewById(R.id.tv_create_task_end_date)
    TextView tvAddEndTime;
    @ViewById(R.id.tv_create_task_add_subtasks_label)
    TextView tvAddSubtask;
    @ViewById(R.id.iv_create_task_catagory_chevron)
    ImageView ivCatagoryChevron;
    @ViewById(R.id.iv_create_task_subcatagory_chevron)
    ImageView ivSubCatagoryChevron;
    @ViewById(R.id.iv_create_task_type_chevron)
    ImageView ivTypeChevron;
    @ViewById(R.id.iv_create_assignee_chevron)
    ImageView ivAssigneeChevron;
    @ViewById(R.id.iv_add_subtask_chevron)
    ImageView ivSubtaskChevron;
    @ViewById(R.id.iv_close)
    ImageView ivClose;
    @ViewById(R.id.rv_subtask_list)
    RecyclerView rvSubtaskList;
    private List<Subtask> subtaskList = new ArrayList<>();

    private SubtaskListAdapter subtaskListAdapter;
    private TextWatcher mEmptyWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(etTaskTitle.getText().toString())) {
                tvCreateTask.setAlpha(0.4f);
                tvCreateTask.setEnabled(false);
            } else {
                tvCreateTask.setAlpha(1.0f);
                tvCreateTask.setEnabled(true);
            }
        }
    };

    @AfterViews
    public void populateViews() {
        subtaskListAdapter = new SubtaskListAdapter(CreateTaskActivity.this, subtaskList, this);
        rvSubtaskList.setLayoutManager(new LinearLayoutManager(this));
        rvSubtaskList.setAdapter(subtaskListAdapter);

        tvTaskCatagory.setOnClickListener(CreateTaskActivity.this);
        tvTaskSubcatagory.setOnClickListener(CreateTaskActivity.this);
        tvTaskType.setOnClickListener(CreateTaskActivity.this);
        tvTaskDescription.setOnClickListener(CreateTaskActivity.this);
        tvTaskLocation.setOnClickListener(CreateTaskActivity.this);
        tvAddSubtask.setOnClickListener(CreateTaskActivity.this);
        ivCatagoryChevron.setOnClickListener(CreateTaskActivity.this);
        ivSubCatagoryChevron.setOnClickListener(CreateTaskActivity.this);
        ivTypeChevron.setOnClickListener(CreateTaskActivity.this);
        ivAssigneeChevron.setOnClickListener(CreateTaskActivity.this);
        ivSubtaskChevron.setOnClickListener(CreateTaskActivity.this);
        this.etTaskTitle.addTextChangedListener(mEmptyWatcher);
    }

    @Click(R.id.tv_create_task_start_date)
    public void onTaskStartDateClick() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH).show();
    }

    @Click(R.id.iv_close)
    public void onCloseClick() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_task_location_label:
                showLocationDialog();
                break;
            case R.id.tv_create_task_catagory_label:
            case R.id.iv_create_task_catagory_chevron:
                CreateTaskActivity.this.showCustomDialog(tvTaskCatagory, CreateTaskActivity.this.getPopulateList());
                break;
            case R.id.tv_create_task_subcatagory_label:
            case R.id.iv_create_task_subcatagory_chevron:
                CreateTaskActivity.this.showCustomDialog(tvTaskSubcatagory, CreateTaskActivity.this.getPopulateList());
                break;
            case R.id.tv_create_task_type_label:
            case R.id.iv_create_task_type_chevron:
                CreateTaskActivity.this.showCustomDialog(tvTaskType, CreateTaskActivity.this.getPopulateList());
                break;
            case R.id.tv_create_task_description_label:
                CreateTaskActivity.this.showCustomDialog(tvTaskDescription, CreateTaskActivity.this.getPopulateList());
                break;

            case R.id.tv_create_task_add_subtasks_label:
            case R.id.iv_add_subtask_chevron:
                CreateTaskActivity.this.showEditSubtaskDialog(null);
                break;

            case R.id.cl_item_list_subtask:
                CreateTaskActivity.this.showEditSubtaskDialog(subtaskList.get(((Integer) v.getTag()).intValue()));
                break;
        }
    }

    private void showEditSubtaskDialog(Object arg) {
        Subtask subtask = (Subtask) arg;
        new EditSubtaskDialog(this, new EditSubtaskDialog.OnItemClickListener() {
            @Override
            public void onSubtaskLocationClick(final TextView locationWidget) {
                new LocationDialog(CreateTaskActivity.this, new LocationDialog.OnLocationSelectListener() {
                    @Override
                    public void onLocationSelected(String location) {
                        locationWidget.setText(location);
                    }
                }).show();
            }
            @Override
            public void onSubtaskTypeClick(final TextView typeWidget) {
                new SubtaskTypeDialog(CreateTaskActivity.this, new SubtaskTypeDialog.OnSubtaskTypeSelectListener() {
                    @Override
                    public void onSubtaskTypeSelect(String subtaskType) {
                        typeWidget.setText(subtaskType);
                    }
                }).show();
            }

            @Override
            public void onSubtaskSaveClick(Subtask subtask) {
                subtaskList.add(subtask);
                subtaskListAdapter.notifyDataSetChanged();
            }
        }, arg!=null?subtask:null).show();
    }

    private void showCustomDialog(final TextView view, final List<String> populateList) {
        new CreateTaskListDialog(CreateTaskActivity.this, new CreateTaskListDialog.OnItemSelectListener() {
            @Override
            public void onItemSelected(String value) {
                view.setText(value);
            }
        }, populateList).show();
    }

    private void showLocationDialog() {
        new LocationDialog(CreateTaskActivity.this, new LocationDialog.OnLocationSelectListener() {
            @Override
            public void onLocationSelected(String location) {
                tvTaskLocation.setText(location);
            }
        }).show();
    }


    public List<String> getPopulateList() {
        List<String> populateList = new ArrayList<>();
        populateList.add("a");
        populateList.add("b");
        populateList.add("c");
        populateList.add("d");
        populateList.add("e");
        return populateList;
    }
}
