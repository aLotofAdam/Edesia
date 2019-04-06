package com.example.edesia.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class GroceryList extends Fragment {

    public GroceryList() {
        // Required empty public constructor
    }

    @Override //might can change nonnull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate layout
        return inflater.inflate(R.layout.grocery_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // Button button = view.findViewById(R.id.gl_to_home);
        //button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.gl_to_home, null));
        //uses navigationController to use the action gl_to_home button to navigate
        Navigation.findNavController(view).navigate(R.id.action_grocery_list_to_home);
    }
}
