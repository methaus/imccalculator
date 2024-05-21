package com.example.imccalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnCalculate = findViewById(R.id.calculate);
        btnCalculate.setOnClickListener(V -> btnCalculateOnClick());
    }
    private void btnCalculateOnClick() {

        EditText heightInput = findViewById(R.id.height);
        EditText weightInput = findViewById(R.id.weight);
        TextView resultOutPut = findViewById(R.id.result);

        float height = Float.parseFloat(heightInput.getText().toString());
        float weight = Float.parseFloat(weightInput.getText().toString());


        double imc = weight / Math.pow(height / 100, 2);
        double idealWeight = 22 * (height * height / Math.pow(100, 2));

        String resultFinal = "Seu IMC é \"" + String.format("%.1f", imc) + "\"\n";

        if(imc >= 18.5 && imc <= 24.9){
            resultFinal+= "Você está no peso ideal.\n";
        }else {
            if(imc > 24.9 && imc <= 29.9){
                resultFinal+= "Você está com sobrepeso.\n";
            }else if(imc > 29.9 && imc <= 34.9){
                resultFinal+= "Você está com obesidade (1º grau).\n";
            }else if(imc > 34.9 && imc <= 39.9){
                resultFinal+= "Você está com obesidade severa (2º grau).\n";
            }else if(imc > 39.9){
                resultFinal+= "Você está com obesidade mórbida (3º grau).\n";
            }else if(imc < 18.5){
                resultFinal+= "Você está abaixo do peso.\n";
            }
            resultFinal+= "Seu peso ideal é \"" + String.format("%.1f", idealWeight) + "\".\n";
        }

        resultOutPut.setText(resultFinal);
    }
}