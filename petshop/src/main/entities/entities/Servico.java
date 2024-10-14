package entities;

public class Servico {

    public enum ClassificacaoServico {
        BANHO,
        TOSA,
        CONSULTA_VETERINÁRIA,
        PASSEIO
    }

    private int id;
    private String nome;
    private ClassificacaoServico classificacao;

    public Servico(int id, String nome, ClassificacaoServico classificacao) {
        this.id = id;
        this.nome = nome;
        this.classificacao = classificacao;
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
}
