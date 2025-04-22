import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int vendaId;
    private List<Medicamento> itensVendidos;
    private double valorTotal;
    private LocalDateTime dataHora;
    private Funcionario funcionario;
    private Cliente cliente;

    public Venda(int vendaId, Funcionario funcionario, Cliente cliente) {
        this.vendaId = vendaId;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.itensVendidos = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
        this.valorTotal = 0;
    }

    
    public void adicionarItem(Medicamento medicamento, int quantidade) {
    for (int i = 0; i < quantidade; i++) {
        itensVendidos.add(medicamento);
        valorTotal += medicamento.getPreco();
    }
}


    public void aplicarDesconto(double valorDesconto) {
        valorTotal -= valorDesconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getVendaId() {
        return vendaId;
    }

    public List<Medicamento> getItensVendidos() {
        return itensVendidos;
    }
}
