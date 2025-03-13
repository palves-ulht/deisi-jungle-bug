package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    protected int nrTurnos;
    protected ArrayList<Jogador> jogadores = new ArrayList<>();
    protected ArrayList<Cogumelo> cogumelos = new ArrayList<>();
    protected ArrayList<Banana> bananas = new ArrayList<>();
    protected int nrTurnosTotais = 1;
    protected int tamanhoDoBoard;
    protected boolean vencedor = false;
    protected boolean venceuDistanciaEntreOs2Jogadores = false;
    protected boolean venceuSegundoJogador = false;


    ArrayList<Alimento> alimentos = new ArrayList<>();

    public GameManager() {
    }

    public String[][] getSpecies() {
        String[] especie1 = {"E", "Elefante", "elephant.png", "180", "4", "10", "1..6"};
        String[] especie2 = {"P", "Passaro", "bird.png", "70", "4", "50", "5..6"};
        String[] especie3 = {"Z", "Tarzan", "tarzan.png", "70", "2", "20", "1..6"};
        String[] especie4 = {"L", "Leao", "lion.png", "80", "2", "10", "4..6"};
        String[] especie5 = {"T", "Tartaruga", "turtle.png", "150", "1", "5", "1..3"};

        String[][] speciesInfo = new String[5][3];

        speciesInfo[0] = especie1;
        speciesInfo[1] = especie2;
        speciesInfo[2] = especie3;
        speciesInfo[3] = especie4;
        speciesInfo[4] = especie5;


        return speciesInfo;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {
        alimentos.clear();
        bananas.clear();
        cogumelos.clear();
        tamanhoDoBoard = jungleSize;
        nrTurnosTotais = 0;
        createInitialJungle(jungleSize, playersInfo);
        ArrayList<Integer> ids = new ArrayList<>();
        for (String[] idsJogadores : playersInfo) {
            if (idsJogadores[0] != null && idsJogadores[0].matches("[0-9]+")) {
                ids.add(Integer.valueOf(idsJogadores[0]));}}
        for (String[] x : playersInfo) {
            if (jogadores.size() != 0) {//verificar se é repetido
                for (int i = 0; i < ids.size(); i++) {
                    if (Collections.frequency(ids, ids.get(i)) > 1 || ids.get(i) <= 0) {
                        return new InitializationError("Há ids repetidos");
                    }
                }
            }
            if (!(x[0] != null && x[0].matches("[0-9]+"))) { //verifica se o id é numerico
                return new InitializationError("Id invalido");}
            if (x[1] == null || x[1].isEmpty()) {//verificar se está vazio e escreve o nome
                return new InitializationError("Já existe");}
            String[][] especiesMatriz = getSpecies();//mete o id da especie e o nome da especie
            boolean controle = true;
            for (String[] e : especiesMatriz) {
                if (e[0].equals(x[2])) { //compara o id do getSpecies
                    controle = false;break;}}
            if (controle) {return new InitializationError("Especies com ids repetidos");}}
        for (String[] x : foodsInfo) {
            Cogumelo alimento = new Cogumelo();
            Banana banana = new Banana();
            String[][] comidaMatriz = getFoodTypes();
            boolean controle = true;
            if (jungleSize < playersInfo.length * 2 || (playersInfo.length > 4 || playersInfo.length < 2)) {return new InitializationError("Fora dos limites");}
            for (String[] e : comidaMatriz) {
                if (e[0].equals(x[0]) && x[0].matches("[a-zA-Z]+")) { //compara o id do getFoodType
                    alimento.setId(x[0]);
                    banana.setId(x[0]);
                    if (Objects.equals(x[0], "m")) {
                        int saveCogumelos = getRandomNumber(10, 50);
                        alimento.setnCogumelos(saveCogumelos);
                    }
                    if (Objects.equals(x[0], "b")) {
                        banana.setNrBananasNoCacho(3);
                    }controle = false;break;}}
            if (controle) {return new InitializationError("Ids invalidos");}
            if (x[1].matches("[0-9]+")) {
                if (Integer.parseInt(x[1]) >= tamanhoDoBoard || Integer.parseInt(x[1]) <= 1) {
                    return new InitializationError("Alimentos fora dos limites");
                }
            } else {return new InitializationError("A posicão devia de ser um numero inteiro");}
            alimento.setPosicao(Integer.parseInt(x[1]));
            banana.setPosicao(Integer.parseInt(x[1]));
            alimentos.add(alimento);cogumelos.add(alimento);bananas.add(banana);
        }return null;}

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {
        jogadores.clear();
        tamanhoDoBoard = jungleSize;
        int turnos = 1;nrTurnosTotais = 0;nrTurnos = 1;
        ArrayList<Integer> ids = new ArrayList<>();
        for (String[] idsJogadores : playersInfo) {
            if (idsJogadores[0] != null && idsJogadores[0].matches("[0-9]+")) {
                ids.add(Integer.valueOf(idsJogadores[0]));}}
        if (jungleSize < playersInfo.length * 2 || (playersInfo.length > 4 || playersInfo.length < 2)) {
            return new InitializationError("Fora dos limites");}
        for (String[] x : playersInfo) {
            Jogador jogador = new Jogador();
            Especie especies = new Especie();
            if (jogadores.size() != 0) {//verificar se é repetido
                for (int i = 0; i < ids.size(); i++) {
                    if (Collections.frequency(ids, ids.get(i)) > 1 || ids.get(i) <= 0) {
                        return new InitializationError("Há ids repetidos");}}}
            if (x[0] != null && x[0].matches("[0-9]+")) { //verifica se o id é numerico
                jogador.setId(Integer.parseInt(x[0])); //mete o id
            } else {return new InitializationError("Id invalido");}
            if (x[1] == null || x[1].isEmpty()) {//verificar se está vazio e escreve o nome
                return new InitializationError("Já existe");}
            jogador.setNome(x[1]); //mete o nome;
            String[][] especiesMatriz = getSpecies();
            boolean controle = true;
            for (String[] e : especiesMatriz) {//mete o id da especie e o nome da especie
                if (e[0].equals(x[2])) { //compara o id do getSpecies
                    especies.setId(x[2]);
                    especies.setNome(e[1]);
                    especies.setConsumoEnergia(Integer.parseInt(e[4]));
                    especies.setGanhoEnergia(Integer.parseInt(e[5]));
                    controle = false;
                    break;}}
            for (String[] e : especiesMatriz) {
                if (e[0].equals(x[2])) {
                    jogador.setEnergia(Integer.parseInt(e[3]));break;}}
            if (controle) {return new InitializationError("Especies com ids repetidos"); }
            for (String[] e : especiesMatriz) {
                ArrayList<Integer> numeroDados = new ArrayList<>();
                if (e[0].equals(x[2])) {
                    char[] vowelArray = e[6].toCharArray();
                    int inicio = vowelArray[0] - 48;
                    int finalArray = vowelArray[3] - 48;
                    for (int u = inicio; u <= finalArray; u++) {
                        numeroDados.add(u);}
                    jogador.setVelocidade(numeroDados);break;}}
            jogador.setEspecies(especies);
            jogador.setPosicao(1); //mete a posicao incial na posicao do board 1
            jogadores.add(jogador); //meter para dentro do array
            jogadores.sort(Comparator.comparing(Jogador::getId));
        }
        for (Jogador jogador : jogadores) {
            jogador.setTurnoJogador(turnos);turnos++;}
        return null;
    }

    public int[] getPlayerIds(int squareNr) {
        ArrayList<Integer> idJogadoresList = new ArrayList<>();

        if (squareNr < 1 || squareNr > tamanhoDoBoard) {
            return new int[0];
        }

        for (Jogador e : jogadores) {
            if (e.getPosicao() == squareNr) {
                idJogadoresList.add(e.getId());
            }
        }

        int[] idJogadores = new int[idJogadoresList.size()];
        for (int i = 0; i < idJogadores.length; i++) {
            idJogadores[i] = idJogadoresList.get(i); // a posicao da lista dos ids, vamos buscar a posicao do id do jogadorList
        }
        return idJogadores;
    }

    public String[] getSquareInfo(int squareNr) {
        ArrayList<String> listaIdJogadores = new ArrayList<>();
        String[] posicao = new String[3];
        if (squareNr < 1 || squareNr > tamanhoDoBoard) {return null;}
        if (squareNr == tamanhoDoBoard) { //verifica se o square é a ultima posicao se for mete a foto
            posicao[0] = "finish.png";
            posicao[1] = "Meta";
        } else {
            posicao[0] = "blank.png";
            posicao[1] = "Vazio";
        }
        for (Alimento e : alimentos) {
            if (squareNr == e.getPosicao()) {
                if (Objects.equals(e.getId(), "e")) {
                    posicao[0] = "grass.png";
                    posicao[1] = "Erva : +- 20 energia";
                }
                if (Objects.equals(e.getId(), "a")) {
                    posicao[0] = "water.png";
                    posicao[1] = "Agua : + 15U|20% energia";
                }
                for (Banana banana : bananas) {
                    if (banana.getPosicao() == squareNr) {
                        if (Objects.equals(e.getId(), "b")) {
                            posicao[0] = "bananas.png";
                            posicao[1] = "Bananas : " + banana.getNrBananasNoCacho() + " : + 40 energia";
                        }
                    }
                }
                if (Objects.equals(e.getId(), "c")) {
                    posicao[0] = "meat.png";
                    if (nrTurnosTotais > 12) {
                        posicao[1] = "Carne toxica";
                    } else {
                        posicao[1] = "Carne : + 50 energia : " + nrTurnosTotais + " jogadas";
                    }
                }
                for (Cogumelo cogumelo : cogumelos) {
                    if (cogumelo.getPosicao() == squareNr) {
                        if (Objects.equals(e.getId(), "m")) {
                            posicao[0] = "mushroom.png";
                            posicao[1] = "Cogumelo Magico : +- " + cogumelo.getnCogumelos() + "% energia";
                        }
                    }
                }
            }
        }
        for (Jogador e : jogadores) {
            if (squareNr == e.getPosicao()) {
                listaIdJogadores.add(String.valueOf(e.getId()));
            }
            String result = String.valueOf(listaIdJogadores);
            result = result.replace("[", ""); //para retirar o [
            result = result.replace("]", ""); //para retirar o ]
            result = result.replace(" ", ""); //para retirar o " "
            posicao[2] = result;
        }
        return posicao;
    }

    public String[] getPlayerInfo(int playerId) {
        String[] idJogadores = new String[5];
        for (Jogador e : jogadores) {
            if (playerId == e.getId()) {
                idJogadores[0] = String.valueOf(e.getId());
                idJogadores[1] = e.getNome();
                idJogadores[2] = e.getEspecies().getId(); //vai às especies para ir buscar o id da especie
                idJogadores[3] = String.valueOf(e.getEnergia());
                idJogadores[4] = e.getVelocidade().get(0) + ".." + e.getVelocidade().get(e.getVelocidade().size() - 1); //TODO METI O -1 rever
            }
        }
        return idJogadores;
    }

    public String[] getCurrentPlayerInfo() {
        jogadores.sort(Comparator.comparing(Jogador::getId));
        String[] idJogadores = new String[5];
        for (Jogador e : jogadores) {
            if (e.getTurnoJogador() == nrTurnos) {
                idJogadores[0] = String.valueOf(e.getId());
                idJogadores[1] = e.getNome();
                idJogadores[2] = e.getEspecies().getId(); //vai às especies para ir buscar o id da especie
                idJogadores[3] = String.valueOf(e.getEnergia());
                idJogadores[4] = e.getVelocidade().get(0) + ".." + e.getVelocidade().get(e.getVelocidade().size() - 1);
            }
        }
        return idJogadores;
    }

    public String[][] getPlayersInfo() {
        String[][] y = new String[jogadores.size()][4];
        for (int z = 0; z < jogadores.size(); z++) {
            y[z] = getPlayerInfo(jogadores.get(z).getId());
        }
        System.out.println(Arrays.deepToString(y));
        return y;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        jogadores.sort(Comparator.comparing(Jogador::getId));
        if (!bypassValidations) {
            if (nrSquares > 6 || nrSquares < -6) {nrTurnosAndReset();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);}
        }
        for (Jogador g : jogadores) {
            if (g.getTurnoJogador() == nrTurnos) {
                if (nrSquares < 0) {
                    if (!g.getVelocidade().contains(nrSquares * -1) && !bypassValidations) {
                        nrTurnosAndReset();
                        return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
                    }
                    if (g.getEnergia() > 0) {
                        if (g.getEnergia() < g.getEspecies().getConsumoEnergia() * (nrSquares * -1)) {
                            nrTurnosAndReset();
                            return new MovementResult(MovementResultCode.NO_ENERGY, null);
                        }//verificamos se ele anda para tras da 1ºcasa
                        if (g.getPosicao() + nrSquares < 1) {
                            nrTurnosAndReset();
                            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
                        }
                        for (Alimento alimento : alimentos) {
                            if (g.getPosicao() + nrSquares == alimento.getPosicao()) {
                                if (Objects.equals(g.getEspecies().getId(), "E") && Objects.equals(alimento.getId(), "c")) {
                                    elephantEnergyAndPositionMoveBack(g, nrSquares);
                                    return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                                }
                                energyAndPositionWithCatchFoodMoveBack(g, nrSquares);
                                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + checkFood(alimento.getId()));
                            }
                        }
                        energyAndPositiondMoveBack(g, nrSquares);
                    } else {
                        energyAndPositiondDontMove(g, nrSquares);
                        return new MovementResult(MovementResultCode.NO_ENERGY, null);
                    }
                }
                if (nrSquares > 0) {
                    if (!g.getVelocidade().contains(nrSquares) && !bypassValidations) {
                        nrTurnosAndReset();
                        return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
                    }
                    if (g.getEnergia() > 0) {
                        if (g.getEnergia() < g.getEspecies().getConsumoEnergia() * nrSquares) {
                            energyAndPositiondDontMove(g, nrSquares); // palves
                            nrTurnosAndReset();
                            return new MovementResult(MovementResultCode.NO_ENERGY, null);
                        }
                        if (g.getPosicao() + nrSquares > tamanhoDoBoard) {
                            g.setPosicao(tamanhoDoBoard);
                            vencedor = true;
                        } else {
                            for (Alimento alimento : alimentos) {
                                if (g.getPosicao() + nrSquares == alimento.getPosicao()) {
                                    if (Objects.equals(g.getEspecies().getId(), "E") && Objects.equals(alimento.getId(), "c")) {
                                        elephantEnergyAndPositionMoveFoward(g, nrSquares);
                                        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                                    }
                                    energyAndPositionWithCatchFoodMoveFoward(g, nrSquares);
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + checkFood(alimento.getId()));
                                }
                            }
                            energyAndPositiondMoveFoward(g, nrSquares);
                        }
                    } else {
                        energyAndPositiondDontMove(g, nrSquares);
                        nrTurnosAndReset();
                        return new MovementResult(MovementResultCode.NO_ENERGY, null);
                    }
                }
                if (nrSquares == 0) {
                    for (Alimento a : alimentos) {
                        if (g.getPosicao() == a.getPosicao()) {
                            dontMoveFoodEnergy(g, nrSquares);
                            return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + checkFood(a.getId()));
                        }
                    }
                    energyAndPositiondDontMove(g, nrSquares);
                }
            }
        }
        checkPositionPlayers();
        nrTurnosAndReset();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {
        //for jogadores
        //se algum dos jogadores estiver no ultimo scare
        //se tiver devolvo a informacao com o getPlayerInfo
        jogadores.sort(Comparator.comparing(Jogador::getPosicao).reversed());

        for (Jogador s : jogadores) {
            if (s.getPosicao() == tamanhoDoBoard || vencedor) {
                return getPlayerInfo(s.getId());
            }
            if (venceuDistanciaEntreOs2Jogadores) {
                venceuSegundoJogador = true;
                return getPlayerInfo(jogadores.get(1).getId());
            }
        }
        return null;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> resultado = new ArrayList<>();
        StringBuilder a = new StringBuilder();
        //jogadores.sort(Comparator.comparing(Jogador::getPosicao).reversed());
        if (venceuSegundoJogador){
            Collections.swap(jogadores, 0, 1);
        }
        for (int i = 1; i < jogadores.size() + 1; i++) {
            a = new StringBuilder();
            a.append("#").append(i).append(" ").append(jogadores.get(i - 1).getNome()).append(", ").
                    append(jogadores.get(i - 1).getEspecies()).append(", ").append(jogadores.get(i - 1).
                            getPosicao()).append(", ").append(jogadores.get(i - 1).getDistancia()).append(", ")
                    .append(jogadores.get(i - 1).getNrAlimentosApanhados()).append(", ");

            a.deleteCharAt(a.length() - 1);
            a.deleteCharAt(a.length() - 1);
            resultado.add(a.toString());
        }
        return resultado;
    }

    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("Joao luis a21903149");
        JLabel jlabel2 = new JLabel("Beatriz Neves 22103215");
        panel.add(jlabel);
        panel.add(jlabel2);
        panel.setSize(300, 300);
        return panel;
    }

    public String whoIsTaborda() {
        return "wrestling";
    }


    public String[][] getFoodTypes() {
        String[] alimento1 = {"e", "Erva", "grass.png"};
        String[] alimento2 = {"a", "Agua", "water.png"};
        String[] alimento3 = {"b", "Cacho de bananas", "bananas.png"};
        String[] alimento4 = {"c", "Carne", "meat.png"};
        String[] alimento5 = {"m", "Cogumelo Magico", "mushroom.png"};

        String[][] alimentosInfo = new String[5][3];

        alimentosInfo[0] = alimento1;
        alimentosInfo[1] = alimento2;
        alimentosInfo[2] = alimento3;
        alimentosInfo[3] = alimento4;
        alimentosInfo[4] = alimento5;

        return alimentosInfo;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] currentPlayerInfo = new String[2];
        Jogador jogador = new Jogador();
        for (Jogador jogador1 : jogadores) {
            if (jogador1.getTurnoJogador() == nrTurnos) {
                jogador = jogador1;
            }
        }
        int jogadorConsumo = jogador.getEspecies().getConsumoEnergia() * nrPositions;
        int jogadorGanhoDeEnergia = jogador.getEspecies().getGanhoEnergia();
        if (jogadorConsumo < 0) {
            jogadorConsumo = jogadorConsumo * -1;
        }
        currentPlayerInfo[0] = String.valueOf(jogadorConsumo);
        currentPlayerInfo[1] = String.valueOf(jogadorGanhoDeEnergia);
        return currentPlayerInfo;
    }

    public boolean saveGame(File file) {
        try {
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(), true));
                writer.write(this.tamanhoDoBoard + "\n");
                writer.write(this.nrTurnosTotais + "\n");
                writer.write(this.nrTurnos + "\n");
                for (Jogador a : this.jogadores) {
                    writer.write(a.getId() + ";" + a.getPosicao() + ";" + a.getNome()
                            + ";" + a.getEspecies().getId() + ";" + a.getEnergia() + ";" + a.getTurnoJogador() +
                            ";" + a.getVelocidade() + ";" + a.getEspecies().getNome() + ";" + a.getEspecies().getConsumoEnergia()
                            + ";" + a.getEspecies().getGanhoEnergia() + "\n");
                }
                writer.write("alimentos:" + "\n");
                for (Banana a : this.bananas) {
                    for (Cogumelo c : this.cogumelos) {
                        if (c.getPosicao() == a.getPosicao()) {
                            writer.write(a.getId() + ";" + a.getPosicao() + ";" + c.getnCogumelos()
                                    + ";" + a.getNrBananasNoCacho() + "\n");
                        }
                    }
                }
                writer.close();
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadGame(File file) {
        try {
            this.alimentos = new ArrayList<>();
            this.jogadores = new ArrayList<>();
            this.cogumelos = new ArrayList<>();
            this.bananas = new ArrayList<>();
            Scanner reader = new Scanner(file);
            this.tamanhoDoBoard = Integer.parseInt(reader.nextLine());
            this.nrTurnosTotais = Integer.parseInt(reader.nextLine());
            this.nrTurnos = Integer.parseInt(reader.nextLine());
            String size;
            String s;
            String[] filter;
            while (reader.hasNextLine()) {
                size = reader.nextLine();
                s = size;
                filter = s.split(";");
                Jogador jogador = new Jogador();
                Especie especies = new Especie();
                if (Objects.equals(filter[0], "alimentos:")) {break;}
                jogador.setId(Integer.parseInt(filter[0]));
                jogador.setPosicao(Integer.parseInt(filter[1]));
                jogador.setNome(filter[2]);
                especies.setId(filter[3]);
                jogador.setEnergia(Integer.parseInt(filter[4]));
                jogador.setTurnoJogador(Integer.parseInt(filter[5]));
                ArrayList<Integer> velocidadeCerta = new ArrayList<>();
                filter[6] = filter[6].replace("[", "");
                filter[6] = filter[6].replace("]", "");
                String[] v = filter[6].split(", ");
                for (int c = 0; c < v.length; c++) {velocidadeCerta.add(Integer.parseInt(v[c]));}
                especies.setNome(filter[7]);
                especies.setConsumoEnergia(Integer.parseInt(filter[8]));
                especies.setGanhoEnergia(Integer.parseInt(filter[9]));
                jogador.setVelocidade(velocidadeCerta);
                jogador.setEspecies(especies);
                this.jogadores.add(jogador);
            }
            while (reader.hasNextLine()) {
                size = reader.nextLine();
                s = size;
                filter = s.split(";");
                Alimento alimento = new Alimento();
                Cogumelo cogumelo = new Cogumelo();
                Banana banana = new Banana();
                alimento.setId(filter[0]);
                alimento.setPosicao(Integer.parseInt(filter[1]));
                cogumelo.setId(filter[0]);
                cogumelo.setPosicao(Integer.parseInt(filter[1]));
                cogumelo.setnCogumelos(Integer.parseInt(filter[2]));
                banana.setId(filter[0]);
                banana.setPosicao(Integer.parseInt(filter[1]));
                banana.setNrBananasNoCacho(Integer.parseInt(filter[3]));
                this.alimentos.add(alimento);
                this.cogumelos.add(cogumelo);
                this.bananas.add(banana);
            }
        } catch (FileNotFoundException e) {return false;}return true;}

    //Elefante - herbivoro
    //Pássaro - omnivoro
    //Tarzan - omnivoro
    //Leão - carnivoro
    //Tartaruga - omnivoro
    public void catchFood(Jogador jogador) {
        switch (jogador.getAlimentosApanhados().get(jogador.getAlimentosApanhados().size() - 1).toString()) {
            case "e" -> { //erva
                if (Objects.equals(jogador.getEspecies().getId(), "Z") || Objects.equals(jogador.getEspecies().getId(), "T") ||
                        Objects.equals(jogador.getEspecies().getId(), "E") || Objects.equals(jogador.getEspecies().getId(), "P")) {
                    int energia = jogador.getEnergia();
                    energia += 20;
                    if (energia > 200) {
                        jogador.setEnergia(200);
                    }jogador.setEnergia(energia);}
                if (Objects.equals(jogador.getEspecies().getId(), "L")) {
                    int energia = jogador.getEnergia();
                    energia -= 20;
                    jogador.setEnergia(energia);
                }
            }
            case "a" -> { //agua
                if (Objects.equals(jogador.getEspecies().getId(), "L") || Objects.equals(jogador.getEspecies().getId(), "E")) {
                    int energia = jogador.getEnergia();
                    energia += 15;
                    if (energia > 200) {
                        jogador.setEnergia(200);
                    }
                    jogador.setEnergia(energia);
                }
                if (Objects.equals(jogador.getEspecies().getId(), "P") || Objects.equals(jogador.getEspecies().getId(), "T") ||
                        Objects.equals(jogador.getEspecies().getId(), "Z")) {
                    int energia = jogador.getEnergia();
                    int calculoEnergia = (int) (energia * 0.20);
                    energia += calculoEnergia;
                    if (energia > 200) {
                        jogador.setEnergia(200);
                    }
                    jogador.setEnergia(energia);
                }
            }
            case "b" -> {//banana
                if (Objects.equals(jogador.getEspecies().getId(), "Z") || Objects.equals(jogador.getEspecies().getId(), "T") ||
                        Objects.equals(jogador.getEspecies().getId(), "E") || Objects.equals(jogador.getEspecies().getId(), "P") ||
                        Objects.equals(jogador.getEspecies().getId(), "L")) {
                    catchBanana(jogador);
                }
            }
            case "c" -> { //carne
                if (Objects.equals(jogador.getEspecies().getId(), "Z") || Objects.equals(jogador.getEspecies().getId(), "L") ||
                        Objects.equals(jogador.getEspecies().getId(), "T") || Objects.equals(jogador.getEspecies().getId(), "P")) {
                    catchCarne(jogador);
                }
            }
            case "m" -> { //cogumelos
                if (Objects.equals(jogador.getEspecies().getId(), "T") || Objects.equals(jogador.getEspecies().getId(), "E") ||
                        Objects.equals(jogador.getEspecies().getId(), "P") || Objects.equals(jogador.getEspecies().getId(), "Z") ||
                        Objects.equals(jogador.getEspecies().getId(), "L")) {
                    catchCogumelos(jogador);
                }
            }
        }
    }

    public String checkFood(String id) {
        if (Objects.equals(id.toString(), "e")) {
            return "Erva";
        }
        if (Objects.equals(id.toString(), "a")) {
            return "Agua";
        }
        if (Objects.equals(id.toString(), "b")) {
            return "Bananas";
        }
        if (Objects.equals(id.toString(), "c")) {
            return "Carne";
        }
        if (Objects.equals(id.toString(), "m")) {
            return "Cogumelo Magico";
        }
        return null;
    }

    public void checkPositionPlayers() {
        ArrayList<Jogador> jogadors = new ArrayList<>(jogadores);
        jogadors.sort(Comparator.comparing(Jogador::getPosicao).reversed());
        int distanciaEntreOs2Jogadores;
        distanciaEntreOs2Jogadores = jogadors.get(0).getPosicao() - jogadors.get(1).getPosicao();
        double arredondar = (double) tamanhoDoBoard / (double) 2;
        if (distanciaEntreOs2Jogadores >= (int) Math.ceil(arredondar)) {
            venceuDistanciaEntreOs2Jogadores = true;
        }else {
            venceuDistanciaEntreOs2Jogadores = false;
        }
    }

    public void elephantEnergyAndPositionMoveBack(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares * -1;
        g.setDistancia(distancia);
        energia += g.getEspecies().getConsumoEnergia() * nrSquares;
        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        checkPositionPlayers();
        nrTurnosAndReset();
    }

    public void energyAndPositionWithCatchFoodMoveBack(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares * -1;
        g.setDistancia(distancia);
        int alimentosApanhados = g.getNrAlimentosApanhados();
        alimentosApanhados += 1;
        g.setNrAlimentosApanhados(alimentosApanhados);
        energia += g.getEspecies().getConsumoEnergia() * nrSquares;
        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        for (Jogador jogador : jogadores) {
            if (jogador.getTurnoJogador() == nrTurnos) {
                for (int i = 0; i < alimentos.size(); i++) {
                    if (jogador.getPosicao() == alimentos.get(i).getPosicao()) {
                        jogador.setAlimentosApanhados(alimentos.get(i));
                        //alimentos.remove(i);
                        System.out.println(jogador.getAlimentosApanhados());
                        break;
                    }
                }
                break;
            }
        }
        catchFood(g);
        checkPositionPlayers();
        nrTurnosAndReset();
    }

    public void energyAndPositiondMoveBack(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares * -1;
        g.setDistancia(distancia);
        energia += g.getEspecies().getConsumoEnergia() * nrSquares;


        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        checkPositionPlayers();
    }

    public void elephantEnergyAndPositionMoveFoward(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares;
        g.setDistancia(distancia);
        energia -= g.getEspecies().getConsumoEnergia() * nrSquares;

        g.setEnergia(energia);

        checkPositionPlayers();
        nrTurnosAndReset();
    }

    public void energyAndPositionWithCatchFoodMoveFoward(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares;
        g.setDistancia(distancia);
        int alimentosApanhados = g.getNrAlimentosApanhados();
        alimentosApanhados += 1;
        g.setNrAlimentosApanhados(alimentosApanhados);
        energia -= g.getEspecies().getConsumoEnergia() * nrSquares;

        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }

        for (Jogador jogador : jogadores) {
            if (jogador.getTurnoJogador() == nrTurnos) {
                for (int i = 0; i < alimentos.size(); i++) {
                    if (jogador.getPosicao() == alimentos.get(i).getPosicao()) {
                        jogador.setAlimentosApanhados(alimentos.get(i));
                        //alimentos.remove(i);
                        System.out.println(jogador.getAlimentosApanhados());
                        break;
                    }
                }
                break;
            }
        }
        catchFood(g);
        checkPositionPlayers();
        nrTurnosAndReset();
    }

    public void energyAndPositiondMoveFoward(Jogador g, int nrSquares) {
        int posicao = g.getPosicao(); //vai buscar a posicao do jogador
        posicao += nrSquares; //faz posicao mais o numero que o dado deu
        g.setPosicao(posicao); //escreve a posicao final
        int energia = g.getEnergia();
        int distancia = g.getDistancia();
        distancia += nrSquares;
        g.setDistancia(distancia);
        energia -= g.getEspecies().getConsumoEnergia() * nrSquares;


        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        checkPositionPlayers();
    }

    public void energyAndPositiondDontMove(Jogador g, int nrSquares) {
        int energia = g.getEnergia();
        energia += g.getEspecies().getGanhoEnergia();

        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        checkPositionPlayers();
    }

    public void nrTurnosAndReset() {
        nrTurnos++;
        nrTurnosTotais++;
        if (nrTurnos == jogadores.size() + 1) {
            nrTurnos = 1;
        }
    }

    public void dontMoveFoodEnergy(Jogador g, int nrSquares) {
        int energia = g.getEnergia();
        energia += g.getEspecies().getGanhoEnergia();
        int alimentosApanhados = g.getNrAlimentosApanhados();
        alimentosApanhados += 1;
        g.setNrAlimentosApanhados(alimentosApanhados);

        if (energia > 200) {
            g.setEnergia(200);
        }else {
            g.setEnergia(energia);
        }
        for (Jogador jogador : jogadores) {
            if (jogador.getTurnoJogador() == nrTurnos) {
                for (int i = 0; i < alimentos.size(); i++) {
                    if (jogador.getPosicao() == alimentos.get(i).getPosicao()) {
                        jogador.setAlimentosApanhados(alimentos.get(i));
                        //alimentos.remove(i);
                        System.out.println(jogador.getAlimentosApanhados());
                        break;
                    }
                }
                break;
            }
        }
        catchFood(g);
        checkPositionPlayers();
        nrTurnosAndReset();
    }

    public void catchCarne(Jogador jogador) {
        if (nrTurnosTotais <= 12) {
            int energia = jogador.getEnergia();
            energia += 50;
            if (energia > 200) {
                jogador.setEnergia(200);
            }
            jogador.setEnergia(energia);
        } else {
            int energia = jogador.getEnergia();
            int reducaoEnergia = energia / 2;
            energia -= reducaoEnergia;
            jogador.setEnergia(energia);
        }
    }
    public void catchBanana(Jogador jogador) {
        for (Banana alimento : bananas) {
            if (Objects.equals(alimento.getId(), "b")) {
                alimento.setNrVezesApanhado(+1);
            }
            if (alimento.getPosicao() == jogador.getPosicao()) {
                if (alimento.getNrBananasNoCacho() > 0) {
                    if (alimento.getNrVezesApanhado() <= 3) {
                        int nrBananas = alimento.getNrBananasNoCacho();
                        if (jogador.getNrBananasApanhadas() < 2) {
                            nrBananas = alimento.getNrBananasNoCacho();
                            int energia = jogador.getEnergia();
                            energia += 40;
                            if (energia > 200) {
                                jogador.setEnergia(200);
                            } else {
                                jogador.setEnergia(energia);
                            }
                            alimento.setNrBananasNoCacho(nrBananas - 1);
                            int bananasApanhadas = jogador.getNrBananasApanhadas();
                            System.out.println("banans apnahadas antes: " + bananasApanhadas);
                            jogador.setNrBananasApanhadas(bananasApanhadas + 1);
                            int apanhar = jogador.getNrBananasApanhadas();
                            System.out.println("banans apnahadas depois: " + apanhar);
                        } else {
                            System.out.println("apanhou 2 ou mais bananas");
                            alimento.setNrBananasNoCacho(nrBananas - 1);
                            int energia = jogador.getEnergia();
                            energia -= 40;

                            jogador.setEnergia(energia);

                        }
                    }
                }
            }
        }
    }
    public void catchCogumelos(Jogador jogador) {
        int nCogumelos = 0;
        for (Cogumelo alimentos : cogumelos) {
            if (Objects.equals(alimentos.getId(), "m") && alimentos.getPosicao() == jogador.getPosicao()) {
                nCogumelos = alimentos.getnCogumelos();
                break;
            }
        }
        if (nrTurnosTotais % 2 == 0) {
            int energia = jogador.getEnergia();
            double percentagem = (double) nCogumelos / 100;
            System.out.println("aumenta " + percentagem);
            int calculoDoN = (int) (energia * percentagem);
            energia -= calculoDoN;
            System.out.println(energia);
            if (energia > 200) {
                jogador.setEnergia(200);
            }else {
                jogador.setEnergia(energia);
            }
        } else {
            int energia = jogador.getEnergia();
            double percentagem = (double) nCogumelos / 100;
            int calculoDoN = (int) (energia * percentagem);
            System.out.println(calculoDoN);
            energia += calculoDoN;
            System.out.println("diminui " + percentagem);
            System.out.println(energia);

            if (energia > 200) {
                jogador.setEnergia(200);
            }else {
                jogador.setEnergia(energia);
            }
        }
    }

}