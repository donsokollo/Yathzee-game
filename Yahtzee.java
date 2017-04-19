/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		resetCategories();
		for (int i = 0; i < 13; i++ ){
			for (int playerNumber = 1; playerNumber <= nPlayers; playerNumber++){
			
				display.printMessage("Player " + playerNumber + ", please make a first draw");
				display.waitForPlayerToClickRoll(playerNumber);
				makeDraws(playerNumber);
				selectCategory(playerNumber);
			}
		}
		for (int playerNumber = 1; playerNumber <= nPlayers; playerNumber++){
			updateScores(playerNumber);
			updateTotal(playerNumber);
		}
		informWhoWon();
		
		/* You fill this in */
	}
	
	private void drawTheDice(int PlayerNumber, int currentRollNumber){
		rollNumber[PlayerNumber][currentRollNumber] = rgen.nextInt(1, 6);
		/*rollNumber[1][0] = 0;*/
	}
	
	private void makeDraws(int playerNumber){

		makeFirstDraw(playerNumber);
		display.printMessage("Player " + playerNumber + "Please make a second draw");
		makeNextDraw(playerNumber);
		display.printMessage("Player " + playerNumber + "Please make a third draw");
		makeNextDraw(playerNumber);
	}
	
	private void makeFirstDraw(int playerNumber){
		for(int i=0; i < N_DICE; i++){
			drawTheDice(playerNumber, i);
		}
		display.displayDice(rollNumber[playerNumber]);
	}
	
	private void makeNextDraw(int playerNumber){
		display.waitForPlayerToSelectDice();
		for(int i=0; i < N_DICE; i++){
			if (display.isDieSelected(i)) drawTheDice(playerNumber, i);
		}
		display.displayDice(rollNumber[playerNumber]);
	}
	
	private void selectCategory(int playerNumber){
		display.printMessage("Please select category of your choice");		
		updateCategory(playerNumber);
		updateTotal(playerNumber);
		
	}
	
	/*to koniecznie trzeba rozbiæ*/
	
	private void updateCategory(int playerNumber){
		
		boolean ProperCategory = false;
		while (!ProperCategory){
			int numberOfCategory = display.waitForPlayerToSelectCategory();
			boolean categoryValid = checkIfApproperiateCategory(numberOfCategory, playerNumber);
			if ( !categoryChosen[playerNumber][numberOfCategory]){
				ProperCategory = true;
				categoryChosen[playerNumber][numberOfCategory] = true;
				if (categoryValid){
					int score = scoreSet.setTheScore(numberOfCategory, rollNumber[playerNumber]);
					display.updateScorecard(numberOfCategory, playerNumber, score);
					scoreCard[playerNumber][numberOfCategory] = score;
				} else {
					display.updateScorecard(numberOfCategory, playerNumber, 0);
				}
				
			} else {
				display.printMessage("Player" + playerNumber + ". You selected a wrong category. "
						+ "Please select a proper one");
			}
		}
	}
	
	private boolean checkIfApproperiateCategory(int numberOfCategory, int playerNumber){
		
		 return catCheck.checkCategory(rollNumber[playerNumber], numberOfCategory);
	}
	
	private void resetCategories(){
		for(int j = 0; j < MAX_PLAYERS; j++){
			for (int i =0; i < N_DICE; i++){
				categoryChosen[j][i] = false;
			}
		}
	}
	
	private void updateScores(int player){
		updateUpperScore(player);
		updateBonus(player);
		updateLowerScore(player);
	}
	
	private void updateUpperScore(int player){
		int sumOfUpper = 0;
		for (int i = 1; i < 7; i++){
			sumOfUpper+= scoreCard[player][i];
		}
		display.updateScorecard(7, player, sumOfUpper);
		scoreCard[player][7] = sumOfUpper;
	}
	
	private void updateTotal(int player){
		int sum = 0;
		for (int i = 1; i < 7; i++){
			sum+= scoreCard[player][i];
		}
		for (int i = 8; i < 16; i++){
			sum+= scoreCard[player][i];
		}
		display.updateScorecard(17, player, sum);
		scoreCard[player][17] = sum;
	}
	
	private void updateLowerScore(int player){
		int sumOfLower = 0;
		
		for (int i = 9; i < 16; i++){
			sumOfLower+= scoreCard[player][i];
		}
		display.updateScorecard(16, player, sumOfLower);
		scoreCard[player][16] = sumOfLower;
	}
	
	private void updateBonus(int player){
		int sumOfUpper = 0;
		for (int i = 1; i < 7; i++){
			sumOfUpper+= scoreCard[player][i];
		}
		if (sumOfUpper >= 63) display.updateScorecard(8, player, 35);
	}
	
	private void informWhoWon(){
		int[] scores = {scoreCard[1][17], scoreCard[2][17],scoreCard[3][17],scoreCard[4][17]};
		int maxScore[] = maxScore(scores);
		if (maxScore[1] == 1){
			display.printMessage("It's a draw!");
		} else if (scoreCard[1][17] == maxScore[0] ){
			display.printMessage("Player 1 won with score " + scoreCard[1][17]);
		} else if (scoreCard[2][17] == maxScore[0]){
			display.printMessage("Player 2 won with score " + scoreCard[2][17]);
		} else if (scoreCard[3][17] == maxScore[0]){
			display.printMessage("Player 3 won with score " + scoreCard[3][17]);
		} else if (scoreCard[4][17] == maxScore[0]){
			display.printMessage("Player 4 won with score " + scoreCard[4][17]);
		}
		
	}
	
	public static int[] maxScore(int[] n) {
	    int i = 0;
	    int timesOfMax = 0;
	    int[] max = {n[i], 0};
	    while (++i < n.length){
	        if (n[i] > max[0]){
	            max[0] = n[i];
	        }
	    }
	    i=0;
	    while (++i < n.length){
	        if (n[i] == max[0]){
	        	timesOfMax++;
	        }
	    }
	    if (timesOfMax > 1) max[1] = 1;
	    return max;
	}
	
	
		
/* Private instance variables */
	private boolean[][] categoryChosen = new boolean[MAX_PLAYERS+1][16];
	private int nPlayers;
	private int[][] rollNumber = new int[MAX_PLAYERS+1][N_DICE];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private YahtzeeMagicStub cheatCheckCategory;
	private ScoreSetter scoreSet = new ScoreSetter();
	private int[][] scoreCard = new int[MAX_PLAYERS+1][N_CATEGORIES+1];
	private CategoryChecker catCheck = new CategoryChecker();
}
