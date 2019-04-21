package com.example.edesia.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import com.example.edesia.middle.CameraSource;
import com.example.edesia.middle.CameraSourcePreview;
import com.example.edesia.middle.DetectorProcessor;
import com.example.edesia.middle.GraphicOverlay;
import com.example.edesia.middle.OCRGraphic;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static com.example.edesia.middle.CameraSource.Builder;
import static com.example.edesia.middle.CameraSource.CAMERA_FACING_BACK;

/**
 * Activity for the Ocr Detecting app.  This app detects text and displays the value with the
 * rear facing camera. During detection overlay graphics are drawn to indicate the position,
 * size, and contents of each TextBlock.
 */
public class OCR_Vision extends AppCompatActivity {

    private static final String TAG = "OCRVision";

    //Intent request code to handle updating play services if needed.
    private static final int RC_HANDLE_GMS = 9001;

    //Permission request codes need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    //Constants used to pass extra data in the intent
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";
    public static final String TextBlockObject = "String";

    private CameraSource cameraSource;
    private CameraSourcePreview preview;
    private GraphicOverlay<OCRGraphic> graphicOverlay;

    //Helper objects for detecting taps and pinches.
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    List<String>ingredientList = new ArrayList<>(
            Arrays.asList("bread","rye bread","mustard","relish","deli corned beef","sauerkraut",
                    "berries","whole,cloves","coriander seeds","extra-virgin olive oil",
                    "large white onion","clove of garlic","tomato paste","ground beef","chili powder",
                    "ground cumin","dried oregano","paprika","cayenne pepper","Kosher salt",
                    "Freshly ground black pepper","yellow onion","white onion","paprika",
                    "pickling spice","Crescent Rolls","cabbage leaves","Italian bread crumbs",
                    "red wine","corned beef","low-sodium beef broth","dried thyme","Swiss cheese",
                    "ketchup","large carrots","stalks celery","baby potatoes","corned beef brisket",
                    "kosher salt","bay leaves","thyme","sprigs thyme","russet potatoes","Kosher salt",
                    "butter","milk","sour cream","beef chuck stew meat","extra-virgin olive oil",
                    "large onion","provolone","rolls","boneless ribeye steaks","carrots",
                    "garlic powder","fresh thyme","ground beef","frozen peas","all-purpose flour",
                    "extra-virgin olive oil","yellow onion","jalapeño","cloves garlic","oregano",
                    "Cooking spray","grits","ground cumin","green lentils",
                    "boneless skinless chicken breasts","low-sodium chicken broth","Kosher salt",
                    "Freshly ground black pepper","can of white beans","sour cream","butter","onion",
                    "red bell pepper","shrimp","garlic cloves","Cajun seasoning","lemon juice",
                    "Worstershire sauce","Kosher salt","cream cheese","sour cream",
                    "shredded mozzarella","shredded Parmesan","butter","extra-virgin olive oil",
                    "shrimp","lemon","cloves garlic","crushed red pepper flakes","Kosher salt",
                    "Kosher salt","cooked shrimp","limes","oranges","red onion","jalapeños",
                    "cucumber","cherry tomatoes","cilantro","mayonnaise","butter","all-purpose flour",
                    "yellow onion","green bell pepper","celery ribs","cloves garlic","andouille sausage",
                    "cajun seasoning","kosher salt","Freshly ground black pepper","bay leaf",
                    "fire-roasted diced tomatoes","chicken broth","extra-virgin olive oil",
                    "bell peppers","onion","Kosher salt","black pepper",
                    "boneless skinless chicken breasts","chili powder","ground cumin","dried oregano",
                    "flour tortillas","shredded Monterey jack","shredded cheddar","avocado","fettucine",
                    "butter divided","shrimp","kosher salt","ground black pepper","clove garlic",
                    "all-purpose flour","heavy cream","whole milk","egg","cabbage","rice noodles",
                    "lime juice","brown sugar","fish sauce","low-sodium soy sauce","cayenne pepper",
                    "vegetable oil","bell pepper","garlic cloves","eggs","shrimp","black pepper",
                    "extra-virgin olive oil","onion","bell peppers","Kosher salt",
                    "ground black pepper","boneless chicken breasts","oregano","sausage",
                    "cloves garlic","tomatoes","low-sodium chicken stock","crushed tomatoes",
                    "rice","Old Bay seasoning","vegetable oil","garlic","carrots","green bell pepper",
                    "white rice","peas","soy sauce","sesame oil","egg","sriracha","panko bread crumbs",
                    "extra-virgin olive oil","garlic powder","Kosher salt","ground black pepper",
                    "large eggs","flour","raw shrimp","Fresh cilantro","mayonnaise","olive oil",
                    "onion","cloves garlic","bell pepper","shrimp","Cajun seasoning","kosher salt",
                    "Freshly ground black pepper","extra-virgin olive oil","large onion","ground beef",
                    "garlic cloves","kosher salt","refried beans","water","tortilla chips",
                    "shredded cheddar","Shredded Monterey jack","jalapeños","black beans","avocado"));

    /**
     * Initializes the UI and creates the detector pipeline.
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.google_vision);

        // Construct an Intent object for groceryList
        final Intent intent = new Intent(this, GroceryList.class);
        //ArrayList<String> gList = new ArrayList<>();


        // Get array list from OCR_Vision
        final ArrayList<String>gList = getIntent().getStringArrayListExtra("gList");

        preview = findViewById(R.id.preview);
        graphicOverlay = findViewById(R.id.graphicOverlay);

        //Set good defaults for capturing text.
        boolean autoFocus = true;
        boolean useFlash = false;

        //initialize the database
        //Database db = new Database(this);
        //Query for ingredients
        //ingredientList = db.getIngredients();
        //Gets individual ingredients from query result
        //RegexMatcher.PatternMatch(ingredientList);

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


        Snackbar.make(graphicOverlay, "Pinch/Stretch to zoom",
                Snackbar.LENGTH_LONG).show();


        // Set up the Text To Speech engine.
           /* TextToSpeech.OnInitListener listener =
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
            tts = new TextToSpeech(this.getApplicationContext(), listener);*/

        FloatingActionButton fab = findViewById(R.id.Visionfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stop();
                receiveDetections();
                Snackbar.make(view, "Camera stopped", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //final ArrayList<String> gList =

                intent.putStringArrayListExtra("gList",gList);
                // Start the groceryList
                startActivity(intent);

            }

            private void receiveDetections() {
            }

            private void stop() {
            }
        });
    }
    //TODO handle this
    public static ArrayList<String> getList(){

        return new ArrayList<>(
                Arrays.asList("bread","rye bread","mustard","relish","deli corned beef","sauerkraut",
                        "berries","whole,cloves","coriander seeds","extra-virgin olive oil",
                        "large white onion","clove of garlic","tomato paste","ground beef","chili powder",
                        "ground cumin","dried oregano","paprika","cayenne pepper","Kosher salt",
                        "Freshly ground black pepper","yellow onion","white onion","paprika",
                        "pickling spice","Crescent Rolls","cabbage leaves","Italian bread crumbs",
                        "red wine","corned beef","low-sodium beef broth","dried thyme","Swiss cheese",
                        "ketchup","large carrots","stalks celery","baby potatoes","corned beef brisket",
                        "kosher salt","bay leaves","thyme","sprigs thyme","russet potatoes","Kosher salt",
                        "butter","milk","sour cream","beef chuck stew meat","extra-virgin olive oil",
                        "large onion","provolone","rolls","boneless ribeye steaks","carrots",
                        "garlic powder","fresh thyme","ground beef","frozen peas","all-purpose flour",
                        "extra-virgin olive oil","yellow onion","jalapeño","cloves garlic","oregano",
                        "Cooking spray","grits","ground cumin","green lentils",
                        "boneless skinless chicken breasts","low-sodium chicken broth","Kosher salt",
                        "Freshly ground black pepper","can of white beans","sour cream","butter","onion",
                        "red bell pepper","shrimp","garlic cloves","Cajun seasoning","lemon juice",
                        "Worstershire sauce","Kosher salt","cream cheese","sour cream",
                        "shredded mozzarella","shredded Parmesan","butter","extra-virgin olive oil",
                        "shrimp","lemon","cloves garlic","crushed red pepper flakes","Kosher salt",
                        "Kosher salt","cooked shrimp","limes","oranges","red onion","jalapeños",
                        "cucumber","cherry tomatoes","cilantro","mayonnaise","butter","all-purpose flour",
                        "yellow onion","green bell pepper","celery ribs","cloves garlic","andouille sausage",
                        "cajun seasoning","kosher salt","Freshly ground black pepper","bay leaf",
                        "fire-roasted diced tomatoes","chicken broth","extra-virgin olive oil",
                        "bell peppers","onion","Kosher salt","black pepper",
                        "boneless skinless chicken breasts","chili powder","ground cumin","dried oregano",
                        "flour tortillas","shredded Monterey jack","shredded cheddar","avocado","fettucine",
                        "butter divided","shrimp","kosher salt","ground black pepper","clove garlic",
                        "all-purpose flour","heavy cream","whole milk","egg","cabbage","rice noodles",
                        "lime juice","brown sugar","fish sauce","low-sodium soy sauce","cayenne pepper",
                        "vegetable oil","bell pepper","garlic cloves","eggs","shrimp","black pepper",
                        "extra-virgin olive oil","onion","bell peppers","Kosher salt",
                        "ground black pepper","boneless chicken breasts","oregano","sausage",
                        "cloves garlic","tomatoes","low-sodium chicken stock","crushed tomatoes",
                        "rice","Old Bay seasoning","vegetable oil","garlic","carrots","green bell pepper",
                        "white rice","peas","soy sauce","sesame oil","egg","sriracha","panko bread crumbs",
                        "extra-virgin olive oil","garlic powder","Kosher salt","ground black pepper",
                        "large eggs","flour","raw shrimp","Fresh cilantro","mayonnaise","olive oil",
                        "onion","cloves garlic","bell pepper","shrimp","Cajun seasoning","kosher salt",
                        "Freshly ground black pepper","extra-virgin olive oil","large onion","ground beef",
                        "garlic cloves","kosher salt","refried beans","water","tortilla chips",
                        "shredded cheddar","Shredded Monterey jack","jalapeños","black beans","avocado"));

    }

    /**
     * Handles the requesting of the camera permission.  This includes
     * showing a "Snackbar" message of why the permission is needed then
     * sending the request.
     */
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

    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the ocr detector to detect small text samples
     * at long distances.
     * <p>
     * Suppressing InlinedApi since there is a check that the minimum version is met before using
     * the constant.
     */
    @SuppressLint("InlinedApi")
    private void createCameraSource(boolean autoFocus, boolean useFlash) {
        Context context = getApplicationContext();

            /* A text recognizer is created to find text.  An associated multi-processor instance
             is set to receive the text recognition results, track the text, and maintain
             graphics for each text block on screen.  The factory is used by the multi-processor to
             create a separate tracker instance for each text block.*/
        //Create the TextRecognizer
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
        //Set the TextRecognizer's Processor.
        textRecognizer.setProcessor(new DetectorProcessor(graphicOverlay));

        //Check if the TextRecognizer is operational.
        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies are not yet available.");

            //Check for low storage.  If there is low storage, the native library will not be
            //downloaded, so detection will not become operational.
            IntentFilter lowStorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = registerReceiver(null, lowStorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(this, R.string.low_storage_error,
                        Toast.LENGTH_LONG).show();
                Log.w(TAG, getString(R.string.low_storage_error));
            }
        }

        //Create the mCameraSource using the TextRecognizer.
        cameraSource = new Builder(getApplicationContext(),
                textRecognizer).setFacing(CAMERA_FACING_BACK)
                //High resolution is set to get smaller text. Can adjust if needed
                .setRequestedPreviewSize(1280, 1024).setRequestedFps(2.0f)
                .setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
                .setFocusMode(autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO : null)
                .build();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (preview != null) {
            preview.stop();
        }
    }

    /**
     * Releases the resources associated with the camera source, the associated detectors, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (preview != null) {
            preview.release();
        }
    }

    /**
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
     */
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
            boolean autoFocus = getIntent().getBooleanExtra(AutoFocus, true);
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
                .setPositiveButton(R.string.ok, listener).show();
    }

    /**
     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
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

    /**
     * onTap is called to send text to grocery list.
     *
     * @param rawX - the raw position of the tap
     * @param rawY - the raw position of the tap.
     * @return true if the tap was on a TextBlock
     */
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

    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
        }
    }

    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        /**
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
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return false;
        }

        /**
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
         */
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        /**
         * Responds to the end of a scale gesture. Reported by existing
         * pointers going up.
         * <p/>
         * Once a scale has ended, {@link ScaleGestureDetector#getFocusX()}
         * and {@link ScaleGestureDetector#getFocusY()} will return focal point
         * of the pointers remaining on the screen.
         *
         * @param detector The detector reporting the event - use this to
         *                 retrieve extended info about event state.
         */
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            if (cameraSource != null) {
                cameraSource.doZoom(detector.getScaleFactor());
            }
        }
    }
}
