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
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface

 */

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
        view.findViewById(R.id.meal_randomizer).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_Home_to_meal_randomizer, savedInstanceState));
        view.findViewById(R.id.actionbar).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_Home_to_recipe_search));
    }

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
}
