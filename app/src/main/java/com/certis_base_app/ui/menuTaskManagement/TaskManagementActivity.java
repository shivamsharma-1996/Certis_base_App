package com.certis_base_app.ui.menuTaskManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.certis_base_app.R;

public class TaskManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_management);
        Toast.makeText(this, "task", Toast.LENGTH_SHORT).show();

    }
}
