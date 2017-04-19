
public class CategoryChecker implements YahtzeeConstants {
	
	public boolean checkCategory(int[] rollNumber, int category){
		int[] currentRoll = makeRollNumberFromOneToFive(rollNumber);
		switch (category){		
		case 1: case 2: case 3: case 4: case 5: case 6: case 15:
			return true;
		case 9:
			return isThreeOfaKind(currentRoll);
		case 10: 
			return isFourOfaKind(currentRoll);
		case 11:
			return isFullHouse(currentRoll);
		case 12:
			return isSmallStraight(currentRoll);
		case 13:
			return isLargeStraight(currentRoll);
		case 14:
			return isYathezee(currentRoll);
		default: return false;
		}
	}
	
	private boolean isThreeOfaKind(int[] rollNumber){
		if (isThreeWithFirstDiceAsFirst(rollNumber) || isThreeWithSecondDiceAsFirst(rollNumber) || isThreeWithThirdDiceAsFirst(rollNumber)){
			return true;
		} else return false;
	}



	private boolean isThreeWithFirstDiceAsFirst(int[] rollNumber){
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[3])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[4])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[5])) return true;
		
		if ((rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[4])) return true;
		if ((rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[5])) return true;
		
		if ((rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;
		
		
		return false;
		
	}

	private boolean isThreeWithSecondDiceAsFirst(int[] rollNumber){		
		if ((rollNumber[2] == rollNumber[3]) && (rollNumber[2] == rollNumber[4])) return true;
		if ((rollNumber[2] == rollNumber[3]) && (rollNumber[2] == rollNumber[5])) return true;
		
		if ((rollNumber[2] == rollNumber[4]) && (rollNumber[2] == rollNumber[5])) return true;
		
		return false;
	}
	
	private boolean isThreeWithThirdDiceAsFirst(int[] rollNumber){				
		if ((rollNumber[3] == rollNumber[4]) && (rollNumber[3] == rollNumber[5])) return true;
		
		return false;
	}
	
	private boolean isFourOfaKind(int[] rollNumber){
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[4])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;		
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;
		
		if ((rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;
		
		
		if ((rollNumber[2] == rollNumber[3]) && (rollNumber[2] == rollNumber[4]) && (rollNumber[2] == rollNumber[5])) return true;

		return false;
	}
	
	private boolean isFullHouse(int[] rollNumber){
		if (isThreeWithFirstDiceAsFirstFullHouse(rollNumber) || isThreeWithSecondDiceAsFirstFullHouse(rollNumber) || isThreeWithThirdDiceAsFirstFullHouse(rollNumber)){
			return true;
		} else return false;
	}
	
	
	private boolean isThreeWithFirstDiceAsFirstFullHouse(int[] rollNumber){
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[3]) && (rollNumber[4] == rollNumber[5])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[3] == rollNumber[5])) return true;
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[5]) && (rollNumber[3] == rollNumber[4])) return true;
		
		if ((rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[4]) && (rollNumber[2] == rollNumber[5])) return true;
		if ((rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[5]) && (rollNumber[2] == rollNumber[4])) return true;
		
		if ((rollNumber[1] == rollNumber[4]) && (rollNumber[1] == rollNumber[5]) && (rollNumber[2] == rollNumber[3])) return true;
		
		
		return false;
		
	}

	private boolean isThreeWithSecondDiceAsFirstFullHouse(int[] rollNumber){		
		if ((rollNumber[2] == rollNumber[3]) && (rollNumber[2] == rollNumber[4]) && (rollNumber[1] == rollNumber[5])) return true;
		if ((rollNumber[2] == rollNumber[3]) && (rollNumber[2] == rollNumber[5]) && (rollNumber[1] == rollNumber[4])) return true;
		
		if ((rollNumber[2] == rollNumber[4]) && (rollNumber[2] == rollNumber[5]) && (rollNumber[1] == rollNumber[3])) return true;
		
		return false;
	}
	
	private boolean isThreeWithThirdDiceAsFirstFullHouse(int[] rollNumber){				
		if ((rollNumber[3] == rollNumber[4]) && (rollNumber[3] == rollNumber[5]) && (rollNumber[1] == rollNumber[2])) return true;
		
		return false;
	}
	
	
	private boolean isSmallStraight(int[] rollNumber){
		if (isNumber(1, rollNumber) && isNumber(2, rollNumber) && isNumber(3, rollNumber)
				&& isNumber(4, rollNumber)) return true;
		if (isNumber(2, rollNumber) && isNumber(3, rollNumber) && isNumber(4, rollNumber)
				&& isNumber(5, rollNumber)) return true;
		if (isNumber(3, rollNumber) && isNumber(4, rollNumber) && isNumber(5, rollNumber)
				&& isNumber(6, rollNumber)) return true;
		return false;
	}
	
	private boolean isLargeStraight(int[] rollNumber){
		if (isNumber(1, rollNumber) && isNumber(2, rollNumber) && isNumber(3, rollNumber)
				&& isNumber(4, rollNumber) && isNumber(5, rollNumber)) return true;
		if (isNumber(2, rollNumber) && isNumber(3, rollNumber) && isNumber(4, rollNumber)
				&& isNumber(5, rollNumber) && isNumber(6, rollNumber)) return true;
		return false;
	}
	
	private boolean isNumber(int checkedNumber, int[] rollNumber){
		
		for (int i=1; i<6; i++){
			if (rollNumber[i] == checkedNumber) return true;		
		}
		return false;
	}
	
	
	private boolean isYathezee(int[] rollNumber){
		if ((rollNumber[1] == rollNumber[2]) && (rollNumber[1] == rollNumber[3]) && (rollNumber[1] == rollNumber[4])  && (rollNumber[1] == rollNumber[5])) return true;
		return false;
	}
	
	private int[] makeRollNumberFromOneToFive(int[] roll){
		int[] currentRoll = new int[6];
		currentRoll[0] = 0;
		for (int i = 1; i<6; i++){
			currentRoll[i] = roll[i-1];
		}
		return currentRoll;
	}
}
	
	
	
	