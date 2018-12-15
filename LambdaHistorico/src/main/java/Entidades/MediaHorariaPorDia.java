package Entidades;

import Entidades.Enums.DiaDaSemana;

public class MediaHorariaPorDia extends Media {

    DiaDaSemana diaDaSemana;
    int hora;

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }
    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public int getHora() {
        return hora;
    }
    public void setHora(int hora) {
        this.hora = hora;
    }

}
