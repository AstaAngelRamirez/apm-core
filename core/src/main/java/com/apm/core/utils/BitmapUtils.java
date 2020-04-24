package com.apm.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ing. Oscar G. Medina Cruz on 19/06/17.
 * <p>
 * Handle bitmap actions and functions
 */

public class BitmapUtils {

    /**
     * Save an image byte array into temporary file
     *
     * @param context     current application context
     * @param bitmapBytes image byte array
     * @param imageName   name of the file to save
     * @return temporary file
     * @throws IOException if something was wrong with file creation
     */
    public static File SaveBitmapInTemporaryFile(Context context, byte[] bitmapBytes, String imageName) throws IOException {
        File tempFile = File.createTempFile(imageName.replace(" ", "_"), ".jpg", context.getExternalCacheDir());
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(bitmapBytes);
        fos.flush();
        fos.close();
        return tempFile;
    }

    /**
     * Save an image byte array into temporary file
     *
     * @param bitmapBytes image byte array
     * @param imageName   name of the file to save
     * @return new file created
     * @throws IOException if something was wrong with file creation
     */
    public static File SaveImageBytesInFolder(byte[] bitmapBytes, File storePath, String imageName) throws IOException {
        File outFile = new File(storePath, imageName);
        outFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(bitmapBytes);
        fos.flush();
        fos.close();
        return outFile;
    }

    /**
     * Get pixel size of image
     *
     * @param imageFile source image file
     * @return Point object that contains with and height in x and y properties
     */
    public static Point GetImageSize(File imageFile) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        int width = options.outWidth;
        int height = options.outHeight;

        String type = options.outMimeType;
        return new Point(width, height);
    }

    /**
     * Get pixel size of image
     *
     * @param imageBytes    Source image file in byte array
     * @return              Point object that contains with and height in x and y properties
     */
    public static Point GetImageSize(byte[] imageBytes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
        int width = options.outWidth;
        int height = options.outHeight;

        String type = options.outMimeType;
        return new Point(width, height);
    }

    /**
     * Get pixel size of image
     * @param bitmap    Source image in bitmap
     * @return          Point object that contains with and height in x and y properties
     */
    public static Point GetImageSize(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        return new Point(width, height);
    }

    /**
     * Get scaled bitmap from file source
     * @param imageFile     Image file to be scaled
     * @param targetW       Image target width
     * @param targetH       Image target height
     * @return              Bitmap of scaled image
     */
    public static Bitmap GetScaledBitmapFromFile(File imageFile, int targetW, int targetH) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bmOptions);
    }

    /**
     * Get scaled image file from file source
     * @param imageFile         Source image file
     * @param targetW           Image target width
     * @param targetH           Image target height
     * @param compressFormat    Image compress format
     * @param imageQuality      Image compress quality
     * @throws IOException
     */
    public static void GetScaledImageFileFromFile(File imageFile, int targetW, int targetH, Bitmap.CompressFormat compressFormat, int imageQuality) throws IOException {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bitmapOptions);
        int photoW = bitmapOptions.outWidth;
        int photoH = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        Bitmap finalBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bitmapOptions);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        finalBitmap.compress(compressFormat, imageQuality, stream);
        byte[] imageBytes = stream.toByteArray();

        String[] path = FileUtils.GetFilePathNameExt(imageFile);

        SaveImageBytesInFolder(imageBytes, new File(path[0]), imageFile.getName());
    }

    /**
     * Get scaled image file from file source
     * @param bitmap            Bitmap source to be scaled
     * @param targetW           Image target width
     * @param targetH           Image target height
     * @param compressFormat    Image compress format
     * @param imageQuality      Image compress quality
     * @return                  Bitmap of scaled image
     */
    public static Bitmap GetScaledBitmapFromBitmap(Bitmap bitmap, int targetW, int targetH, Bitmap.CompressFormat compressFormat, int imageQuality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, imageQuality, stream);
        byte[] imageBytes = stream.toByteArray();

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions);

        int photoW = bitmapOptions.outWidth;
        int photoH = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions);
    }

    /**
     * Get scaled image file from bitmap source
     * @param bitmap            Bitmap source to be scaled
     * @param destFile          Image file destination for scaled bitmap
     * @param targetW           Image target width
     * @param targetH           Image target height
     * @param compressFormat    Image compress format
     * @param imageQuality      Image compress quality
     * @return                  File of scaled bitmap
     * @throws IOException
     */
    public static File GetScaledImageFileFromBitmap(Bitmap bitmap, File destFile, int targetW, int targetH, Bitmap.CompressFormat compressFormat, int imageQuality) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, imageQuality, stream);
        byte[] imageBytes = stream.toByteArray();

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions);

        int photoW = bitmapOptions.outWidth;
        int photoH = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        String[] path = FileUtils.GetFilePathNameExt(destFile);

        return SaveImageBytesInFolder(imageBytes, new File(path[0]), destFile.getName());
    }

    /**
     * Get scaled image byte array from bitmap source
     * @param bitmap            Bitmap source to be scaled
     * @param targetW           Image target width
     * @param targetH           Image target height
     * @param compressFormat    Image compress format
     * @param imageQuality      Image compress quality
     * @return                  Byte array of scaled image
     */
    public static byte[] GetScaledImageBytesFromBitmap(Bitmap bitmap, int targetW, int targetH, Bitmap.CompressFormat compressFormat, int imageQuality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, imageQuality, stream);
        byte[] imageBytes = stream.toByteArray();

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions);

        int photoW = bitmapOptions.outWidth;
        int photoH = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        stream = new ByteArrayOutputStream();
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions)
                .compress(compressFormat, imageQuality, stream);

        return stream.toByteArray();
    }

    /**
     * Get scaled image byte array from bitmap source
     * @param imageBytes        Image source bytes to be scaled
     * @param targetW           Image target width
     * @param targetH           Image target height
     * @param compressFormat    Image compress format
     * @param imageQuality      Image compress quality
     * @return                  Byte array of scaled image
     */
    public static byte[] GetScaledImageBytesFromBytes(byte[] imageBytes, int targetW, int targetH, Bitmap.CompressFormat compressFormat, int imageQuality) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions);

        int photoW = bitmapOptions.outWidth;
        int photoH = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, bitmapOptions)
                .compress(compressFormat, imageQuality, stream);

        return stream.toByteArray();
    }

    /**
     * Combine two bitmap (in byte array format) in same byte array
     *
     * @param baseImage    base byte array bitmap
     * @param overlayImage overlay byte array bitmap
     * @param config       output configuration
     * @param compressFormat    compress format for final image
     * @return combined byte array
     * @throws IOException if the file cannot be created
     */
    public static byte[] CombineBitmapArraysInSameBitmap(byte[] baseImage, byte[] overlayImage, Bitmap.Config config, Bitmap.CompressFormat compressFormat) throws IOException {
        Point frameBitmapSize = GetImageSize(baseImage);
        Bitmap resultBitmap = Bitmap.createBitmap(frameBitmapSize.x, frameBitmapSize.y, config);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, new Matrix(), (Paint)null);
        canvas.drawBitmap(BitmapFactory.decodeByteArray(baseImage, 0, baseImage.length), 0.0F, 0.0F, (Paint)null);
        byte[] scaledOverlay = GetScaledImageBytesFromBytes(overlayImage, frameBitmapSize.x, frameBitmapSize.y, Bitmap.CompressFormat.PNG, 100);
        canvas.drawBitmap(BitmapFactory.decodeByteArray(scaledOverlay, 0, scaledOverlay.length), 0.0F, 0.0F, (Paint)null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        resultBitmap.compress(compressFormat, 100, byteArrayOutputStream);
        byte[] finalBitmap = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        resultBitmap.recycle();
        return finalBitmap;
    }

    /**
     * Rotate a bitmap in desired angle
     *
     * @param source Bitmap to be rotated
     * @param angle  A destination angle
     * @return Rotated bitmap
     */
    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    /**
     * Resize a bitmap in desired size
     *
     * @param bm        Source bitmap
     * @param newHeight New height of bitmap
     * @param newWidth  New width of bitmap
     * @return A resized bitmap
     */
    public static Bitmap ResizeBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }

    /**
     * Get rotated image from image byte array
     * @param imageRotation     Final rotation degrees
     * @param compressFormat    Final compress format
     * @param fileData          Byte array of image file
     * @return                  Byte array of rotated image
     */
    public static byte[] RotateImageFromBytes(int imageRotation, Bitmap.CompressFormat compressFormat, byte[] fileData) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapUtils.RotateBitmap(BitmapFactory.decodeByteArray(fileData, 0, fileData.length), imageRotation);
        bitmap.compress(compressFormat, 100, stream);
        bitmap.recycle();
        return stream.toByteArray();
    }
}
