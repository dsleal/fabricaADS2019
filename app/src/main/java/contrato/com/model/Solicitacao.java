package contrato.com.model;

import java.sql.Date;

public class Solicitacao {
    private Integer id;
    private Float valor;
    private Date data;
    private StatusSolicitacao statusSolicitacao;
    private String descricao;
    private Cliente cliente;
    private TipoPrestador tipoPrestador;

    public Solicitacao() {
    }


    public Solicitacao(Integer id, Float valor, Date data, StatusSolicitacao statusSolicitacao, String descricao, Cliente cliente, TipoPrestador tipoPrestador) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.statusSolicitacao = statusSolicitacao;
        this.descricao = descricao;
        this.cliente = cliente;
        this.tipoPrestador = tipoPrestador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPrestador getTipoPrestador() {
        return tipoPrestador;
    }

    public void setTipoPrestador(TipoPrestador tipoPrestador) {
        this.tipoPrestador = tipoPrestador;
    }
}

