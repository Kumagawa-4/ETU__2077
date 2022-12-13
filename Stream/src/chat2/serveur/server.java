package chat2.serveur;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class server extends Thread {
    private Socket socket;
    private ArrayList<server> threadList;
    private PrintWriter write;

    public server(Socket socket, ArrayList<server> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
             write = new PrintWriter(socket.getOutputStream(),true);
            while(true) {
                String outputString = input.readLine();
                if(outputString.equals("exit")) {
                    break;
                }
                printToALlClients(outputString);
                System.out.println("Server accueil :" + outputString);

            }
        } catch (Exception e) {
            System.out.println("Error" +e.getStackTrace());
        }
    }

    public void printToALlClients(String outputString) {
        for( server sT: threadList) {
            sT.write.println(outputString);
             System.out.println(outputString);      
        }

    }
}

