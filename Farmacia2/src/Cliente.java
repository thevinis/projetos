public class Cliente {
    private int clienteId;
    private String nome;
    private String cpf;

    public Cliente(int clienteId, String nome, String cpf) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getClienteId() { return clienteId; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    public boolean cpfValido() {
        return cpf != null && cpf.matches("\\d{11}");
    }
}
