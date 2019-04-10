package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface

 */
public class Home extends Fragment {

    public Home() {
        // Required empty public constructor
    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override //might can change nonnull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getActionBar().show();
        //inflate layout
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //uses navigationController to use the action gl_to_home button to navigate
        //Navigation.findNavController(view).navigate(R.id.action_Home_to_grocery_list, savedInstanceState);
        view.findViewById(R.id.to_ExpandedDayView).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_home_to_expanded_day_view, savedInstanceState));
        view.findViewById(R.id.to_RecipeSearch).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_Home_to_recipe_search, savedInstanceState));
    }

    public interface OnFragmentInteractionListener {
    }
}
