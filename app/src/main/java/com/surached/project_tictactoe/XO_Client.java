package com.surached.project_tictactoe;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class XO_Client extends AsyncTask<Void, Void, Void> {
    private int port;
    private String Ip_Address, message, response;

    public XO_Client(String Ip_Address, int post, String message) {
        this.Ip_Address = Ip_Address;
        this.port = post;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(Ip_Address, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("MSG " + message);
            response = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("send", "onPostExecute: " + response);
    }
}
