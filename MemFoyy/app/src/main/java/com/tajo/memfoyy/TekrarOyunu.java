package com.tajo.memfoyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TekrarOyunu extends AppCompatActivity {

    private Toolbar tekrarOyunu;

    Button newG;
    Button btn1, btn2, btn3,
            btn4, btn5, btn6,
            btn7, btn8, btn9,
            btn10, btn11, btn12;

    List<Integer> buttons;

    int currentLevel, currentGuess;

    public void init() {
        tekrarOyunu = (Toolbar) findViewById(R.id.actionbarTekrar);
        setSupportActionBar(tekrarOyunu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sayı Tekrarı Oyunu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tekrar_oyunu);
        init();

        newG = (Button) findViewById(R.id.newgame);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);

        btn1.setTag(1);
        btn2.setTag(2);
        btn3.setTag(3);
        btn4.setTag(4);
        btn5.setTag(5);
        btn6.setTag(6);
        btn7.setTag(7);
        btn8.setTag(8);
        btn9.setTag(9);
        btn10.setTag(10);
        btn11.setTag(11);
        btn12.setTag(12);

        disableButtons();

        buttons = new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);
        buttons.add(4);
        buttons.add(5);
        buttons.add(6);
        buttons.add(7);
        buttons.add(8);
        buttons.add(9);
        buttons.add(10);
        buttons.add(11);
        buttons.add(12);


        newG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newG.setVisibility(View.INVISIBLE);
                currentGuess = 0;
                currentLevel = 1;
                generateButons(currentLevel);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });


        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button)v).setText("O");
            }
        });

    }

    private void buttonLogic(View v){
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < currentLevel; i++ ){
            tempList.add(buttons.get(i));
        }

        if (tempList.contains(v.getTag())){
            currentGuess++;
            checkWin();
        } else{
            lostGame();
        }

    }

    private void checkWin(){
        if (currentGuess == currentLevel){
            disableButtons();
            if (currentLevel == 12){
                Toast.makeText(this,"BAŞARDIN!", Toast.LENGTH_LONG).show();
                newG.setVisibility(View.VISIBLE);
            } else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentGuess = 0;
                        currentLevel++;
                        generateButons(currentLevel);

                    }
                }, 1000);

            }
        }
    }

    private void lostGame(){
        Toast.makeText(this,"Yanlış Bastın KAYBETTİN!!", Toast.LENGTH_LONG).show();
        disableButtons();
        newG.setVisibility(View.VISIBLE);

    }

    private void generateButons(int number){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");

        Collections.shuffle(buttons);

        for (int i = 0; i < number; i++){
            showButton(buttons.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                btn9.setText("");
                btn10.setText("");
                btn11.setText("");
                btn12.setText("");

                enableButtons();
            }
        },1000);

    }

    private void showButton(int number){
        switch (number){
            case 1:
                btn1.setText("O");
                break;
            case 2:
                btn2.setText("O");
                break;
            case 3:
                btn3.setText("O");
                break;
            case 4:
                btn4.setText("O");
                break;
            case 5:
                btn5.setText("O");
                break;
            case 6:
                btn6.setText("O");
                break;
            case 7:
                btn7.setText("O");
                break;
            case 8:
                btn8.setText("O");
                break;
            case 9:
                btn9.setText("O");
                break;
            case 10:
                btn10.setText("O");
                break;
            case 11:
                btn11.setText("O");
                break;
            case 12:
                btn12.setText("O");
                break;
        }
    }

    private void enableButtons(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn10.setEnabled(true);
        btn11.setEnabled(true);
        btn12.setEnabled(true);
    }

    private void disableButtons(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
        btn11.setEnabled(false);
        btn12.setEnabled(false);
    }
}