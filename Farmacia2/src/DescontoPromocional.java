public class DescontoPromocional implements Desconto {
    private double percentual;

    public DescontoPromocional(double percentual) {
        this.percentual = percentual;
    }

    @Override
    public void aplicarDesconto(Venda venda) {
        venda.aplicarDesconto(venda.getValorTotal() * (percentual / 100));
    }
}
