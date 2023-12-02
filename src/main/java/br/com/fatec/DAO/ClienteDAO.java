package br.com.fatec.DAO;

import br.com.fatec.Model.Cliente;
import br.com.fatec.Persistencia.Banco;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ClienteDAO implements DAO<Cliente> {
    
    private Cliente cliente;
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Cliente dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Veiculo (id_cliente, nome, "
                   + "email, cpf, enderco ) values (?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getIdCliente()); //1º interrogação
        pst.setString(2, dado.getNome()); //2º interrogacao
        pst.setString(3, dado.getEmail()); //3º interrogacao
        pst.setString(4, dado.getCpf()); //4º interrogacao
        pst.setString(5, dado.getEndereco()); //3º interrogacao        
        
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
    public boolean remove(Cliente dado) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM Cliente WHERE nome = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, dado.getNome()); //1º interrogação
        
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
    public boolean altera(Cliente dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE Veiculo SET nome = ?, email = ?, "
                   + "cpf = ?, endereco = ? WHERE id_cliente = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(5, dado.getIdCliente()); //
        pst.setString(1, dado.getNome());
        pst.setString(2, dado.getEmail()); 
        pst.setString(3, dado.getCpf()); 
        pst.setString(4, dado.getEndereco()); 
        
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
    public Cliente buscaID(Cliente dado) throws SQLException {
        cliente = null;
        //Comando SELECT
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdCliente());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto proprietario
            cliente = new Cliente();
            //move os dados do resultSet para o objeto proprietario
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEndereco(rs.getString("endereco"));
        }
        
        Banco.desconectar();
        
        return cliente;
    }

    @Override
    public Cliente buscaNome(Cliente dado) throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        
        cliente = null;
        //Comando SELECT
        String sql = "SELECT * FROM Cliente WHERE nome = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getNome());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            cliente = new Cliente();
            //move os dados do resultSet para o objeto veiculo
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEndereco(rs.getString("endereco"));
            
        }
        
        Banco.desconectar();
        
        return cliente;
    }

    @Override
    public Collection<Cliente> lista(String filtro) throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        
        Collection<Cliente> listagem = new ArrayList<>();
        
        cliente = null;
        //Comando SELECT
        String sql = "SELECT * FROM Cliente ";
        //colocar filtro ou nao
        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        while(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            cliente = new Cliente();
            //move os dados do resultSet para o objeto veiculo
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.getNome(rs.getString("nome"));
            //cliente.getEmail(rs.getString("email"));
            //cliente.getCpf(rs.getString("cpf"));
            //cliente.getEndereco(rs.getString("endereco"));
           

           
            //adicionar na coleção
            listagem.add(cliente);
        }
        
        Banco.desconectar();
        
        return listagem;
    }


}
