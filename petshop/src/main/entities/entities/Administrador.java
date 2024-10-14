package entities;

public class Administrador extends Pessoa {
    private String cargo;

    public Administrador(String nome, String endereco, String telefone, String email, String cargo) {
        super(nome, endereco, telefone, email);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}

