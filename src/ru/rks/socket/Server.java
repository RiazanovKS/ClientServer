package ru.rks.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер ждет клиента...");
        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             DataInputStream dataInputStream = new DataInputStream(inputStream);
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {


            System.out.println("Новое соединение: "+clientSocket.getInetAddress().toString());
            String string;
            while(true){
                string = dataInputStream.readUTF();
                System.out.println("Прислал клиент: "+string);
                dataOutputStream.writeUTF(string);
                System.out.println("отправлено клиенту"+string);
                if(string.equalsIgnoreCase("exit")){
                    break;
                }
                outputStream.flush();
            }
            System.out.println("Клиент отключился");
        }
    }
}
