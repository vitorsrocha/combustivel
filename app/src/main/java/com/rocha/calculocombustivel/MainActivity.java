package com.rocha.calculocombustivel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;


public class MainActivity extends AppCompatActivity {

    EditText editGasolina ;
    EditText editAlcool;
    Button buttonCalcular;
    TextView textResultadoo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //remover titulo

        editGasolina = (EditText)findViewById(R.id.edtGasolina);
        editAlcool = (EditText)findViewById(R.id.editAlcool);
        textResultadoo = (TextView) findViewById(R.id.textResultado);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);

        //Mascaras
        SimpleMaskFormatter smf = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editGasolina, smf);
        editGasolina.addTextChangedListener(mtw);
        MaskTextWatcher mtw2 = new MaskTextWatcher(editAlcool, smf);
        editAlcool.addTextChangedListener(mtw2);
        //--------
        //click do botão

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(buttonCalcular.getWindowToken(), 0);
        //ferificação de campo vazio
                if (editGasolina.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Falta preço da Gasolina", Toast.LENGTH_SHORT).show();
                }
                else if (editAlcool.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Falta preço do Álcool", Toast.LENGTH_SHORT).show();
                }
        //----------------------
        //inicio da logica
                else{
                    float valorG = Float.parseFloat(String.valueOf(editGasolina.getText())); // converter String em Float
                    float valorA = Float.parseFloat(String.valueOf(editAlcool.getText()));

                    if ((valorG * 0.70) < valorA ) { //caso 70% do valor da gasolina for menor q preço do álcool
                        Toast.makeText(getApplicationContext(), "Gasolina", Toast.LENGTH_SHORT).show(); // alert

                    }else if ((valorG * 0.70) > valorA)// caso 70% da gasolina for maior que o álcool
                    {
                        Toast.makeText(getApplicationContext(), "Álcool", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Igual", Toast.LENGTH_SHORT).show();
                    }
        //fim da logica
                    editGasolina.getText().clear(); // linpar EditText
                    editAlcool.getText().clear();
                }
            }
        });
    }
}
