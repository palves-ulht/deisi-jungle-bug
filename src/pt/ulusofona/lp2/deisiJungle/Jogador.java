package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Jogador {
    protected int id;
    protected String nome;
    protected Especie especies;
    protected int energia;
    protected int posicao;
    protected int turnoJogador;
    protected ArrayList<Integer> velocidade = new ArrayList<>();
    protected ArrayList<Alimento> alimentosApanhados = new ArrayList<>();
    protected int nrBananasApanhadas = 1;
    protected int distancia;
    protected int nrAlimentosApanhados;

    public int getDistancia() {
        return distancia;
    }

    public int getNrAlimentosApanhados() {
        return nrAlimentosApanhados;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setNrAlimentosApanhados(int nrAlimentosApanhados) {
        this.nrAlimentosApanhados = nrAlimentosApanhados;
    }

    public void setNrBananasApanhadas(int nrBananasApanhadas) {
        this.nrBananasApanhadas = nrBananasApanhadas;
    }

    public int getNrBananasApanhadas() {
        return nrBananasApanhadas;
    }

    public void setAlimentosApanhados(Alimento alimentosApanhados) {
        this.alimentosApanhados.add(alimentosApanhados);
    }

    public ArrayList<Alimento> getAlimentosApanhados() {
        return alimentosApanhados;
    }

    public void setVelocidade(ArrayList<Integer> velocidade) {
        this.velocidade = velocidade;
    }

    public ArrayList<Integer> getVelocidade() {
        return velocidade;
    }

    public int getTurnoJogador() {
        return turnoJogador;
    }

    public void setTurnoJogador(int turnoJogador) {
        this.turnoJogador = turnoJogador;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEspecies(Especie especies) {
        this.especies = especies;
    }

    public Especie getEspecies() {
        return especies;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public Jogador() {
    }

    public Jogador(int id, String nome, Especie especies) {
        this.id = id;
        this.nome = nome;
        this.especies = especies;
    }

    public Jogador(int id, String nome, int energia) {
        this.id = id;
        this.nome = nome;
        this.energia = energia;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Jogador:" + "id=" + id + "nome: " + nome + '\'' + ", especies=" + especies +
                ", energia=" + energia  + " pos: " + posicao + " consumo de energia:" + especies.getConsumoEnergia()+"\n";
    }
}
