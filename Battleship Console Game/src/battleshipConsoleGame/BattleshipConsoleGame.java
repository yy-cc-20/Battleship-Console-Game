package battleshipConsoleGame;
import java.util.Scanner;

/* A Battleship game is a classic two-player strategy game where each player places a fleet of ships on a grid and 
 * takes turns trying to guess the coordinates of the opponent's ships. This implementation assumes a 5x5 grid:
 */

public class BattleshipConsoleGame {
	public static Scanner userInput = new Scanner(System.in);
	
	// Game settings
	public static final int SEA_GRID_STARTING_POINT = 0;
	public static final int SEA_GRID_LENGTH = 5;
	public static final int SEA_GRID_WIDTH = SEA_GRID_LENGTH;
	public static final int NUMBER_OF_SHIPS_OWNED_BY_EACH_PLAYER = 5;
	public static final int SHIP_LENGTH = 2;
	public static final int SHIP_WIDTH = 1;
	public static final int SCREEN_HEIGHT = 30;
	public static final char SHIP_OCCUPIED = 'S';
	public static final char SHIP_ATTACKED_BY_OPPONENT = '!';
	public static final char SHIP_NOT_OCCUPIED = ' ';
	public static final char ATTACKED_AREA = 'X';
	
	public static void main(String[] args) {
		Player humanPlayer = new HumanPlayer();
		Player computerPlayer = new ComputerPlayer();
				
		//showGameName();
		//showHowToPlay();
		
		for (int ithShip = 0; ithShip < BattleshipConsoleGame.NUMBER_OF_SHIPS_OWNED_BY_EACH_PLAYER; ++ithShip) {
			humanPlayer.placeShipInSea(ithShip);
			computerPlayer.placeShipInSea(ithShip);
		}
		
		while(true) {
			showGameName();
			System.out.println("Each player has " + BattleshipConsoleGame.NUMBER_OF_SHIPS_OWNED_BY_EACH_PLAYER + " "
					+ BattleshipConsoleGame.SHIP_LENGTH + "x" + BattleshipConsoleGame.SHIP_WIDTH + " ships.");
			System.out.println();
			Player.showSea(humanPlayer, computerPlayer);
			humanPlayer.guessTheOpponentsShips(computerPlayer);
			if (gameOver(humanPlayer, computerPlayer))
				break;
			
			computerPlayer.guessTheOpponentsShips(humanPlayer);
			if (gameOver(humanPlayer, computerPlayer))
				break;
			waitForUser();
			clearScreen();
		}
		Player.showSea(humanPlayer, computerPlayer);
		userInput.close();
	}
	
	static public void showGameName() {
		System.out.println();
		System.out.println("################################################");
		System.out.println();
		System.out.println("                 BATTLESHIP WAR                 ");
		System.out.println();
		System.out.println("################################################");
		System.out.println();
	}
	
	static public void showHowToPlay() {
		System.out.println("A Battleship game is a classic two-player ");
		System.out.println("strategy game where each player places a fleet ");
		System.out.println("of ships on their grids and takes turns trying ");
		System.out.println("to guess the coordinates of the opponent's ");
		System.out.println("ships. ");
		System.out.println();
	}

	static public void clearScreen() {
		for (int screenLevel = 0; screenLevel < SCREEN_HEIGHT; screenLevel++) {
			System.out.println();
		}
	}
	
	static public void waitForUser() {
		System.out.println("[ENTER]");
		BattleshipConsoleGame.userInput.nextLine();
	}
	
	// game over: if all the ships of one side are discovered
	// show who wins
	static public boolean gameOver(Player humanPlayer, Player computerPlayer) {
		if (computerPlayer.getNumberOfShipsSurvived() == 0) {
			System.out.println("You win! You have found all the opponent's ships.");
			return true;
		} else if (humanPlayer.getNumberOfShipsSurvived() == 0) {
			System.out.println("Computer win! Computer have found all your ships.");
			return true;
		} else 
			return false;	
	}
	
	/* UI design
	 * 
	 
	################################################
	
				    BATTLESHIP WAR
	
	################################################
	
	A Battleship game is a classic two-player 
	strategy game where each player places a fleet 
	of ships on their grids and takes turns trying 
	to guess the coordinates of the opponent's 
	ships. 
	
	Place 5 2X1 ships in your grid:
	 
	  0 1 2 3 4 5 6 7 8 9		  
	0| | | | | | | | | | |		
	1| | |						
	2| | |						
	3| | |
	4| | |
	5| | |
	6| | |
	7| | |
	8| | |
	9| | |
	
	Select coordinate x and y for ship 1 (eg. 0 1)>
	
	Invalid input. Please enter numbers between 0 and 9, and a space between x and y coordinate.
	
	Invalid input. This location already has a ship.
		
	This is a 2x1 ship. Place the ship horizontally or vertically (h/v)?>
	
	Invalid input. Please enter an alphabet h or v.
	
	
	
	################################################
	
				    BATTLESHIP WAR
	
	################################################                
	
	       Your sea                Opponent's sea
	  0 1 2 3 4 5 6 7 8 9		  1	2 3 4 5 6 7 8 9	
	0| | | | | | | | | | |		0| | 
	1| | |						1|
	2| | |						2|
	3| | |
	4| | |
	5| | |
	6| | |
	7| | |
	8| | |
	9| | |
	
	Your turn. Guess the coordinates x and y of the opponent's ships (eg. 0 1)>
	Invalid input. Please enter numbers between 0 and 9, and a space between x and y coordinate.
	You missed. / You found opponent's ship at ([x], [y])!
	
	Computer turn. Computer missed. / Computer turn. Computer found your ship at ([x], [y])!
	
	You win! You have found all the opponent's ships.
	Computer win! Computer have found all your ships.
	
	*/
}
