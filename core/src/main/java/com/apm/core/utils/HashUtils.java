package com.apm.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ing. Oscar G. Medina Cruz on 18/06/18.
 */
public class HashUtils {
    /**
     * This method was created to help the developer to know what keyhash we need to register in
     * facebook developers to enable procedures like login.
     *
     * SHA1 example: "EF:71:4D:78:F9:52:05:B6:03:C5:56:52:34:86:51:H6:ED:40:7B:4B"
     * <p>
     * Reference: https://stackoverflow.com/questions/45388382/facebook-login-in-my-android-app-is-working-fine-in-release-apk-but-not-working
     */
    public static String GetKeyHashFromSHA1(String sha1Code) {
        // Replace sha1String with: GooglePlayConsole -> Gestion de versiones -> Firma de aplicaciones
        // -> Certificado de firma de aplicaciones -> Huella digital del certificado SHA1
        String[] sha1String = sha1Code.split(":");
        byte[] sha1 = new byte[sha1String.length];

        for (int i = 0; i < sha1.length; i++) {
            sha1[i] = Integer.decode("0x" + sha1String[i]).byteValue();
        }

        return Base64.encodeToString(sha1, Base64.NO_WRAP);
    }

    /**
     * Generate the application keyhash
     *
     * Package name example: com.apm.core
     */
    public static String GetAppKeyHash(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
