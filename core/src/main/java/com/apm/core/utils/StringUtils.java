package com.apm.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import com.apm.core.enums.CompareType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ing. Oscar G. Medina Cruz on 28/08/17.
 *
 * Handle string actions and functions
 */

public class StringUtils {

    /**
     * Decode String from {@linkplain java.nio.charset.Charset charset} of origin to an expected one
     *
     * @param undecodedString Undecoded String
     * @param inputCharset The name of a supported {@linkplain java.nio.charset.Charset charset} of input
     * @param outputCharset The name of a supported {@linkplain java.nio.charset.Charset charset} for output
     * @return Decoded String
     */
    public static String DecodeString(String undecodedString, String inputCharset, String outputCharset) {
        String decodedString = "";

        try {
            decodedString = new String(undecodedString.getBytes(inputCharset), outputCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decodedString;
    }

    /**
     * Generate random file name
     *
     * @return file name in format yyyyMMdd_HHmmss
     */
    public static String GetRandomFileName(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
    }

    /**
     * Get date as String in dd / MMMM / yyyy format
     *
     * @param calendar      calendar instance
     * @return              String with formatted date
     */
    public static String GetDateAsString(Calendar calendar) {
        SimpleDateFormat month = new SimpleDateFormat("MMMM", Locale.getDefault());
        SimpleDateFormat other = new SimpleDateFormat("dd / - / yyyy", Locale.getDefault());
        String monthString = month.format(calendar.getTime());
        String otherString = other.format(calendar.getTime());
        monthString = monthString.substring(0, 1).toUpperCase() + monthString.substring(1);
        return otherString.replace("-", monthString);
    }

    /**
     * Get date as String in desired format
     *
     * @param calendar      calendar instance
     * @param format        String with date format
     * @return              String with formatted date
     */
    public static String GetDateAsString(Calendar calendar, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * Compare String
     *
     * @param baseString        base comparable String
     * @param compareString     comparable String
     * @return                  if the Strings are equals
     */
    public static boolean CompareEquals(String baseString, String compareString) {
        return baseString.equals(compareString);
    }

    /**
     * Check length of String
     *
     * @param baseString        base comparable String
     * @param lengthToCheck     length to check
     * @param compareType       object compare type {@link CompareType}
     * @return                  result of the condition of length
     */
    public static boolean CheckStringLength(String baseString, int lengthToCheck, CompareType compareType) {
        switch (compareType) {
            case MAJOR:
                return baseString.length() > lengthToCheck;
            case MAJOR_EQUALS:
                return baseString.length() >= lengthToCheck;
            case MINOR:
                return baseString.length() < lengthToCheck;
            case MINOR_EQUALS:
                return baseString.length() <= lengthToCheck;
            case EQUALS:
                return baseString.length() == lengthToCheck;
            default:
                return false;
        }
    }

    /**
     * Check if email has correct format
     *
     * @param email         String of checkable email
     * @return              if the email was the correct format
     */
    public static boolean IsEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Read content of raw file
     * @param context   Application context
     * @param resId     Resource id of raw file
     * @return          String with raw file content
     */
    public static String ReadRawTextFile(Context context, int resId) {
        InputStream inputStream = context.getResources().openRawResource(resId);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the application name from context
     * @param context   Application context
     * @return          Application name
     */
    public static String GetApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

}
