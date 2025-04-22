import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Medicamento> estoque = new ArrayList<>();

        // Adiciona alguns medicamentos ao estoque
        estoque.add(new Medicamento("001", "Paracetamol", 10.0, 50, LocalDate.of(2025, 12, 1)));
        estoque.add(new Medicamento("002", "Ibuprofeno", 15.0, 30, LocalDate.of(2025, 11, 20)));
        estoque.add(new Medicamento("003", "Dipirona", 8.0, 100, LocalDate.of(2025, 10, 15)));

        System.out.println("=== SISTEMA DE VENDAS DE FARMACIA ===");

        // Cadastro do cliente
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("CPF (apenas numeros): ");
        String cpf = scanner.nextLine();
        Cliente cliente = new Cliente(1, nomeCliente, cpf);

        // Seleção do funcionário (Caixa ou Gerente)
        System.out.print("Funcionario responsavel (1 - Caixa, 2 - Gerente): ");
        int tipoFuncionario = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha
        Funcionario funcionario;
        if (tipoFuncionario == 2) {
            funcionario = new Gerente(1001, "Gerente Joao");
        } else {
            funcionario = new Caixa(2001, "Caixa Maria");
        }

        // Cria venda
        Venda venda = new Venda(1, funcionario, cliente);

        // Mostra medicamentos disponíveis
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== ESTOQUE DISPONIVEL ===");
            for (int i = 0; i < estoque.size(); i++) {
                System.out.println((i + 1) + " - " + estoque.get(i));
            }

            System.out.print("Escolha o numero do medicamento a adicionar ou 0 para encerrar: ");
            int escolha = scanner.nextInt();
            if (escolha == 0) break;

            Medicamento selecionado = estoque.get(escolha - 1);
            System.out.print("Quantidade: ");
            int qtd = scanner.nextInt();

            try {
                selecionado.diminuirQuantidade(qtd);
                venda.adicionarItem(selecionado, qtd);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.print("Deseja adicionar mais medicamentos? (s/n): ");
            scanner.nextLine(); // consumir quebra
            String resposta = scanner.nextLine();
            continuar = resposta.equalsIgnoreCase("s");
        }

        // Aplica desconto se for cliente registrado
        Desconto descontoCliente = new DescontoParaClienteRegistrado();
        descontoCliente.aplicarDesconto(venda);

        // Aplica desconto promocional se desejar
        System.out.print("Deseja aplicar desconto promocional? (s/n): ");
        String aplicarPromo = scanner.nextLine();
        if (aplicarPromo.equalsIgnoreCase("s")) {
            System.out.print("Percentual de desconto (%): ");
            double percentual = scanner.nextDouble();
            scanner.nextLine();
            DescontoPromocional descontoPromocional = new DescontoPromocional(percentual);
            descontoPromocional.aplicarDesconto(venda);
            System.out.printf("Desconto promocional de %.2f%% aplicado.\n", percentual);
        }

        // Se for gerente, pode aplicar um desconto adicional
        if (funcionario instanceof Gerente) {
            System.out.print("Deseja aplicar desconto de gerente? (s/n): ");
            String aplicarGerente = scanner.nextLine();
            if (aplicarGerente.equalsIgnoreCase("s")) {
                System.out.print("Percentual de desconto do gerente (%): ");
                double percGerente = scanner.nextDouble();
                scanner.nextLine();
                ((Gerente) funcionario).aplicarDesconto(venda, percGerente);
                System.out.printf("Desconto adicional de gerente de %.2f%% aplicado.\n", percGerente);
            }
        }

        // Processa pagamento
        System.out.printf("Valor final da venda: R$%.2f\n", venda.getValorTotal());
        if (funcionario instanceof Caixa caixa) {
            caixa.processarPagamento(venda.getValorTotal());
        } else if (funcionario instanceof Gerente gerente) {
            gerente.processarPagamento(venda.getValorTotal());
        }

        // Exibe resumo
        System.out.println("\n=== RESUMO DA VENDA ===");
        System.out.println("Cliente: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
        System.out.println("Funcionario: " + funcionario.getNome());
        System.out.println("Medicamentos comprados:");
        for (Medicamento med : venda.getItensVendidos()) {
            System.out.println("- " + med.getNome() + " - R$" + med.getPreco());
        }
        System.out.printf("Valor total final com descontos: R$%.2f\n", venda.getValorTotal());

        scanner.close();
    }
}
