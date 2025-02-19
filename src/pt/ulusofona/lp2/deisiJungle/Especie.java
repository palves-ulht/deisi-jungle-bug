package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Especie {
    protected String id;
    protected String nome;
    protected String imagem;
    protected int consumoEnergia;
    protected int ganhoEnergia;


    public void setConsumoEnergia(int consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public void setGanhoEnergia(int ganhoEnergia) {
        this.ganhoEnergia = ganhoEnergia;
    }


    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getGanhoEnergia() {
        return ganhoEnergia;
    }

    public Especie() {
    }

    public Especie(String id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem(String nome) {
        if ("Tartaruga".equals(nome)) {
            return "turtle.png";
        }
        if ("Leao".equals(nome)){
            return "lion.png";
        }
        if ("Tarzan".equals(nome)){
            return "tarzan.png";

        }
        if ("Elefante".equals(nome)){
            return "elephant.png";
        }
        if ("Passaro".equals(nome)){
            return "bird.png";
        }
        return null;
    }

    @Override
    public String toString() {
        return nome;
    }
}
