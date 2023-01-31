package com.radmanpooya.artina.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.dddmodel.ModelViewerGUI;
import com.radmanpooya.artina.dddmodel.MyModelLoaderTask;

import org.the3deer.android_3d_model_engine.camera.CameraController;
import org.the3deer.android_3d_model_engine.collision.CollisionController;
import org.the3deer.android_3d_model_engine.collision.CollisionEvent;
import org.the3deer.android_3d_model_engine.controller.TouchController;
import org.the3deer.android_3d_model_engine.controller.TouchEvent;
import org.the3deer.android_3d_model_engine.event.SelectedObjectEvent;
import org.the3deer.android_3d_model_engine.services.LoaderTask;
import org.the3deer.android_3d_model_engine.services.SceneLoader;
import org.the3deer.android_3d_model_engine.view.FPSEvent;
import org.the3deer.android_3d_model_engine.view.ModelSurfaceView;
import org.the3deer.android_3d_model_engine.view.ViewEvent;
import org.the3deer.util.android.ContentUtils;
import org.the3deer.util.event.EventListener;

import java.io.IOException;
import java.net.URI;
import java.util.EventObject;

public class MyModelActivity extends Activity implements EventListener {

    private static final int REQUEST_CODE_LOAD_TEXTURE = 1000;
    private static final int FULLSCREEN_DELAY = 10000;

    /**
     * Type of model if file name has no extension (provided though content provider)
     */
    private int paramType;
    /**
     * The file to load. Passed as input parameter
     */
    private URI paramUri;
    /**
     * Enter into Android Immersive mode so the renderer is full screen or not
     */
    private boolean immersiveMode;
    /**
     * Background GL clear color. Default is light gray
     */
    private float[] backgroundColor = new float[]{0.0f, 0.0f, 0.0f, 1.0f};

    private ModelSurfaceView glView;
    private TouchController touchController;
    private SceneLoader scene;
    private ModelViewerGUI gui;
    private CollisionController collisionController;
    private Handler handler;
    private CameraController cameraController;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_model);

        Bundle b = getIntent().getExtras();
        Log.i("DDDIIIGGG","getExtras : "+b.toString());
        if (b != null) {
            try {
                if (b.getString("uri") != null) {
                    this.paramUri = new URI(b.getString("uri"));
                    Log.i("ModelActivity", "Params: uri '" + paramUri + "'");
                }
                this.paramType = b.getString("type") != null ? Integer.parseInt(b.getString("type")) : -1;
                this.immersiveMode = "true".equalsIgnoreCase(b.getString("immersiveMode"));

                if (b.getString("backgroundColor") != null) {
                    String[] backgroundColors = b.getString("backgroundColor").split(" ");
                    backgroundColor[0] = Float.parseFloat(backgroundColors[0]);
                    backgroundColor[1] = Float.parseFloat(backgroundColors[1]);
                    backgroundColor[2] = Float.parseFloat(backgroundColors[2]);
                    backgroundColor[3] = Float.parseFloat(backgroundColors[3]);
                }
            } catch (Exception ex) {
                Log.e("ModelActivity", "Error parsing activity parameters: " + ex.getMessage(), ex);
            }

        }

        handler = new Handler(getMainLooper());

        // Create our 3D scenario
        Log.i("ModelActivity", "Loading Scene...");
        scene = new SceneLoader(this, paramUri, paramType);
        scene.addListener(this);
        if (paramUri == null) {
            final LoaderTask task = new MyModelLoaderTask(this, null, scene);
            task.execute();
        }

        try {
            Log.i("ModelActivity", "Loading GLSurfaceView...");
            glView = new ModelSurfaceView(this, backgroundColor, this.scene);
            glView.addListener(this);
            setContentView(glView);
        } catch (Exception e) {
            Log.e("ModelActivity", e.getMessage(), e);
            //Toast.makeText(this, "Error loading OpenGL view:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        try {
            Log.i("ModelActivity", "Loading TouchController...");
            touchController = new TouchController(this);
            touchController.addListener(this);
            //touchController.addListener(glView);
        } catch (Exception e) {
            Log.e("ModelActivity", e.getMessage(), e);
            //Toast.makeText(this, "Error loading TouchController:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        try {
            Log.i("ModelActivity", "Loading CollisionController...");
            collisionController = new CollisionController(glView, scene);
            collisionController.addListener(this);
            //touchController.addListener(collisionController);
            //touchController.addListener(scene);
        } catch (Exception e) {
            Log.e("ModelActivity", e.getMessage(), e);
            //Toast.makeText(this, "Error loading CollisionController\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        try {
            Log.i("ModelActivity", "Loading CameraController...");
            cameraController = new CameraController(scene.getCamera());
            //glView.getModelRenderer().addListener(cameraController);
            //touchController.addListener(cameraController);
        } catch (Exception e) {
            Log.e("ModelActivity", e.getMessage(), e);
            //Toast.makeText(this, "Error loading CameraController" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        try {
            // TODO: finish UI implementation
            Log.i("ModelActivity", "Loading GUI...");
            gui = new ModelViewerGUI(glView, scene);
            touchController.addListener(gui);
            glView.addListener(gui);
            scene.addGUIObject(gui);
        } catch (Exception e) {
            Log.e("ModelActivity", e.getMessage(), e);
            //Toast.makeText(this, "Error loading GUI" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Show the Up button in the action bar.

        //setupActionBar();

        /*setupOnSystemVisibilityChangeListener();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setupOrientationListener();
        }*/

        // load model
        scene.init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_LOAD_TEXTURE:
                // The URI of the selected file
                final Uri uri = data.getData();
                if (uri != null) {
                    Log.i("ModelActivity", "Loading texture '" + uri + "'");
                    try {
                        ContentUtils.setThreadActivity(this);
                        scene.loadTexture(null, uri);
                    } catch (IOException ex) {
                        Log.e("ModelActivity", "Error loading texture: " + ex.getMessage(), ex);
                        //Toast.makeText(this, "Error loading texture '" + uri + "'. " + ex
                        //        .getMessage(), Toast.LENGTH_LONG).show();
                    } finally {
                        ContentUtils.setThreadActivity(null);
                    }
                }
        }
    }

    @Override
    public boolean onEvent(EventObject event) {
        if (event instanceof FPSEvent){
            gui.onEvent(event);
        }
        else if (event instanceof SelectedObjectEvent){
            gui.onEvent(event);
        }
        else if (event.getSource() instanceof MotionEvent){
            // event coming from glview
            touchController.onMotionEvent((MotionEvent) event.getSource());
        }
        else if (event instanceof CollisionEvent){
            scene.onEvent(event);
        }
        else if (event instanceof TouchEvent){
            TouchEvent touchEvent = (TouchEvent) event;
            if (touchEvent.getAction() == TouchEvent.Action.CLICK){
                if (!collisionController.onEvent(event)){
                    scene.onEvent(event);
                }
            } else {
                if (scene.getSelectedObject() != null) {
                    scene.onEvent(event);
                } else {
                    cameraController.onEvent(event);
                    scene.onEvent(event);
                    if (((TouchEvent) event).getAction() == TouchEvent.Action.PINCH) {
                        glView.onEvent(event);
                    }
                }
            }
        }
        else if (event instanceof ViewEvent) {
            ViewEvent viewEvent = (ViewEvent) event;
            if (viewEvent.getCode() == ViewEvent.Code.SURFACE_CHANGED) {
                cameraController.onEvent(viewEvent);
                touchController.onEvent(viewEvent);

                // process event in GUI
                if (gui != null) {
                    gui.setSize(viewEvent.getWidth(), viewEvent.getHeight());
                    gui.setVisible(true);
                }
            } else if (viewEvent.getCode() == ViewEvent.Code.PROJECTION_CHANGED){
                cameraController.onEvent(event);
            }
        }
        return true;
    }

}