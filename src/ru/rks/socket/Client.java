package ru.rks.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
             String fromUser,fromServer;
             while((fromUser=scan.readLine())!=null){
                 bufferedWriter.write(fromUser);
                 System.out.println("Отправлено серверу "+ fromUser );
                 fromServer= bufferedReader.readLine();
                 System.out.println("Сервер прислал"+ fromServer);
                 if(fromUser.equalsIgnoreCase("exit")){
                     break;
                 }
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}