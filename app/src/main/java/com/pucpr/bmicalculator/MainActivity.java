package com.pucpr.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editWeight, editHeight;
    Button buttonCalculate;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textResult = findViewById(R.id.textResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weightStr = editWeight.getText().toString();
                String heightStr = editHeight.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                float weight = Float.parseFloat(weightStr);
                float height = Float.parseFloat(heightStr);

                float bmi = weight / (height * height);
                String classification = classifyBMI(bmi);

                String result = String.format(Locale.getDefault(), "BMI: %.2f\n%s", bmi, classification);
                textResult.setText(result);
            }
        });
    }

    private String classifyBMI(float bmi) {
        if (bmi < 18.5f) {
            return "Underweight";
        } else if (bmi < 24.9f) {
            return "Normal weight";
        } else if (bmi < 29.9f) {
            return "Overweight";
        } else if (bmi < 34.9f) {
            return "Obesity Class I";
        } else if (bmi < 39.9f) {
            return "Obesity Class II";
        } else {
            return "Obesity Class III";
        }
    }
}
