public class Gerente extends Caixa {
    public Gerente(int funcionarioId, String nome) {
        super(funcionarioId, nome);
    }

    public void aplicarDesconto(Venda venda, double percentual) {
        double valorDesconto = venda.getValorTotal() * (percentual / 100);
        venda.aplicarDesconto(valorDesconto);
    }
}
