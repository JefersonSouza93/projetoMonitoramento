package Entidades;

import java.util.Calendar;

public abstract class Media {

    long id;
    double valorMedia;
    long supermercadoId;
    Calendar timeStamp;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double getValorMedia() {
        return valorMedia;
    }
    public void setValorMedia(long valorMedia) {
        this.valorMedia = valorMedia;
    }

    public long getIdSupermercado() {
        return supermercadoId;
    }
    public void setIdSupermercado(long supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    public Calendar gettimeStamp() {
        return timeStamp;
    }
    public void settimeStamp(Calendar  timeStamp) {
        this.timeStamp = timeStamp;
    }

}
