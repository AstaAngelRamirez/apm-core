package com.apm.example_core;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.apm.core.utils.IntentUtils;
import com.apm.core.utils.ShareUtils;
import com.apm.core.utils.UriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    //region CONSTANTS
    private final static int PICK_FILE_RESULT = 100;
    private final static int PERMISSION_REQUEST = 200;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_barcode_generator)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, BarcodeGeneratorActivity.class));
                    }
                });

        findViewById(R.id.button_video_player)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, VideoPlayerActivity.class));
                    }
                });

        findViewById(R.id.button_share)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermissions(PERMISSION_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE);
                        //ShareUtils.ShareFile(MainActivity.this, "Share with", new File("/data/user/0/com.apm.example_core/cache/20181102_105535.jpg")); // file location example
                    }
                });

        findViewById(R.id.button_share_asset)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            File file = ShareUtils.ShareImageAsset(MainActivity.this, "Share with", "android.png");
                            Log.i(MainActivity.class.getSimpleName(), "File to share: " + file.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.button_share_bitmap)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            InputStream stream = getAssets().open("android.png");
                            byte[] fileBytes = new byte[stream.available()];
                            stream.read(fileBytes);
                            stream.close();

                            File file = ShareUtils.ShareImageBitmap(MainActivity.this, "Share with",
                                    BitmapFactory.decodeByteArray(fileBytes, 0, fileBytes.length));
                            Log.i(MainActivity.class.getSimpleName(), "File to share: " + file.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_RESULT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            ShareUtils.ShareUri(MainActivity.this, "Share using", uri);
        }
    }

    public void requestPermissions(final int requestCode, String... permissions) {
        boolean grantPermissions = false;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                grantPermissions = true;
                break;
            }
        }

        if (grantPermissions) {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    requestCode);
        } else {
            startActivityForResult(IntentUtils.CreatePickFileIntent("Pick a file using", ""), PICK_FILE_RESULT);
        }
    }

    public boolean hasPermissions(String... permissions) {
        if (permissions.length > 0){
            boolean hasPermissionResponse = true;
            for (String permission : permissions){
                if (ActivityCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED){
                    hasPermissionResponse = false;
                    break;
                }
            }
            return hasPermissionResponse;
        }
        return false;
    }
}
