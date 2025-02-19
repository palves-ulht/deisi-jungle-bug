package pt.ulusofona.lp2.deisiJungle;

public class Banana extends Alimento{
    protected int nrVezesApanhado;
    protected int nrBananasNoCacho;

    public void setNrBananasNoCacho(int nrBananasNoCacho) {
        this.nrBananasNoCacho = nrBananasNoCacho;
    }

    public int getNrBananasNoCacho() {
        return nrBananasNoCacho;
    }

    public void setNrVezesApanhado(int nrVezesApanhado) {
        this.nrVezesApanhado = 1;
    }

    public int getNrVezesApanhado() {
        return nrVezesApanhado;
    }

}
