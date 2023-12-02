/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Locacao;
import br.com.fatec.Model.Cliente;
import br.com.fatec.Model.Carro;
import br.com.fatec.Persistencia.Banco;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class LocacaoDAO implements DAO<Locacao> {
    
    private Locacao locacao;
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Locacao dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO locacao (id_locacao, id_cliente, id_carro, "
                   + "tempo_aluguel, valor_total) values (?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getIdLocacao()); //1º interrogação
        pst.setInt(2, dado.getCliente().getIdCliente()); //2º interrogacao
        pst.setInt(3, dado.getCarro().getIdCarro()); //2º interrogacao
        pst.setInt(4, dado.getTempoAluguel());
        pst.setFloat(5, dado.getValorTotal());
        
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
    public boolean remove(Locacao dado) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM locacao WHERE id_locacao = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getTempoAluguel()); //1º interrogação
        
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
    public boolean altera(Locacao dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE Veiculo SET id_cliente = ?, id_carro = ?, data_inicio = ?, "
                   + "data_devolucao = ?, valor_total = ? WHERE id_locacao = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(7, dado.getIdLocacao()); //
        pst.setInt(1, dado.getCliente().getIdCliente()); 
        pst.setInt(2, dado.getCarro().getIdCarro());
        pst.setInt(4, dado.getTempoAluguel());
        pst.setFloat(5, dado.getValorTotal());
        
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
    public Locacao buscaID(Locacao dado) throws SQLException {
        locacao = null;
        
        String sql = "SELECT * FROM locacao WHERE id_locacao = ?";
        
         //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdLocacao());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        if(rs.next()) {
            locacao = new Locacao();
            locacao.setIdLocacao(rs.getInt("id_locacao"));
            
            Cliente cliente = new Cliente();
            
            cliente.setIdCliente(rs.getInt("id_cliente"));
            
            Carro carro = new Carro();
            
            carro.setIdCarro(rs.getInt("id_carro"));
            
            locacao.setTempoAluguel(rs.getInt("tempo_aluguel"));
            locacao.setValorTotal(rs.getFloat("valor_total"));
        }
        
        Banco.desconectar();
        
        return locacao;
    }

    @Override
    public Locacao buscaNome(Locacao model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Locacao> lista(String filtro) throws SQLException {
        Collection<Locacao> listagem = new ArrayList<>();
        
        locacao = null;
        //Comando SELECT
        String sql = "SELECT * FROM Locacao ";
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
            locacao = new Locacao();
            //move os dados do resultSet para o objeto veiculo
            locacao.setIdLocacao(rs.getInt("id_locacao"));
            locacao.setTempoAluguel(rs.getInt("tempo_aluguel"));
            
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            
            Carro carro = new Carro();
            carro.setIdCarro(rs.getInt("id_carro"));
            
            //adicionar na coleção
            listagem.add(locacao);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

    @Override
    public Locacao buscaAvancada(Locacao model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

