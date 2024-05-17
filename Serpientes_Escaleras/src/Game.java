import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private GameBoard board;
    private List<Player> players;
    private int currentPlayerIndex;
    private Random random;

    public Game(GameBoard board, int numberOfPlayers, String[] playerNames) {
        this.board = board;
        this.players = new ArrayList<>();
        this.random = new Random();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(playerNames[i]));
        }
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int rollDice() {
        return random.nextInt(6) + 1;
    }

    public boolean movePlayer(int roll) {
        Player player = getCurrentPlayer();
        int newPosition = player.getPosition() + roll;
        if (newPosition > board.getSize() * board.getSize()) {
            return false;
        }
        newPosition = board.getNextPosition(newPosition);
        player.setPosition(newPosition);
        return newPosition == board.getSize() * board.getSize();
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameBoard getBoard() {
        return board;
    }
}
