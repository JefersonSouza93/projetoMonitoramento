package AcessoBancoDeDados.Repositorios;

import Entidades.Enums.DiaDaSemana;
import Entidades.MediaHorariaPorDia;

import java.util.List;

import static AcessoBancoDeDados.AcessadorBanco.SelectGeralLista;

public class RepositorioMediaHorariaPorDia {

    public List SelectMediaPorDiaPorSupermercado(DiaDaSemana diaDaSemana, int supermercadoId){
        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE DiaDaSemana = " + diaDaSemana.ordinal() +
                "AND SupermercadoId = " + supermercadoId + ";";
        try{
            List medias = SelectGeralLista(sqlQuery, MediaHorariaPorDia.class);
            return medias;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return null;
        }
    }

    public List SelectMediaPorSupermercado(int supermercadoId){
        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE SupermercadoId = " + supermercadoId + ";";
        try{
            List medias = SelectGeralLista(sqlQuery, MediaHorariaPorDia.class);
            return medias;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return null;
        }
    }

}
