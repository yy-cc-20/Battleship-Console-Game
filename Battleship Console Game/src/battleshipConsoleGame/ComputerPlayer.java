package battleshipConsoleGame;

import java.util.LinkedList;
import java.util.Queue;

public class ComputerPlayer extends Player {
	private Queue<int[]> locationPendingAttack = new LinkedList<int[]>();
	
	// future improvement: computer strategy to find the ships
	@Override
	public void guessTheOpponentsShips(Player opponentPlayer) {
		int[] shipCoordinateXandY;
		
		System.out.print("Computer turn. ");
		
		if (locationPendingAttack.isEmpty()) {
			while(true) {
				shipCoordinateXandY = getRandomCoordinates();
				if (attackableLocation(shipCoordinateXandY[0], shipCoordinateXandY[1]))
					break;
			}
		}
		else 
			shipCoordinateXandY = locationPendingAttack.poll();
			
		if (opponentPlayer.thisLocationHasShip(shipCoordinateXandY[0], shipCoordinateXandY[1])) {
			opponentPlayer.seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.SHIP_ATTACKED_BY_OPPONENT;
			System.out.println("Computer found your ship at (" + shipCoordinateXandY[0] + "," + shipCoordinateXandY[1] + ")!");
			opponentPlayer.numberOfShipsSurvived -= 1 * 1.0 / BattleshipConsoleGame.SHIP_LENGTH * BattleshipConsoleGame.SHIP_WIDTH;
			
			locationPendingAttack.clear();
			// find the remaining ship body to continue attack it
			if (attackableLocation(shipCoordinateXandY[0] - 1, shipCoordinateXandY[1]))
				locationPendingAttack.add(new int[] {shipCoordinateXandY[0] - 1, shipCoordinateXandY[1]}); // top
			if (attackableLocation(shipCoordinateXandY[0] + 1, shipCoordinateXandY[1]))
				locationPendingAttack.add(new int[] {shipCoordinateXandY[0] + 1, shipCoordinateXandY[1]}); // bottom
			if (attackableLocation(shipCoordinateXandY[0], shipCoordinateXandY[1] - 1))
				locationPendingAttack.add(new int[] {shipCoordinateXandY[0], shipCoordinateXandY[1] - 1}); // left
			if (attackableLocation(shipCoordinateXandY[0], shipCoordinateXandY[1] + 1))
				locationPendingAttack.add(new int[] {shipCoordinateXandY[0], shipCoordinateXandY[1] + 1}); // right
		} else {
			opponentPlayer.seaGrid[shipCoordinateXandY[0]][shipCoordinateXandY[1]] = BattleshipConsoleGame.ATTACKED_AREA;
			System.out.println("Computer missed.");
		}
	}
	
	private boolean attackableLocation(int x, int y) {
		if (!validSeaCoordinate(x, y))
			return false;
		if (seaGrid[x][y] == BattleshipConsoleGame.SHIP_ATTACKED_BY_OPPONENT)
			return false;
		if (seaGrid[x][y] == BattleshipConsoleGame.ATTACKED_AREA)
			return false;
		return true;
	}
}
