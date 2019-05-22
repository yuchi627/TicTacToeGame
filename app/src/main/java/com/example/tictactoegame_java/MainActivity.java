package com.example.tictactoegame_java;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bu1 ; //
    Button bu2 ;
    Button bu3 ;
    Button bu4 ;
    Button bu5 ;
    Button bu6 ;
    Button bu7 ;
    Button bu8 ;
    Button bu9 ;

    boolean isCPUmode = true;
    boolean isWin = false;
    int winner = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bu1 = (Button) findViewById(R.id.bu1); //
        bu2 = (Button) findViewById(R.id.bu2);
        bu3 = (Button) findViewById(R.id.bu3);
        bu4 = (Button) findViewById(R.id.bu4);
        bu5 = (Button) findViewById(R.id.bu5);
        bu6 = (Button) findViewById(R.id.bu6);
        bu7 = (Button) findViewById(R.id.bu7);
        bu8 = (Button) findViewById(R.id.bu8);
        bu9 = (Button) findViewById(R.id.bu9);

        AlertDialog.Builder bldr = new AlertDialog.Builder(this);  //
        bldr.setTitle("Game Start");
        bldr.setPositiveButton("1P", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCPUmode = true;
            }
        });
        bldr.setNegativeButton("2P", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCPUmode = false;
            }
        });

        AlertDialog dilg = bldr.create();
        dilg.setCancelable(false);
        dilg.show();


    }

    void buClick(View view) {
        Button buSelected = (Button) view;
        int cellID = 0;
        switch (buSelected.getId()) { //
            case R.id.bu1:
                cellID = 1;
                break;
            case R.id.bu2:
                cellID = 2;
                break;
            case R.id.bu3:
                cellID = 3;
                break;
            case R.id.bu4:
                cellID = 4;
                break;
            case R.id.bu5:
                cellID = 5;
                break;
            case R.id.bu6:
                cellID = 6;
                break;
            case R.id.bu7:
                cellID = 7;
                break;
            case R.id.bu8:
                cellID = 8;
                break;
            case R.id.bu9:
                cellID = 9;
                break;
        }
        playGame(cellID, buSelected);
        if(isWin) {
            AlertDialog.Builder bldr = new AlertDialog.Builder(this);  //
            if(winner == 1)
            {
                bldr.setTitle("Congratulations! Player1 is win!");
            }
            else if(winner == 2)
            {
                bldr.setTitle("Congratulations! Player2 is win!");
            }
            else
            {
                bldr.setTitle("Congratulations! Nobody is win!");
            }
            bldr.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Replay();
                }
            });

            AlertDialog dilg = bldr.create();
            dilg.setCancelable(false);
            dilg.show();

            isWin = false;
            winner = -1;
        }
    }

    List<Integer> player1 = new ArrayList<Integer>(); //
    List<Integer> player2 = new ArrayList<Integer>(); //
    int activePlayer = 1;

    private void playGame(int cellID,Button buSelected) {
        if (activePlayer == 1) {
            buSelected.setText("X");
            buSelected.setBackgroundColor(Color.parseColor("#009193"));
            player1.add(cellID);
            activePlayer = 2;
            if(isCPUmode) AutoPlay();
        } else {
            buSelected.setText("O");
            buSelected.setBackgroundColor(Color.parseColor("#FF9300"));
            player2.add(cellID);
            activePlayer = 1;
        }
        buSelected.setEnabled(false); //
        Log.d("a",player1.toString());
        Log.d("b",player2.toString());
        checkWinner();
    }

    private void checkWinner() {
        winner = -1;

        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) { // row1
            winner = 1;
        }
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2;
        }
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6)) { // row2
            winner = 1;
        }
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2;
        }
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9)) {// row3
            winner = 1;
        }
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2;
        }
        else if(player1.contains(1) && player1.contains(4) && player1.contains(7)) {// col1
            winner = 1;
        }
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2;
        }
        else if(player1.contains(2) && player1.contains(5) && player1.contains(8)) {// col2
            winner = 1;
        }
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2;
        }
        else if(player1.contains(3) && player1.contains(6) && player1.contains(9)) {// col3
            winner = 1;
        }
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2;
        }
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9)) {// slash1
            winner = 1;
        }
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2;
        }
        else if(player1.contains(3) && player1.contains(5) && player1.contains(7)) {// slash2
            winner = 1;
        }
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2;
        }else if(player1.size()+player2.size() == 9){ //
            winner = 3;
        }else {
            winner = -1;
        }

        if(winner != -1) {
            if(winner == 1) {
                Toast.makeText(this,"Player 1 won the game.",Toast.LENGTH_LONG).show();
            }else if(winner == 2){
                Toast.makeText(this,"Player 2 won the game.",Toast.LENGTH_LONG).show();
            }else { //tie
                Toast.makeText(this,"The game is tie.",Toast.LENGTH_LONG).show();
            }
            isWin = true;
        }
    }

    private void AutoPlay() {
        List<Integer> emptyCells = new ArrayList<Integer>(); //
        int i = 1 ;
        for( i = 1 ; i < 10 ; i++)  { //
            if(!(player1.contains(i)) && !(player2.contains(i))) {
                emptyCells.add(i);
            }
        }
        Random r = new Random(); //
        int randIndex = r.nextInt(emptyCells.size()-0)+0;
        int cellID = emptyCells.get(randIndex); //

        Button buSelected;
        switch (cellID) {
            case 1:
                buSelected = bu1;
                break;
            case 2:
                buSelected = bu2;
                break;
            case 3:
                buSelected = bu3;
                break;
            case 4:
                buSelected = bu4;
                break;
            case 5:
                buSelected = bu5;
                break;
            case 6:
                buSelected = bu6;
                break;
            case 7:
                buSelected = bu7;
                break;
            case 8:
                buSelected = bu8;
                break;
            case 9:
                buSelected = bu9;
                break;
            default:
                buSelected = bu1;
        }

        playGame(cellID,buSelected);
    }

    void button_init( Button bu ){
        bu.setText("");
        bu.setBackgroundColor(Color.parseColor("#C3C3C3"));
        bu.setEnabled(true);
    }
    
    private void Replay() {
        AlertDialog.Builder bldr = new AlertDialog.Builder(this);  //
        bldr.setTitle("Game Start");
        bldr.setPositiveButton("1P", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCPUmode = true;
            }
        });
        bldr.setNegativeButton("2P", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCPUmode = false;
            }
        });

        AlertDialog dilg = bldr.create();
        dilg.setCancelable(false);
        dilg.show();

        player1.clear();
        player2.clear();
        activePlayer = 1;
        button_init(bu1);
        button_init(bu2);
        button_init(bu3);
        button_init(bu4);
        button_init(bu5);
        button_init(bu6);
        button_init(bu7);
        button_init(bu8);
        button_init(bu9);

    }
}
