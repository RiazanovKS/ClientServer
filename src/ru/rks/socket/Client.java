package ru.rks.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
             DataInputStream dataInputStream = new DataInputStream(inputStream);
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
             String fromUser,fromServer;
             while(true){
                 System.out.println("Введите строку");
                 fromUser = scan.readLine();
                 dataOutputStream.writeUTF(fromUser);
                 System.out.println("Отправлено серверу "+ fromUser );
                 fromServer = dataInputStream.readUTF();
                 System.out.println("Сервер прислал "+ fromServer);
                 if(fromServer.equalsIgnoreCase("exit")){
                     break;
                 }
             }
            System.out.println("Вы отключились");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}