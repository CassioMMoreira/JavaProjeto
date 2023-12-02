/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Carro;
import br.com.fatec.Model.Marca;
import br.com.fatec.Model.Categoria;
import br.com.fatec.Persistencia.Banco;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDAO implements DAO <Carro> {
    
    //variaveis auxiliares
    private Carro carro;
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    

    @Override
    public boolean insere(Carro dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Carro (id_carro, placa, "
                   + "modelo, id_marca, id_categoria) values (?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getIdCarro()); //1º interrogação
      //  pst.setString(2, dado.getProprietario().getCodProprietario()); //2º interrogacao
        pst.setString(2, dado.getPlaca());
        pst.setString(3, dado.getModelo());
        pst.setInt(4, dado.getMarca().getIdMarca());
        pst.setInt(5, dado.getCategoria().getIdCategoria());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            inseriu = true; //tudo certo com a inserção
        else
            inseriu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return inseriu;

    }

    @Override
    public boolean remove(Carro dado) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM Carro WHERE id_carro = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getIdCarro()); //1º interrogação
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            removeu = true; //tudo certo com a inserção
        else
            removeu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Carro dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE Veiculo SET placa = ?, modelo = ?, "
                   + "id_marca = ?, id_categoria = ? WHERE id_carro = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(5, dado.getIdCarro()); //
        //pst.setString(2, dado.getProprietario().getCodProprietario()); 
        pst.setString(1, dado.getPlaca()); 
        pst.setString(2, dado.getModelo());
        pst.setInt(3, dado.getMarca().getIdMarca());
        pst.setInt(4, dado.getCategoria().getIdCategoria());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            alterou = true; //tudo certo com a inserção
        else
            alterou = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return alterou;
    }

    @Override
    public Carro buscaID(Carro dado) throws SQLException {
        carro = null;
        //Comando SELECT
        String sql = "SELECT * FROM Carro WHERE id_carro = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdCarro());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto proprietario
            carro = new Carro();
            //move os dados do resultSet para o objeto proprietario
            carro.setIdCarro(rs.getInt("id_carro"));
            carro.setPlaca(rs.getString("placa"));
            carro.setModelo(rs.getString("modelo"));
            
            Marca marca = new Marca();
            
            marca.setIdMarca(rs.getInt("id_marca"));
            
            Categoria categoria = new Categoria();
            
            categoria.setIdCategoria(rs.getInt("id_categoria"));
        }
        
        Banco.desconectar();
        
        return carro;
    }

    @Override
    public Carro buscaNome(Carro dado) throws SQLException {
        carro = null;
        //Comando SELECT
        String sql = "SELECT * FROM Carro WHERE nome = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getNome());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto proprietario
            carro = new Carro();
            //move os dados do resultSet para o objeto proprietario
            carro.setIdCarro(rs.getInt("id_carro"));
            carro.setPlaca(rs.getString("placa"));
            carro.setModelo(rs.getString("modelo"));
            
            Marca marca = new Marca();
            
            marca.setIdMarca(rs.getInt("id_marca"));
            
            Categoria categoria = new Categoria();
            
            categoria.setIdCategoria(rs.getInt("id_categoria"));
        }
        
        Banco.desconectar();
        
        return carro;
    }

    @Override
    public Collection<Carro> lista(String filtro) throws SQLException {
        Collection<Carro> listagem = new ArrayList<>();
        
        carro = null;
        
        String sql = "SELECT * FROM Carro ";
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
            
            carro = new Carro();
            
            carro.setIdCarro(rs.getInt("id_carro"));
            carro.setPlaca(rs.getString("placa"));
            carro.setModelo(rs.getString("modelo"));
            
            Marca marca = new Marca();
            
            marca.setIdMarca(rs.getInt("id_marca"));
            
            Categoria categoria = new Categoria();
            
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            
            
            //adicionar na coleção
            listagem.add(carro);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

    @Override
    public Carro buscaAvancada(Carro model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}