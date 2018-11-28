package com.certis_base_app.ui.createTask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.certis_base_app.R;
import com.certis_base_app.model.Subtask;
import com.certis_base_app.ui.BaseActivity;
import com.certis_base_app.ui.custom_views.RecyclerItemTouchHelper;
import com.certis_base_app.ui.dialog.general.CreateTaskListDialog;
import com.certis_base_app.ui.dialog.location.LocationDialog;
import com.certis_base_app.ui.dialog.subtask.EditSubtaskDialog;
import com.certis_base_app.ui.dialog.subtask.SubtaskTypeDialog;
import com.certis_base_app.utills.Singleton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_create_task)
public class CreateTaskActivity extends BaseActivity implements View.OnClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private static final int REQUEST_CODE_OFFICER_ASSIGNEE = 101;

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
    @ViewById(R.id.tv_create_task_end_date)
    TextView tvAddEndDate;
    @ViewById(R.id.tv_create_task_start_time)
    TextView tvAddStartTime;
    @ViewById(R.id.tv_create_task_end_time)
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
    @ViewById(R.id.tv_create_assignee_label)
    TextView tvCreateAssigneeLabel;
    @ViewById(R.id.tv_add_assignee)
    TextView tvAddAssignee;
    @ViewById(R.id.iv_create_assignee_chevron)
    ImageView tvCreateAssigneeChevron;
    @ViewById(R.id.group_assignee)
    Group mGroupAssignee;
    @ViewById(R.id.rv_assignee_list)
    RecyclerView rvAssigneeList;
    private int assignee_count;
    private List<Subtask> subtaskList = new ArrayList<>();

    private SubtaskTypeAdapter subtaskTypeAdapter;
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
        subtaskTypeAdapter = new SubtaskTypeAdapter(CreateTaskActivity.this, subtaskList, this);
        rvSubtaskList.setLayoutManager(new LinearLayoutManager(this));
        rvSubtaskList.setAdapter(subtaskTypeAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvSubtaskList);

        tvCreateTask.setOnClickListener(CreateTaskActivity.this);
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
        tvCreateAssigneeLabel.setOnClickListener(CreateTaskActivity.this);
        ivAssigneeChevron.setOnClickListener(CreateTaskActivity.this);
        tvCreateAssigneeChevron.setOnClickListener(CreateTaskActivity.this);

        tvAddStartDate.setOnClickListener(CreateTaskActivity.this);
        tvAddEndDate.setOnClickListener(CreateTaskActivity.this);
        tvAddStartTime.setOnClickListener(CreateTaskActivity.this);
        tvAddEndTime.setOnClickListener(CreateTaskActivity.this);
        this.etTaskTitle.addTextChangedListener(mEmptyWatcher);
    }

    @Click(R.id.iv_close)
    public void onCloseClick() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_task:
                CreateTaskActivity.this.finish();
                break;
            case R.id.view_foreground:
                CreateTaskActivity.this.showEditSubtaskDialog(subtaskList.get(((Integer) v.getTag()).intValue()));
                break;

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
                //CreateTaskActivity.this.showCustomDialog(tvTaskDescription, CreateTaskActivity.this.getPopulateList());
                break;

            case R.id.tv_create_task_add_subtasks_label:
            case R.id.iv_add_subtask_chevron:
                CreateTaskActivity.this.showEditSubtaskDialog(null);
                break;

            case R.id.tv_create_assignee_label:
            case R.id.tv_add_assignee:
            case R.id.iv_create_assignee_chevron:
            case R.id.cl_item_list_assignee:
                CreateTaskActivity.this.startCalenderActivity();
                break;

            case R.id.tv_create_task_start_date:
                CreateTaskActivity.this.showDatePicker(tvAddStartDate);
                break;
            case R.id.tv_create_task_end_date:
                CreateTaskActivity.this.showDatePicker(tvAddEndDate);
                break;
            case R.id.tv_create_task_start_time:
                CreateTaskActivity.this.showTimePicker(tvAddStartTime);
                break;
            case R.id.tv_create_task_end_time:
                CreateTaskActivity.this.showTimePicker(tvAddEndTime);
                break;
        }
    }

    public void showDatePicker(final TextView widget) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                GregorianCalendar date1 = new GregorianCalendar(year, month, dayOfMonth);
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
//                String result = formatter.format(date1.getTime());
                widget.setText(String.format("%s, %s %s", CreateTaskActivity.this.getDayOfWeek(year,month, dayOfMonth),
                        dayOfMonth-1, CreateTaskActivity.this.getMonth(month)));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showTimePicker(final TextView widget) {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(CreateTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                widget.setText(String.format("%02d:%02d %s", selectedHour, selectedMinute, (selectedHour < 12) ? "AM" : "PM"));
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true).show();
    }



    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public String getDayOfWeek(int year, int month, int dayOfMonth) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(year, month, dayOfMonth-1);
        String dayOfWeek = simpledateformat.format(date);
        return dayOfWeek;
    }

    private void startCalenderActivity() {
        Intent calenderIntent = new Intent(CreateTaskActivity.this, TaskCalenderActivity.class);
        CreateTaskActivity.this.startActivityForResult(calenderIntent, REQUEST_CODE_OFFICER_ASSIGNEE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_OFFICER_ASSIGNEE) {
            switch (resultCode){
                case RESULT_OK:
                    assignee_count = data!=null? data.getIntExtra("OFFICERS_COUNT", 0): 0;
                    if(assignee_count>0){
                        mGroupAssignee.setVisibility(View.GONE);
                        rvAssigneeList.setVisibility(View.VISIBLE);
                        rvAssigneeList.setLayoutManager(new LinearLayoutManager(CreateTaskActivity.this));
                        rvAssigneeList.setAdapter(new TaskAssigneeAdapter(assignee_count, CreateTaskActivity.this));
                    }
                    break;
                case RESULT_CANCELED:
                    mGroupAssignee.setVisibility(View.VISIBLE);
                    rvAssigneeList.setVisibility(View.GONE);
                    break;
            }
        }
    }
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (viewHolder instanceof SubtaskTypeAdapter.SubtaskListItemHolder)
        {
            final int adapterPosition = viewHolder.getAdapterPosition();
            final Subtask removedSubtask = this.subtaskList.remove(adapterPosition);
            CreateTaskActivity.this.subtaskTypeAdapter.setSubtaskList(subtaskList);
            Singleton.showSnackbar(CreateTaskActivity.this,this.rvSubtaskList,0,R.string.snackBar_title_subtask_deleted, R.string.snackBar_action_undo, new View.OnClickListener() {
                public void onClick(View view) {
                    CreateTaskActivity.this.subtaskList.add(adapterPosition, removedSubtask);
                    CreateTaskActivity.this.subtaskTypeAdapter.setSubtaskList(subtaskList);
                }
            }, R.color.colorAccent);
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
                subtaskTypeAdapter.notifyDataSetChanged();
            }
        }, arg != null ? subtask : null).show();
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
