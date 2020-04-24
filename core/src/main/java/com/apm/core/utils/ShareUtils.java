package com.apm.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ing. Oscar G. Medina Cruz on 27/03/18.
 */

public class ShareUtils {

    /**
     * Share image in native Android intent
     *
     * @param activity    Current activity
     * @param dialogTitle Native share dialog title
     * @param base64Image Image to share in base 64 String
     * @return Path of the saved image into gallery
     */
    public static File ShareImage(Activity activity, String dialogTitle, String base64Image) throws IOException {
        byte[] imageInBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return ShareImage(activity, dialogTitle, imageInBytes);
    }

    /**
     * Share image in native Android intent
     *
     * @param activity     Current activity
     * @param dialogTitle  Native share dialog title
     * @param imageInBytes Image to share in byte array
     * @return Saved image file
     */
    public static File ShareImage(Activity activity, String dialogTitle, byte[] imageInBytes) throws IOException {
        File file = FileUtils.SaveImageBytes(imageInBytes, activity.getCacheDir().getAbsolutePath(),
                StringUtils.GetRandomFileName(), Bitmap.CompressFormat.JPEG);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, UriUtils.GetFileUri(activity, file));
        activity.startActivity(Intent.createChooser(shareIntent, dialogTitle));

        return file;
    }

    /**
     * Share image in native Android intent
     *
     * @param activity    Current activity
     * @param dialogTitle Native share dialog title
     * @param imageBitmap Image to share in bitmap object
     * @return Saved image file
     */
    public static File ShareImageBitmap(Activity activity, String dialogTitle, Bitmap imageBitmap) throws IOException {
        File file = FileUtils.SaveImageBitmap(imageBitmap, activity.getCacheDir().getAbsolutePath(),
                StringUtils.GetRandomFileName(), Bitmap.CompressFormat.JPEG, 100);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, UriUtils.GetFileUri(activity, file));
        activity.startActivity(Intent.createChooser(shareIntent, dialogTitle));

        return file;
    }

    /**
     * Share image in native Android intent
     *
     * @param activity    Current activity
     * @param dialogTitle Native share dialog title
     * @param imageFile   Image to share in file
     */
    public static void ShareImageFile(Activity activity, String dialogTitle, File imageFile) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, UriUtils.GetFileUri(activity, imageFile));
        activity.startActivity(Intent.createChooser(shareIntent, dialogTitle));
    }

    /**
     *
     * @param activity Current activity
     * @param dialogTitle Native share dialog title
     * @param assetImage Image to share in asset path format
     * @return Saved image file
     * @throws IOException
     */
    public static File ShareImageAsset(Activity activity, String dialogTitle, String assetImage) throws IOException {
        InputStream stream = activity.getAssets().open(assetImage);
        byte[] fileBytes = new byte[stream.available()];
        stream.read(fileBytes);
        stream.close();

        return ShareImage(activity, dialogTitle, fileBytes);
    }

    /**
     * Share image in native Android intent
     *
     * @param activity     Current activity
     * @param dialogTitle  Native share dialog title
     * @param imageToShare Image to share in file object
     * @throws FileNotFoundException
     */
    public static void ShareImageDirect(Activity activity, String dialogTitle, File imageToShare) throws FileNotFoundException {
        if (FileUtils.GetMimeTypeFromFile(imageToShare).contains("image")) {
            final Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, UriUtils.GetFileUri(activity, imageToShare));
            activity.startActivity(Intent.createChooser(shareIntent, dialogTitle));
        } else {
            Log.i("ShareImageDirect", "Can't share file that isn't an image with this method. Use ShareMedia instead.");
        }
    }

    /**
     * Share image in native Android intent
     *
     * @param activity    Current activity
     * @param dialogTitle Native share dialog title
     * @param fileToShare File to share
     */
    public static void ShareFile(Activity activity, String dialogTitle, File fileToShare) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType(FileUtils.GetMimeTypeFromFile(fileToShare));
        shareIntent.putExtra(Intent.EXTRA_STREAM, UriUtils.GetFileUri(activity, fileToShare));
        activity.startActivity(Intent.createChooser(shareIntent, dialogTitle));
    }

    /**
     * Share image in native Android intent
     *
     * @param context     Application context
     * @param dialogTitle Native share dialog title
     * @param uriToShare  Uri to share
     */
    public static void ShareUri(Context context, String dialogTitle, Uri uriToShare) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType(FileUtils.GetMimeTypeFromUri(context, uriToShare));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToShare);
        context.startActivity(Intent.createChooser(shareIntent, dialogTitle));
    }
}
