package Entidades;

import Entidades.Enums.Mes;

public class MediaMensalPorAno extends Media {

    Mes mes;
    int ano;

    public Mes getMes() {
        return mes;
    }
    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}
