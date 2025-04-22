public abstract class Funcionario {
    protected int funcionarioId;
    protected String nome;

    public Funcionario(int funcionarioId, String nome) {
        this.funcionarioId = funcionarioId;
        this.nome = nome;
    }

    public int getFuncionarioId() { return funcionarioId; }
    public String getNome() { return nome; }
}
