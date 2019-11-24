package contrato.com.model;

import java.sql.Date;

public class OrdemServico {
    private long id;
    private Date data;
    private String descricao;
    private Float valor;
    private StatusServico statusServico;
    private Prestador prestador;
    private Solicitacao solicitacao;

    public OrdemServico() {
    }

    public OrdemServico(long id, Date data, String descricao, Float valor, StatusServico statusServico, Prestador prestador, Solicitacao solicitacao) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.statusServico = statusServico;
        this.prestador = prestador;
        this.solicitacao = solicitacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}

