public class DescontoParaClienteRegistrado implements Desconto {
    private static final double DESCONTO_CLIENTE = 0.05; // 5%

    @Override
    public void aplicarDesconto(Venda venda) {
        if (venda.getCliente() != null && validarCPF(venda.getCliente().getCpf())) {
            double desconto = venda.getValorTotal() * DESCONTO_CLIENTE;
            venda.aplicarDesconto(desconto);
            System.out.println("Desconto de cliente registrado aplicado: R$ " + desconto);
        }
    }

    private boolean validarCPF(String cpf) {
        // Validação simples: CPF com 11 dígitos numéricos
        return cpf != null && cpf.matches("\\d{11}");
    }
}
