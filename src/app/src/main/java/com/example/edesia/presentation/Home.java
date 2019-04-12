package com.example.edesia.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

//TODO jons home activity
public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};

        Spinner s1 = findViewById(R.id.month_spinner1);
        Spinner s2 = findViewById(R.id.day_spinner1);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);

        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(days);
    }
}

//TODO on Jon merge: converted Home to activity and put past fragment code below

/*
import android.app.Activity;
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
    Context context;

    public Home() {
        // Required empty public constructor
    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        assert activity != null;
        //activity.setContentView(R.layout.home);
        /*
        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;

        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
        */

       // Spinner s1 = activity.findViewById(R.id.month_spinner1);
        //Spinner s2 = activity.findViewById(R.id.day_spinner1);
        /*
        SpinnerAdapter months = new SpinnerAdapter() {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };

        //ArrayAdapter(activity.getBaseContext(), android.R.layout.simple_spinner_item, R.id.month_spinner1);
        months.getDropDownView(R.id.month_spinner1, getView(), (ViewGroup) Objects.requireNonNull(getView()).getParent());
               // , this, android.R.layout.simple_spinner_item);setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);
*/
        /*ArrayAdapter months = ArrayAdapter.createFromResource(activity.getBaseContext(), R.array.months,
                android.R.layout.simple_spinner_item);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);

        ArrayAdapter days = ArrayAdapter.createFromResource(activity.getBaseContext(), R.array.Numbers,
                android.R.layout.simple_spinner_item);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(days);*/
    /*}

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
//        view.findViewById(R.id.action_home_to_expanded_day_view).setOnClickListener(Navigation.
  //              createNavigateOnClickListener(R.id.action_home_to_expanded_day_view, savedInstanceState));
//        view.findViewById(R.id.meal_randomizer).setOnClickListener(Navigation.
  //              createNavigateOnClickListener(R.id.action_Home_to_meal_randomizer, savedInstanceState));
    //    view.findViewById(R.id.actionbar).setOnClickListener(Navigation.
      //          createNavigateOnClickListener(R.id.action_Home_to_recipe_search));
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
//}