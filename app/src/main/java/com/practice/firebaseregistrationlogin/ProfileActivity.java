package com.practice.firebaseregistrationlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView EmailIdProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EmailIdProfile=(TextView)findViewById(R.id.textViewProfileEmailId);
        EmailIdProfile.setText(getIntent().getExtras().getString("Email"));
    }

    public void btnLogout_click(View v)
    {
        Intent i=new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(i);
    }
}
