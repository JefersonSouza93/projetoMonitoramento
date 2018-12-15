package Entidades;

public class SupermercadoResposta {

    private Long id;
    private String nome;
    private String rede;
    private double latitude;
    private double longitude;
    private String endereco;
    private double lotacaoAtual;
    private Long lotacaoMaxima;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

}
