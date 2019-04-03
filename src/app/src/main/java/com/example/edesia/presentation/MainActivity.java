package com.example.edesia.presentation;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavHostFragment host;
    private NavController navController;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View view = new View()
        // TODO get rid of? navi activity contains navhost, but might use in activity_main.xml
        this.setContentView(R.layout.activity_main);

        // TODO might need to change to main_menu
        Toolbar toolbar = this.findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.navi_map);
       // fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                //Navigation.findNavController().navigate(R.id.nav_host_fragment);
            //}
       // });


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_grocery_list_to_home);
            }
        });

        //R.id.action_home_to_current_meal_plan.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.next_fragment, null));


        //Home.instantiate(). setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_homePageFragment));
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //Navigation.setViewNavController(fab, navController);

        //naviMap = findViewById(R.id.navi_map);

        //navController = Navigation.findNavController(this, findViewById(R.id.nav_host_fragment));
        //NavHostFragment navHostFragment = this.create((R.id.nav_host_fragment), savedInstanceState); findViewById(R.id.nav_host_fragment); (R.id.nav_host_fragment); getSupportFragmentManager(findViewById(R.id.nav_host_fragment)); NavHostFragment. findFragmentById(R.id.nav_host_fragment));
        //if (navHostFragment != null){
        //-->>>Navigation.setViewNavController(view, navController);
        //NavHostFragment.findNavController(Fragment.instantiate(this, com.example.edesia.presentation.Home, savedInstanceState));
            //NavHostFragment host = NavHostFragment.create(R.navigation.navi_graph);
            //1.1 NavController navController = host.getNavController();
           // Intrinsics.checkExpressionValueIsNotNull(var12, "host.navController");
            //NavController navController = var12;

            DrawerLayout drawerLayout = this.findViewById(R.id.navigationView); //maybe

            Set<Integer> topLevelDestinations = new HashSet<>();
            topLevelDestinations.add(R.id.navi_map);
            //topLevelDestinations.add(drawerLayout);

            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations)
                    .setDrawerLayout(drawerLayout).build();

           // NavController navController = NavigationUI.setupActionBarWithNavController(this, R.id.nav_controller_view_tag); .findNavController(this, R.id.nav_host_fragment);

            NavigationUI.setupActionBarWithNavController(this, navController, this.appBarConfiguration);

           // Set topLevelDestinationId = Set(new Integer[]{-1000018, -1000177});
            //Function fallbackOnNavigateUpListener = MainActivity.onCreate.inlined.AppBarConfiguration.INSTANCE;

//            AppBarConfiguration var13 = (new AppBarConfiguration.Builder(topLevelDestinationId)
//                    .setDrawerLayout(drawerLayout).setFallbackOnNavigateUpListener
//                            ((AppBarConfiguration.OnNavigateUpListener)
//                                    (new MainActivity$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(fallbackOnNavigateUpListener$iv))).build();

            //Intrinsics.checkExpressionValueIsNotNull(var13, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
//            AppBarConfiguration var11 = var13;
//            this.appBarConfiguration = var11;
//            AppBarConfiguration appBarConfiguration = this.appBarConfiguration;
//            if (this.appBarConfiguration == null) {
//                System.out.println("appBarConfiguration is null");
//            }

            this.setupActionBar(navController, appBarConfiguration);
            this.setupBottomNavMenu(navController);

            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                public final void onDestinationChanged(@NonNull NavController navController,
                                                       @NonNull NavDestination destination,
                                                       @Nullable Bundle savedInstanceState) {
                   // Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                    //Intrinsics.checkParameterIsNotNull(destination, "destination");

                    String var10000;
                    String var5;
                    try {
                        var10000 = MainActivity.this.getResources().getResourceName(destination.getId());
                        //Intrinsics.checkExpressionValueIsNotNull(var10000, "resources.getResourceName(destination.id)");
                        var5 = var10000;
                    } catch (Resources.NotFoundException var7) {
                        var10000 = Integer.toString(destination.getId());
                        //Intrinsics.checkExpressionValueIsNotNull(var10000, "Integer.toString(destination.id)");
                        var5 = var10000;
                    }

                    Toast.makeText(MainActivity.this, ("Navigated to " + var5), Toast.LENGTH_LONG).show();
                    Log.d("NavigationActivity", "Navigated to " + var5);
                }
            });
        //}
    }

    private final void setupBottomNavMenu(NavController navController) {
        BottomNavigationView bottomNav = this.findViewById(R.id.bottom_menu_navi);
        if (bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }



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