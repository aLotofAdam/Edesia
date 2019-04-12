package com.example.edesia.middle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.edesia.presentation.MainActivity;
import com.example.edesia.presentation.SignUpActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

public class Navigation extends MainActivity {
    @Override
    public NavController getNavController() {
        return super.getNavController();
    }

    @Override
    public BottomNavigationView setupBottomNavMenu(NavController navController) {
        return super.setupBottomNavMenu(navController);
    }

    @Override
    public BottomNavigationView getBottomNav() {
        return super.getBottomNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return super.onNavigationItemSelected(menuItem);
    }

    @Override
    public void hideBottomNavigationView(BottomNavigationView view) {
        super.hideBottomNavigationView(view);
    }

    @Override
    public void showBottomNavigationView(BottomNavigationView view) {
        super.showBottomNavigationView(view);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void setNavigationVisibility(boolean visible) {
        super.setNavigationVisibility(visible);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        super.onFragmentInteraction(uri);
    }

    public void openSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
