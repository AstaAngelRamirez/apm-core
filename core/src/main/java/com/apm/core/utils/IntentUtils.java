package com.apm.core.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.apm.core.enums.MediaFileType;

import java.io.File;
import java.util.List;
import java.util.Stack;

/**
 * Created by Ing. Oscar G. Medina Cruz on 20/06/17.
 * <p>
 * Handle intent actions and functions
 */

public class IntentUtils {

    /**
     * Create an intent to get app list that handles email
     *
     * @param activity         Application context
     * @param source           Source intent
     * @param destinationEmail Email address of destination
     * @param chooserTitle     Chooser dialog title
     * @return Email intent chooser
     */
    public static Intent CreateEmailOnlyChooserIntent(Activity activity, Intent source,
                                                      String destinationEmail, CharSequence chooserTitle) {
        Stack<Intent> intents = new Stack<>();
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                destinationEmail, null));
        List<ResolveInfo> activities = activity.getPackageManager()
                .queryIntentActivities(i, 0);

        for (ResolveInfo ri : activities) {
            Intent target = new Intent(source);
            target.setPackage(ri.activityInfo.packageName);
            intents.add(target);
        }

        if (!intents.isEmpty()) {
            Intent chooserIntent = Intent.createChooser(intents.remove(0),
                    chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intents.toArray(new Parcelable[intents.size()]));

            return chooserIntent;
        } else {
            return Intent.createChooser(source, chooserTitle);
        }
    }

    /**
     * Create an intent to pick a file from device
     *
     * @param chooserTitle Chooser dialog title
     * @param mimeType     File mime type
     * @return An intent to pick a file
     */
    public static Intent CreatePickFileIntent(String chooserTitle, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType.equals("") ? "*/*" : mimeType);
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Create an intent to pick an image from device or from camera
     *
     * @param activity      Current activity
     * @param chooserTitle  Dialog title to show
     * @param includeCamera If native camera will be included in this intent
     * @param cameraImage   Image file to store camera picture
     * @return An intent to pick an image
     */
    public static Intent CreatePickImageIntent(Activity activity, String chooserTitle, boolean includeCamera, File cameraImage) {
        Intent takePictureCameraIntent = null;
        Intent takePictureGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        takePictureGalleryIntent.setType("image/*");

        if (includeCamera) {
            takePictureCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                takePictureCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(activity,
                                activity.getPackageName() + ".provider", cameraImage));
            } else {
                takePictureCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraImage));
            }
        }

        Intent chooserIntent = Intent.createChooser(takePictureGalleryIntent, chooserTitle);
        if (includeCamera) {
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePictureCameraIntent});
        }

        return chooserIntent;
    }
}
