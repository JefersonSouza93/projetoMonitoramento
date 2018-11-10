import AcessoBancoDeDados.Repositorios.RepositorioSupermercado;
import Entidades.Supermercado;

public class Main {

    public static void main(String[] args) {
	// write your code here

        RepositorioSupermercado repositorio = new RepositorioSupermercado();
        Object teste = repositorio.SelectSupermercado(1);
        System.out.print(teste);
        //receive api data
        //send sql query
        //send redis query
        //return supermarketList
    }
}
