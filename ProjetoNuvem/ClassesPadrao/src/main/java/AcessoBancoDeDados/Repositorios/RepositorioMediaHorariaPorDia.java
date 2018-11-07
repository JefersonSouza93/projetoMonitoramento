package AcessoBancoDeDados.Repositorios;

import AcessoBancoDeDados.AcessadorBanco;
import Entidades.Enums.DiaDaSemana;
import Entidades.MediaHorariaPorDia;

import java.util.List;

public class RepositorioMediaHorariaPorDia {

    public List SelectMediaPorDiaPorSupermercado(DiaDaSemana diaDaSemana, int supermercadoId){
        String sqlQuery = "SELECT * FROM SUPERMERCADO" +
                "WHERE DiaDaSemana = " + diaDaSemana.ordinal() +
                "AND SupermercadoId = " + supermercadoId + ";";
        try{
            AcessadorBanco acessador = new AcessadorBanco();
            List medias = acessador.selectGeralLista(sqlQuery, MediaHorariaPorDia.class);
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
            AcessadorBanco acessador = new AcessadorBanco();
            List medias = acessador.selectGeralLista(sqlQuery, MediaHorariaPorDia.class);
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
