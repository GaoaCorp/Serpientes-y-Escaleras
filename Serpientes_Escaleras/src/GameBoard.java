import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public GameBoard(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void addSnake(int from, int to) {
        if (from > to) {
            snakes.put(from, to);
        }
    }

    public void addLadder(int from, int to) {
        if (from < to) {
            ladders.put(from, to);
        }
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public int getNextPosition(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        } else if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        return position;
    }
}