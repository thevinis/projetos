public class Caixa extends Funcionario {
    public Caixa(int funcionarioId, String nome) {
        super(funcionarioId, nome);
    }

    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " processado por " + nome);
    }
}
