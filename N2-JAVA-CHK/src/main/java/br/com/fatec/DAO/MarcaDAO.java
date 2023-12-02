package br.com.fatec.DAO;

import br.com.fatec.Model.Marca;
import br.com.fatec.Persistencia.Banco;
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;



public class MarcaDAO implements DAO<Marca> {
    
    private Marca marca;
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql


    @Override
    public boolean insere(Marca model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Marca model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean altera(Marca model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marca buscaNome(Marca model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Marca> lista(String filtro) throws SQLException {
        Collection<Marca> listagem = new ArrayList<>();
        
        marca = null;
        //Comando SELECT
        String sql = "SELECT * FROM Marca ";
        //colocar filtro ou nao
        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            marca = new Marca();
            //move os dados do resultSet para o objeto veiculo
            marca.setIdMarca(rs.getInt("id_marca"));
            marca.setNome(rs.getString("nome"));
            
            //adicionar na coleção
            listagem.add(marca);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

    @Override
    public Marca buscaID(Marca dado) throws SQLException {
        marca = null;
        
        String sql = "SELECT * FROM Marca WHERE id_marca = ?";
        
         //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdMarca());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        if(rs.next()) {
            marca = new Marca();
            marca.setIdMarca(rs.getInt("id_marca"));
            marca.setNome(rs.getString("nome"));
        }
        
        Banco.desconectar();
        
        return marca;
    }

    @Override
    public Marca buscaAvancada(Marca model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
