package com.company.Entidades;

public class Lotacao {

    double LotacaoInstante;
    double LotacaoMaxima;
    Supermercado supermercado;

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    public double getLotacaoInstante() {
        return LotacaoInstante;
    }

    public void setLotacaoInstante(double lotacaoInstante) {
        LotacaoInstante = lotacaoInstante;
    }

    public double getLotacaoMaxima() {
        return LotacaoMaxima;
    }

    public void setLotacaoMaxima(double lotacaoMaxima) {
        LotacaoMaxima = lotacaoMaxima;
    }
}
