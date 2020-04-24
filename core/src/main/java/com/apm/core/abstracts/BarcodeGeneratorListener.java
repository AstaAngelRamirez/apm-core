package com.apm.core.abstracts;

import android.graphics.Bitmap;

import com.apm.core.contracts.IBarcodeGeneratorListener;
import com.google.zxing.WriterException;

/**
 * Created by Ing. Oscar G. Medina Cruz on 28/05/18.
 */
public abstract class BarcodeGeneratorListener implements IBarcodeGeneratorListener {


    @Override
    public void onBarcodeGenerateStart() {

    }

    @Override
    public void onBarcodeGenerateSuccessful(Bitmap barcode) {

    }

    @Override
    public void onBarcodeGenerateSuccessful(String barcode) {

    }

    @Override
    public void onBarcodeGenerateSuccessful(byte[] barcode) {

    }

    @Override
    public void onBarcodeGenerateFailed(WriterException exception) {

    }

    @Override
    public void onBarcodeGenerateFinish() {

    }
}
