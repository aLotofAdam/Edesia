package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
//import androidx.navigation.ui.R;

public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener {
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login);

        // TODO get rid of? navi activity contains navhost, but might use in activity_main.xml
        this.setContentView(R.layout.activity_main);

        // TODO might need to change to main_menu
        Toolbar toolbar = findViewById(R.id.toolbar);

        //setup action bar with navHost controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupWithNavController(this, navController);
        DrawerLayout drawerLayout = findViewById(R.id.navigationView);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        //this.setActionBar(toolbar); //try setActionBar, or setupActionBar too
        this.setupBottomNaviMenu(navController);
        this.setupNavigationMenu(navController); //change to toolbar?
        //this.setupActionBar(navController, appBarConfiguration);


        //appBarConfiguration = AppBarConfiguration(navController.getGraph()).setDrawerLayout(drawerLayout);

        //appBarConfiguration = new AppBarConfiguration(Set<Integer> , drawerLayout, appBarConfiguration);
        ///appBarConfiguration = AppBarConfiguration.OnNavigateUpListener(navController.getGraph())
              //.setDrawerLayout(drawerLayout);

        //appBarConfiguration = new;
       // NavigationUI.navigateUp(navController, drawerLayout);

        //navController.addOnDestinationChangedListener(listener);

      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    //@Override
    //public boolean onSupportNavigateUp() = findNavController(R.id.activity_main).navigateUp();

    private void setupBottomNaviMenu(NavController navController){
        //navigation UI setup for bottom navigation menu
        BottomNavigationView bottomNavi = findViewById(R.id.bottom_menu_navi);
        if (bottomNavi != null){
            NavigationUI.setupWithNavController(bottomNavi, navController);
        }
    }

    private void setupNavigationMenu(NavController navController){
        //split screen mode support, drags view out from left
        View sideNavView = findViewById(R.id.navi_view);
    }

    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig){
        //navigation UI determines whether to put an up arrow in action bar or draw icon
        //setupActionBar(navController, appBarConfig);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        Menu navigationView = findViewById(R.id.navi_view);
        //Add items to inflate the menu if there isn't a navigationView;
        // this adds items to the action bar if it is present.
        if (navigationView == null){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return retValue;
    }

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

    @Override
    public boolean onSupportNavigateUp() {
        //navigation UI will support up navigation, or drawer menu
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
        //return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment),
          //     drawerLayout);
    }

}