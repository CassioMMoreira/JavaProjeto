package br.com.fatec.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Cliente {
    private int idCliente;
    private String nome;
    private String email;
    private String cpf;
    private String endereco;

    // Construtores

    public Cliente() {
    }

    public Cliente(int idCliente, String nome, String email, String cpf, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    
    // ... (construtores existentes)
    // Getters e setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // hashCode e equals

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nome, email, cpf, endereco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(email, cliente.email) &&
                Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(endereco, cliente.endereco);
    }

    public String getNome(String string) {
        return nome;
    }

    public String getEmail(String string) {
        return email;
    }

    public String getCpf(String string) {
        return cpf;
    }

    public String getEndereco(String string) {
        return endereco;
    }
    
}
