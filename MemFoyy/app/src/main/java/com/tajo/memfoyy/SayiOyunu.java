package com.tajo.memfoyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class SayiOyunu extends AppCompatActivity {

    private Toolbar sayiOyunu;

    TextView levell, numberr;
    EditText number_et;
    Button btn_onay, btn_return, btn_restart;
    Random r;

    int currentLevel = 1;
    String generatedNumber;

    public void init() {
        sayiOyunu = (Toolbar) findViewById(R.id.sayiOyunuActionBar);
        setSupportActionBar(sayiOyunu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sayı Hafıza Oyunu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayi_oyunu);
        init();

        levell = (TextView) findViewById(R.id.level);
        numberr = (TextView) findViewById(R.id.number);
        number_et = (EditText) findViewById(R.id.number_editText);
        btn_onay = (Button) findViewById(R.id.btn_onay);
        btn_return =(Button) findViewById(R.id.btn_return);
        btn_restart =(Button) findViewById(R.id.btn_restart);

        r = new Random();

        number_et.setVisibility(View.GONE);
        btn_onay.setVisibility(View.GONE);
        numberr.setVisibility(View.VISIBLE);
        levell.setText("Level: " + currentLevel);


        generatedNumber = generateNumber(currentLevel);

        numberr.setText(generatedNumber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                number_et.setVisibility(View.VISIBLE);
                btn_onay.setVisibility(View.VISIBLE);
                numberr.setVisibility(View.GONE);

                number_et.requestFocus();
            }
        }, 2000);

        btn_onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (generatedNumber.equals(number_et.getText().toString())) {

                    number_et.setVisibility(View.GONE);
                    btn_onay.setVisibility(View.GONE);
                    numberr.setVisibility(View.VISIBLE);

                    number_et.setText("");


                    currentLevel++;

                    levell.setText("Level: " + currentLevel);

                    generatedNumber = generateNumber(currentLevel);

                    numberr.setText(generatedNumber);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            number_et.setVisibility(View.VISIBLE);
                            btn_onay.setVisibility(View.VISIBLE);
                            numberr.setVisibility(View.GONE);

                            number_et.requestFocus();
                        }
                    }, 2000);
                }
                else{
                    levell.setText("Oyun Bitti! Sayı " + generatedNumber + " olacaktı \n" + currentLevel + ". seviyeye kadar gelebildiniz");
                    btn_onay.setEnabled(false);
                    btn_return.setVisibility(View.VISIBLE);
                    btn_restart.setVisibility(View.VISIBLE);
                    btn_return.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent donIntent = new Intent(SayiOyunu.this,AppActivity.class);
                            startActivity(donIntent);
                            finish();
                        }
                    });
                    btn_restart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recreate();
                        }
                    });
                }
            }
        });
    }

    private String generateNumber(int digits) {
        String output = "";
        for (int i = 0; i < digits; i++) {
            int randomDigit = r.nextInt(10);
            output = output + "" + randomDigit;
        }
        return output;
    }
}