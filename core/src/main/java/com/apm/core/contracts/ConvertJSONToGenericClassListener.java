package com.apm.core.contracts;

import java.util.List;

/**
 * Created by IngMedina on 02/12/2017.
 */

public interface ConvertJSONToGenericClassListener<T> {

    void onBeginConvertion();
    void onFinishConvertion(T genericClass, List<T> genericClassArray);

}
