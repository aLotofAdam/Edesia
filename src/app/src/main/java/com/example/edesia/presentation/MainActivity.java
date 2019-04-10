package com.example.edesia.presentation;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//TODO on merge: changed mainActivity to not be an empty constructor
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Login.OnFragmentInteractionListener, SignUp.OnFragmentInteractionListener,
        CurrentMealPlan.OnFragmentInteractionListener, EditMenu.OnFragmentInteractionListener,
        ExpandedDayView.OnFragmentInteractionListener, GoogleVision.OnFragmentInteractionListener,
        GroceryList.OnFragmentInteractionListener, Home.OnFragmentInteractionListener,
        MealRandomizer.OnFragmentInteractionListener, RecipeOverview.OnFragmentInteractionListener,
        RecipeSearch.OnFragmentInteractionListener, RecipeSteps.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener, UploadRecipe.OnFragmentInteractionListener,
        UserMenu.OnFragmentInteractionListener {
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawer_layout;

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
        }
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu24px);
        }
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
                   // boolean navigated = false;
                     //   navigated = NavigationUI.onNavDestinationSelected(menuItem, navController);
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

		//attach NavController to NavHost
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
        setupBottomNavMenu(navController);

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
    }

    public void onFragmentInteraction(URI uri){

    }
    //broken casting
    private void setupDrawerMenu(NavController navController) {
        DrawerLayout drawer_menu = this.findViewById(R.id.drawer_layout);
        if (drawer_menu != null) {
            NavigationUI.setupActionBarWithNavController(this, navController, drawer_menu);
        }
    }

	//method to attach navController to bottom navigation menu
    private final void setupBottomNavMenu(NavController navController) {
        BottomNavigationView bottomNav = this.findViewById(R.id.bottom_menu_navi);
        if (bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }

	//method to attach navController to acton bar
    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig){
        //navigation UI determines whether to put an up arrow in action bar or draw icon
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    }

	//method to setup the overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        Menu navigationView = findViewById(R.id.navi_map);
        //Add items to inflate the menu if there isn't a navigationView;
        //this adds items to the action bar if it is present.
        if (navigationView == null){
            getMenuInflater().inflate(R.menu.drawer_menu, menu);
            return true;
        }
        return retValue;
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
}