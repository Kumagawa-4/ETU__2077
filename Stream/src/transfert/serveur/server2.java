package transfert.serveur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server2 {
    public static void main(String[] zero) throws IOException {
        Socket sock = new ServerSocket(9000).accept();
        System.out.println("Nom du fichier");
        Scanner sc = new Scanner(System.in);
        commun.transfert(
                sock.getInputStream(),
                new FileOutputStream("C:/Users/KTM/eclipse-workspace/Stream/src/transfert/"+sc),//destination final
                true);

        sock.close();
    }
}