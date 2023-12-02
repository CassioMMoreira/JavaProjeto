/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Model;

/**
 *
 * @author SAMSUNG
 */
import java.util.Objects;

public class Categoria {
    private int idCategoria;
    private String nome;

    // Construtores
    public Categoria() {
    }

    public Categoria(int idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    // Getters e setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    // ...
    // hashCode e equals
    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria == categoria.idCategoria &&
                Objects.equals(nome, categoria.nome);
    }
}
