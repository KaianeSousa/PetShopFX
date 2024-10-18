package entities;

public abstract class Pessoa {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    public Pessoa(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    // Métodos para definir novos valores
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setEndereco(String novoEndereco) {
        this.endereco = novoEndereco;
    }

    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }
}
