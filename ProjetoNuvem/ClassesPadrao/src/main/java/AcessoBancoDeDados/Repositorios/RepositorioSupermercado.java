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
            List supermercados = acessador.selectGeralLista(sqlQuery, Supermercado.class);
            return supermercados;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return null;
        }
    }

    public Object SelectSupermercado(long id){
        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE Id = " + id + ";";
        try{
            AcessadorBanco acessador = new AcessadorBanco();
            Object supermercado = acessador.selectGeralObjeto(sqlQuery, Supermercado.class);
            return supermercado;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return null;
        }
    }

    public void AddSupermercado(Supermercado supermercado){
        String sqlQuery = "";
        try{
            AcessadorBanco acessador = new AcessadorBanco();
            acessador.addOrUpdateOrDelete(sqlQuery, Supermercado.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        }
    }
}
