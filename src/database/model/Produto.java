package database.model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private String localizacao;
    private Double codigoBarras;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Double getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Double codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

}
