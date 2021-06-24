package com.tajo.memfoyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class HafizaOyunu extends AppCompatActivity {

    ImageView curView = null;

    Button tekrar_btn;
    private Toolbar hafizaOyunu;
    private int countPair = 0;
    final int[] drawable = new int[]{R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,
            R.drawable.sample_3,R.drawable.sample_4,R.drawable.sample_5,R.drawable.sample_6,R.drawable.sample_7};

    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    int currenttPos = -1;

    public void init() {
        hafizaOyunu = (Toolbar) findViewById(R.id.hafizaOyunuActionBar);
        setSupportActionBar(hafizaOyunu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Resimli Hafıza Oyunu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hafiza_oyunu);
        init();

        tekrar_btn = (Button)findViewById(R.id.tekrar_btn);
        tekrar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        GridView gridView = (GridView)findViewById(R.id.gridView);
        ImageAdapter  imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currenttPos <0){
                    currenttPos = position;
                    curView = (ImageView)view;
                    ((ImageView)view).setImageResource(drawable[pos[position]]);
                }else {
                    if (currenttPos == position){
                        ((ImageView)view).setImageResource(R.drawable.hidden);
                    }else if(pos[currenttPos] !=pos[position]){
                        curView.setImageResource(R.drawable.hidden);
                        Toast.makeText(getApplicationContext(), "Yanlış Eşleştirme", Toast.LENGTH_SHORT).show();
                    }else{
                        ((ImageView)view).setImageResource(drawable[pos[position]]);
                        countPair++;

                        if (countPair==-1){
                            Toast.makeText(getApplicationContext(), "Kazandınız", Toast.LENGTH_SHORT).show();
                        }
                    }
                    currenttPos = -1;
                }
            }
        });
    }
}