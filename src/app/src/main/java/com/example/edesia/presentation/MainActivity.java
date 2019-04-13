package com.example.edesia.presentation;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//TODO on merge: changed mainActivity to not be an empty constructor, removed implemented home and mealrandomizer
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Login.OnFragmentInteractionListener, SignUp.OnFragmentInteractionListener,
        CurrentMealPlan.OnFragmentInteractionListener, EditMenu.OnFragmentInteractionListener, GoogleVision.OnFragmentInteractionListener,
        RecipeOverview.OnFragmentInteractionListener, RecipeSteps.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener, UploadRecipe.OnFragmentInteractionListener,
        UserMenu.OnFragmentInteractionListener {
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawer_layout;
    public BottomNavigationView bottomNav;
    public NavController navController;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO might need to change to main_menu
        Toolbar toolbar = findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }/*
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu24px);
        }*/
        final Activity activity = this;

        //interface for Navigation drawer
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer_layout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        //sets up navigation drawer
        final NavigationView navigationView = findViewById(R.id.drawer_view);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                    //navigation UI looks for action matching the menu item and navigates there if found.
                    //otherwise, this will bubble up to parent
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

                    //set item as selected
                    menuItem.setChecked(true);
                    //close drawer when item is tapped
                    drawer_layout.closeDrawers();

                    navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
                    navigationView.setItemIconTintList(ColorStateList.valueOf(Color.GRAY));

                    return NavigationUI.onNavDestinationSelected(menuItem, navController)
                            || MainActivity.super.onOptionsItemSelected(menuItem);
                }
            });

		//TODO maybe add fab?
        //FloatingActionButton fab = findViewById(R.id.navi_map);
       // fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                //Navigation.findNavController().navigate(R.id.nav_host_fragment);
            //}
       // });

		//TODO dup NavController above, need both? //attach NavController to NavHost
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //setupDrawerMenu(navController); //broken casting

        //drawer_layout = findViewById(R.id.drawer_layout);

		//sets top level destinations via the navigation graph
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.navi_map);
        topLevelDestinations.add(R.id.actionbar);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).
                setDrawerLayout(drawer_layout).build();

       // appBarConfiguration = new AppBarConfiguration();

        //sets up action bar with navController and top level destinations and passes to method for handling
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		setupActionBar(navController, appBarConfiguration);

		//method to setup bottom navigation bar
        bottomNav = setupBottomNavMenu(navController);

        //hideBottomNavigationView(bottomNav);
        //TODO mess with these
        //hideBottomNavigationView(bottomNav);
        //showBottomNavigationView(bottomNav);
        //onResume();

		//creates the listener for navigation on the main activity
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
			public final void onDestinationChanged(@NonNull NavController navController,
                @NonNull NavDestination destination, @Nullable Bundle savedInstanceState) {
                    String newDestination;
                    String destinations;
                    try {
                        newDestination = MainActivity.this.getResources().getResourceName(destination.getId());
                        destinations = newDestination;
                    } catch (Resources.NotFoundException var7) {
                        newDestination = Integer.toString(destination.getId());
                        destinations = newDestination;
                    }
					//TODO remove Toast message after testing
                    Toast.makeText(MainActivity.this, ("Navigated to " + destinations), Toast.LENGTH_LONG).show();
                    Log.d("NavigationActivity", "Navigated to " + destinations);
            }
        });

        //TODO start activity stuff
        /*ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};

        Spinner s1 = (Spinner)findViewById(R.id.month_spinner1);
        Spinner s2 = (Spinner)findViewById(R.id.day_spinner1);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(days);*/

    }

    //broken casting
    private void setupDrawerMenu(NavController navController) {
        DrawerLayout drawer_menu = this.findViewById(R.id.drawer_layout);
        if (drawer_menu != null) {
            NavigationUI.setupActionBarWithNavController(this, navController, drawer_menu);
        }
    }

    public NavController getNavController() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController;
    }

    //method to attach navController to bottom navigation menu
    public BottomNavigationView setupBottomNavMenu(NavController navController) {
        BottomNavigationView bottomNav = this.findViewById(R.id.bottom_menu_navi);
        if (bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
        return bottomNav;
    }

    public BottomNavigationView getBottomNav() {
        //BottomNavigationView bottomNav;
        bottomNav = this.findViewById(R.id.bottom_menu_navi);
        return bottomNav;
    }

    //method to attach navController to acton bar
    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig){
        //navigation UI determines whether to put an up arrow in action bar or draw icon
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    }

	//TODO change this from navi view, method to setup the overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //boolean retValue = super.onCreateOptionsMenu(menu);
        //menu = findViewById(R.menu.actionbar_menu;
        //Add items to inflate the menu if there isn't a navigationView;
        //this adds items to the action bar if it is present.
        //if (menu == null){
            getMenuInflater().inflate(R.menu.actionbar_menu, menu);
            return true;
        //}
        //return retValue;
    }

	//method to attach navController to navigation drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: drawer_layout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);

        //navigation UI looks for action matching the menu item and navigates there if found.
        //otherwise, this will bubble up to parent
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //boolean navigated = false;
        //if (item != null) {
            //navigated = NavigationUI.onNavDestinationSelected(item, navController);
        //}
        //if (navigated) return true;
        //else return super.onOptionsItemSelected(item);
    }

	//method to add up navigation support if no drawer is present
    @Override
    public boolean onSupportNavigateUp() {
        //navigation UI will support up navigation, or drawer menu
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    //listener for handling events on navigation items
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void hideBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(view.getHeight()).setDuration(300);
        bottomNav.setVisibility(View.GONE);
    }

    public void showBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(300);
        bottomNav.setVisibility(View.VISIBLE);
    }

   /* @Override
    public void onClose() {
        BottomNavigationView bottomNavigationView = getBottomNav();
        hideBottomNavigationView(bottomNavigationView);
    }

    @Override
    public void onOpen() {
        BottomNavigationView bottomNavigationView = getBottomNav();
        showBottomNavigationView(bottomNavigationView);
    }*/

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof Login) {
            Login login = (Login) fragment;
            login.onStop();
        }
        if (fragment instanceof SignUp) {
            SignUp signup = (SignUp) fragment;
            signup.onStop();
        }
        /*if (fragment instanceof Home) {
            Home home = (Home) fragment;
            home.onResume();
        }*/
    }

    public void setNavigationVisibility(boolean visible) {
        bottomNav = getBottomNav();
        if(!bottomNav.isShown() && visible){
            bottomNav.setVisibility(View.INVISIBLE);
        }
        if (bottomNav.isShown() && visible) {
            bottomNav.setVisibility(View.INVISIBLE);
        }
        else if (!bottomNav.isShown() && !visible){
            bottomNav.setVisibility(View.VISIBLE);
        }
        else if (bottomNav.isShown() && !visible){
            bottomNav.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}