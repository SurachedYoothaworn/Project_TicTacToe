package com.surached.project_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private static final int port = 2222;
    Button btn_start,btn_x,btn_o;
    EditText name,ip;
    String UserName;
    TextView infoIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoIP = findViewById(R.id.login_title);
        name = findViewById(R.id.username);
        ip = findViewById(R.id.ip_address);
        infoIP.setText(getIpAddress()+":"+port);

        btn_x = findViewById(R.id.btn_x);
        btn_o = findViewById(R.id.btn_o);

        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_TicTacToe("x");
            }
        });
        btn_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_TicTacToe("o");
            }
        });


    }

    public void openActivity_TicTacToe(String play){
        Intent intent = new Intent(this,ActivityTicTacToe.class);
        /*String Name = name.toString();*/
        String Name = name.getText().toString();
        String ipAddress = ip.getText().toString();
        String Text = Name;
        intent.putExtra("UserName", Text);
        intent.putExtra("ipAddress", ipAddress);
        intent.putExtra("port", port);
        intent.putExtra("play", play);

        startActivity(intent);
    }

    public String getIpAddress(){
        String ip = "";
        try{
            Enumeration<NetworkInterface> netInt = NetworkInterface.getNetworkInterfaces();
            while(netInt.hasMoreElements()){
                NetworkInterface networkInterface = netInt.nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
                while(enumInetAddress.hasMoreElements()){
                    InetAddress inetAddress = enumInetAddress.nextElement();
                    if(inetAddress.isSiteLocalAddress()){
                        ip += "My IP  Address : " + inetAddress.getHostAddress();
                    }
                }
            }

        }catch (SocketException e){

        }
        return ip;
    }

}
