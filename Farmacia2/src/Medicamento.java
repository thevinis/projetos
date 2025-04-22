import java.time.LocalDate;

public class Medicamento {
    private String id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDate dataValidade;

    public Medicamento(String id, String nome, double preco, int quantidade, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public LocalDate getDataValidade() { return dataValidade; }

    public void diminuirQuantidade(int quantidadeVendida) throws Exception {
        if (quantidadeVendida > quantidade) throw new Exception("Estoque insuficiente.");
        this.quantidade -= quantidadeVendida;
    }

    @Override
    public String toString() {
        return String.format("Medicamento: Nome=%s, Preco=%.2f, Quantidade=%d, ID=%s, Data de Validade=%s",
                nome, preco, quantidade, id, dataValidade);
    }
}
