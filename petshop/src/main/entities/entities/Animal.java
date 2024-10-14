package entities;

public class Animal {
    private int id;
    private String nome;
    private String raca;
    private String tipo;
    private String idade;

    public Animal(int id, String nome, String raca, String tipo, String idade) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.tipo = tipo;
        this.idade = idade;
    }

    public Animal(String nome, String idade, String tipo, String raca) {
        this.nome = nome;
        this.raca = raca;
        this.tipo = tipo;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getIdade() {
        return idade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
