package com.efpro.iak20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //inisialisasi view
    private EditText edtUser, edtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inisialisasi id
        edtUser     = (EditText) findViewById(R.id.edtUser);
        edtPass     = (EditText) findViewById(R.id.edtPass);
        btnLogin    = (Button) findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jika username dan password kosong
                if (edtUser.getText().toString().isEmpty() || edtPass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Username or Password is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (edtUser.getText().toString().equals("admin") && edtPass.getText().toString().equals("admin")){
                        startActivity( new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password is Wrong", Toast.LENGTH_SHORT).show();
                        edtUser.setText("");
                        edtPass.setText("");
                    }
                }
            }
        });
    }
}
