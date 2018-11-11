package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "Supermercado")
@NamedQueries({@NamedQuery(name = "xoblou", query="SELECT * FROM Supermercado"),
        @NamedQuery(name = "xoblou2", query="SELECT s FROM Supermercado s WHERE s.id = :id")
}
)
public class Supermercado {

    long id;
    String nome;
    String descricao;
    String rede;
    double latitude;
    double longitude;
    String endereco;
    double lotacaoAtual;
    double lotacaoMaxima;

    @Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //@Column (name = "Nome")
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public double getLotacaoAtual() {
        return lotacaoAtual;
    }
    public void setLotacaoAtual(long lotacaoAtual) {
        this.lotacaoAtual = lotacaoAtual;
    }

    public double getLotacaoMaxima() {
        return lotacaoMaxima;
    }
    public void setLotacaoMaxima(long lotacaoMaxima) {
        this.lotacaoMaxima = lotacaoMaxima;
    }

}
