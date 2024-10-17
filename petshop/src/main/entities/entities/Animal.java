package entities;

public class Animal {
    private int id;
    private String nome;
    private String raca;
    private String idade;
    private String genero;


    public Animal(String nome, String idade, String genero, String raca) {
        this.nome = nome;
        this.raca = raca;
        this.genero = genero;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }


    public String getRaca() {
        return raca;
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

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
