package com.certis_base_app.ui.menuMessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.certis_base_app.R;

public class MessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();

    }
            }
