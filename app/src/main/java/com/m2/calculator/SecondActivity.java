package com.m2.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        Double result = getIntent().getDoubleExtra("key1", 0);

        ImageButton heartButton = findViewById(R.id.heart_button);
        Spinner spinner = findViewById(R.id.dropdown);
        RangeSlider rangeSlider1 = findViewById(R.id.range_slider1);
        RangeSlider rangeSlider2 = findViewById(R.id.range_slider2);
        TextView resultTextView = findViewById(R.id.result_textview);

        if (result % 1 == 0) {
            resultTextView.setText(String.valueOf(Math.round(result)));
        } else {
            resultTextView.setText(String.valueOf(result).replace('.', ','));
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
        rangeSlider1.setCustomThumbDrawable(R.drawable.rangeslider_thumb);
        rangeSlider1.setThumbRadius(20);
        rangeSlider1.setStepSize(10);
        rangeSlider1.setTrackHeight(10);
        rangeSlider1.setTickVisible(false);
        rangeSlider2.setCustomThumbDrawable(R.drawable.rangeslider_thumb);
        rangeSlider2.setThumbRadius(20);
        rangeSlider2.setStepSize(10);
        rangeSlider2.setTrackHeight(10);
        rangeSlider2.setTickVisible(false);

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heartButton.isActivated()){
                    heartButton.setActivated(false);
                    heartButton.setBackground(getResources().getDrawable(R.drawable.heart));
                } else {
                    heartButton.setActivated(true);
                    heartButton.setBackground(getResources().getDrawable(R.drawable.heart_clicked));
                }
            }

        });
    }
}