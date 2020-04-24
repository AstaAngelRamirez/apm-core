package com.apm.core.utils;

import android.os.AsyncTask;

import com.apm.core.contracts.ConvertJSONToGenericClassListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ing. Oscar G. Medina Cruz on 23/06/17.
 *
 * Handle {@link JSONObject} and {@link JSONArray} functions
 */

public class JSONUtils {

    /**
     * Converts a JSONArray object to Java String array
     * @param jsonArray         JOSNArray to be parsed
     * @return                  Java String array
     * @throws JSONException
     */
    public static String[] ConvertJSONArrayToStringArray(JSONArray jsonArray) throws JSONException {
        String[] array = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++){
            array[i] = jsonArray.getString(i);
        }
        return array;
    }

    /**
     * Convert HasMap to JSONObject
     *
     * @param map values map
     * @return JSONObject with parsed map content
     * @throws JSONException if something was wrong with JSONObject creation
     */
    public static JSONObject BuildJSONObjectFromMap(HashMap<String, String> map) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        for (String key : map.keySet()){
            jsonObject.put(key, map.get(key));
        }

        return jsonObject;
    }

    public static <T> T ConvertJSONStringToModel(String jsonString, Class<T> genericClass, boolean inBackground, ConvertJSONToGenericClassListener convertJSONToGenericClassListener){
        if (null == jsonString) {
            return null;
        }

        if (!inBackground) {
            return new Gson().fromJson(jsonString, genericClass);
        } else {
            ParseJSONToGenericClass<T> parseJSONToGenericClass = new ParseJSONToGenericClass<>(genericClass, convertJSONToGenericClassListener);
            parseJSONToGenericClass.execute(jsonString);
            return null;
        }
    }

    public static <T> List<T> ConvertJSONStringToModelArray(String jsonString, Class<T[]> genericClass, boolean inBackground, ConvertJSONToGenericClassListener convertJSONToGenericClassListener){
        if (!inBackground) {
            return Arrays.asList(new Gson().fromJson(jsonString, genericClass));
        } else {
            ParseJSONToGenericClassArray<T> parseJSONToGenericClassArray = new ParseJSONToGenericClassArray<>(genericClass, convertJSONToGenericClassListener);
            parseJSONToGenericClassArray.execute(jsonString);
            return null;
        }
    }

    private static class ParseJSONToGenericClass<T> extends AsyncTask<String, Void, Void>{

        private Class<T> genericClass;
        private T generatedClass;
        private ConvertJSONToGenericClassListener convertJSONToGenericClassListener;

        ParseJSONToGenericClass(Class<T> genericClass, ConvertJSONToGenericClassListener convertJSONToGenericClassListener){
            this.genericClass = genericClass;
            this.convertJSONToGenericClassListener = convertJSONToGenericClassListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (convertJSONToGenericClassListener != null){
                convertJSONToGenericClassListener.onBeginConvertion();
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            generatedClass = new Gson().fromJson(strings[0], genericClass);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoids) {
            super.onPostExecute(aVoids);

            if (convertJSONToGenericClassListener != null){
                convertJSONToGenericClassListener.onFinishConvertion(generatedClass, null);
            }
        }
    }

    private static class ParseJSONToGenericClassArray<T> extends AsyncTask<String, Void, Void>{

        private Class<T[]> genericClass;
        private List<T> generatedClass;
        private ConvertJSONToGenericClassListener convertJSONToGenericClassListener;

        ParseJSONToGenericClassArray(Class<T[]> genericClass, ConvertJSONToGenericClassListener convertJSONToGenericClassListener){
            this.genericClass = genericClass;
            this.convertJSONToGenericClassListener = convertJSONToGenericClassListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (convertJSONToGenericClassListener != null){
                convertJSONToGenericClassListener.onBeginConvertion();
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            generatedClass = Arrays.asList(new Gson().fromJson(strings[0], genericClass));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoids) {
            super.onPostExecute(aVoids);

            if (convertJSONToGenericClassListener != null){
                convertJSONToGenericClassListener.onFinishConvertion(null, generatedClass);
            }
        }
    }
}
