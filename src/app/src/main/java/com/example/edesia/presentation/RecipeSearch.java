package com.example.edesia.presentation;
//imports needed
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class RecipeSearch extends AppCompatActivity {
    private static final String TAG = "RecipeSearch";
    private Button searchBtn;
    public EditText recipeSearch;
    /*
        public RecipeSearch(String recipeNme) {
            // Required empty public constructor
            this.recipeName = recipeNe;

        }
    /*
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecipeSearch.

    // TODO: Rename and change types and number of parameters
    public static RecipeSearch newInstance(String param1, String param2) {
        RecipeSearch fragment = new RecipeSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(recipeSearch);
                recipeString = findViewId(R.id.recipeString);
                recipeSearch = findViewById(R.id.recipe_search);
                searchBtn = findViewById(R.id.recipeSearchBtn);
                //need to maybe add a results variable
                result_address=findViewById(R.id.result);
                searchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                        databaseAccess.open();
                        String r = recipeString.getText().toString();
                        String address = databaseAccess.getAddress(r);
                        result_address.setText(address);
                        databaseAccess.closeDb();
                    }
                });



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
//NEED THIS
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_recipe_layout, container, false);
        btnRecipeSearch=(Button) view.findViewById(R.id.recipeSearch);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    }
}
