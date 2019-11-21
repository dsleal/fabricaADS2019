package contrato.com.model;

import java.sql.Date;

public class Solicitacao {
    private Long id;
    private Float valor;
    private Date data;
    private StatusServico statusSolicitacao;
    private String descricao;
    private Cliente cliente;

    public Solicitacao() {
    }

    public Solicitacao(Long id, Float valor, Date data, StatusServico statusSolicitacao, String descricao, Cliente cliente) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.statusSolicitacao = statusSolicitacao;
        this.descricao = descricao;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public StatusServico getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusServico statusSolicitacao) {
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
}

