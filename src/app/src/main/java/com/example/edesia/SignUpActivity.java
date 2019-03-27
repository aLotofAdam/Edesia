package com.example.edesia;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText textInputName;
    private TextInputEditText textInputUsername;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputPassword;
    private TextInputEditText textInputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    private boolean confirmPassword(){

        if(textInputPassword.equals(textInputConfirmPassword)) {
            return true;
        }else{
            textInputPassword.setError("Passwords Do Not Match");
            return false;
        }
    }
}
