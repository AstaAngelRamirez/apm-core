package com.apm.core.contracts;

import android.graphics.Bitmap;

import com.google.zxing.WriterException;

/**
 * Created by Ing. Oscar G. Medina Cruz on 25/10/17.
 *
 * Handles barcode generation events
 */

public interface IBarcodeGeneratorListener {

    /**
     * Trigger when barcode generation starts
     */
    void onBarcodeGenerateStart();

    /**
     * Trigger when barcode generation was successful
     * @param barcode image as bitmap
     */
    void onBarcodeGenerateSuccessful(Bitmap barcode);

    /**
     * Trigger when barcode generation was successful
     * @param barcode image as base 64 String
     */
    void onBarcodeGenerateSuccessful(String barcode);

    /**
     * Trigger when barcode generation was successful
     * @param barcode image as byte array
     */
    void onBarcodeGenerateSuccessful(byte[] barcode);

    /**
     * Trigger when something was wrong with barcode generation
     * @param exception receive barcode exception
     */
    void onBarcodeGenerateFailed(WriterException exception);

    /**
     * Trigger when barcode generation finishes
     */
    void onBarcodeGenerateFinish();
}
