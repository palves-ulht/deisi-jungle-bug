package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TestBug {

    @Test
    public void testBug() {

        GameManager gm = new GameManager();
        gm.loadGame(new File("jogo2.txt"));

        System.out.println("Energia do pássaro" + gm.getPlayerInfo(1)[3]);

        // move o pássaro 5 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(5, false).code());
        System.out.println("Energia do pássaro" + gm.getPlayerInfo(1)[3]);

        // move o elefante 2 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(2, false).code());

        // move o pássaro 5 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(5, false).code());
        System.out.println("Energia do pássaro" + gm.getPlayerInfo(1)[3]);

        // move o elefante 2 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(2, false).code());

        // move o pássaro 5 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(5, false).code());
        System.out.println("Energia do pássaro" + gm.getPlayerInfo(1)[3]);

        // move o elefante 2 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(2, false).code());

        // move o pássaro 5 casas - não tem energia
        assertEquals(MovementResultCode.NO_ENERGY, gm.moveCurrentPlayer(5, false).code());

        // move o elefante 2 casas
        assertEquals(MovementResultCode.VALID_MOVEMENT, gm.moveCurrentPlayer(2, false).code());

        // move o pássaro 5 casas - devia dar NO_ENERGY mas dá CAUGHT_FOOD
        assertEquals(MovementResultCode.NO_ENERGY, gm.moveCurrentPlayer(5, false).code());

    }
}
