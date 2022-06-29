package com.example.flags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText input;
TextView timer;
ImageView flags;
TextView score;
Button repeat;
int count=0;
Integer flagsArray[]={R.drawable.france,R.drawable.ethiopia,R.drawable.usa,R.drawable.wale,R.drawable.jamaica,
R.drawable.morocco,R.drawable.germany,R.drawable.japan,R.drawable.maqedonia};
String answers[]={"france","ethiopia","usa","wale","jamaica","morocco","germany","japan","maqedonia"};
int countBackup=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        repeat=findViewById(R.id.repeat);
        timer=findViewById(R.id.timer);
        flags=findViewById(R.id.flags);
        score=findViewById(R.id.score);
        repeat.setEnabled(false);
        repeat.setOnClickListener(this);
        timer(6000);

    }

    private void timer(int f) {
        Random rand1=new Random();
        int n=rand1.nextInt(9);
        flags.setImageResource(flagsArray[n]);
        String actualAnswer=answers[n];
        new CountDownTimer(f,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                if(!input.getText().toString().toLowerCase().isEmpty()){
                    String actualInput=input.getText().toString().toLowerCase();
                    if(actualInput.equals(actualAnswer)){
                        count++;
                        countBackup=count;
                        score.setText(Integer.toString(count));
                        play();

                    }else{
                        count--;
                        countBackup=count;
                        score.setText(Integer.toString(count));
                        repeat.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "The answer was "+actualAnswer, Toast.LENGTH_SHORT).show();

                    }
                }else{
                    count--;
                    countBackup=count;
                    score.setText(Integer.toString(count));
                    repeat.setEnabled(true);
                }
            }
        }.start();

    }
    
    public void play(){
        Random rand2=new Random();
        int n2=rand2.nextInt(9);
        flags.setImageResource(flagsArray[n2]);
        String actualAnswer=answers[n2];
        timer(6000);
        input.setText("");
        score.setText(Integer.toString(countBackup));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==repeat.getId()){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}