import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private List<Player> players;
    private boolean finished;
    private static final AtomicInteger idProvider = new AtomicInteger(0);
    private final Integer id;

    public Game(List<Player> players) {
        if (checkUniqueIds(players))
            this.players = players;
        finished = false;
        id = idProvider.getAndIncrement();
        initGameForPlayers();
    }

    public Game(Player... players) {
        this.players = new ArrayList<>();
        for (Player player : players) {
            if (!addPlayer(player)) {
                throw new IllegalArgumentException();
            }
        }
        finished = false;
        id = idProvider.getAndIncrement();
        initGameForPlayers();

    }

    private void initGameForPlayers() {
        for (Player p : players) {
            List<Score> gameScores = new ArrayList<>();
            gameScores.add(Score.p0);
            p.getResults().put(id, gameScores);
        }
    }

    public boolean checkUniqueIds(List<Player> players) {
        Set<Integer> ids = new HashSet<>();
        for (Player player : players) {
            if (!ids.add(player.getId())) {
                throw new IllegalArgumentException();
            }
        }
        return true;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public Player getPlayerById(int id) {
        return players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }


    public boolean isPresent(int id) {
        return players.stream().anyMatch(p -> p.getId() == id);
    }

    public boolean addPlayer(Player player) {
        if (!isPresent(player.getId()))
            return players.add(player);
        return false;
    }


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Player getGameWinner() {
        return players.stream().filter(Player::wonLastGame).findFirst().orElse(null);
    }

    public int getId() {
        return id;
    }
}
