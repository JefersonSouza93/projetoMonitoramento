import AcessoBancoDeDados.Repositorios.RepositorioSupermercado;
import AcessoRedis.AcessadorRedis;
import Entidades.Supermercado;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try{
            AcessadorRedis acessadorRedis = new AcessadorRedis();
            acessadorRedis.addSet("xoblou", "TOP");
            String TOP = acessadorRedis.getSet("xoblou");
            System.out.println(TOP);
            String Diego = acessadorRedis.getSet("Dieguitos ");
            System.out.println(Diego);
        } catch(Exception e){
            e.printStackTrace();
        }
        //receive api data
        //send sql query
        //send redis query
        //return supermarketList
    }
}
