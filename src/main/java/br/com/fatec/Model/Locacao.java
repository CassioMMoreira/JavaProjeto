package br.com.fatec.Model;


import java.sql.Date;
import java.util.Objects;

public class Locacao {
    private int idLocacao;
    private Cliente cliente;
    private Carro carro;
    private int tempoAluguel;

  
    private float valorTotal;  // Alterei o nome do atributo para corresponder ao método

    // Construtores
    public Locacao() {
    }

    public Locacao(int idLocacao, Cliente cliente, Carro carro, int tempoAluguel , float valorTotal) {
        this.idLocacao = idLocacao;
        this.cliente = cliente;
        this.carro = carro;
        this.tempoAluguel = tempoAluguel;
        this.valorTotal = valorTotal;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    
      public int getTempoAluguel() {
        return tempoAluguel;
    }

    public void setTempoAluguel(int tempoAluguel) {
        this.tempoAluguel = tempoAluguel;
    }

    // ...
    // hashCode e equals
    @Override
    public int hashCode() {
        return Objects.hash(idLocacao, cliente, carro, tempoAluguel, valorTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return idLocacao == locacao.idLocacao &&
                Objects.equals(cliente, locacao.cliente) &&
                Objects.equals(carro, locacao.carro) &&
                Objects.equals(tempoAluguel, locacao.tempoAluguel) &&
                Objects.equals(valorTotal, locacao.valorTotal);
    }
}
