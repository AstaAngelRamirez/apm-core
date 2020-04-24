package com.apm.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ing. Oscar G. Medina Cruz on 28/08/17.
 */

public class AssetUtils {

    /**
     * Return typeface object with font stored in assets
     *
     * @param context       Application context
     * @param assetFontPath Asset font path
     * @param assetFontName Asset font name
     * @return Typeface object of selected asset font
     */
    public static Typeface GetTypeFace(Context context, String assetFontPath, String assetFontName) {
        return Typeface.createFromAsset(context.getAssets(), assetFontPath + assetFontName);
    }

    /**
     * Load custom typeface from stored font in assets
     *
     * @param context       Application context
     * @param textView      TextView that load the custom font
     * @param assetFontPath Asset font path
     * @param assetFontName Asset font name
     */
    public static void LoadTypeFace(Context context, TextView textView, String assetFontPath, String assetFontName) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), assetFontPath + assetFontName));
    }

    /**
     * Read text file content from assets in UTF-8
     *
     * @param context  Application context
     * @param fileName File name to read
     * @return String with file content
     * @throws IOException If file reading fails
     */
    public static String GetTextFromFile(Context context, String fileName) throws IOException {
        StringBuilder returnString = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                returnString.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return returnString.toString();
    }

    /**
     * Read text file content from assets in specific charset
     *
     * @param context     Application context
     * @param fileName    File name to read
     * @param charsetName Charset name to read file
     * @return String with file content
     * @throws IOException If file reading fails
     */
    public static String GetTextFromFile(Context context, String fileName, String charsetName) throws IOException {
        StringBuilder returnString = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName), charsetName));

            String line;
            while ((line = reader.readLine()) != null) {
                returnString.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return returnString.toString();
    }

    /**
     * Get an Asset file as {@link File} instance
     *
     * @param activity  Current activity
     * @param assetName Asset name (including subdirectories)
     * @return A temp file of asset
     * @throws IOException
     */
    public static File GetAssetAsFile(Activity activity, String assetName) throws IOException {
        return FileUtils.SaveTempFileFromStream(activity, assetName, activity.getAssets().open(assetName));
    }
}
