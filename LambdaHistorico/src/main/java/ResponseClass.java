import Entidades.HistoricoResposta;

public class ResponseClass {
    HistoricoResposta historico;

    public HistoricoResposta getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoResposta historico) {
        this.historico = historico;
    }

    public ResponseClass(HistoricoResposta historico) {
        this.historico = historico;
    }

    public ResponseClass() {
    }

}