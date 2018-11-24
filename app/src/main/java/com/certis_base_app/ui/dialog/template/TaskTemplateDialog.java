package com.certis_base_app.ui.dialog.template;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.dialog.location.LocationDialog;

import java.util.ArrayList;
import java.util.List;

public class TaskTemplateDialog extends Dialog implements View.OnClickListener {
    private RecyclerView rvTemplates;
    private TaskTemplateAdapter taskTemplateAdapter;
    List<String> taskTemplates;
    private ImageView closeImage;

    private OnTemplateSelectListener templateSelectListener;

    public interface OnTemplateSelectListener {
        void onTemplateSelected(String template);
    }

    public TaskTemplateDialog(@NonNull Context context, OnTemplateSelectListener onTemplateSelectListener) {
        super(context);
        this.templateSelectListener = onTemplateSelectListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_task_template, null, false);
        this.setContentView(view);
        this.rvTemplates = view.findViewById(R.id.rv_task_template);
        this.closeImage = view.findViewById(R.id.iv_close);
        this.rvTemplates.setLayoutManager(new LinearLayoutManager(getContext()));
        this.taskTemplateAdapter = new TaskTemplateAdapter(this.getTaskTemplates(), getContext(), this);
        rvTemplates.setAdapter(taskTemplateAdapter);
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskTemplateDialog.this.dismiss();
            }
        });
    }

    public List<String> getTaskTemplates() {
        taskTemplates = new ArrayList<>();
        taskTemplates.add("Flight-triggered tasks");
        taskTemplates.add("Incident-triggered tasks");
        taskTemplates.add("Scheduled tasks");
        taskTemplates.add("Scheduled tasks");
        taskTemplates.add("Ad-hoc tasks");
        taskTemplates.add("Empty template");
        return taskTemplates;
    }

    @Override
    public void onClick(View view) {
        TaskTemplateDialog.this.dismiss();
        templateSelectListener.onTemplateSelected(taskTemplates.get(((Integer) view.getTag()).intValue()));
    }
}
