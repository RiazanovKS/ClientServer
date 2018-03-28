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
             BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {


            System.out.println("Новое соединение: "+clientSocket.getInetAddress().toString());
            String string;
            while((string=bufferedReader.readLine())!=null){
                if(string.equalsIgnoreCase("exit")){
                    break;
                }
                System.out.println("Прислал клиент: "+string);
                bufferedWriter.write(string);
                System.out.println("отправлено клиенту"+string);
                outputStream.flush();
            }
            System.out.println("Клиент отключился");
        }
    }
}
