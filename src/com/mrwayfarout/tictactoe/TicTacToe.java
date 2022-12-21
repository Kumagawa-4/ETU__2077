package com.mrwayfarout.tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class TicTacToe implements Runnable {

	private Scanner scanner = new Scanner(System.in);
	private JFrame frame;

	private Thread thread;

	private DataOutputStream dos;

	private ServerSocket serverSocket;
	Painter painter;
	private static final long serialVersionUID = 1L;


	socket socket= new socket();
	draw dessin= new draw();
	fonction f= new fonction();
	public TicTacToe() {
		System.out.println("Please input the IP: ");
		socket.setIp(scanner.nextLine());
		System.out.println("Please input the port: ");
		socket.setPort( scanner.nextInt());
		while (socket.getPort() < 1 || socket.getPort() > 65535) {
			System.out.println("The port you entered was invalid, please input another port: ");
			socket.setPort( scanner.nextInt());
		}
	   dessin.loadImages();
		painter = new Painter();
		painter.setPreferredSize(new Dimension(dessin.getWIDTH(), dessin.getHEIGHT()));

		if (!socket.connect()) socket.initializeServer();

		frame = new JFrame();
		frame.setTitle("Tic-Tac-Toe");
		frame.setContentPane(painter);
		frame.setSize(dessin.getWIDTH(), dessin.getHEIGHT());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		thread = new Thread(this, "TicTacToe");
		thread.start();
	}

	public void run() {
		while (true) {
			tick();
			painter.repaint();

			if (!dessin.isCircle() && !dessin.isAccepted()) {
				socket.listenForServerRequest();
			}

		}
	}
	public void tick() {

		if (fonction.errors >= 10) {draw.oppose=true ;};
	
		if (!fonction.yourTurn && !draw.oppose) {
			try {
				int space = socket.getDis().readInt();
				if (	draw.circle) fonction.zone[space] = "X";
				else fonction.zone[space] = "O";
				f.checkEnemyWin();
				f.checkForTie();
				fonction.yourTurn=true;
			} catch (IOException e) {
				e.printStackTrace();
				fonction.errors++;
			}
		}
	}
	private class Painter extends JPanel implements MouseListener {

		public Painter() {
			setFocusable(true);
			requestFocus();
			setBackground(Color.WHITE);
			addMouseListener(this);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			dessin.render(g);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (dessin.isAccepted()) {
				if (fonction.yourTurn && !dessin.isOppose() && !draw.won  && !dessin.isEnemyWon()) {
					int x = e.getX() / dessin.getLengthOfSpace() ;
					int y = e.getY() /  dessin.getLengthOfSpace();
					y *= 3;
					int position = x + y;

					if (dessin.getSpaces()[position] == null) {
						if (!dessin.isCircle()) dessin.getSpaces()[position] = "X";
						else dessin.getSpaces()[position] = "O";
						fonction.yourTurn=false;
						repaint();
						Toolkit.getDefaultToolkit().sync();

						try {
							dos.writeInt(position);
							dos.flush();
						} catch (IOException e1) {
							f.errors++;
							e1.printStackTrace();
						}

						System.out.println("DATA WAS SENT");
						f.checkWin();
						f.checkForTie();

					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	
	}

  
	

	



}
