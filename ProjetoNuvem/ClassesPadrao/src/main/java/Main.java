import AcessoBancoDeDados.Repositorios.RepositorioSupermercado;
import Entidades.Supermercado;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Supermercado supermercado = new Supermercado();
        supermercado.setDescricao("supermercado top");
        supermercado.setEndereco("CAsa do Lula");
        supermercado.setLatitude(23.3);
        supermercado.setLongitude(45.5);
        supermercado.setNome("CarrefourDoDiego");
        supermercado.setRede("FogoNoPrius");
        supermercado.setLotacaoMaxima(120);

        RepositorioSupermercado repositorio = new RepositorioSupermercado();
        repositorio.AddSupermercado(supermercado);
        Object teste = repositorio.SelectSupermercado(1);
        System.out.print(teste);
        //receive api data
        //send sql query
        //send redis query
        //return supermarketList
    }
}
