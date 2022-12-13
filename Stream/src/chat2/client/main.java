package chat2.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import chat2.client.*;

public class main {
    static String userInput=null;
    public static void main(String[] args) {
        fenetre f = new fenetre();
        try (Socket socket = new Socket(f.c.getIp(), 5000)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String nom = f.c.getPseudo();
            client clientRun = new client(socket);

            new Thread(clientRun).start();

            do {
                if (f.c.getPseudo() != null) {
                    String message = ("(" + nom + ")   " + " message : ");
                    System.out.println(message);
                    userInput = f.zone.getText();
                    output.println(message + " " + userInput);
                    if( userInput.equals("")){
                        Thread.sleep(10000);

                    }else 
                    Thread.sleep(1000);

                    if (userInput.equals("exit")) {
                        break;
                    }
                }

            } while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("" + e.getStackTrace());
        }
    }
    /*
     * System.out.println("Taper ip du server");
     * Scanner sc = new Scanner(System.in);
     * String ip_server= sc.nextLine();
     * try (Socket socket = new Socket(ip_server, 5000)) {
     * PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
     * Scanner scanner = new Scanner(System.in);
     * String userInput;
     * String nom = "null";
     * client clientRun = new client(socket);
     * 
     * new Thread(clientRun).start();
     * 
     * do {
     * if (nom.equals("null")) {
     * System.out.println("Enter un pseudo ");
     * userInput = scanner.nextLine();
     * nom = userInput;
     * output.println(userInput);
     * if (userInput.equals("exit")) {
     * break;
     * }
     * } else {
     * String message = ("("+nom+")   "+" message : ");
     * System.out.println(message);
     * userInput = scanner.nextLine();
     * output.println(message + " " + userInput);
     * if (userInput.equals("exit")) {
     * break;
     * }
     * }
     * 
     * } while (!userInput.equals("exit")) ;
     * scanner.close();
     * sc.close();
     * 
     * } catch (Exception e) {
     * System.out.println("" + e.getStackTrace());
     * }
     * }
     */
}