package battleshipConsoleGame;

import java.util.InputMismatchException;

public class HumanPlayer extends Player {
	/*@Override
	public void placeShipInSea(int ithShip) {
		int[] shipCoordinateXandY;
		char horizontalOrVerticalShipAngle;
		
		while(true) {
			shipCoordinateXandY = getIntCoordinateFromUser("Select coordinate x and y for ship " + (ithShip + 1) + "> ");
			
			// check if the coordinates is within the grid
			if (!validSeaCoordinate(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
				System.out.println("Invalid input. Please enter numbers between 0 and 9, and a space between x and y coordinate.");
				continue;
			}
			
			if (thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
				System.out.println("Invalid input. This location already has a ship.");
				continue;
			} 
			
			// get and validate user input for the ship angle (horizontal or vertical)
			System.out.print("This is a " + BattleshipConsoleGame.SHIP_LENGTH + "x" + BattleshipConsoleGame.SHIP_WIDTH 
					+ " ship. Place the ship horizontally or vertically (h/v)?> ");
			horizontalOrVerticalShipAngle = BattleshipConsoleGame.userInput.nextLine().toCharArray()[0];
			
			if (horizontalOrVerticalShipAngle == 'h' || horizontalOrVerticalShipAngle == 'H') {
				if (!validSeaCoordinate(shipCoordinateXandY[0], shipCoordinateXandY[1] + 1)) {
					System.out.println("Invalid input. This location is outside the sea grid.");
					continue;
				}
					
				if (thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1] + 1)) {
					System.out.println("Invalid input. This location already has a ship.");
					continue;
				} 
				
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1] + 1] = BattleshipConsoleGame.SHIP_OCCUPIED;	
				
			} else if (horizontalOrVerticalShipAngle == 'v' || horizontalOrVerticalShipAngle == 'V') {
				if (!validSeaCoordinate(shipCoordinateXandY[0] + 1, shipCoordinateXandY[1])) {
					System.out.println("Invalid input. This location is outside the sea grid.");
					continue;
				}
				
				if (thisLocationHasShip(shipCoordinateXandY[0] + 1, shipCoordinateXandY[1])) {
					System.out.println("Invalid input. This location already has a ship.");
					continue;
				} 
				
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;
				seaGrid[shipCoordinateXandY[0] + 1][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;
				
			} else {
				System.out.println("Invalid input. Please enter an alphabet h or v.");
				continue;
			}
			
			return;
		}
	}*/

	@Override
	public void guessTheOpponentsShips(Player opponentPlayer) {
		int[] shipCoordinateXandY;
		while(true) {
			shipCoordinateXandY = getIntCoordinateFromUser("Your turn. Guess the coordinates x and y of the opponent's ships (eg. 0 1)> ");
			
			// check if the coordinates is within the grid
			if (!validSeaCoordinate(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
				System.out.println("Invalid input. Please enter numbers between 0 and 9, and a space between x and y coordinate.");
				continue;
			}
			break;
		}
		
		if (opponentPlayer.thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
			opponentPlayer.seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_ATTACKED_BY_OPPONENT;
			System.out.println("You found opponent's ship at (" + shipCoordinateXandY[0] + "," + shipCoordinateXandY[1] + ")!");
			opponentPlayer.numberOfShipsSurvived -= 1 * 1.0 / BattleshipConsoleGame.SHIP_LENGTH * BattleshipConsoleGame.SHIP_WIDTH;
		} else {
			opponentPlayer.seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.ATTACKED_AREA;
			System.out.println("You missed.");
		}
		System.out.println();
	}
	
	private int[] getIntCoordinateFromUser(String prompt) {
		while (true) {
			int shipCoordinateX;
			int shipCoordinateY;
			
			// validate data type int
			try {
				System.out.print(prompt);
				shipCoordinateX = BattleshipConsoleGame.userInput.nextInt();
				shipCoordinateY = BattleshipConsoleGame.userInput.nextInt();
				BattleshipConsoleGame.userInput.nextLine();
			} catch(InputMismatchException e) {
				BattleshipConsoleGame.userInput.nextLine();
				System.out.println("Invalid input. Please enter numbers between 0 and 9, and a space between x and y coordinate.");
				continue;
			}
			
			return new int[] {shipCoordinateX, shipCoordinateY};
		}
	}
}
