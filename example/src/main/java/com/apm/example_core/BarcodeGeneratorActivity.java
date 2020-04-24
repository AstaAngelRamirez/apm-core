package com.apm.example_core;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apm.core.abstracts.BarcodeGeneratorListener;
import com.apm.core.utils.BarcodeGenerator;
import com.google.zxing.WriterException;

public class BarcodeGeneratorActivity extends AppCompatActivity {

    BarcodeGeneratorListener barcodeGeneratorListener
            = new BarcodeGeneratorListener() {
        @Override
        public void onBarcodeGenerateStart() {
            super.onBarcodeGenerateStart();

            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        public void onBarcodeGenerateSuccessful(Bitmap barcode) {
            super.onBarcodeGenerateSuccessful(barcode);

            findViewById(R.id.nested_codebar_string_container).setVisibility(View.INVISIBLE);
            findViewById(R.id.image_codebar).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.image_codebar)).setImageBitmap(barcode);
        }

        @Override
        public void onBarcodeGenerateSuccessful(String barcode) {
            super.onBarcodeGenerateSuccessful(barcode);

            findViewById(R.id.nested_codebar_string_container).setVisibility(View.VISIBLE);
            findViewById(R.id.image_codebar).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.text_codebar)).setText(barcode);
        }

        @Override
        public void onBarcodeGenerateSuccessful(byte[] barcode) {
            super.onBarcodeGenerateSuccessful(barcode);

            findViewById(R.id.nested_codebar_string_container).setVisibility(View.VISIBLE);
            findViewById(R.id.image_codebar).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.text_codebar)).setText(String.valueOf(barcode));
        }

        @Override
        public void onBarcodeGenerateFailed(WriterException exception) {
            super.onBarcodeGenerateFailed(exception);

            findViewById(R.id.nested_codebar_string_container).setVisibility(View.VISIBLE);
            findViewById(R.id.image_codebar).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.text_codebar)).setText(exception.getMessage());
        }

        @Override
        public void onBarcodeGenerateFinish() {
            super.onBarcodeGenerateFinish();

            findViewById(R.id.progress_bar).setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_generator);

        (findViewById(R.id.button_barcode_generator_image))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new BarcodeGenerator.Builder()
                                .asBitmap()
                                .listener(barcodeGeneratorListener)
                                .build()
                                .generate(((AppCompatEditText) findViewById(R.id.edit_text_codebar)).getText().toString());
                    }
                });

        (findViewById(R.id.button_barcode_generator_base64))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new BarcodeGenerator.Builder()
                                .asBase64String()
                                .listener(barcodeGeneratorListener)
                                .build()
                                .generate(((AppCompatEditText) findViewById(R.id.edit_text_codebar)).getText().toString());
                    }
                });

        (findViewById(R.id.button_barcode_generator_bytearray))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new BarcodeGenerator.Builder()
                                .asByteArray()
                                .listener(barcodeGeneratorListener)
                                .build()
                                .generate(((AppCompatEditText) findViewById(R.id.edit_text_codebar)).getText().toString());
                    }
                });
    }
}
