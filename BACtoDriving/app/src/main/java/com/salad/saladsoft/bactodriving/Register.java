package com.salad.saladsoft.bactodriving;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends ActionBarActivity implements View.OnClickListener{
    EditText etName, etWeight, etUsername, etPassword, etCPassword;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etWeight = (EditText) findViewById(R.id.registerWeight);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etCPassword = (EditText) findViewById(R.id.etCPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String password2 = etCPassword.getText().toString();
                int age = Integer.parseInt(etWeight.getText().toString());
                if(password.equals(password2)) {
                    User user = new User(name, age, username, password);
                    registerUser(user);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Passwords Don't Match!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }
}