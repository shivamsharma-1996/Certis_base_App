package com.certis_base_app.ui.dialog.general;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.certis_base_app.R;
import com.certis_base_app.ui.dialog.template.TaskTemplateAdapter;

import java.util.List;

public class CreateTaskListDialog extends Dialog implements View.OnClickListener{
    private RecyclerView rvTemplates;
    private TaskTemplateAdapter adapter;
    List<String> list ;
    private ImageView closeImage;

    private OnItemSelectListener onItemSelectListener;
    public interface OnItemSelectListener{
        void onItemSelected(String template);
    }

    public CreateTaskListDialog(@NonNull Context context, OnItemSelectListener onItemSelectListener, List<String> list) {
        super(context);
        this.onItemSelectListener = onItemSelectListener;
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_general_create_task, null, false);
        this.setContentView(view);
        this.rvTemplates = view.findViewById(R.id.rv_task_template);
        this.closeImage = view.findViewById(R.id.iv_close);
        this.rvTemplates.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new TaskTemplateAdapter(list, getContext(), this);
        rvTemplates.setAdapter(adapter);
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTaskListDialog.this.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        CreateTaskListDialog.this.dismiss();
        onItemSelectListener.onItemSelected(list.get(((Integer) view.getTag()).intValue()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(onItemSelectListener!=null){
            onItemSelectListener = null;
        }
    }
}
