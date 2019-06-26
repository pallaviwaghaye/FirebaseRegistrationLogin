package com.practice.firebaseregistrationlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailId;
    private EditText password;
    private EditText fullName;
    private EditText MobNo;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailId=(EditText)findViewById(R.id.editTextRegEmailid);
        password=(EditText)findViewById(R.id.editTextRegPassword);
        fullName=(EditText)findViewById(R.id.editTextRegFullName);
        MobNo=(EditText)findViewById(R.id.editTextRegMobileNumber);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    public void btnRegistrationUser_Click(View v)
    {
        final ProgressDialog progressDialog=ProgressDialog.show(RegistrationActivity.this,"Please wait..","Processing",true);
        (firebaseAuth.createUserWithEmailAndPassword(emailId.getText().toString(),password.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    DatabaseReference db= FirebaseDatabase.getInstance().getReference();
                    DatabaseReference users = db.child("Users").child(firebaseAuth.getCurrentUser().getUid());
                    users.child("Name").setValue(MobNo);

                    Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Log.e("error",task.getException().toString());
                    Toast.makeText(RegistrationActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
