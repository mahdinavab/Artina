package com.radmanpooya.artina.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.radmanpooya.artina.R;

import org.the3deer.util.android.AndroidURLStreamHandlerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MiddleActivity extends AppCompatActivity {

    /*private static final int REQUEST_CODE_LOAD_MODEL = 1101;
    private static final int REQUEST_CODE_OPEN_MATERIAL = 1102;
    private static final int REQUEST_CODE_OPEN_TEXTURE = 1103;
    private static final int REQUEST_CODE_ADD_FILES = 1200;
    private static final String SUPPORTED_FILE_TYPES_REGEX = "(?i).*\\.(obj|stl|dae|gltf|index)";


    *//**
     * Load file user data
     */
    private Map<String, Object> loadModelParameters = new HashMap<>();

    static {
        System.setProperty("java.protocol.handler.pkgs", "org.the3deer.util.android");
        URL.setURLStreamHandlerFactory(new AndroidURLStreamHandlerFactory());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);

        String file = "https://fast-programmer.ir/models/engine.obj";
        Log.i("DDDIIIGGG","file : "+file);
        if (file != null) {
            if (file.endsWith(".index")) {
            } else {
                launchModelRendererActivity(Uri.parse(file));
            }
        }


    }

    private void launchModelRendererActivity(Uri uri) {
        Log.i("Menu", "Launching renderer for '" + uri + "'");
        Intent intent = new Intent(getApplicationContext(), MyModelActivity.class);
        try {
            URI.create(uri.toString());
            intent.putExtra("uri", uri.toString());
        } catch (Exception e) {
            // info: filesystem url may contain spaces, therefore we re-encode URI
            try {
                intent.putExtra("uri", new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), uri.getQuery(), uri.getFragment()).toString());
            } catch (URISyntaxException ex) {
                Toast.makeText(this, "Error: " + uri.toString(), Toast.LENGTH_LONG).show();
                return;
            }
        }
        intent.putExtra("immersiveMode", "false");

        // content provider case
        if (!loadModelParameters.isEmpty()) {
            intent.putExtra("type", loadModelParameters.get("type").toString());
            //intent.putExtra("backgroundColor", "0.25 0.25 0.25 1");
            loadModelParameters.clear();
        }

        startActivity(intent);
        finish();
    }

}