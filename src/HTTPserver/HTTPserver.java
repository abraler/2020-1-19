package HTTPserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPserver {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        ExecutorService es=Executors.newFixedThreadPool(10);
        while (true) {
            Socket socket = serverSocket.accept();
            es.execute(new Task(socket));
        }
    }
}
