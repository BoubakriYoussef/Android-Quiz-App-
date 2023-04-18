package com.example.myquizapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonOK;
    private EditText editTextNumber;
    private TextView textViewIndication;
    private TextView textViewScore;
    private ProgressBar progressBarTentatives;
    private TextView textViewTentatives;
    private ListView listViewHistorique;
    private int secret;
    private int counter;
    private int score;
    private List <String> listHistorique=new ArrayList<>();

    private int maxTentatives=6;



    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOK=findViewById(R.id.buttonOK);
        editTextNumber=findViewById(R.id.editTextNumber);
        textViewIndication=findViewById(R.id.textViewIndication);
        progressBarTentatives=findViewById(R.id.progressBarTentatives);
        textViewTentatives=findViewById(R.id.textViewTentatives);
        listViewHistorique=findViewById(R.id.listViewHistorique);
        textViewScore=findViewById(R.id.textViewScore);
        initialisation();

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number=Integer.parseInt(editTextNumber.getText().toString());

                if(number>secret){
                    textViewIndication.setText(R.string.valeur_sup);
                } else if (number<secret) {
                    textViewIndication.setText(R.string.valeur_inf);
                }
                else {
                    textViewIndication.setText(R.string.bravo);
                    score+=5;
                    textViewScore.setText(String.valueOf(score));
                    rejouer();
                }
                ++counter;
                textViewTentatives.setText(String.valueOf(counter));
                progressBarTentatives.setProgress(counter);
                if(counter>maxTentatives){
                    rejouer();
                }
            }
        });

    }

    private void rejouer() {
        Log.i("MyLog", "REJOUER.....");
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Rejouer?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"OUI",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initialisation();
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Quitter", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }

    private void initialisation() {
        secret=1+((int)(Math.random()*10));
        counter =1;

        textViewTentatives.setText(String.valueOf(counter));
        progressBarTentatives.setProgress(counter);
        progressBarTentatives.setMax(maxTentatives);
        textViewScore.setText(String.valueOf(score));

    }
}