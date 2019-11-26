package contrato.com.model;

public class TipoPrestador {
    private int id;
    private String descricao;
    private boolean ativo;

    public TipoPrestador() {
    }

    public TipoPrestador(int id, String descricao, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public TipoPrestador(String descricao, boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public TipoPrestador(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
