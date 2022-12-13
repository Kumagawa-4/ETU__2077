
package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.net.*;

public class server {
    public static void main(String[] test) {

        ServerSocket serveur;
        Socket client;
      final  BufferedReader reade;
      final   PrintWriter write;
        Scanner sc = new Scanner(System.in);

        try {
                serveur = new ServerSocket(100);
                client = serveur.accept();
                write = new PrintWriter(client.getOutputStream());
                reade  = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Thread envoi = new Thread(new Runnable() {
                    String msg;

                @Override
                public void run() {
                    while (true) {
                        msg = sc.nextLine();
                        write.println(msg);
                        if (msg == "") {
                            try {
                                client.close();
                                serveur.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        write.flush();
                    }
                }
            });
            envoi.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = reade.readLine();
                        // tant que le client est connecté
                        while (msg != null) {
                            System.out.println("Client : " + msg);
                            msg = reade.readLine();
                        }
                        // sortir de la boucle si le client a déconecté
                        System.out.println("Client déconecté");
                        // fermer le flux et la session socket
                        write.close();
                        client.close();
                        serveur.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

