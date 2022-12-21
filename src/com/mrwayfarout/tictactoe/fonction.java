package com.mrwayfarout.tictactoe;
import java.io.IOException;
public class fonction {
	socket socket = new socket();
	
	public static boolean yourTurn = false;
	public static String[] zone = new String[9];

    public static int errors=0;
	
    
	private int[][] wins = new int[][] { 
		{ 0, 1, 2 }, 
		{ 3, 4, 5 }, { 6, 7, 8 }, 
		{ 0, 3, 6 }, { 1, 4, 7 }, 
		{ 2, 5, 8 }, { 0, 4, 8 }, 
		{ 2, 4, 6 } };
    public  void checkWin() {
		for (int i = 0; i < wins.length; i++) {
			if (	draw.circle) {
				if (zone[wins[i][0]] == "O" && zone[wins[i][1]] == "O" &&zone[wins[i][2]] == "O") {
					draw.firstSpot = wins[i][0];
					draw.secondSpot = wins[i][2];
					draw.won = true;
				}
			} else {
				if (zone[wins[i][0]] == "X" && zone[wins[i][1]] == "X" && zone[wins[i][2]] == "X") {
					draw.firstSpot = wins[i][0];
					draw.secondSpot = wins[i][2];
					draw.won = true;
				}
			}
		}
	}

	public void checkEnemyWin() {
		for (int i = 0; i < wins.length; i++) {
			if (	draw.circle ) {
				if (zone[wins[i][0]] == "X" && zone[wins[i][1]] == "X" && zone[wins[i][2]] == "X") {
					draw.firstSpot = wins[i][0];
					draw.secondSpot = wins[i][2];
					draw.enemyWon=true;
				}
			} else {
				if (zone[wins[i][0]] == "O" &&zone[wins[i][1]] == "O" && zone[wins[i][2]] == "O") {
					draw.firstSpot = wins[i][0];
					draw.secondSpot = wins[i][2];
					draw.enemyWon=true
;
				}
			}
		}
	}

	public void checkForTie() {
		for (int i = 0; i < zone.length; i++) {
			if (zone[i] == null) {
				return;
			}
		}
draw.tie=true;	}
  



	
	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}
	
}
