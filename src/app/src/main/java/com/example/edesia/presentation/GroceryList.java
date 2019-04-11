package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GroceryList extends Fragment {
    private OnFragmentInteractionListener mListener;

    public GroceryList() {
        // Required empty public constructor
    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate layout
        return inflater.inflate(R.layout.grocery_list, container, false);
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // Button button = view.findViewById(R.id.gl_to_home);
        //button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.gl_to_home, null));
        //uses navigationController to use the action gl_to_home button to navigate
        //Navigation.findNavController(view).navigate(R.id.action_grocery_list_to_home);

        view.findViewById(R.id.grocery_list).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_grocery_list_to_home, savedInstanceState));
    }*/

    //allows communication between fragment and activity
    public interface OnFragmentInteractionListener {
    }
}
