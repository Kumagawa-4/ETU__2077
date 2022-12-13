package contenu;
import java.io.*;  
import java.net.*;
import java.util.Scanner;

public class client {

public static void main(String[] zero) throws IOException {

File f = new File("../test1.jpg");
if(f.exists())
{
System.out.println("Envoi du fichier "+f.toURI().toURL());
Socket s = new Socket(InetAddress.getLocalHost(),9001);

OutputStream fluxsortie = s.getOutputStream();

long taillefichier =f.length();

System.out.println("Taille : "+ taillefichier);

long nbpassagesuposé=taillefichier / 4096;

System.out.println("Passages supposés : "+nbpassagesuposé);

InputStream in = new BufferedInputStream(new FileInputStream(f));
ByteArrayOutputStream tableaubytes = new ByteArrayOutputStream();
BufferedOutputStream tampon = new BufferedOutputStream(tableaubytes);

int lu = in.read();
int[] aecrire = new int[4096];
int compteur = 0;
long ouonestrendu=0;

while(lu > -1)
{
aecrire[compteur] = lu;
lu = in.read();
compteur++;


if(compteur == 4096)
{
compteur=0;
ouonestrendu++;
for(int x=0;x<4096;x++)
tampon.write(aecrire[x]);

byte[] b=tableaubytes.toByteArray();
tampon.write(b);
tampon.flush();

tableaubytes.reset();
System.out.println("Avancement : "+(float) ouonestrendu/nbpassagesuposé * 100+"%");
}
}


for(int x=0;x<compteur;x++)
tampon.write(aecrire[x]);

tampon.flush();
fluxsortie.write(tableaubytes.toByteArray());
fluxsortie.flush();

System.out.println("Avancement: "+(float) ouonestrendu/nbpassagesuposé * 100+"%");

System.out.println(" finished");
in.close();
tampon.close();
System.out.println("Passages effectués : "+ouonestrendu);
s.close();
}
else
{
System.out.println("Le fichier "+f+" est introuvable");
}

}

}
