package com.mrwayfarout.tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class draw {
	private BufferedImage board;
	private BufferedImage redX;
	private BufferedImage blueX;
	private BufferedImage redCircle;
	private BufferedImage blueCircle;
	public BufferedImage getBoard() {
		return board;
	}

	public void setBoard(BufferedImage board) {
		this.board = board;
	}

	public BufferedImage getRedX() {
		return redX;
	}

	public void setRedX(BufferedImage redX) {
		this.redX = redX;
	}

	public BufferedImage getBlueX() {
		return blueX;
	}

	public void setBlueX(BufferedImage blueX) {
		this.blueX = blueX;
	}

	public BufferedImage getRedCircle() {
		return redCircle;
	}

	public void setRedCircle(BufferedImage redCircle) {
		this.redCircle = redCircle;
	}

	public BufferedImage getBlueCircle() {
		return blueCircle;
	}

	public void setBlueCircle(BufferedImage blueCircle) {
		this.blueCircle = blueCircle;
	}
	public static boolean circle = true;
	public static int firstSpot = -1;
	public static int secondSpot = -1;
	public static  boolean accepted = false;
	
	public static boolean oppose = false;

	public static boolean enemyWon = false;
	
	static boolean tie = false;
	static boolean won = false;

	private Font font = new Font("Verdana", Font.ITALIC, 32);
	private Font smallerFont = new Font("Verdana",  Font.ITALIC ,20);
	private Font largerFont = new Font("Verdana", Font.ITALIC, 50);

	private String waitingString = "En attent";
	
	private String communique = "En attent";

	private String wonString = "Tu a gagner!";
	private String enemyWonString = "AD a gagner!";
	private String tieString = "Game ended in a tie.";

	private String[] spaces = new String[9];

	private int lengthOfSpace = 160;
	

	private final int WIDTH = 506;

	private final int HEIGHT = 527;
	void render(Graphics g) {
		g.drawImage(board, 0, 0, null);
		if (oppose) {
			g.setColor(Color.RED);
			g.setFont(smallerFont);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(communique);
			g.drawString(communique, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
			return;
		}

		if (accepted) {
			for (int i = 0; i < spaces.length; i++) {
				if (spaces[i] != null) {
					if (spaces[i].equals("X")) {
						if (circle) {
							g.drawImage(redX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						} else {
							g.drawImage(blueX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						}
					} else if (spaces[i].equals("O")) {
						if (circle) {
							g.drawImage(blueCircle, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						} else {
							g.drawImage(redCircle, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						}
					}
				}
			}
			if (won || enemyWon) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(10));
				g.setColor(Color.BLACK);
				g.drawLine(firstSpot % 3 * lengthOfSpace + 10 * firstSpot % 3 + lengthOfSpace / 2, (int) (firstSpot/ 3) * lengthOfSpace + 10 * (int) (firstSpot / 3) + lengthOfSpace / 2, secondSpot % 3 * lengthOfSpace + 10 * secondSpot % 3 + lengthOfSpace / 2, (int) (secondSpot / 3) * lengthOfSpace + 10 * (int) (secondSpot / 3) + lengthOfSpace / 2);

				g.setColor(Color.RED);
				g.setFont(largerFont);
				if (won) {
					int stringWidth = g2.getFontMetrics().stringWidth(wonString);
					g.drawString(wonString, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
				} else if (enemyWon) {
					int stringWidth = g2.getFontMetrics().stringWidth(enemyWonString);
					g.drawString(enemyWonString, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
				}
			}
			if (tie) {
				Graphics2D g2 = (Graphics2D) g;
				g.setColor(Color.BLACK);
				g.setFont(largerFont);
				int stringWidth = g2.getFontMetrics().stringWidth(tieString);
				g.drawString(tieString, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
			}
		} else {
			g.setColor(Color.RED);
			g.setFont(font);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(waitingString);
			g.drawString(waitingString, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
		}

	}
	public void loadImages() {
		try {
			BufferedImage terrain = ImageIO.read(getClass().getResourceAsStream("terrain.png"));
			BufferedImage girl = ImageIO.read(getClass().getResource("red.png"));
			BufferedImage gir2 = ImageIO.read(getClass().getResource("crane.png"));
			BufferedImage crane = ImageIO.read(getClass().getResource("crane.png"));
			BufferedImage crane2 = ImageIO.read(getClass().getResource("red.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
 
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public boolean isCircle() {
		return circle;
	}
	public void setCircle(boolean circle) {
		this.circle = circle;
	}
	public int getWIDTH() {
		return WIDTH;
	}
	public int getHEIGHT() {
		return HEIGHT;
	}
	public int getLengthOfSpace() {
		return lengthOfSpace;
	}
	public void setLengthOfSpace(int lengthOfSpace) {
		this.lengthOfSpace = lengthOfSpace;
	}
	public String[] getSpaces() {
		return spaces;
	}
	public void setSpaces(String[] spaces) {
		this.spaces = spaces;
	}
	public String getCommunique() {
		return communique;
	}
	public void setCommunique(String communique) {
		this.communique = communique;
	}
	public boolean isOppose() {
		return oppose;
	}
	public void setOppose(boolean oppose) {
		this.oppose = oppose;
	}
	public boolean isEnemyWon() {
		return enemyWon;
	}
	public void setEnemyWon(boolean enemyWon) {
		this.enemyWon = enemyWon;
	}
	public boolean isTie() {
		return tie;
	}
	public void setTie(boolean tie) {
		this.tie = tie;
	}
}
