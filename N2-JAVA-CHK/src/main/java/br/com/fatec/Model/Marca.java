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

public class Marca {
    private int idMarca;
    private String nome;

    // Construtores
    public Marca() {
    }

    public Marca(int idMarca, String nome) {
        this.idMarca = idMarca;
        this.nome = nome;
    }
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    // Getters e setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    // hashCode e equals
    @Override
    public int hashCode() {
        return Objects.hash(idMarca, nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() == o.getClass()) return false;
        Marca marca = (Marca) o;
        return idMarca == marca.idMarca && Objects.equals(nome, marca.nome);
    }

    public String getNome(String string) {
        return nome;
    }
}

