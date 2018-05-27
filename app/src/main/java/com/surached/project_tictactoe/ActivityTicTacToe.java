package com.surached.project_tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityTicTacToe extends AppCompatActivity {
    ImageButton btn_01, btn_02, btn_03, btn_04, btn_05, btn_06, btn_07, btn_08, btn_09;
    String UserName, ipAddress, play;
    int port;
    XO_Server server;
    TextView namePlayer, nameAnemy;
    ArrayList<String> board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserName = getIntent().getStringExtra("UserName");
        ipAddress = getIntent().getStringExtra("ipAddress");
        port = getIntent().getIntExtra("port", 2222);
        play = getIntent().getStringExtra("play");
        server = new XO_Server(this, port);

        setContentView(R.layout.activity_tic_tac_toe);
        namePlayer = findViewById(R.id.namePlayer);
        Log.d("show", UserName);
        namePlayer.setText(UserName);

        nameAnemy = findViewById(R.id.nameAnemy);

        board = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            board.add(i, "-");
        }

        btn_01 = findViewById(R.id.grid01);
        btn_02 = findViewById(R.id.grid02);
        btn_03 = findViewById(R.id.grid03);
        btn_04 = findViewById(R.id.grid04);
        btn_05 = findViewById(R.id.grid05);
        btn_06 = findViewById(R.id.grid06);
        btn_07 = findViewById(R.id.grid07);
        btn_08 = findViewById(R.id.grid08);
        btn_09 = findViewById(R.id.grid09);

        btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(0, play);
                setResponseAction(btn_01, play);
                sendData("btn_01-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(1, play);
                setResponseAction(btn_02, play);
                sendData("btn_02-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(2, play);
                setResponseAction(btn_03, play);
                sendData("btn_03-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(3, play);
                setResponseAction(btn_04, play);
                sendData("btn_04-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(4, play);
                setResponseAction(btn_05, play);
                sendData("btn_05-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(5, play);
                setResponseAction(btn_06, play);
                sendData("btn_06-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(6, play);
                setResponseAction(btn_07, play);
                sendData("btn_07-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(7, play);
                setResponseAction(btn_08, play);
                sendData("btn_08-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });
        btn_09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.set(8, play);
                setResponseAction(btn_09, play);
                sendData("btn_09-" + play + " " + UserName);
                checkWinner(play, UserName);
            }
        });

    }

    public void sendData(String message) {
        XO_Client client = new XO_Client(ipAddress, port, message);
        client.execute();
    }

    public void setResponseAction(ImageButton btn, String play) {
        if (play.equals("x")) {
            btn.setImageResource(R.drawable.pic_x);
        } else if (play.equals("o")) {
            btn.setImageResource(R.drawable.pic_o);
        }
        btn.setEnabled(false);
//        checkWinner(play, user);
    }

    public void checkWinner(String player, String user) {
        Log.d("gg", "checkWinner: " + board);
        Log.d("gg", "checkWinner: " + player + " " + user);
        if ((board.get(0).equals(player) && board.get(1).equals(player) && board.get(2).equals(player)) ||
                (board.get(3).equals(player) && board.get(4).equals(player) && board.get(5).equals(player)) ||
                (board.get(6).equals(player) && board.get(7).equals(player) && board.get(8).equals(player)) ||
                (board.get(0).equals(player) && board.get(3).equals(player) && board.get(6).equals(player)) ||
                (board.get(1).equals(player) && board.get(4).equals(player) && board.get(7).equals(player)) ||
                (board.get(2).equals(player) && board.get(5).equals(player) && board.get(8).equals(player)) ||
                (board.get(0).equals(player) && board.get(4).equals(player) && board.get(8).equals(player)) ||
                (board.get(2).equals(player) && board.get(4).equals(player) && board.get(6).equals(player))
                ) {
            alert(user);
        }
    }

    public void alert(String user) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage(user + " is winner!!!");
        alertDlg.setCancelable(false); // We avoid that the dialong can be cancelled, forcing the user to choose one of the options
        alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }
        );
        alertDlg.create().show();
    }
}
