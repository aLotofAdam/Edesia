package com.example.edesia.presentation;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;

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
        Home.OnFragmentInteractionListener, CurrentMealPlan.OnFragmentInteractionListener,
        EditMenu.OnFragmentInteractionListener, ExpandedDayView.OnFragmentInteractionListener,
        GoogleVision.OnFragmentInteractionListener, RecipeOverview.OnFragmentInteractionListener,
        RecipeSearch.OnFragmentInteractionListener, RecipeSteps.OnFragmentInteractionListener,
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
					/*//Test navigation toast message
                    Toast.makeText(MainActivity.this, ("Navigated to " + destinations), Toast.LENGTH_LONG).show();
                    Log.d("NavigationActivity", "Navigated to " + destinations);*/
            }
        });
    }

    public NavController getNavController() {
        return Navigation.findNavController(this, R.id.nav_host_fragment);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Add items to inflate the menu if there isn't a navigationView;
        //this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
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

    /**
     * Activity for the Ocr Detecting app.  This app detects text and displays the value with the
     * rear facing camera. During detection overlay graphics are drawn to indicate the position,
     * size, and contents of each TextBlock.
     */
    /*public final class OcrCaptureActivity extends AppCompatActivity {
        private static final String TAG = "OcrCaptureActivity";

        // Intent request code to handle updating play services if needed.
        private static final int RC_HANDLE_GMS = 9001;

        // Permission request codes need to be < 256
        private static final int RC_HANDLE_CAMERA_PERM = 2;

        // Constants used to pass extra data in the intent
        public static final String AutoFocus = "AutoFocus";
        public static final String UseFlash = "UseFlash";
        public static final String TextBlockObject = "String";

        private CameraSource cameraSource;
        private CameraSourcePreview preview;
        private GraphicOverlay<OCRGraphic> graphicOverlay;

        // Helper objects for detecting taps and pinches.
        private ScaleGestureDetector scaleGestureDetector;
        private GestureDetector gestureDetector;

        *//**
         * Initializes the UI and creates the detector pipeline.
         *//*
        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.google_vision);

            preview = (CameraSourcePreview) findViewById(R.id.surface_view);
            graphicOverlay = findViewById(R.id.text_view);

            // Set good defaults for capturing text.
            boolean autoFocus = true;
            boolean useFlash = false;

            // Check for the camera permission before accessing the camera.  If the
            // permission is not granted yet, request permission.
            int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (rc == PackageManager.PERMISSION_GRANTED) {
                createCameraSource(autoFocus, useFlash);
            } else {
                requestCameraPermission();
            }

            gestureDetector = new GestureDetector(this, new CaptureGestureListener());
            scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

            Snackbar.make(graphicOverlay, "Tap to make list. Pinch/Stretch to zoom",
                    Snackbar.LENGTH_LONG).show();

            // Set up the Text To Speech engine.
           *//* TextToSpeech.OnInitListener listener =
                    new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(final int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                Log.d("OnInitListener", "Text to speech engine started successfully.");
                                tts.setLanguage(Locale.US);
                            } else {
                                Log.d("OnInitListener", "Error starting the text to speech engine.");
                            }
                        }
                    };
            tts = new TextToSpeech(this.getApplicationContext(), listener);*//*
        }

        *//**
         * Handles the requesting of the camera permission.  This includes
         * showing a "Snackbar" message of why the permission is needed then
         * sending the request.
         *//*
        private void requestCameraPermission() {
            Log.w(TAG, "Camera permission is not granted. Requesting permission");

            final String[] permissions = new String[]{Manifest.permission.CAMERA};

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
                return;
            }

            final Activity thisActivity = this;

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(thisActivity, permissions, RC_HANDLE_CAMERA_PERM);
                }
            };

            Snackbar.make(graphicOverlay, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, listener).show();
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            boolean b = scaleGestureDetector.onTouchEvent(e);

            boolean c = gestureDetector.onTouchEvent(e);

            return b || c || super.onTouchEvent(e);
        }

        *//**
         * Creates and starts the camera.  Note that this uses a higher resolution in comparison
         * to other detection examples to enable the ocr detector to detect small text samples
         * at long distances.
         *
         * Suppressing InlinedApi since there is a check that the minimum version is met before using
         * the constant.
         *//*
        @SuppressLint("InlinedApi")
        private void createCameraSource(boolean autoFocus, boolean useFlash) {
            Context context = getApplicationContext();

            // A text recognizer is created to find text.  An associated multi-processor instance
            // is set to receive the text recognition results, track the text, and maintain
            // graphics for each text block on screen.  The factory is used by the multi-processor to
            // create a separate tracker instance for each text block.
            // TODO: Create the TextRecognizer
            TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
            // TODO: Set the TextRecognizer's Processor.
            textRecognizer.setProcessor(new DetectorProcessor(graphicOverlay));

            // TODO: Check if the TextRecognizer is operational.
            if (!textRecognizer.isOperational()) {
                // Note: The first time that an app using a Vision API is installed on a
                // device, GMS will download a native libraries to the device in order to do detection.
                // Usually this completes before the app is run for the first time.  But if that
                // download has not yet completed, then the above call will not detect any text,
                // barcodes, or faces.
                //
                // isOperational() can be used to check if the required native libraries are currently
                // available.  The detectors will automatically become operational once the library
                // downloads complete on device.
                Log.w(TAG, "Detector dependencies are not yet available.");

                // Check for low storage.  If there is low storage, the native library will not be
                // downloaded, so detection will not become operational.
                IntentFilter lowStorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
                boolean hasLowStorage = registerReceiver(null, lowStorageFilter) != null;

                if (hasLowStorage) {
                    Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show();
                    Log.w(TAG, getString(R.string.low_storage_error));
                }
            }

            // Creates and starts the camera.  Note that this uses a higher resolution in comparison
            // to other detection examples to enable the text recognizer to detect small pieces of text.
            // TODO: Create the mCameraSource using the TextRecognizer.
            CameraSource cameraSource;
            cameraSource = new CameraSource.Builder(getApplicationContext(),
                    textRecognizer).setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024).setRequestedFps(2.0f)
                    .setAutoFocusEnabled(autoFocus).build();
            //setFocusMode(autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO : null).build();
            //setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
        }

        *//**
         * Restarts the camera.
         *//*
        @Override
        protected void onResume() {
            super.onResume();
            startCameraSource();
        }

        *//**
         * Stops the camera.
         *//*
        @Override
        protected void onPause() {
            super.onPause();
            if (preview != null) {
                preview.stop();
            }
        }

        *//**
         * Releases the resources associated with the camera source, the associated detectors, and the
         * rest of the processing pipeline.
         *//*
        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (preview != null) {
                preview.release();
            }
        }

        *//**
         * Callback for the result from requesting permissions. This method
         * is invoked for every call on {@link #requestPermissions(String[], int)}.
         * <p>
         * <strong>Note:</strong> It is possible that the permissions request interaction
         * with the user is interrupted. In this case you will receive empty permissions
         * and results arrays which should be treated as a cancellation.
         * </p>
         *
         * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
         * @param permissions  The requested permissions. Never null.
         * @param grantResults The grant results for the corresponding permissions
         *                     which is either {@link PackageManager#PERMISSION_GRANTED}
         *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
         * @see #requestPermissions(String[], int)
         *//*
        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            if (requestCode != RC_HANDLE_CAMERA_PERM) {
                Log.d(TAG, "Got unexpected permission result: " + requestCode);
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
            }

            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Camera permission granted - initialize the camera source");
                // we have permission, so create the camerasource
                boolean autoFocus = getIntent().getBooleanExtra(AutoFocus,true);
                boolean useFlash = getIntent().getBooleanExtra(UseFlash, false);
                createCameraSource(autoFocus, useFlash);
                return;
            }

            Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                    " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Multitracker sample")
                    .setMessage(R.string.no_camera_permission)
                    .setPositiveButton(R.string.ok, listener)
                    .show();
        }

        *//**
         * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
         * (e.g., because onResume was called before the camera source was created), this will be called
         * again when the camera source is created.
         *//*
        private void startCameraSource() throws SecurityException {
            // check that the device has play services available.
            int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                    getApplicationContext());
            if (code != ConnectionResult.SUCCESS) {
                Dialog dlg =
                        GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
                dlg.show();
            }

            if (cameraSource != null) {
                try {
                    preview.start(cameraSource, graphicOverlay);
                } catch (IOException e) {
                    Log.e(TAG, "Unable to start camera source.", e);
                    cameraSource.release();
                    cameraSource = null;
                }
            }
        }


        *//**
         * onTap is called to send text to grocery list.
         *
         * @param rawX - the raw position of the tap
         * @param rawY - the raw position of the tap.
         * @return true if the tap was on a TextBlock
         *//*
        private boolean onTap(float rawX, float rawY) {
            // TODO: Send text to Grocery List when the user taps on screen.
            OCRGraphic graphic = graphicOverlay.getGraphicAtLocation(rawX, rawY);
            TextBlock text = null;
            if (graphic != null) {
                text = graphic.getTextBlock();
                if (text != null && text.getValue() != null) {
                    Log.d(TAG, "text data is being sent to Grocery List " + text.getValue());
                    // TODO: Handle passing the data
                    //tts.speak(text.getValue(), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                } else {
                    Log.d(TAG, "text data is null");
                }
            } else {
                Log.d(TAG, "no text detected");
            }
            return text != null;
        }

        public static class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
            }
        }

        public static class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

            *//**
             * Responds to scaling events for a gesture in progress.
             * Reported by pointer motion.
             *
             * @param detector The detector reporting the event - use this to
             *                 retrieve extended info about event state.
             * @return Whether or not the detector should consider this event
             * as handled. If an event was not handled, the detector
             * will continue to accumulate movement until an event is
             * handled. This can be useful if an application, for example,
             * only wants to update scaling factors if the change is
             * greater than 0.01.
             *//*
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                return false;
            }

            *//**
             * Responds to the beginning of a scaling gesture. Reported by
             * new pointers going down.
             *
             * @param detector The detector reporting the event - use this to
             *                 retrieve extended info about event state.
             * @return Whether or not the detector should continue recognizing
             * this gesture. For example, if a gesture is beginning
             * with a focal point outside of a region where it makes
             * sense, onScaleBegin() may return false to ignore the
             * rest of the gesture.
             *//*
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            *//**
             * Responds to the end of a scale gesture. Reported by existing
             * pointers going up.
             * <p/>
             * Once a scale has ended, {@link ScaleGestureDetector#getFocusX()}
             * and {@link ScaleGestureDetector#getFocusY()} will return focal point
             * of the pointers remaining on the screen.
             *
             * @param detector The detector reporting the event - use this to
             *                 retrieve extended info about event state.
             *//*
            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                if (cameraSource != null) {
                   // cameraSource.doZoom(detector.getScaleFactor());
                }
            }
        }
    }*/
}