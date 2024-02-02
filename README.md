**Battleship Console Game Overview:**

This Java program implements a simplified version of the classic Battleship game. It consists of two players, a human player, and a computer player. Each player places a fleet of ships on a 5x5 grid and takes turns guessing the coordinates of the opponent's ships. The game continues until all ships of one player are discovered.

### How to Play:
1. Clone the repository.
2. Compile and run the `BattleshipConsoleGame.java` file.
3. Follow the prompts to place your ships and guess the opponent's ships.

Feel free to explore the code and make improvements. Contributions are welcome!

### Key Features:

1. **Player Classes:**
   - `Player`: An abstract class for both `HumanPlayer` and `ComputerPlayer`.
   - `HumanPlayer`: Handles interactive ship placement and guessing by the human player.
   - `ComputerPlayer`: Implements random ship placement and basic attacking strategy.

2. **Game Flow:**
   - Players take turns placing ships and guessing opponent ship locations.
   - The game continues until all ships of one player are discovered.
   - Displays the state of both players' grids after each turn.

3. **User Interface:**
   - Provides a clear console display with instructions and grid representation.
   - Users input coordinates for ship placement and guessing opponent ships.

4. **Object-Oriented Principles:**
   - **Inheritance:** `Player` class is inherited by specific player types.
   - **Polymorphism:** Different implementations of `guessTheOpponentsShips` for human and computer players.
   - **Abstraction:** Abstract methods in the `Player` class for ship placement and attacking.

5. **Future Improvements:**
   - Enhanced computer player strategies for more challenging gameplay.
   - Additional features or difficulty levels could be introduced.

### Conclusion:

The program offers a concise implementation of Battleship, showcasing core object-oriented principles. It serves as a foundation for further enhancements and a basic yet enjoyable gaming experience in the console.

![image](https://github.com/yy-cc-20/Battleship-Console-Game/assets/65067887/3046bd8c-aa4a-4030-a693-3d2836290e04)

![image](https://github.com/yy-cc-20/Battleship-Console-Game/assets/65067887/2388ef47-5eb3-406e-894e-cb7ba423837c)

![image](https://github.com/yy-cc-20/Battleship-Console-Game/assets/65067887/77a073c8-3d68-408e-818a-96cbb24c7092)

