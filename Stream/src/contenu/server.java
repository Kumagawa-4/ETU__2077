package contenu;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server {

    public static void main(String[] zero) throws IOException {

        ServerSocket socketserver = new ServerSocket(9001);
        Socket serviceSocket = socketserver.accept();

        int lu;

        InputStream inpute = serviceSocket.getInputStream();

        OutputStream out = new FileOutputStream("../test.jpg");

        BufferedInputStream inBuffer = new BufferedInputStream(inpute);

        BufferedOutputStream outBuffer = new BufferedOutputStream(out);

        lu = inBuffer.read();

        int compteur = 0;

        while (lu > -1) {
            outBuffer.write(lu);
            lu = inBuffer.read();

            compteur++;
        }

        outBuffer.write(lu);

        outBuffer.flush();
        outBuffer.close();
        inBuffer.close();

        out.flush();
        out.close();
        inpute.close();
        serviceSocket.close();

    }
}
