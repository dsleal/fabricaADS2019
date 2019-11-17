package contrato.com.model;

public class TipoPrestador {
    private int id;
    private String descricao;
    private boolean desativado;

    public TipoPrestador() {
    }

    public TipoPrestador(int id, String descricao, boolean desativado) {
        this.id = id;
        this.descricao = descricao;
        this.desativado = desativado;
    }

    public TipoPrestador(String descricao, boolean desativado) {
        this.descricao = descricao;
        this.desativado = desativado;
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

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }
}
