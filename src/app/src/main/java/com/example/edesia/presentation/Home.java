package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {

    LoginActivity user = new LoginActivity();

    ImageButton randomizer, userMenu, groceryList, recipeSearch, googleVision, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        randomizer = findViewById(R.id.Randomizer);
        userMenu = findViewById(R.id.UserMenu);
        groceryList = findViewById(R.id.GroceryList);
        recipeSearch = findViewById(R.id.RecipeSearch);
        googleVision = findViewById(R.id.OCR);
        logout = findViewById(R.id.LogOut);


        randomizer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRandomizer();
            }
        });

        userMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openUserMenu();
            }
        });

        groceryList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGroceryList();
            }
        });

        recipeSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRecipeSearch();
            }
        });

        googleVision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGoogleVision();
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LogOut();
            }
        });


    }

    public void openRandomizer(){
        Intent intent = new Intent(this, MealRandomizer.class);
        startActivity(intent);
    }

    public void openRecipeSearch(){
        Intent intent = new Intent(this, RecipeSearch.class);
        startActivity(intent);
    }


    public void openGroceryList(){
        Intent intent = new Intent(this, GroceryList.class);
        startActivity(intent);
    }


    public void openUserMenu(){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }


    public void openGoogleVision(){
        Intent intent = new Intent(this, OCR_Vision.class);
        startActivity(intent);
    }

    public void LogOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        user.setCurrentUser("");
        startActivity(intent);
    }
}

/* TODO Previous life as a fragment below
package com.example.edesia.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface

 */
/*
public class Home extends Fragment {
    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override //might can change nonnull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(),
                R.style.ThemeOverlay_AppCompat_Dark_ActionBar);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        return localInflater.inflate(R.layout.home, container, false);

        //inflate layout
        //return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //uses navigationController to use the action gl_to_home button to navigate
        //Navigation.findNavController(view).navigate(R.id.action_Home_to_grocery_list, savedInstanceState);
        /*view.findViewById(R.id.to_ExpandedDayView).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_home_to_expanded_day_view, savedInstanceState));*/
        /*view.findViewById(R.id.meal_randomizer).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_Home_to_meal_randomizer, savedInstanceState));*/
        /*view.findViewById(R.id.actionbar).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_Home_to_recipe_search));*/
    /*}

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

        //hides bottom navigation bar
        //void onClose();
        //shows bottom navigation bar
        // void onOpen();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;

        //navController = activity.getNavController();
        //activity.setupBottomNavMenu(activity.getNavController());

        activity.setNavigationVisibility(false);

        BottomNavigationView bottomNavigationView = activity.getBottomNav();

        activity.showBottomNavigationView(bottomNavigationView);

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
/*}
 */