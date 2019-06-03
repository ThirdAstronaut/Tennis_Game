import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class ControllerTest {


    @Test
    public void whenCalledReturnsConsoleOutputWhoseTurnIsIt() {
        String output = "Please enter the player that wins the point: ";
        assertEquals(output, Controller.whoseTurnOutput());
    }

    @Test
    public void whenCalledReturnsConsoleOutputPlayersDescription() {
        Player player = Mockito.mock(Player.class);
        String partialOutput = " = ";
        assertEquals(player.getName() + partialOutput + player.getId(), Controller.playerDescription(player));
    }

    @Test
    public void whenCalledReturnsConsoleOutputWinnerPrompt() {
        Player player = Mockito.mock(Player.class);
        String partialOutput = " has won the game.";
        assertEquals(player.getName() + partialOutput, Controller.winnerPrompt(player));
    }

    @Test
    public void whenCalledReturnsConsoleOutputInitialText() {
        Game game = new Game(new Player(1, "Test1"), new Player(2, "Test2"));
        StringBuilder output = new StringBuilder();
        for (Player player : game.getPlayers()) {
            output.append(player.getName());
            output.append(" = ");
            output.append(player.getId());
        }
        System.out.println(output.toString());
    }


}
