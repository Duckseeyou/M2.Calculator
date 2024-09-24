package com.m2.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private TextView output;
    private double a, b, result = 0;
    private String actionButton = "";
    private boolean isEqualLast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);

    }
    public void onNumberClick(View view){
        Button pressedButton = (Button) view;

        if (output.getText().toString().trim().equals("0") || isEqualLast){
            output.setText(pressedButton.getText());
            isEqualLast = false;
        } else {
            if (!pressedButton.getText().toString().equals(",") || !output.getText().toString().contains(",")) {
                if (output.getText().toString().length() < 13) {
                    output.append(pressedButton.getText());
                }
                isEqualLast = false;
            }
        }
    }

    public void onActionClick(View view){
        Button pressedButton = (Button) view;

        switch (pressedButton.getText().toString()){
            case "⬅":
                if (output.getText().toString().length() > 1){
                    output.setText(output.getText().toString().substring(0, output.length() - 1));
                } else {
                    output.setText("0");
                }
                break;
            case "AC":
                output.setText("0");
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
                a = Double.parseDouble(output.getText().toString().replace(',', '.'));
                output.setText("0");
                actionButton = pressedButton.getText().toString();
                break;
            case "±":
                if (!output.getText().toString().equals("0") && !output.getText().toString().contains("-"))
                output.setText("-" + output.getText().toString());
                break;
        }
    }
    public void onEqualClick(View view){
        Button invisibleButton = findViewById(R.id.invisible_button);
        if (!output.getText().toString().equals("Err")){
            b = Double.parseDouble(output.getText().toString().replace(',', '.'));
        }
        switch (actionButton) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "×":
                result = a * b;
                break;
            case "÷":
                result = a / b;
                break;
            case "":
                result = Double.parseDouble(output.getText().toString().replace(',', '.'));

        }
        if(b != 0) {
            if (result % 1 == 0) {
                output.setText(String.valueOf(Math.round(result)));
            } else {
                output.setText(String.valueOf(result).replace('.', ','));
            }
            a = result;
            isEqualLast = true;
            actionButton = "";
            b = 0;
        } else if (b == 0 && result != 0){
            output.setText("Err");
        }

        invisibleButton.setVisibility(View.VISIBLE);

    }

    public void onInvisibleButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("key1", result);
        startActivity(intent);
    }
}