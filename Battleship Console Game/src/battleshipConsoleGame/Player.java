package battleshipConsoleGame;

import java.util.Random;

public abstract class Player {
	protected char[][] seaGrid;
	protected int[][] shipsLocation;
	protected double numberOfShipsSurvived;
	static Random random = new Random();
	
	public Player() {
		seaGrid = new char[BattleshipConsoleGame.SEA_GRID_LENGTH][BattleshipConsoleGame.SEA_GRID_WIDTH];
		shipsLocation = new int[BattleshipConsoleGame.NUMBER_OF_SHIPS_OWNED_BY_EACH_PLAYER][2];
		initializeSea();
		numberOfShipsSurvived = BattleshipConsoleGame.NUMBER_OF_SHIPS_OWNED_BY_EACH_PLAYER;
	}
	
	private void initializeSea() {
		for (int ithRow = BattleshipConsoleGame.SEA_GRID_STARTING_POINT; ithRow < BattleshipConsoleGame.SEA_GRID_LENGTH; ithRow++) {
			for (int ithColumn = BattleshipConsoleGame.SEA_GRID_STARTING_POINT; ithColumn < BattleshipConsoleGame.SEA_GRID_WIDTH; ithColumn++) {
				seaGrid[ithRow][ithColumn] = BattleshipConsoleGame.SHIP_NOT_OCCUPIED;
			}
		}
	}
	
	public void showSea() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for (int ithRow = BattleshipConsoleGame.SEA_GRID_STARTING_POINT; ithRow < BattleshipConsoleGame.SEA_GRID_LENGTH; ithRow++) {
			System.out.print(ithRow + "|");
			for (int ithColumn = BattleshipConsoleGame.SEA_GRID_STARTING_POINT; ithColumn < BattleshipConsoleGame.SEA_GRID_WIDTH; ithColumn++) {
				System.out.print(seaGrid[ithRow][ithColumn] + "|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void placeShipInSea(int ithShip) {
		int[] shipCoordinateXandY;
		char horizontalOrVerticalShipAngle;
		while(true) {
			shipCoordinateXandY = getRandomCoordinates();
							
			if (thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
				continue;
			} 
			
			horizontalOrVerticalShipAngle = getRandomHorizontalOrVerticalAngle();
			
			if (horizontalOrVerticalShipAngle == 'h' || horizontalOrVerticalShipAngle == 'H') {
				if (!validSeaCoordinate(shipCoordinateXandY[0], shipCoordinateXandY[1] + 1)) {
					continue;
				}
					
				if (thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1] + 1)) {
					continue;
				} 
				
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1] + 1] = BattleshipConsoleGame.SHIP_OCCUPIED;		
				
			} else if (horizontalOrVerticalShipAngle == 'v' || horizontalOrVerticalShipAngle == 'V') {
				if (!validSeaCoordinate(shipCoordinateXandY[0] + 1, shipCoordinateXandY[1])) {
					continue;
				}
				
				if (thisLocationHasShip(shipCoordinateXandY[0] + 1, shipCoordinateXandY[1])) {
					continue;
				} 
				
				seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;
				seaGrid[shipCoordinateXandY[0] + 1][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_OCCUPIED;	
			}	
			return;
		}
	}
	
	public static void showSea(Player player1, Player player2) {
		System.out.println("  Your sea            Opponent's sea   ");
		System.out.println("  0 1 2 3 4 		  0 1 2 3 4 	");
		for (int ithRow = BattleshipConsoleGame.SEA_GRID_STARTING_POINT; ithRow < BattleshipConsoleGame.SEA_GRID_LENGTH; ithRow++) {
			System.out.print(ithRow + "|");
			for (int ithColumn = 0; ithColumn < BattleshipConsoleGame.SEA_GRID_WIDTH; ithColumn++) {
				System.out.print(player1.seaGrid[ithRow][ithColumn] + "|");
			}
			
			System.out.print("		" + ithRow + "|");
			for (int ithColumn = 0; ithColumn < BattleshipConsoleGame.SEA_GRID_WIDTH; ithColumn++) {
				if (player2.seaGrid[ithRow][ithColumn] == BattleshipConsoleGame.SHIP_OCCUPIED)
					System.out.print(" " + "|");
				else
					System.out.print(player2.seaGrid[ithRow][ithColumn] + "|");
			}
			System.out.println();
		}
		System.out.println(player1.getNumberOfShipsSurvived() + " ships survived      " + player2.getNumberOfShipsSurvived() + " ships survived");
		System.out.println();
	}
	
	public double getNumberOfShipsSurvived() {
		return numberOfShipsSurvived;
	}
	
	// check if the location already has a ship
	public boolean thisLocationHasShip(int x, int y) {
		return seaGrid[x][y] == BattleshipConsoleGame.SHIP_OCCUPIED;
	}
	
	protected int[] getRandomCoordinates() {
		final int minX = BattleshipConsoleGame.SEA_GRID_STARTING_POINT;
		final int maxX = BattleshipConsoleGame.SEA_GRID_LENGTH;
		final int minY = BattleshipConsoleGame.SEA_GRID_STARTING_POINT;
		final int maxY = BattleshipConsoleGame.SEA_GRID_WIDTH;
		return new int[] {(random.nextInt(maxX - minX) + minX), (random.nextInt(maxY - minY) + minY)};
	}
	
	private char getRandomHorizontalOrVerticalAngle() {
		return random.nextInt(1) == 0 ? 'h' : 'v';
	}
	
	// check if the coordinates is within the grid
	static boolean validSeaCoordinate(int x, int y) {
		return !(x < BattleshipConsoleGame.SEA_GRID_STARTING_POINT || y < BattleshipConsoleGame.SEA_GRID_STARTING_POINT 
				|| x >= BattleshipConsoleGame.SEA_GRID_LENGTH || y >= BattleshipConsoleGame.SEA_GRID_WIDTH);
	}
		
	public abstract void guessTheOpponentsShips(Player opponentPlayer);
}
