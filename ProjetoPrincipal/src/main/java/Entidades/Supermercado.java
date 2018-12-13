package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "Supermercado")
@NamedQueries({
        @NamedQuery(name = "Supermercado.findAll", query = "SELECT s FROM Supermercado s"),
        @NamedQuery(name = "Supermercado.findById", query = "SELECT s FROM Supermercado s WHERE s.id = :id"),
        @NamedQuery(name = "Supermercado.findByLongitudeAndLatitude", query = "SELECT s FROM Supermercado s " +
                                    "WHERE s.latitude < :latitudeMin AND s.latitude > :latitudeMax  " +
                                    "AND s.longitude < :longitudeMin AND s.longitude > :longitudeMax")
})
public class Supermercado {

    private Long id;
    private String nome;
    private String rede;
    private double latitude;
    private double longitude;
    private String endereco;
    private double lotacaoAtual;
    private Long lotacaoMaxima;
    private Historico historico;

    @Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //@Column (name = "Nome")
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRede() {
        return rede;
    }
    public void setRede(String rede) {
        this.rede = rede;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Transient
    public double getLotacaoAtual() {
        return lotacaoAtual;
    }
    public void setLotacaoAtual(double lotacaoAtual) {
        this.lotacaoAtual = lotacaoAtual;
    }

    public Long getLotacaoMaxima() {
        return lotacaoMaxima;
    }
    public void setLotacaoMaxima(long lotacaoMaxima) {
        this.lotacaoMaxima = lotacaoMaxima;
    }

    @OneToOne(mappedBy = "supermercado", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    public Historico getHistorico() {
        return historico;
    }
    public void setHistorico(Historico historico){
        this.historico = historico;
    }
}
