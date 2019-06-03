import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class GameController {

    @Test
    public void shouldAddPlayerWithUniqueId() {
        Player player1 = Mockito.mock(Player.class);
        when(player1.getId()).thenReturn(1);
        Player player2 = Mockito.mock(Player.class);
        when(player2.getId()).thenReturn(2);
        Game game = new Game();
        assertTrue(game.addPlayer(player1));
        assertTrue(game.addPlayer(player2));

        assertFalse(game.addPlayer(player1));
        assertFalse(game.addPlayer(new Player(1, "TestName")));

    }

    @Test
    public void returnsWhetherPlayerIsPresent() {
        Player player1 = Mockito.mock(Player.class);
        when(player1.getId()).thenReturn(1);
        Player player2 = Mockito.mock(Player.class);
        when(player2.getId()).thenReturn(2);
        Game game = new Game(player1, player2);

        assertTrue(game.isPresent(1));
        assertTrue(game.isPresent(2));
        assertFalse(game.isPresent(3));
    }


    @Test
    public void shouldCreateTwoPlayersGame() {
        Player player1 = Mockito.mock(Player.class);
        when(player1.getId()).thenReturn(1);
        when(player1.getName()).thenReturn("Test1");

        Player player2 = Mockito.mock(Player.class);
        when(player2.getId()).thenReturn(2);
        when(player2.getName()).thenReturn("Test2");


        Game game = new Game(player1, player2);
        assertEquals(game.getPlayers().size(), 2);

        assertEquals(game.getPlayers().size(), 2);


    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingGameShouldThrowException() {
        Player player1 = Mockito.mock(Player.class);
        when(player1.getId()).thenReturn(1);
        new Game(player1, player1);
    }

}
