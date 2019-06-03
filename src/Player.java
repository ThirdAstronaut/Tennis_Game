import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private int id;
    private String name;
    private Map<Integer, List<Score>> results;
    private boolean wonLastGame;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        results = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, List<Score>> getResults() {
        return results;
    }

    public void wonLastGame(boolean wonLastGame) {
        this.wonLastGame = wonLastGame;
    }

    public boolean wonLastGame() {
        return wonLastGame;
    }
}
