package com.apm.core.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import com.apm.core.enums.FileSizeType;
import com.apm.core.enums.MediaFileType;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Ing. Oscar G. Medina Cruz on 22/06/17.
 * <p>
 * Handle file actions and functions
 */

public class FileUtils {

    // CONSTANTS
    private static final double SPACE_KB = 1024;
    private static final double SPACE_MB = 1024 * SPACE_KB;
    private static final double SPACE_GB = 1024 * SPACE_MB;
    private static final double SPACE_TB = 1024 * SPACE_GB;

    /**
     * Gets MimeType of file
     *
     * @param file source file
     * @return MimeType in string
     */
    public static String GetMimeTypeFromFile(File file) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath());
        if (extension != null && !extension.equals("")) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        } else if (extension != null) {
            extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    /**
     * Gets MimeType of Uri
     *
     * @param uri source Uri
     * @return MimeType in string
     */
    public static String GetMimeTypeFromUri(Context context, Uri uri) {
        return context.getContentResolver().getType(uri);
    }

    /**
     * Save byte array into a temporary image file on device's picture directory
     *
     * @param bitmapBytes File byte array
     * @return Image file
     * @throws IOException If something was wrong with file creation
     */
    public static File SaveImageBytes(byte[] bitmapBytes) throws IOException {
        File file = CreateMediaFile(MediaFileType.IMAGE, "");
        FileOutputStream fos = new FileOutputStream(Objects.requireNonNull(file));
        fos.write(bitmapBytes);
        fos.flush();
        fos.close();
        return file;
    }

    /**
     * Save byte array in selected path
     *
     * @param bitmapBytes Image in byte array
     * @param path        Image path
     * @param name        Image name with extension
     * @return Image file
     * @throws IOException If something was wrong with file creation
     */
    public static File SaveImageBytes(byte[] bitmapBytes, String path, String name) throws IOException {
        File file = new File(new File(path), name);

        if (!file.exists()) {
            new File(path).mkdirs();
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapBytes);
        fos.flush();
        fos.close();
        return file;
    }

    /**
     * Save image from byte array to file
     *
     * @param bitmapBytes    Image in byte array
     * @param path           Image path
     * @param name           Image name with extension
     * @param compressFormat Desired compress format
     * @return Image file
     * @throws IOException If something was wrong with file creation
     */
    public static File SaveImageBytes(byte[] bitmapBytes, String path, String name, Bitmap.CompressFormat compressFormat) throws IOException {
        File file = new File(path + File.separator + name + "." +
                (compressFormat == Bitmap.CompressFormat.JPEG ? "jpg" :
                        (compressFormat == Bitmap.CompressFormat.PNG ? "png" : "jpg")));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapBytes);
        fos.close();
        return file;
    }

    /**
     * Save image from bitmap to file
     *
     * @param sourceBitmap   Image in bitmap
     * @param path           Image path
     * @param name           Image name without extension
     * @param compressFormat {@link Bitmap.CompressFormat compress format}
     * @param outputQuality  0 - 100 image percent quality
     * @return Image file
     * @throws IOException If something was wrong with file creation
     */
    public static File SaveImageBitmap(Bitmap sourceBitmap, String path, String name, Bitmap.CompressFormat compressFormat, int outputQuality) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        sourceBitmap.compress(compressFormat, outputQuality, stream);

        byte[] sourceBytes =
                RotateImageFromBytes(stream.toByteArray(), 90, compressFormat, outputQuality);

        File file = new File(path + File.separator + name + "." +
                (compressFormat == Bitmap.CompressFormat.JPEG ? "jpg" :
                        (compressFormat == Bitmap.CompressFormat.PNG ? "png" : "jpg")));

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(sourceBytes);
        fos.close();
        return file;
    }

    /**
     * Read text file and return it's content
     *
     * @param filename File name (with full path)
     * @return File content in String
     * @throws IOException If something was wrong with file read process
     */
    public static String ReadTextFile(String filename) throws IOException {
        File file = new File(filename);
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append('\n');
        }
        br.close();
        return text.toString();
    }

    /**
     * Read text file and return it's content
     *
     * @param file File to be read
     * @return File content in string
     * @throws IOException If something was wrong with file read process
     */
    public static String ReadTextFile(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append('\n');
        }
        br.close();
        return text.toString();
    }

    /**
     * Copy file
     *
     * @param srcFile Source file
     * @param dstFile Destination file
     * @throws IOException If something was wrong with file copy process
     */
    public static void CopyFile(File srcFile, File dstFile) throws IOException {
        InputStream in = new FileInputStream(srcFile);
        OutputStream out = new FileOutputStream(dstFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * Copy file from input to output stream
     *
     * @param sourceInput Source that contains file to copy
     * @param destOutput  Output to save then contains
     * @throws IOException If something was wrong with file copy process
     */
    private static void CopyFile(InputStream sourceInput, OutputStream destOutput) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = sourceInput.read(buffer)) != -1) {
            destOutput.write(buffer, 0, read);
        }
    }

    /**
     * Copy asset file to internal storage
     *
     * @param assetManager    Asset manager
     * @param sourceAssetName Source asset name
     * @param destPath        Destination path
     * @param destFileName    File name of the new file
     * @throws IOException If something was wrong with file copy process
     */
    public static void CopyAsset(AssetManager assetManager, String sourceAssetName, File destPath, String destFileName) throws IOException {
        File destFile = new File(destPath, destFileName);

        if (destFile.exists())
            return;

        if (!destPath.exists())
            destPath.mkdirs();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(sourceAssetName);
            out = new FileOutputStream(destFile);
            CopyFile(in, out);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Move file from source to destination path
     *
     * @param file File to be moved
     * @param dir  Destination directory
     * @throws IOException If something was wrong with the file move
     */
    public static File MoveFile(File file, File dir) throws IOException {
        File newFile = new File(dir, file.getName());
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            outputChannel = new FileOutputStream(newFile).getChannel();
            inputChannel = new FileInputStream(file).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
            file.delete();
        } finally {
            if (inputChannel != null) inputChannel.close();
            if (outputChannel != null) outputChannel.close();
        }
        return newFile;
    }

    /**
     * Get file as byte array
     *
     * @param sourceFile File to be readed as byte array
     * @return Byte array with file content
     */
    public static byte[] GetFileAsByteArray(File sourceFile) {
        int size = (int) sourceFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(sourceFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * Get a path as file path from Uri object
     *
     * @param context Application context
     * @param uri     Uri of file to be returned as file path
     * @return File path
     */
    public static String GetPathFromUriFile(Context context, Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);

        String filePath = null;

        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }

        return filePath;
    }

    /**
     * Get an array of length 3, containing a separated strings with path, name and extension of a file without "."
     *
     * @param file Requested file
     * @return Array with requested info
     */
    public static String[] GetFilePathNameExt(File file) {
        String filePath = file.getAbsolutePath().
                substring(0, file.getAbsolutePath().lastIndexOf(File.separator) + 1);
        String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separator) + 1);
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        fileName = fileName.replace(fileExt, "");
        fileExt = fileExt.substring(1);

        return new String[]{filePath, fileName, fileExt};
    }

    /**
     * Get image in byte array format from pixel buffer
     *
     * @param pixelsBuffer   Byte array with image in pixels
     * @param imageSize      Desired image size
     * @param destWidth      Desired image width
     * @param destHeight     Desired image height
     * @param bitmapConfig   Desired bitmap configuration
     * @param compressFormat Desired image compress format
     * @param imageQuality   Desired image quality
     * @return A byte array with image
     */
    public static byte[] GetImageFromPixels(int[] pixelsBuffer, int imageSize, int destWidth, int destHeight, Bitmap.Config bitmapConfig, Bitmap.CompressFormat compressFormat, int imageQuality) {
        Bitmap bitmap = Bitmap.createBitmap(destWidth, destHeight, bitmapConfig);
        bitmap.setPixels(pixelsBuffer, imageSize - destWidth, -destWidth, 0, 0, destWidth, destHeight);

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, imageQuality, blob);

        bitmap.recycle();

        return blob.toByteArray();
    }

    /**
     * Get rotated image from source image in byte array
     *
     * @param fileData       Desired image in byte array
     * @param compressFormat Desired image compress format
     * @param imageQuality   Desired image quality
     * @param imageRotation  Desired image rotation
     * @return A rotated image in byte array format
     */
    public static byte[] RotateImageFromBytes(byte[] fileData, int imageRotation, Bitmap.CompressFormat compressFormat, int imageQuality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapUtils.RotateBitmap(BitmapFactory.decodeByteArray(fileData, 0, fileData.length), imageRotation);
        bitmap.compress(compressFormat, imageQuality, stream);
        bitmap.recycle();
        return stream.toByteArray();
    }

    /**
     * Save a temp file from input stream source
     *
     * @param context     Application context
     * @param fileName    File name
     * @param inputStream Source input stream
     * @return New temporary file created
     * @throws IOException If something was wrong with the file creation
     */
    public static File SaveTempFileFromStream(Context context, String fileName, InputStream inputStream) throws IOException {
        File file = null;
        file = new File(context.getCacheDir(), fileName);

        if (!file.exists()) {
            OutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
            output.flush();
            output.close();
        }

        return file;
    }

    /**
     * Write JSON file from String content
     *
     * @param filePath    File path of the new JSON file
     * @param jsonName    Name of the new JSON file
     * @param jsonContent String with JSON content
     * @throws IOException If something was wrong with the file creation
     */
    public static void WriteJSONFile(File filePath, String jsonName, String jsonContent) throws IOException {
        if (!filePath.exists())
            filePath.mkdirs();
        File file = new File(filePath, jsonName + ".json");
        if (file.exists())
            file.delete();
        FileWriter writer = new FileWriter(file, true);
        writer.append(jsonContent);
        writer.flush();
        writer.close();
    }

    /**
     * Get file size in desired {@link FileSizeType}
     *
     * @param file         File to get size
     * @param fileSizeType {@link FileSizeType}
     * @return File size as String
     */
    public static String GetFileSize(File file, FileSizeType fileSizeType) {
        double fileSize;

        if (!file.isFile()) {
            throw new IllegalArgumentException("Expected argument to be a file");
        } else {
            fileSize = (double) file.length();//in Bytes

            switch (fileSizeType) {
                case B:
                    return String.valueOf(fileSize);
                case KB:
                    return String.valueOf(fileSize / SPACE_KB);
                case MB:
                    return String.valueOf(fileSize / SPACE_MB);
                case GB:
                    return String.valueOf(fileSize / SPACE_GB);
                case TB:
                    return String.valueOf(fileSize / SPACE_TB);
                default:
                    return "Unknown";
            }
        }
    }

    /**
     * Create a media file into media directories
     *
     * @param mediaFileType  Media file type of the requested file
     * @param childDirectory If the user wants to create a subdirectory in parent path
     * @return New created file
     */
    public static File CreateMediaFile(MediaFileType mediaFileType, String childDirectory) {
        File mediaDir = null;
        String mediaPrefix = "";
        String mediaPostfix = "";

        switch (mediaFileType) {
            case AUDIO:
                mediaDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_MUSIC);
                mediaPrefix = "AU_";
                mediaPostfix = ".mp3";
                break;
            case VIDEO:
                mediaDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_MOVIES);
                mediaPrefix = "VID_";
                mediaPostfix = ".mp4";
                break;
            case IMAGE:
                mediaDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                mediaPrefix = "IMG_";
                mediaPostfix = ".jpg";
                break;
        }

        File mediaStorageDir = null;

        if (childDirectory == null || childDirectory.equals("")) {
            mediaStorageDir = new File(mediaDir.getAbsolutePath());
        } else {
            mediaStorageDir = new File(mediaDir, childDirectory);
        }

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.US).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                mediaPrefix + timeStamp + mediaPostfix);
    }
}
