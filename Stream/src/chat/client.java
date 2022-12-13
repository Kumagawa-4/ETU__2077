package chat;
import java.io.*;  
import java.net.*;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		 BufferedReader read;
		 Socket client;
		 PrintWriter write;
		 Scanner sc = new Scanner(System.in);
		 try {
		 /*
		 * les informations du serveur ( port et adresse IP ou nom d'hote
		 * 127.0.0.1 est l'adresse local de la machine
		 * 192.168.88.18 Mialy
		 * 192.168.20.253
		 */
		 client = new Socket("localhost",100);
		 
		 write = new PrintWriter(client.getOutputStream());
		 read = new BufferedReader(new InputStreamReader(client.getInputStream()));
		 
		Thread envoyer = new Thread(new Runnable() {
		 String msg;
		 @Override
		 public void run() {
		    while(true){
		 msg = sc.nextLine();
		        write.println(msg);
		        write.flush();
                if(msg==""){
                    try {
                        client.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                 }
		    }
		 }
		 });
		 envoyer.start();
		 
		 Thread recevoir = new Thread(new Runnable() {
		 String msg;
		 @Override
		 public void run() {
		 try {
		 msg = read.readLine();
		 while(msg!=null){
		 System.out.println("Admin : "+msg);
		 msg = read.readLine();
		 }
		 System.out.println("Admin déconecté");
		 write.close();
		 client.close();
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


