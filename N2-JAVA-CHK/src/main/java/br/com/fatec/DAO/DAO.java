/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;


import java.sql.SQLException;
import java.util.Collection;

/**
 * Interface GENÉRICA
 * @param <T> Tipo da entidade

 */
public interface DAO<T> {
    public boolean insere(T model) throws SQLException;
    public boolean remove(T model) throws SQLException;
    public boolean altera(T model) throws SQLException;
    public T buscaID(T model) throws SQLException;
    public T buscaAvancada(T model) throws SQLException;
    public T buscaNome(T model) throws SQLException;
    Collection<T> lista(String filtro) throws SQLException;
}

