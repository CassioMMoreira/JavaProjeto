/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Categoria;
import br.com.fatec.Persistencia.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoriaDAO implements DAO <Categoria> {
    
    private Categoria categoria;
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql


    @Override
    public boolean insere(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean altera(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria buscaID(Categoria dado) throws SQLException {
        categoria = null;
        
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        
         //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdCategoria());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        if(rs.next()) {
            categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNome(rs.getString("nome"));
        }
        
        Banco.desconectar();
        
        return categoria;
    }

    @Override
    public Categoria buscaNome(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Categoria> lista(String filtro) throws SQLException {
        Collection<Categoria> listagem = new ArrayList<>();
        
        categoria = null;
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
            categoria = new Categoria();
            //move os dados do resultSet para o objeto veiculo
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNome(rs.getString("nome"));
            
            //adicionar na coleção
            listagem.add(categoria);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

    @Override
    public Categoria buscaAvancada(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
