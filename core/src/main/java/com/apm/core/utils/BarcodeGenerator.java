package com.apm.core.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Base64;

import com.apm.core.abstracts.BarcodeGeneratorListener;
import com.apm.core.enums.BarcodeType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.ByteArrayOutputStream;

/**
 * Created by Ing. Oscar G. Medina Cruz on 25/10/17.
 * <p>
 * Generate barcode from numeric value in background, and return it as a Bitmap, base 64 String or byte array
 */

public class BarcodeGenerator {

    private BarcodeType mBarcodeType;
    private BarcodeGeneratorListener mBarcodeGeneratorListener;

    /**
     * BarcodeGenerator constructor
     */
    private BarcodeGenerator(BarcodeType barcodeType, BarcodeGeneratorListener barcodeGeneratorListener) {
        this.mBarcodeType = barcodeType;
        this.mBarcodeGeneratorListener = barcodeGeneratorListener;
    }

    public void generate(String codebarString){
        new Generator().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, codebarString);
    }

    @SuppressLint("StaticFieldLeak")
    private class Generator extends AsyncTask<String, String, Object>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mBarcodeGeneratorListener != null) {
                mBarcodeGeneratorListener.onBarcodeGenerateStart();
            }
        }

        @Override
        protected Object doInBackground(String... params) {
            com.google.zxing.Writer c9 = new Code128Writer();

            try {
                BitMatrix bm = c9.encode(params[0], BarcodeFormat.CODE_128, 512, 256);
                Bitmap bitmap = Bitmap.createBitmap(512, 256, Bitmap.Config.ARGB_8888);
                ByteArrayOutputStream byteArrayOutputStream = null;

                for (int i = 0; i < 512; i++) {
                    for (int j = 0; j < 256; j++) {
                        bitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                    }
                }

                switch (mBarcodeType) {
                    case BITMAP:
                        return bitmap;
                    case STRING:
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        return Base64.encodeToString(byteArrayOutputStream.toByteArray(),
                                Base64.NO_WRAP);
                    case BYTE_ARRAY:
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        return byteArrayOutputStream.toByteArray();
                }
            } catch (WriterException e) {
                return e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            if (mBarcodeGeneratorListener != null) {
                if (result == null) {
                    mBarcodeGeneratorListener.onBarcodeGenerateFailed(new WriterException("Cannot convert barcode."));
                } else if (result instanceof WriterException) {
                    mBarcodeGeneratorListener.onBarcodeGenerateFailed((WriterException) result);
                } else if (result instanceof Bitmap) {
                    mBarcodeGeneratorListener.onBarcodeGenerateSuccessful((Bitmap) result);
                } else if (result instanceof String) {
                    mBarcodeGeneratorListener.onBarcodeGenerateSuccessful(String.valueOf(result));
                } else if (result instanceof byte[]) {
                    mBarcodeGeneratorListener.onBarcodeGenerateSuccessful((byte[]) result);
                }

                mBarcodeGeneratorListener.onBarcodeGenerateFinish();
            }
        }
    }

    public static class Builder {

        private BarcodeType mBarcodeType = BarcodeType.BITMAP;
        private BarcodeGeneratorListener mListener = null;

        public Builder asBitmap() {
            this.mBarcodeType = BarcodeType.BITMAP;
            return this;
        }

        public Builder asBase64String() {
            this.mBarcodeType = BarcodeType.STRING;
            return this;
        }

        public Builder asByteArray() {
            this.mBarcodeType = BarcodeType.BYTE_ARRAY;
            return this;
        }

        public Builder listener(BarcodeGeneratorListener barcodeGeneratorListener) {
            this.mListener = barcodeGeneratorListener;
            return this;
        }

        public BarcodeGenerator build() {
            return new BarcodeGenerator(mBarcodeType, mListener);
        }
    }
}
