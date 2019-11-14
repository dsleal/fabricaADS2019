package contrato.com.model;

public class TipoPrestador {
    private int codigo;
    private String descricao;
    private boolean desativado;

    public TipoPrestador() {
    }

    public TipoPrestador(int codigo, String descricao, boolean desativado) {
        this.codigo = codigo;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
