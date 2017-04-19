
public class ScoreSetter implements YahtzeeConstants {
	
	public int setTheScore(int categoryNumber, int[] rollNumber){
		int score = 0;
		switch (categoryNumber){		
		case 1: 
			return checkScoreForRepetitions(1, rollNumber);
		case 2:
			return 2 * checkScoreForRepetitions(2, rollNumber);
		case 3:
			return 3 * checkScoreForRepetitions(3, rollNumber);
		case 4:
			return 4 * checkScoreForRepetitions(4, rollNumber);
		case 5:
			return 5 * checkScoreForRepetitions(5, rollNumber);
		case 6:
			return 6 * checkScoreForRepetitions(6, rollNumber);
		case 9: case 10: case 15:
			return sumOfAllDices(rollNumber);
		case 11:
			return 25;
		case 12:
			return 30;
		case 13:
			return 40;
		case 14:
			return 50;
		default: return score;
		}
	}
	
	private int checkScoreForRepetitions(int rollNumChecked, int[] rollNumber){
		int currentScore = 0;
		for (int i = 0; i < N_DICE; i++){		
			if (rollNumber[i] == rollNumChecked){
				currentScore++;
			}
		}
		return currentScore;
	}
	
	private int sumOfAllDices(int[] rollNumber){
		int currentScore = 0;
		for (int i = 0; i < N_DICE; i++){
			currentScore+=rollNumber[i];
		}
		return currentScore;
	}
	
}
