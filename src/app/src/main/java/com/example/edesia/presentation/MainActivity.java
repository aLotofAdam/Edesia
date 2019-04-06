package com.example.edesia.presentation;

import android.content.res.Resources;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//TODO on merge: changed mainActivity to not be an empty constructor
public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // TODO get rid of? navi activity contains navhost, but might use in activity_main.xml
        this.setContentView(R.layout.activity_main);

        // TODO might need to change to main_menu
        Toolbar toolbar = this.findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);

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

		//TODO comment out this method and test. Maybe just comment out public void onclick?
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_grocery_list_to_home);
            }
        });

		//attach NavController to NavHost
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

		//TODO test if this does anything
        DrawerLayout drawerLayout = this.findViewById(R.id.navigationView); //maybe change id?

		////sets top level destinations via the navigation graph
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.navi_map);
        topLevelDestinations.add(R.id.navigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations)
            .setDrawerLayout(drawerLayout).build();

        //sets up action bar with navController and top level destinations and passes to method for handling
        NavigationUI.setupActionBarWithNavController(this, navController, this.appBarConfiguration);
		this.setupActionBar(navController, appBarConfiguration);

		//method to setup bottom navigation bar
        this.setupBottomNavMenu(navController);

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

	//method to setup the navigation drawer
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        Menu navigationView = findViewById(R.id.navi_map);
        //Add items to inflate the menu if there isn't a navigationView;
        //this adds items to the action bar if it is present.
        if (navigationView == null){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return retValue;
    }

	//method to attach navController to navigation drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //navigation UI looks for action matching the menu item and navigates there if found.
        //otherwise, this will bubble up to parent
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        boolean navigated = false;
        if (item != null) {
            navigated = NavigationUI.onNavDestinationSelected(item, navController);
        }
        if (navigated) return true;
        else return super.onOptionsItemSelected(item);
    }

	//method to add up navigation support if no drawer is present
    @Override
    public boolean onSupportNavigateUp() {
        //navigation UI will support up navigation, or drawer menu
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }
}