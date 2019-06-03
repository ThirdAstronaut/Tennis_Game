import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

    private Game game;
    private final Scanner scanner;
    private final PrintStream out;

    public Controller(Game game, InputStream in, PrintStream out) {
        this.game = game;
        scanner = new Scanner(in);
        this.out = out;
    }

    public Controller(Game game) {
        this.game = game;
        scanner = new Scanner(System.in);
        this.out = System.out;
    }


    public static String whoseTurnOutput() {
        return Utils.WHICH_PLAYER_OUTPUT;
    }

    public static String winnerPrompt(Player player) {
        return player.getName() + Utils.HAS_WON_OUTPUT;
    }

    public static String playerDescription(Player player) {
        return player.getName() + Utils.ASSIGNMENT_OUTPUT + player.getId();
    }

    public void start() {
        consoleOutput(createInitialOutput());
        while (!game.isFinished()) {
            displayCurrentState();
            playPoint();
        }
        consoleOutput(winnerPrompt(game.getGameWinner()));
    }

    private void displayCurrentState() {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Player p : game.getPlayers()) {
            counter++;
            Score currentScore = p.getResults().get(game.getId()).get(p.getResults().get(game.getId()).size() - 1);
            if (currentScore != Score.A) {
                stringBuilder.append(p.getResults().get(game.getId()).get(p.getResults().get(game.getId()).size() - 1).getValue());
            } else {
                stringBuilder.append(p.getResults().get(game.getId()).get(p.getResults().get(game.getId()).size() - 1));
            }
            if (counter != game.getPlayers().size())
                stringBuilder.append(Utils.COLON);

        }
        consoleOutput(stringBuilder.toString());

    }

    private void playPoint() {
        int playerNumber = getUserInput();
        int gameId = game.getId();
        int gameNum = game.getPlayerById(playerNumber).getResults().get(gameId).size();

        Score previousScore = game.getPlayerById(playerNumber).getResults().get(gameId).get(gameNum - 1);
        Score newScore;
        if (Score.valueOf(previousScore.name()).getValue() < 40) {
            newScore = previousScore.next();
        } else {
            newScore = handleGameEnding(playerNumber, previousScore);
        }
        game.getPlayerById(playerNumber).getResults().get(game.getId()).add(newScore);
    }

    private Score handleGameEnding(int playerNumber, Score previousScore) {
        List<Player> playerList = game.getPlayers().stream().filter(p -> p.getResults().get(game.getId()).get(p.getResults().get(game.getId()).size() - 1).getValue() >= 40).collect(Collectors.toList());
        if (previousScore.getValue() == 40) {
            long numOfPlayers = playerList.size();
            if (numOfPlayers == 1) {
                assignWinner(playerNumber);
                return previousScore;
            } else {
                boolean wasAdvantage = false;
                for (Player p : playerList) {
                    if (p.getResults().get(game.getId()).get(p.getResults().get(game.getId()).size() - 1).equals(Score.A))
                        wasAdvantage = true;
                    p.getResults().get(game.getId()).set(p.getResults().get(game.getId()).size() - 1, Score.p40);
                }
                if (!wasAdvantage)
                    return Score.A;
                else
                    return Score.p40;
            }
        } else {
            assignWinner(playerNumber);
            return previousScore;
        }
    }

    private void assignWinner(int playerNumber) {
        game.getPlayerById(playerNumber).wonLastGame(true);
        game.setFinished(true);
    }

    private int getUserInput() {
        consoleOutput(whoseTurnOutput());
        int input = scanner.nextInt();

        while (!game.isPresent(input)) {
            consoleOutput(Utils.WRONG_NUMBER_OUTPUT);
            input = scanner.nextInt();
        }
        return input;
    }


    public void consoleOutput(String s) {
        out.println(s);
    }

    private String createInitialOutput() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : game.getPlayers()) {
            stringBuilder.append(playerDescription(player));
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
