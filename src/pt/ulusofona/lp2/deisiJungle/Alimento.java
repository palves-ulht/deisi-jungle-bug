package pt.ulusofona.lp2.deisiJungle;

public class Alimento {
    String id;
    int posicao;
    public void setId(String id) {
        this.id = id;
    }

    public Alimento() {
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getId() {
        return id;
    }

    public int getPosicao() {
        return posicao;
    }

    public Alimento(String id, int posicao) {
        this.id = id;
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return id;
    }
}
