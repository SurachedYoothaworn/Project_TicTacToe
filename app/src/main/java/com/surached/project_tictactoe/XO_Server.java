package com.surached.project_tictactoe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hanchai on 09-May-18.
 */

public class XO_Server {
    private int serverPort;
    ActivityTicTacToe ticTacToe;

    public XO_Server(ActivityTicTacToe ticTacToe, int serverPort) {
        this.serverPort = serverPort;
        this.ticTacToe = ticTacToe;
        ServerWorker serverWorker = new ServerWorker();
        serverWorker.start();
    }

    private class ServerWorker extends Thread {
        int count = 0;

        @Override
        public void run() {

            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                while (true) {
                    final Socket clientSocket = serverSocket.accept();
                    count++;
                    ticTacToe.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           /* mainActivity.txtMonitor.append("Client#"+ count
                                    + clientSocket.getInetAddress()+":"
                                    +clientSocket.getPort()+"\n");*/
                        }
                    });
                    ServerCommunication comm = new ServerCommunication(clientSocket);
                    comm.start();
                }
            } catch (IOException e) {

            }
        }
    }

    private class ServerCommunication extends Thread {
        Socket clientSocket;

        public ServerCommunication(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                final String msg = input.readUTF();
                output.writeUTF("ACK 250 " + ticTacToe.ipAddress);

                ticTacToe.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String msgs[] = msg.split(" ");
                        if (msgs[0].equals("MSG")) {
                            String datas[] = msgs[1].split("-");
                            setResponseAction(datas[0], datas[1], msgs[2]);
                            ticTacToe.nameAnemy.setText(msgs[2]);
                        }
                    }
                });

            } catch (IOException e) {

            }

        }
    }

    private void setResponseAction(String btn, String play, String user) {
        switch (btn) {
            case "btn_01":
                ticTacToe.board.set(0, play);
                ticTacToe.setResponseAction(ticTacToe.btn_01, play);
                break;
            case "btn_02":
                ticTacToe.board.set(1, play);
                ticTacToe.setResponseAction(ticTacToe.btn_02, play);
                break;
            case "btn_03":
                ticTacToe.board.set(2, play);
                ticTacToe.setResponseAction(ticTacToe.btn_03, play);
                break;
            case "btn_04":
                ticTacToe.board.set(3, play);
                ticTacToe.setResponseAction(ticTacToe.btn_04, play);
                break;
            case "btn_05":
                ticTacToe.board.set(4, play);
                ticTacToe.setResponseAction(ticTacToe.btn_05, play);
                break;
            case "btn_06":
                ticTacToe.board.set(5, play);
                ticTacToe.setResponseAction(ticTacToe.btn_06, play);
                break;
            case "btn_07":
                ticTacToe.board.set(6, play);
                ticTacToe.setResponseAction(ticTacToe.btn_07, play);
                break;
            case "btn_08":
                ticTacToe.board.set(7, play);
                ticTacToe.setResponseAction(ticTacToe.btn_08, play);
                break;
            case "btn_09":
                ticTacToe.board.set(8, play);
                ticTacToe.setResponseAction(ticTacToe.btn_09, play);
                break;
        }
        ticTacToe.checkWinner(play,user);
    }

    public int getPort() {
        return serverPort;
    }

}
