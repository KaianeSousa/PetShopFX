package entities;

public class Cliente extends Pessoa {
    private int id;
    private String senha;

    public Cliente(int id, String nome, String endereco, String telefone, String email, String senha) {
        super(nome, endereco, telefone, email);
        this.id = id;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public void setNome(String novoNome) {
        super.setNome(novoNome);
    }

    @Override
    public void setEndereco(String novoEndereco) {
        super.setEndereco(novoEndereco);
    }

    @Override
    public void setTelefone(String novoTelefone) {
        super.setTelefone(novoTelefone);
    }

    @Override
    public void setEmail(String novoEmail) {
        super.setEmail(novoEmail);
    }
}
