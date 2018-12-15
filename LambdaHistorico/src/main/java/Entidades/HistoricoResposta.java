package Entidades;

public class HistoricoResposta {

    private long id;
    private String ultimoDia;
    private String mediaMeses;
    private String mediaDiasDaSemana;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUltimoDia() {
        return ultimoDia;
    }
    public void setUltimoDia(String ultimoDia) {
        this.ultimoDia = ultimoDia;
    }

    public String getMediaMeses() {
        return mediaMeses;
    }
    public void setMediaMeses(String mediaMeses) {
        this.mediaMeses = mediaMeses;
    }

    public String getMediaDiasDaSemana() {
        return mediaDiasDaSemana;
    }
    public void setMediaDiasDaSemana(String mediaDiasDaSemana) {
        this.mediaDiasDaSemana = mediaDiasDaSemana;
    }
}
