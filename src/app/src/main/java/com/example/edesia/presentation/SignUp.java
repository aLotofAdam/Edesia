package com.example.edesia.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class SignUp extends Fragment {

    DatabaseHelper myDb;

    Context context;
    EditText name;
    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button backButton;
    Button registerButton;

    public SignUp() {
        // Required empty public constructor
    }

    //need it?
    private OnFragmentInteractionListener mListener;

    //TODO make Bundle androidx?
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Login);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        View signUpView = localInflater.inflate(R.layout.sign_up, container, false);
        //View signUpView = inflater.inflate(R.layout.sign_up, container, false);

        name = signUpView.findViewById(R.id.editTextName);
        username = signUpView.findViewById(R.id.editTextUsername);
        email = signUpView.findViewById(R.id.editTextEmail);
        password = signUpView.findViewById(R.id.editTextPassword);
        confirmPassword = signUpView.findViewById(R.id.editTextConfirmPassword);
        backButton = signUpView.findViewById(R.id.signUpBackButton);
        registerButton = signUpView.findViewById(R.id.RegisterButton);

        if (getContext() != null){
            this.context = getContext(); //getActivity().getApplicationContext();
        }

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
                    boolean inserted = myDb.insertUserData(name.toString(), username.toString(), email.toString(), password.toString());
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.RegisterButton).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_signUp_to_home, savedInstanceState));
        view.findViewById(R.id.signUpBackButton).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_signUp_to_login, savedInstanceState));
    }

    private boolean confirmPassword(String pass, String cPass){

        if(pass.equals(cPass)) {
            return true;
        }else{
            password.setError("Passwords Do Not Match");
            return false;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        //void onOpen();
       // void onClose();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;

        activity.setNavigationVisibility(true);

        BottomNavigationView bottomNavigationView = activity.getBottomNav();

        activity.hideBottomNavigationView(bottomNavigationView);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*@Override
    public void onStop() {
        super.onStop();
        mListener.onClose();
    }*/

    /*@Override
    public void onResume() {
        super.onResume();
        mListener.onOpen();
    }*/
}
