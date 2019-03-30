package com.example.edesia.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class SignUp extends Fragment {

    DatabaseHelper myDb;
    Context context = getActivity().getApplicationContext();

    EditText name;
    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button backButton;
    Button registerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View signUpView = inflater.inflate(R.layout.sign_up, container, false);

        name = signUpView.findViewById(R.id.editTextName);
        username = signUpView.findViewById(R.id.editTextUsername);
        email = signUpView.findViewById(R.id.editTextEmail);
        password = signUpView.findViewById(R.id.editTextPassword);
        confirmPassword = signUpView.findViewById(R.id.editTextConfirmPassword);
        backButton = signUpView.findViewById(R.id.signUpBackButton);
        registerButton = signUpView.findViewById(R.id.RegisterButton);



        if(name.equals("")||username.equals("")||email.equals("")||password.equals("")||confirmPassword.equals("")){
            Toast.makeText(getContext(), "Fields missing", Toast.LENGTH_SHORT);
        }

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean passwordsMatch = confirmPassword(password.toString(), confirmPassword.toString());
                    boolean inserted = myDb.insertData(name.toString(), username.toString(), email.toString(), password.toString());
                    if(inserted && passwordsMatch) {
                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Registration Incomplete",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        return signUpView;
    }

    private boolean confirmPassword(String pass, String cPass){

        if(pass.equals(cPass)) {
            return true;
        }else{
            password.setError("Passwords Do Not Match");
            return false;
        }
    }


}
