import Entidades.Supermercado;
import java.util.List;

public class ResponseClass {
    List<Supermercado> supermercadoList;

    public List<Supermercado> getSupermercadoList() {
        return supermercadoList;
    }

    public void setSupermercadoList(List<Supermercado> supermercadoList) {
        this.supermercadoList = supermercadoList;
    }

    public ResponseClass(List<Supermercado> supermercadoList) {
        this.supermercadoList = supermercadoList;
    }

    public ResponseClass() {
    }

}