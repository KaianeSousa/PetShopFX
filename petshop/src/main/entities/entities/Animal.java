package entities;

public class Animal {
    private int id;
    private String nome;
    private String tipo;
    private String raca;
    private String genero;
    private String idade;

    public Animal(int id, String nome, String tipo, String raca, String genero, String idade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.raca = raca;
        this.genero = genero;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
