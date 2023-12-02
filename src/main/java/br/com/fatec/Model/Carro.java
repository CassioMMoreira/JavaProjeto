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

public class Carro {
    private int idCarro;
    private String placa;
    private String modelo;
    private Marca marca;
    private Categoria categoria;

    // Construtores
    public Carro() {
    }

    public Carro(int idCarro, String placa, String modelo, Marca marca, Categoria categoria) {
        this.idCarro = idCarro;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.categoria = categoria;
    }
    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Getters e setters
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // ...
    // hashCode e equals
    @Override
    public int hashCode() {
        return Objects.hash(idCarro, placa, modelo, marca, categoria);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return idCarro == carro.idCarro &&
                Objects.equals(placa, carro.placa) &&
                Objects.equals(modelo, carro.modelo) &&
                Objects.equals(marca, carro.marca) &&
                Objects.equals(categoria, carro.categoria);
    }

    public String getNome() {
        return getNome();
    }

}

