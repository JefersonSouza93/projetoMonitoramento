package AcessoBancoDeDados.Repositorios;

import AcessoBancoDeDados.AcessadorBanco;
import Entidades.Supermercado;

import java.util.List;

public class RepositorioSupermercado {

    public List selectSupermercadosEmRegiao(double latitudeMin,
        double longitudeMin, double latitudeMax, double longitudeMax){

        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE Latitude < {" + latitudeMin + "} AND Latitude > {" + latitudeMax + "}" +
                "AND Longitude < {" + longitudeMin + "} AND Longitude > {" + longitudeMax + "}";
        try{
            AcessadorBanco acessador = new AcessadorBanco();
            List supermercados = AcessadorBanco.SelectGeral(sqlQuery);
            return supermercados;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return null;
        }
    }
}
