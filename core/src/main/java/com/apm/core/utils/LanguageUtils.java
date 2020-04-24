package com.apm.core.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by Ing. Oscar G. Medina Cruz on 28/08/17.
 *
 * Handle language options for application
 */

public class LanguageUtils {

    /**
     * Force app language for use languaje selection
     * @param context Application context
     * @param languageCode An ISO 639 alpha-2 or alpha-3 language code, or a language subtag
     * up to 8 characters in length.  See the <code>Locale</code> class description about
     * valid language values.
     * @return New app configuration
     */
    public static Configuration ForceAppLanguage(Context context, String languageCode){
        Configuration conf = context.getResources().getConfiguration();
        conf.setLocale(new Locale(languageCode.toLowerCase()));
        return conf;
    }

    /**
     * Get the current device language. For example: ("es" : for spanish, "en":  for english)
     * @return  Curernt device language
     */
    public static String GetDeviceLanguage(){
        return Locale.getDefault().getLanguage();
    }
}
