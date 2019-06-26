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

public class LoginActivity extends AppCompatActivity {
    private EditText emailIdLogin;
    private EditText passwordLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailIdLogin=(EditText)findViewById(R.id.editTextLoginEmailid);
        passwordLogin=(EditText)findViewById(R.id.editTextLoginPassword);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void btnLoginUser_Click(View v)
    {
        final ProgressDialog progressDialog=ProgressDialog.show(LoginActivity.this,"Please wait..","Processing",true);
        (firebaseAuth.signInWithEmailAndPassword(emailIdLogin.getText().toString(),passwordLogin.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                            intent.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                            startActivity(intent);
                        }
                        else
                        {
                            Log.e("error",task.getException().toString());
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
