package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;


import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;

public class TestGameManager {
    GameManager gm = new GameManager();

    @Test
    public void checkPlayerPosition() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"a", "4"};

        String[][] alimentosInfo = new String[1][2];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[3][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        String ids = "[1, 2]";
        Assert.assertEquals(ids, Arrays.toString(gm.getPlayerIds(1)));

    }

    @Test
    public void checkFoodPositionAgua() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"a", "4"};

        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        System.out.println(gm.createInitialJungle(12,speciesInfo,alimentosInfo));
        String food = "[water.png, Agua : + 15U|20% energia, ]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }


    @Test
    public void checkFoodPositionCarne() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"c", "4"};

        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        System.out.println(gm.createInitialJungle(12,speciesInfo,alimentosInfo));
        String food = "[meat.png, Carne : + 50 energia : 0 jogadas, ]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }

    @Test
    public void checkFoodPositionErva() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"e", "4"};

        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        System.out.println(gm.createInitialJungle(12,speciesInfo,alimentosInfo));
        String food = "[grass.png, Erva : +- 20 energia, ]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }
    @Test
    public void checkFoodPositionBanana() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"b", "4"};

        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        System.out.println(gm.createInitialJungle(12,speciesInfo,alimentosInfo));
        String food = "[bananas.png, Bananas : 3 : + 40 energia, ]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }
    @Test
    public void movePlayer() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] alimento2 = {"b", "4"};

        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento2;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(5,true);
        gm.moveCurrentPlayer(3,true);
        gm.moveCurrentPlayer(-2,true);
        gm.moveCurrentPlayer(0,true);
        gm.moveCurrentPlayer(-1,true);

        String food = "[bananas.png, Bananas : 0 : + 40 energia, 2]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }
    @Test
    public void movePlayerToMultiFood() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "Z"};
        String[] especie3 = {"2", "Pedro", "L"};
        String[] especie4 = {"3", "Bia", "L"};

        String[] alimento2 = {"b", "4"};
        String[] alimento1 = {"c", "5"};
        String[] alimento3 = {"m", "6"};


        String[][] alimentosInfo = new String[3][1];

        alimentosInfo[0] = alimento2;
        alimentosInfo[1] = alimento3;
        alimentosInfo[2] = alimento1;


        String[][] speciesInfo = new String[3][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        speciesInfo[2] = especie4;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(5,true);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(4,true);
        //gm.moveCurrentPlayer(4,true);


        String food = "[bananas.png, Bananas : 3 : + 40 energia, ]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(4)));
    }
    @Test
    public void moveElephantToCarne() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};

        String[] alimento1 = {"c", "5"};


        String[][] alimentosInfo = new String[1][1];

        alimentosInfo[0] = alimento1;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(5,false);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(-1,true);
        gm.moveCurrentPlayer(-4,true);
        gm.moveCurrentPlayer(10,false);

        //gm.moveCurrentPlayer(4,true);


        String food = "[meat.png, Carne : + 50 energia : 6 jogadas, 1,2]";
        Assert.assertEquals(food, Arrays.toString(gm.getSquareInfo(5)));
    }

    @Test
    public void getPlayerInfo() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};

        String[] alimento1 = {"c", "5"};


        String[][] alimentosInfo = new String[1][1];

        alimentosInfo[0] = alimento1;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(5,false);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(-1,true);
        gm.moveCurrentPlayer(-4,true);
        gm.moveCurrentPlayer(10,false);

        //gm.moveCurrentPlayer(4,true);


        String food = "[[1, Joao, E, 132, 1..6], [2, Pedro, E, 156, 1..6]]";
        Assert.assertEquals(food, Arrays.deepToString((gm.getPlayersInfo())));
    }

    @Test
    public void getCurrentEnergy() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};

        String[] alimento1 = {"c", "5"};


        String[][] alimentosInfo = new String[1][1];

        alimentosInfo[0] = alimento1;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(5,false);

        //gm.moveCurrentPlayer(4,true),
        gm.getCurrentPlayerEnergyInfo(5);

        String food = "[[1, Joao, E, 164, 1..6], [2, Pedro, E, 160, 1..6]]";
        Assert.assertEquals(food, Arrays.deepToString((gm.getPlayersInfo())));
    }


    @Test
    public void getWinner() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};

        String[] alimento1 = {"c", "5"};


        String[][] alimentosInfo = new String[1][1];

        alimentosInfo[0] = alimento1;


        String[][] speciesInfo = new String[2][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(5,false);

        //gm.moveCurrentPlayer(4,true),
        gm.getWinnerInfo();

        String food = "[[2, Pedro, E, 160, 1..6], [1, Joao, E, 164, 1..6]]";
        Assert.assertEquals(Arrays.deepToString((gm.getPlayersInfo())),food);
    }
    @Test
    public void catchErvaAndAgua() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};
        String[] especie4 = {"3", "Xoao", "Z"};

        String[] alimento1 = {"e", "5"};
        String[] alimento2 = {"a", "6"};


        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento1;
        alimentosInfo[1] = alimento2;


        String[][] speciesInfo = new String[3][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        speciesInfo[2] = especie4;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(5,false);
        gm.moveCurrentPlayer(5,true);

        //gm.moveCurrentPlayer(4,true),

        String food = "[grass.png, Erva : +- 20 energia, 1]";
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(5))));
    }
    @Test
    public void catchCogumelo() {
        GameManager gm = new GameManager();
        String[] especie1 = {"1", "Joao", "E"};
        String[] especie3 = {"2", "Pedro", "E"};
        String[] especie4 = {"3", "Xoao", "Z"};

        String[] alimento1 = {"m", "5"};
        String[] alimento2 = {"a", "6"};


        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento1;
        alimentosInfo[1] = alimento2;


        String[][] speciesInfo = new String[3][3];
        speciesInfo[0] = especie1;
        speciesInfo[1] = especie3;
        speciesInfo[2] = especie4;

        gm.createInitialJungle(12,speciesInfo,alimentosInfo);
        gm.moveCurrentPlayer(4,true);
        gm.moveCurrentPlayer(4,false);
        gm.moveCurrentPlayer(4,true);

        //gm.moveCurrentPlayer(4,true),

        String food = "[blank.png, Vazio, ]";
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(4))));
    }

    @Test
    public void andar() {
        String[][] playersInfo = new String[][]{{"3", "Joao", "E"}, {"2", "Beatriz", "L"}, {"1", "Neves", "Z"}};
        gm.createInitialJungle(48,playersInfo);
        //System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(0,true);
        //System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(0,true);
        //System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(7,true);
        //System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(-2,true);

        String food = "[blank.png, Vazio, ]";

        gm.getGameResults();
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(5))));
    }
    @Test
    public void testeNoEnergy() {
        String[][] playersInfo = new String[][]{{"1", "Joao", "Z"}, {"2", "Beatriz", "L"}};
        gm.createInitialJungle(48,playersInfo);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(10,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(10,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(12,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));

        gm.moveCurrentPlayer(0,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(13,true);
        gm.moveCurrentPlayer(0,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(2,true);
        gm.moveCurrentPlayer(0,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        String food = "[blank.png, Vazio, ]";

        gm.getGameResults();
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(5))));
    }

    @Test
    public void testeCatchFood() {
        String[][] playersInfo = new String[][]{{"1", "Joao", "Z"}, {"2", "Beatriz", "L"}};
        String[] alimento1 = {"e", "5"};
        String[] alimento2 = {"m", "6"};


        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento1;
        alimentosInfo[1] = alimento2;

        gm.createInitialJungle(100,playersInfo,alimentosInfo);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(10,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(5,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(10,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(5,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(10,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(5,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(-30,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(5,false);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));


        String food = "[blank.png, Vazio, ]";

        System.out.println(gm.getGameResults());
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(2))));
    }
    @Test
    public void testeGetWinner() {
        String[][] playersInfo = new String[][]{{"1", "Joao", "Z"}, {"2", "Beatriz", "L"}};
        String[] alimento1 = {"e", "5"};
        String[] alimento2 = {"m", "6"};


        String[][] alimentosInfo = new String[2][1];

        alimentosInfo[0] = alimento1;
        alimentosInfo[1] = alimento2;

        gm.createInitialJungle(6,playersInfo,alimentosInfo);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(0,true);
        System.out.println(Arrays.toString(gm.getCurrentPlayerInfo()));
        gm.moveCurrentPlayer(5,true);
        gm.moveCurrentPlayer(1,true);
        gm.moveCurrentPlayer(4,true);
        System.out.println(Arrays.toString(gm.getWinnerInfo()));
        System.out.println("venceu: " + Arrays.toString(gm.getWinnerInfo()));

        String food = "[blank.png, Vazio, ]";

        System.out.println(gm.getGameResults());
        Assert.assertEquals(food, Arrays.deepToString((gm.getSquareInfo(1))));
    }
}



