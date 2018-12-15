import Entidades.SupermercadoResposta;

import java.util.List;

public class ResponseClass {
    List<SupermercadoResposta> supermercadoList;

    public List<SupermercadoResposta> getSupermercadoList() {
        return supermercadoList;
    }

    public void setSupermercadoList(List<SupermercadoResposta> supermercadoList) {
        this.supermercadoList = supermercadoList;
    }

    public ResponseClass(List<SupermercadoResposta> supermercadoList) {
        this.supermercadoList = supermercadoList;
    }

    public ResponseClass() {
    }

}