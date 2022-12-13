package transfert.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import transfert.serveur.commun;

public class client2 {
    public static void main(String[] zero) throws IOException {

        Socket sock = new Socket("localhost", 9000);
        /* InetAddress.getLocalHost() */
        commun.transfert(
                new FileInputStream("C:/Users/KTM/eclipse-workspace/Stream/src/transfert/test1.jpg"),
                sock.getOutputStream(),
                true);

        sock.close();
    }
}
