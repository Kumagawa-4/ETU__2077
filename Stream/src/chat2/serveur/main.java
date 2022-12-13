package chat2.serveur;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class main {

    public static void main(String[] args) {
        ArrayList<server> threadList = new ArrayList<>();
        try (ServerSocket ServerSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = ServerSocket.accept();
                server serverThread = new server(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getStackTrace());
        }
    }
}