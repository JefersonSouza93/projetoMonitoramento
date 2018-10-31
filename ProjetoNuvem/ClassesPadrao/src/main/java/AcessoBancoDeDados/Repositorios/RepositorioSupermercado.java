package AcessoBancoDeDados.Repositorios;

import Entidades.Supermercado;
import java.util.List;

import static AcessoBancoDeDados.AcessadorBanco.AddOrUpdateOrDelete;
import static AcessoBancoDeDados.AcessadorBanco.SelectGeralLista;
import static AcessoBancoDeDados.AcessadorBanco.SelectGeralObjeto;

public class RepositorioSupermercado {

    public List selectSupermercadosEmRegiao(double latitudeMin,
        double longitudeMin, double latitudeMax, double longitudeMax){

        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE Latitude < {" + latitudeMin + "} AND Latitude > {" + latitudeMax + "}" +
                "AND Longitude < {" + longitudeMin + "} AND Longitude > {" + longitudeMax + "}";
        try{
            List supermercados = SelectGeralLista(sqlQuery, Supermercado.class);
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
            Object supermercado = SelectGeralObjeto(sqlQuery, Supermercado.class);
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
            AddOrUpdateOrDelete(sqlQuery, Supermercado.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        }
    }
}
