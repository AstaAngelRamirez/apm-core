package com.apm.core.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IngMedina on 05/12/2017.
 */

public class ClassUtils {

    /**
     * Get all private <b>String</b> class variables and put into a <Name, Value> map
     * @param classObject   Class to search <b>String</b> variables
     * @return              Map with response
     */
    public static Map<String, String> GetStringClassVarsToMap(Object classObject){
        Map<String, String> fieldMap = new HashMap<>();
        Class c1 = classObject.getClass();
        Field[] fields = c1.getDeclaredFields();

        for (Field field : fields){
            if (Modifier.isPrivate(field.getModifiers())) {
                String fieldName = field.getName();
                field.setAccessible(true);

                Object newObj = null;
                try {
                    newObj = field.get(classObject);
                    if (newObj != null) {
                        if (newObj instanceof String
                                || newObj instanceof Integer
                                || newObj instanceof Double
                                || newObj instanceof Float
                                || newObj instanceof Character
                                || newObj instanceof Short
                                || newObj instanceof Long
                                || newObj instanceof Byte
                                || newObj instanceof Boolean) {
                            fieldMap.put(fieldName, String.valueOf(newObj));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return fieldMap;
    }

    /**
     * Get all private <b>File</b> class variables and put into a <Name, Value> map
     * @param classObject   Class to search <b>File</b> variables
     * @return              Map with response
     */
    public static Map<String, File> GetFileClassVarsToMap(Object classObject){
        Map<String, File> fieldMap = new HashMap<>();
        Class c1 = classObject.getClass();
        Field[] fields = c1.getDeclaredFields();

        for (Field field : fields){
            if (Modifier.isPrivate(field.getModifiers())) {
                String fieldName = field.getName();
                field.setAccessible(true);

                Object newObj = null;
                try {
                    newObj = field.get(classObject);
                    if (newObj != null) {
                        if (newObj instanceof File) {
                            fieldMap.put(fieldName, (File) newObj);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return fieldMap;
    }
}
