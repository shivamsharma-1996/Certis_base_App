package com.certis_base_app.ui.dialog.template;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.certis_base_app.R;

import org.androidannotations.annotations.ViewById;

import java.util.List;

public class TaskTemplateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> taskTemplates;
    private Context context;
    private View.OnClickListener onClickListener;

    public TaskTemplateAdapter(List<String> taskTemplates, Context context, View.OnClickListener onClickListener) {
        this.taskTemplates = taskTemplates;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_template, parent,false);
        view.setOnClickListener(onClickListener);
        return new Templateholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Templateholder)holder).itemView.setTag(Integer.valueOf(position));
        ((Templateholder)holder).tvTemplateType.setText(taskTemplates.get(position));
    }

    @Override
    public int getItemCount() {
        return taskTemplates!=null ? taskTemplates.size(): 0;
    }

    public class Templateholder extends RecyclerView.ViewHolder{
        TextView tvTemplateType;

        public Templateholder(View itemView) {
            super(itemView);
            tvTemplateType = itemView.findViewById(R.id.tv_template_title);
        }
    }
}
