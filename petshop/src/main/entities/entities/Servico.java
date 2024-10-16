package entities;

public class Servico {

    private int id;
    private String nome;
    private ClassificacaoServico classificacao;

    public Servico(String nome, ClassificacaoServico classificacao) {
        this.nome = nome;
        this.classificacao = classificacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ClassificacaoServico getClassificacao() {
        return classificacao;
    }

    public enum ClassificacaoServico {
        BANHO,
        TOSA,
        CONSULTA_VETERINÁRIA,
        PASSEIO
    }
}
