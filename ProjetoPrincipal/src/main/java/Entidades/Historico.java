package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "Historico")
@NamedQueries({
        @NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
        @NamedQuery(name = "Historico.findBySupermercadoId", query = "SELECT h FROM Historico h WHERE h.supermercado.id = :supermercadoId")
})
public class Historico {

    private long id;
    private String ultimoDia;
    private String mediaMeses;
    private String mediaDiasDaSemana;
    private Supermercado supermercado;

    @Id
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupermercadoId")
    public Supermercado getSupermercado(){
        return this.supermercado;
    }
    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }
}
